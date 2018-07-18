package server.server_proxy;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import interfaccia.IGestoreCPS;
import server.entity.Utente;
import server.entity.Auto;
import server.entity.Configurazione;
public class SkeletonThread implements Runnable{

	private Socket socket;
	private IGestoreCPS ges_cps;
	
	public SkeletonThread(Socket s, IGestoreCPS iges) {
		socket=s;
		ges_cps=iges;
	}
	
	@Override
	public void run() {
		try {
	
			DataInputStream din= new DataInputStream(new BufferedInputStream(socket.getInputStream()));			
			String comando= din.readUTF();
			DataOutputStream dos= new DataOutputStream(new BufferedOutputStream(socket.getOutputStream() ));
			if(comando.equalsIgnoreCase("adattaConfigurazione")){
				System.out.println("Adatta conf");
				//int autoScelta=(int) din.readInt();
				ObjectInputStream ois= new ObjectInputStream(din);
				int autoScelta= (int) ois.readObject();
				System.out.println("auto scelta: "+ autoScelta);
				//int confScelta=(int) din.readInt();
				int confScelta= (int) ois.readObject();
				System.out.println("conf scelta: "+confScelta);
			
				this.ges_cps.associaConfigurazione(autoScelta,confScelta);
				ObjectOutputStream oos= new ObjectOutputStream(dos);
				oos.writeObject(1);
				//dos.writeInt(1);
				//dos.flush();
				oos.flush();
				System.out.println("Configurata");
				
			}else if(comando.equalsIgnoreCase("getAllConf")){
				System.out.println("getAllConf");
				ObjectInputStream ois= new ObjectInputStream(din);
				int id_utente=(int)ois.readObject();
				//int id_utente=(int)din.readInt();;
				//Utente u=this.ges_cps.getUtente(id_utente);
				//
				//Utente utente= new Utente(0,"Dario","123da","mail");
				Utente utente= this.ges_cps.getUtente(id_utente);
				ArrayList<String> lista_conf=new ArrayList<String>();
				lista_conf = this.ges_cps.getAllConf(utente);
				ObjectOutputStream oos= new ObjectOutputStream(dos);
				oos.writeObject(lista_conf);
				oos.flush();
			}else if(comando.equalsIgnoreCase("getAllAuto") ) {
				System.out.println("getAllAuto");
				
				ObjectInputStream ois= new ObjectInputStream(din);
				int id_utente=(int)ois.readObject();
				//Utente u=this.ges_cps.checkUtente(utente);
				Utente utente= this.ges_cps.getUtente(id_utente);
				ArrayList<String> lista_auto= new ArrayList<String>();
				lista_auto = this.ges_cps.getAllAuto(utente) ;
				
				ObjectOutputStream oos= new ObjectOutputStream(dos);
				oos.writeObject(lista_auto);
				oos.flush();
			}else {
				System.out.println("Comando non riconosciuto");
			}
		}catch(IOException e ){
				e.printStackTrace();
		}catch(ClassNotFoundException e) {
				e.printStackTrace();
		}	
	}
}
