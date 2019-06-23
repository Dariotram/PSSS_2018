package com.example.zerin.psss.clientCore;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.example.zerin.psss.ClientEntity.Auto;
import com.example.zerin.psss.ClientEntity.Configurazione;
import com.example.zerin.psss.ClientEntity.Utente;
import com.example.zerin.psss.interfaces.ICallback;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

/*questa è la parte più complicata, qui si crea una estensione di una task asincrona e si avvia in background la socket
cisono due costruttori in quanto uno serve per adattaConfigurazione e l'altro per getAuto e getConf.
Command è usato per verificare quale delle 3 opzioni compiere con il server.
Il risultato va passato attraverso onpostexecute (vedere lì)
 */
public class BackgroundSocket extends AsyncTask<Utente, Void, ArrayList<String>> {
    private Utente u;
    private Socket socket;
    private ICallback callback;
    private ArrayList<String> lista;
    private Activity ac;
    private int command;
    private int autoScelta;
    private int confScelta;
    private int ack;
    private  UtenteBusiness ub;

    public BackgroundSocket(Utente ut,ICallback cal,Activity acti,int c){
        ub=UtenteBusiness.getUtenteBusiness();
        this.u=ut;
        this.callback=cal;
        this.ac=acti;
        this.command=c;
    }

    public BackgroundSocket(int autoScelta,int confScelta,int command,ICallback call,Activity a){
        ub=UtenteBusiness.getUtenteBusiness();
        this.autoScelta=autoScelta;
        this.confScelta=confScelta;
        this.command=command;
        this.callback=call;
        this.ac=a;
    }



    @Override
    protected ArrayList<String> doInBackground(Utente... utentes) {
        try {
            //usare 192.168.1.8 per testarlo in locale
           // socket = new Socket("5.88.218.119", 8585);
            socket = new Socket("192.168.1.126", 8585);

            DataOutputStream dos= new DataOutputStream(new BufferedOutputStream(socket.getOutputStream() ));
            if(command==1) {
                dos.writeUTF("getAllAuto");
            }
            else if(command==2){
                dos.writeUTF("getAllConf");
            }
            else{
                dos.writeUTF("adattaConfigurazione");
            }

            if (command==3){
                ObjectOutputStream oos = new ObjectOutputStream(dos);
                oos.writeObject(autoScelta);
                oos.writeObject(confScelta);
                dos.flush(); //invio al server id dell'auto scelta e della configurazione

                Log.e("p","preso comando 3");

                DataInputStream dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                ObjectInputStream ois = new ObjectInputStream(dis);
                ack= (int) ois.readObject(); //ricevo l'ack che qui ho impostato come un intero perchè con i booleani avevo problemi
                Log.e("p","preso ack "+ack);
                lista=new ArrayList<String>();
                return lista;
            }
            else if(command==2) {
                //se il comando è getConf
                int a = u.getId();
                ObjectOutputStream oos = new ObjectOutputStream(dos);
                oos.writeObject(u.getId());
                dos.flush(); //invio id utente

                DataInputStream dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                ObjectInputStream ois = new ObjectInputStream(dis);
                ArrayList<Integer> listaId= new ArrayList<Integer>();
                ArrayList<String> listaNome= new ArrayList<String>();

                listaId = (ArrayList<Integer>) ois.readObject(); //ricevo listaId
                listaNome= (ArrayList<String>) ois.readObject();//listaNome

                ub.setAllConfig(listaId,listaNome);// salvo queste configurazioni
                return listaNome;
            }else if(command==1){
                int a = u.getId();
                ObjectOutputStream oos = new ObjectOutputStream(dos);
                oos.writeObject(u.getId());
                dos.flush(); //invio id utente

                DataInputStream dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                ObjectInputStream ois = new ObjectInputStream(dis);
                ArrayList<Integer> listaId= new ArrayList<Integer>();
                ArrayList<String> listaTarga= new ArrayList<String>();
                ArrayList<String> listaModello= new ArrayList<String>();

                listaId = (ArrayList<Integer>) ois.readObject(); //ricevo listaId
                listaTarga= (ArrayList<String>) ois.readObject();//ricevo listatarga
                listaModello= (ArrayList<String>) ois.readObject();//ricevo listaModello

                ub.setAllAuto(listaId,listaTarga,listaModello);//salvo queste auto

                return listaTarga;
            }
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
            } catch (IOException ex) {
            Log.v("3","non ci siamo proprio");
            ex.printStackTrace();

        }catch (ClassNotFoundException ex){
            Log.v("5","class not found");
            ex.printStackTrace();
        }

        return lista;
    }

    @Override
    protected void onPostExecute(ArrayList<String> strings) {
        //il risultato del doItInBackground va usato qui e passato alla callback insieme al riferimento all'activity
        //vedere callBack impl
        if(this.command==1) {
            ArrayList<Auto> listaAuto= new ArrayList<Auto>();
            listaAuto=ub.getAllAutobyTarga(strings);//ottengo le auto  a partire delle targhe
            callback.call(listaAuto, ac);
        }
        else if (this.command==2){
            ArrayList<Configurazione> listaConf= new ArrayList<Configurazione>();
            listaConf=ub.getAllConfbyName(strings);//ottengo le configurazioni a partire dai nomi
            callback.call2(listaConf,ac);
        }
        else {
            if(this.ack==1){
                strings.add("OK");
            }
            else{
                strings.add("NO");
            }
            callback.call3(strings,ac);
        }
    }
}
