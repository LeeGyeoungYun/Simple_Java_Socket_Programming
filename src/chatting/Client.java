package chatting;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

public class Client { //처음 닉네임을 입력하였다면, Server와 Socket을 통해 연결되는 순간 User의 권한을 얻고 채팅을 할 수 있게 됩니다.
	
	public static void main(String[] args) { 
		
		Socket socket = null;
		DataInputStream chat_in = null; // 이 변수는 사용자가 입력하는 채팅 부분에 해당됩니다.
		BufferedReader userName = null; //이 변수는 사용자의 닉네임에 해당됩니다
		DataOutputStream chat_out = null; // 이 변수는 사용자가 입력한 데이터를 출력할 때 사용합니다.
		
		try {
			InetAddress ia = null; // Local Host IP Address 가져오기 위한 변수
			ia = InetAddress.getLocalHost(); //현재 PC의 IP 주소 가져오기
			socket = new Socket(ia,4444); //client의 IP와 Port 번호 입력
			
			/*
			 * DataInputStream은 입력 스트림을 받는 매개변수이며,
			 * socket.getInputStream()함수를 통해 소켓에서 전달되는 데이터 스트림을 읽어옵니다.
			 * BufferedReader는 Scanner와 비슷한 개념입니다.
			 * Scanner보다 빠르다는 장점이 있지만 String형으로 버퍼에 저장하기 때문에
			 * 따로 데이터를 가공해서 사용해야하는 경우가 많습니다.
			 */
			
			chat_in = new DataInputStream(socket.getInputStream());
			userName = new BufferedReader(new InputStreamReader(System.in));
			chat_out = new DataOutputStream(socket.getOutputStream());
			
			System.out.print("사용하실 닉네임을 정해주세요 : ");
			String data = userName.readLine(); //채팅에 사용할 닉네임을 받아옵니다.
			
			chat_out.writeUTF(data); //닉네임을 UTF-8로 변경 후 출력스트림에 넣습니다.
			Thread thread = new Thread(new Send(chat_out));// 새로운 쓰레드에 chat_out을 집어 넣도록 합니다.
			thread.start();//쓰레드 시작
				
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		
		try {
			/*
			 * 끊어지기 전까지는 계속 채팅을 입력받기 때문에
			 * 무한루프(while)안에서 동작합니다.
			 * 만일 끊고 싶다면 break에 대한 조건문을 걸면 됩니다.
			 */
			while(true) {
				String str2 = chat_in.readUTF();
				System.out.println(str2);
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}

}
