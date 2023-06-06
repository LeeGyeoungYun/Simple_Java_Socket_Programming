package chatting;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;

/*
 * extends Thread, implements Runnable은 쓰레드 구현을 위해 사용합니다.
 * extends Thread는 Thread를 상속받고 객채화한 뒤에 객체명.start()를 통해 사용하며,
 * implements Runnable은 Thread 객체 안에 쓰레드를 사용하려는 객체를 넣어줘서 객체화한 뒤 사용합니다.
 * extends Thread와 implements Runnable은 비슷합니다.
 * 하지만 implements Runnable을 사용하면 다중 상속이 가능합니다.
 */
public class Send implements Runnable{ //Send 클래스는 유저가 보낸 닉네임, 메세지 등을 서버에 전달하는 역할을 합니다.
									   //Thread를 통해 동작하기 때문에 Runnable이라는 조건자를 달았습니다.
	 /*
	  * Java 간단한 채팅 참조 : https://lktprogrammer.tistory.com/64?category=672211
	  * DataOutputStream 정보 참조 : https://apphappy.tistory.com/69
	  * Send Class는 Client에서 쓰레드를 실행할 때 사용됩니다.
	  */
	DataOutputStream chat_out;
	
	/*
	 * Send 클래스에서의 br은 채팅 내용을 받아오는 역할을 합니다.
	 */
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	
	public Send(DataOutputStream out) {
		this.chat_out = out;
	}
	
	public void run() {
		while(true) {
			try {
				String msg = br.readLine(); // 메세지를 받아오면
				chat_out.writeUTF(msg); // msg 출력
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}