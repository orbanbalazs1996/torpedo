package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.*;
import java.util.*;

public class Client extends Thread {
	static int numberOfClients = 0;
	
	static List<Socket> clients = new LinkedList<Socket>();
	
	private Socket socket;
	private int id;
	private BufferedReader in;
	private PrintWriter out;

	public Client(Socket socket) {
		this.socket = socket;
		this.id = numberOfClients++;
		clients.add(socket);
	}
	
	@Override
	public void run() {
		try {
			this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			this.out = new PrintWriter(new OutputStreamWriter(this.socket.getOutputStream()));
		
			while (true) {
				try {
					Thread.sleep(10);
				} catch (Exception e) {
					// TODO: handle exception
				}
				String s="";
				if (this.in.ready()) {
					s = this.in.readLine();
					System.out.println(s);
					for (Socket socket : clients) {
						if (socket !=this.socket) {
							PrintWriter pr = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
							pr.println(s);
							pr.flush();
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				// ignore
			}
		}
	}
}
