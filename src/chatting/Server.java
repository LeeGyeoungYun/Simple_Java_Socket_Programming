package chatting;

import java.net.ServerSocket;
import java.net.Socket;

public class Server { //서버는 말 그대로 Client들이 User로 등록이 되어있다면 자유롭게 채팅을 주고 받는 공간이 됩니다.
	
	public static void main(String[] args) {
		
		Socket socket = null; //Client와 연결하기 위한 소켓 생성
		User user = new User(); //채팅방에 접속해 있는 Client 관리 객체
		ServerSocket serverSocket = null; //Client 접속을 받기 위한 ServerSocket
		
		int count = 0; //쓰레드 할당을 위한 정수
		Thread thread[] = new Thread[10]; //10개까지 쓰레드 할당, 즉 채팅방 10명이 접속 가능함
		
		try {
			serverSocket = new ServerSocket(4444);
			
			while(true) {
				socket = serverSocket.accept(); //통신이 종료되기까지 연결
				
				/*
				 * receiver Class에서 implements Runnable을 사용했기 때문에
				 * Thread 객체 안에 쓰레드를 사용하려는 객체를 넣어줘서 객체화를 할 수 있습니다.
				 */
				
				thread[count] = new Thread(new Receiver(user, socket)); //Receiver 클래스를 Thread에서 돌림
				thread[count].start();
				count++;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}

}
