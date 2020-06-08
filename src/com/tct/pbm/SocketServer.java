package com.tct.pbm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
	public static Socket createServer(int port) throws IOException {
        Socket socket = null;                //Client�� ����ϱ� ���� Socket
        ServerSocket server_socket = null;  //���� ������ ���� ServerSocket
        try{
            server_socket = new ServerSocket(port);
            System.out.println("���� ����!!");
            socket = server_socket.accept();    //���� ���� , Client ���� ���
        } catch(IOException e) {
            System.out.println("�ش� ��Ʈ�� �����ֽ��ϴ�.");
        }
        
        return socket;
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader in = null;            //Client�κ��� �����͸� �о���̱� ���� �Է½�Ʈ��
		PrintWriter out = null; 
		Socket socket = createServer(9876);
		InputStream inputStream = socket.getInputStream(); // socket�� InputStream���� ���� Stream�� ����.
		in = new BufferedReader(new InputStreamReader(inputStream));
		out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))); //��½�Ʈ�� ����
		String cur="";
		while((cur=in.readLine())!=null) {
			
		}
	}

}
