package client.client_proxy;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

import interfaccia.IGestoreCPS;
import server.entity.Auto;
import server.entity.Configurazione;
import server.entity.Utente;

public class GestoreCPS_Proxy implements IGestoreCPS {

	private static final int NUMPORTA=8585;
	private Socket socket;
	public GestoreCPS_Proxy(){
	}

	@Override
	public ArrayList<Auto> getAllAuto(Utente u) {
		ArrayList<Auto> listaAuto=new ArrayList<Auto>();
		
		try {
			socket= new Socket(InetAddress.getLocalHost().getHostAddress(),NUMPORTA);
			//System.out.println("Avviato getAllAuto");
			DataOutputStream dos= new DataOutputStream(new BufferedOutputStream(socket.getOutputStream() ));
			dos.writeUTF("getAllAuto");
			ObjectOutputStream oos = new ObjectOutputStream(dos);
			oos.writeObject(u);
			dos.flush();
			
			DataInputStream dis= new DataInputStream(new BufferedInputStream(socket.getInputStream() ));
			ObjectInputStream ois= new ObjectInputStream(dis);
			listaAuto=(ArrayList<Auto>)ois.readObject();
			//System.out.println("ottenuto lista Auto"+listaAuto.size());
		}catch(IOException e){
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
			return listaAuto;
	}

	@Override
	public ArrayList<Configurazione> getAllConf(Utente u) {
		
		ArrayList<Configurazione> listaConfigurazione=new ArrayList<Configurazione>();
		try {
			socket= new Socket(InetAddress.getLocalHost().getHostAddress(),NUMPORTA);
		//	System.out.println("Avviato getAllConf");
			DataOutputStream dos= new DataOutputStream(new BufferedOutputStream(socket.getOutputStream() ));
			dos.writeUTF("getAllConf");
			ObjectOutputStream oos = new ObjectOutputStream(dos);
			oos.writeObject(u);
			dos.flush();

			DataInputStream dis= new DataInputStream(new BufferedInputStream(socket.getInputStream() ));
			ObjectInputStream ois= new ObjectInputStream(dis);
			listaConfigurazione=(ArrayList<Configurazione>)ois.readObject();
			//System.out.println("Ottenuto lista Configurazione di dimensione: "+listaConfigurazione.size());
		}catch(IOException e){
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		return listaConfigurazione;
	}

	@Override
	public void adattaConfigurazione(Auto a, Configurazione c) {
	
		try {
			socket= new Socket(InetAddress.getLocalHost().getHostAddress(),NUMPORTA);
			DataOutputStream dos= new DataOutputStream(new BufferedOutputStream(socket.getOutputStream() ));
			dos.writeUTF("AdattaConfigurazione");
			ObjectOutputStream oos = new ObjectOutputStream(dos);
			oos.writeObject(a);
			oos.writeObject(c);
			dos.flush();
			
			DataInputStream dis= new DataInputStream(new BufferedInputStream(socket.getInputStream() ));
			Boolean result=dis.readBoolean();
			if(result) {
				System.out.println("Associazione avvvenuta");
			}else {
				System.out.println("Problemi durante l'associazione");
			}
		}catch(IOException e){
			e.printStackTrace();
		}		
	}

	@Override
	public Utente checkUtente(Utente u) {
		// TODO Auto-generated method stub
		return null;
	}
}
