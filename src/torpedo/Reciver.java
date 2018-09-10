package torpedo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class Reciver extends Thread {
	private Socket socket;
	
	public Reciver(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String s="";
			while (true) {
				try {
					Thread.sleep(10);
					
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				if (in.ready()) {
					s = in.readLine();
					Client.MesssageFromTheServer(s);
					System.out.println(s);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
