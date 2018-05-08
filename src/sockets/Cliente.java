/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fabian.giraldo
 */
public class Cliente {
    public static void main(String[] args) {
        try {
//            Socket cliente = new Socket("localhost",8000);
            
            Socket cliente = new Socket("localhost",8000);
            //3.Abriendo flujos
            InputStream flujoEntrada = cliente.getInputStream();
            OutputStream flujoSalida = cliente.getOutputStream();
            //4. Poniendo decoradores para leer informacion textual
            BufferedReader lectura = new BufferedReader(new InputStreamReader(flujoEntrada));
            PrintWriter escritura = new PrintWriter(flujoSalida,true);
            
            //5.Solicitando datos al usuario
            BufferedReader lecturaUsuario = new BufferedReader(new InputStreamReader(System.in));
            
            
            String mensajeLeido="";
            String mensajeUsuario = "";
            while(true){
                System.out.println("Que mensaje desea enviar:");
                mensajeUsuario = lecturaUsuario.readLine();
                //6. Enviando la informacion al servidor
                escritura.println(mensajeUsuario);
                //7.Recibiendo la respuesta
                mensajeLeido = lectura.readLine();
                System.out.println("Me contestaron " + mensajeLeido);
            }
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
