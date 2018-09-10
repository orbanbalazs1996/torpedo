package server;






//////////////////////Fontos információk:
//	1. Inditsd el a servert
//	2. Inditsd el a clientet
//  3. Kattints az "induljon a moka gombra"
//  4. Inditsd el a clientet
//  5. Kattints az "induljon a moka gombra"
//  6. Lesz 2 ablak, amelyek egymassal tudnak jatszani, egyszer az egyik lo, utana a masik
//  Fontos hogy a 2 setterben ua. a beallitas legyen
public class Server {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread listener = new Listener();
		listener.start();
	}

}
