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
				
				Utente utente= this.ges_cps.getUtente(id_utente);
				ArrayList<Configurazione> lista_conf=new ArrayList<Configurazione>();
				lista_conf = this.ges_cps.getAllConf(utente);
				
				
				ArrayList<Integer> listaId= new ArrayList<Integer>();
				ArrayList<String> listaNome= new ArrayList<String>();
				
				for(int i=0;i<lista_conf.size();i++) {
					 listaId.add(lista_conf.get(i).getId());
					 listaNome.add(lista_conf.get(i).getName());
				}
				
				ObjectOutputStream oos= new ObjectOutputStream(dos);
				oos.writeObject(listaId);
				oos.writeObject(listaNome);
				oos.flush();
				
			}else if(comando.equalsIgnoreCase("getAllAuto") ) {
				System.out.println("getAllAuto");
				
				ObjectInputStream ois= new ObjectInputStream(din);
				int id_utente=(int)ois.readObject();
				Utente utente= this.ges_cps.getUtente(id_utente);
				ArrayList<Auto> lista_auto= new ArrayList<Auto>();
				lista_auto = this.ges_cps.getAllAuto(utente) ;
				
				ArrayList<Integer> listaId= new ArrayList<Integer>();
				ArrayList<String> listaTarga= new ArrayList<String>();
				ArrayList<String> listaModello= new ArrayList<String>();
				
				for(int i=0;i<lista_auto.size();i++) {
					 listaId.add(lista_auto.get(i).getId());
					 listaTarga.add(lista_auto.get(i).getTarga());
					 listaModello.add(lista_auto.get(i).getModello() );
					 System.out.println("Invio auto con id:"+lista_auto.get(i).getId()+" con targa:"+ lista_auto.get(i).getTarga()+" modello:"+ lista_auto.get(i).getModello());
				}
				
				ObjectOutputStream oos= new ObjectOutputStream(dos);
				oos.writeObject(listaId);
				oos.writeObject(listaTarga);
				oos.writeObject(listaModello);
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
