/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udpserver;

/**
 *
 * @author Tony
 */
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer {

    public static void main(String args[]) {
        DatagramSocket datagramSocket = null;

        try {
            datagramSocket = new DatagramSocket(8888);

            byte[] buffer = new byte[65536];
            DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);

            System.out.println("Server is Running, Waiting For Data...");

            while (true) {
                datagramSocket.receive(datagramPacket);
                byte[] data = datagramPacket.getData();
                String s = new String(data, 0, datagramPacket.getLength());

                System.out.println(datagramPacket.getAddress().getHostAddress() + " : " + datagramPacket.getPort() + " : " + s);

                s = "Data yang terkirim : " + s;
                DatagramPacket packet = new DatagramPacket(s.getBytes(), s.getBytes().length, datagramPacket.getAddress(), datagramPacket.getPort());
                datagramSocket.send(packet);
            }

        } catch (IOException e) {
            System.out.println(e);
        }
    }
}