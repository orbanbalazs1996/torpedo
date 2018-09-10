package torpedo;

//////////////////////Fontos információk:
//1. Inditsd el a servert
//2. Inditsd el a clientet(Ezt a filet)
//3. Kattints az "induljon a moka gombra"
//4. Inditsd el a clientet(Ezt a filet)
//5. Kattints az "induljon a moka gombra"
//6. Lesz 2 ablak, amelyek egymassal tudnak jatszani, egyszer az egyik lo, utana a masik
//A program kozel sem tokeletes, de mukodik valamennyire és csak 12 pont kene :S
import java.io.*;
import java.net.*;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.stage.Stage;

public class Client extends Application{
	private static Table PlayersTable;
	//private static AI robot;
	public static PrintWriter Send_Message;
	public static void main(String[] args) {
		try (Socket socket = new Socket(InetAddress.getLocalHost(), 12345);
			BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));) {
			new Reciver(socket).start();

			Send_Message = new PrintWriter(socket.getOutputStream());
			launch(args);

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void Write2Server(String s) {
		Send_Message.println(s);
		Send_Message.flush();
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		new GameSetter();
	}
	
	 public static void tablapeldanyositaniszeretnek(Node _node, int N,Integer [] Ships) {
		 PlayersTable =  new Table(_node, N,Ships);
		 //robot = new AI(N, Ships);
		 Stage ss = new Stage();
		 ss.setTitle("Game of the loosers");
		 ss.setScene(PlayersTable);
		 ss.show();
	 }
	public static void MesssageFromTheServer(String s) {
		System.out.println(s);
		String [] szo = s.split(" ");
		int x = Integer.parseInt(szo[1]);
		int y = Integer.parseInt(szo[2]);
		if (s.contains("shot")){
			PlayersTable.IwasShot(x, y);
		}else if(s.contains("ship")) {
			//talalt
			PlayersTable.aftershot(x, y,"ship");
		}else if( s.contains("sea")){
			//nem
			PlayersTable.aftershot(x, y,"sea");
		}
		
		
		}
	 }




