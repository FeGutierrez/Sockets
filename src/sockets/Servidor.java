/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fabian.giraldo
 */
public class Servidor {
    
    public static void main(String[] args) {
        try {
            //1:ServerSocket
            ServerSocket serverSocket = new ServerSocket(8000);
            
            File archivo = new File("base_datos.txt");
            if (!archivo.exists()) {
                try{
                    if (archivo.createNewFile()) {
                        System.out.println("archivo creado");
                    }
                } catch (IOException ex) {
                    System.out.println("Prohibido crear el archivo");
                }
            }
            //2.Escuchando conexion de un cliente
            System.out.println("Servidor esperando conexiones");
            Socket cliente = serverSocket.accept();
            System.out.println("Se ha conectado un cliente");
            //3.Abriendo flujos
            InputStream flujoEntrada = cliente.getInputStream();
            OutputStream flujoSalida = cliente.getOutputStream();
            
            //4. Poniendo decoradores para leer informacion textual
            BufferedReader lectura = new BufferedReader(new InputStreamReader(flujoEntrada));
            PrintWriter escritura = new PrintWriter(flujoSalida,true);
            String mensajeLeido="";
            String mensajeUsuario="";
            BufferedReader lecturaUsuario = new BufferedReader(new InputStreamReader(System.in));
            while(true){
              //5. Recibo la informacion
              mensajeLeido = lectura.readLine();
                if (mensajeLeido.equals("Dame la bd")) {
                    try {
                    Scanner lecturaArchivo = new Scanner(archivo);
                    while (lecturaArchivo.hasNext()) { 
                        mensajeUsuario = lecturaArchivo.nextLine();
                    }
                } catch (FileNotFoundException ex) {
                        
                }
                } else {
                    System.out.println("Respuesta: ");
                    mensajeUsuario = lecturaUsuario.readLine();
                }
              
                System.out.println("Entrante: " + mensajeLeido);
                
              //6. Mensaje Respuesta (Realizacion ECO)
                escritura.println("ECO " + mensajeUsuario);
              //escritura.println("ECO " + mensajeLeido);
                
            }
            
            
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
