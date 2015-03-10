/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.enfasis.ejemplosocket.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author LOG
 */
public class ServidorTCP {
    
    private ServerSocket server;
    private static final int PORT = 5000;
    private boolean active = true;
    private Socket client;
    private ArrayList<Service> list;
    
    public static void main(String args[]){
        ServidorTCP app = new ServidorTCP();
        if(app.startServer()){
            app.waitConnection();
        }
    }
    public boolean startServer(){
        try{
            System.out.println("[Server] Iniciando Servidor...");
            server = new ServerSocket(PORT);
            System.out.println("[Server] Servidor esperando en puerto "+PORT);
            return true;
        }catch (IOException e){
            return false;        
        }
        
        
    }
    
    public void waitConnection(){
        while(active){
            try{
                System.out.println("[Server] Esperando conexiones...");
                client = server.accept();
                Service service = new Service(client);
                service.start();
                list.add(service);
                System.out.println("[Server] Cliente conectado en..."+client.getInetAddress().getHostAddress()+":"+client.getPort());
            }catch(IOException e){
                //No hacer nada  
               
            }
        }
    }
    
}


