package sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketMain {
        public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(9999);
            Socket socket = serverSocket.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String mesaage = in.readLine();
            System.out.println("Client: "+mesaage);
            socket.close();
            serverSocket.close();
        }catch(IOException e) {
            e.printStackTrace();
        }

    }
}
