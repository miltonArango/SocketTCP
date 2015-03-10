/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.enfasis.ejemplosocket.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LOG
 */
public class Service extends Thread{
    
    private Socket socket;
    private BufferedReader buf;
    private boolean stop = false;
    
    public Service(Socket socket){
        System.out.println("[Service] Servicio iniciado");
        this.socket = socket; 
    }
    public void run(){
        String msg;
        try {
            buf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("[Service] Listo para recibir mensajes");
            while(!stop){
                System.out.println("[Service] Esperando mensajes...");
                msg = buf.readLine();
                System.out.println("[Service] Desde: " + socket.getInetAddress().getHostAddress()
                        + "Mensaje: " + msg);
                if(msg.equals("exit")){
                    stop = true;
                }
            }
        } catch (IOException ex) {
            //
        }
    }
    
}
