package server.server_proxy;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import interfaccia.IGestoreCPS;


public abstract class Gestore_CPS_Skeleton implements IGestoreCPS{

	public void runSkeleton(){
		try{
			ServerSocket server= new ServerSocket(8585);
			System.out.println("Server avviato e in attesa");
			while(true){
				Socket socket=server.accept();
				System.out.println("Entrato un client");
				
				Thread t= new Thread(new SkeletonThread(socket,this));
				t.start();	
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}