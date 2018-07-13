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
				ObjectInputStream ois= new ObjectInputStream(din);
				Auto autoScelta= (Auto) ois.readObject();
				Configurazione confScelta= (Configurazione) ois.readObject();
			
				this.ges_cps.associaConfigurazione(autoScelta,confScelta);
				dos.writeBoolean(true);
				dos.flush();
				System.out.println("Configurata");
				
			}else if(comando.equalsIgnoreCase("getAllConf")){
				System.out.println("getAllConf");
				ObjectInputStream ois= new ObjectInputStream(din);
				Utente utente=(Utente)ois.readObject();
				
				//
				//Utente u=this.ges_cps.checkUtente(utente);
				
				//
				ArrayList<Configurazione> lista_conf=new ArrayList<Configurazione>();
				lista_conf = this.ges_cps.getAllConf(utente);
				
				ObjectOutputStream oos= new ObjectOutputStream(dos);
				oos.writeObject(lista_conf);
				oos.flush();
			}else if(comando.equalsIgnoreCase("getAllAuto") ) {
				System.out.println("getAllAuto");
				
				ObjectInputStream ois= new ObjectInputStream(din);
				Utente utente=(Utente)ois.readObject();
				//Utente u=this.ges_cps.checkUtente(utente);
				ArrayList<Auto> lista_auto= new ArrayList<>();
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
