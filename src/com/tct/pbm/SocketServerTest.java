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

public class SocketServerTest {
	public static Socket createServer(int port) throws IOException {
		Socket socket = null;                //Client와 통신하기 위한 Socket
		ServerSocket server_socket = null;  //서버 생성을 위한 ServerSocket
		try{
			server_socket = new ServerSocket(port);
			System.out.println("서버 오픈!!");
			socket = server_socket.accept();    //서버 생성 , Client 접속 대기
		} catch(IOException e) {
			System.out.println("해당 포트가 열려있습니다.");
		}

		return socket;
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader in = null;            //Client로부터 데이터를 읽어들이기 위한 입력스트림
		PrintWriter out = null;
		Socket socket = createServer(9876);
		InputStream inputStream = socket.getInputStream(); // socket의 InputStream으로 부터 Stream을 얻음.
		in = new BufferedReader(new InputStreamReader(inputStream));
		out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))); //출력스트림 생성
		String cur="";
		while((cur=in.readLine())!=null) {

		}
	}

}