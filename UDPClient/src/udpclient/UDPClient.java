/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udpclient;

/**
 *
 * @author Tony
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
public class UDPClient {
    public static void main(String args[]) {
        DatagramSocket datagramSocket = null;
        String s;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            datagramSocket = new DatagramSocket();
            InetAddress inetAddress = InetAddress.getByName("127.0.0.1");
 while (true) {
                System.out.print("Masukkan pesan anda : ");
                s = bufferedReader.readLine();
                byte[] b = s.getBytes();
                DatagramPacket datagramPacket = new DatagramPacket(b, b.length, inetAddress, 8888);
                datagramSocket.send(datagramPacket);
                byte[] buffer = new byte[65536];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                datagramSocket.receive(packet);
                byte[] data = packet.getData();
                s = new String(data, 0, packet.getLength());
                System.out.println(packet.getAddress().getHostName() + " : " + packet.getPort() + " : " + s);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}