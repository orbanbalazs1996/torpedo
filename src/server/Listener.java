package server;
import java.io.IOException;
import java.net.*;

public class Listener extends Thread {
	@Override
	public void run() {
		try {
			ServerSocket serverSocket = new ServerSocket(12345);
			
			while (!interrupted()) {
				try {
					Socket clientSocket = serverSocket.accept();
					System.out.println(clientSocket.getInetAddress().getHostAddress()+" "+clientSocket.getInetAddress().getHostName());
					new Client(clientSocket).start();
				} catch (SocketTimeoutException e) {
				}
			}
			
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
