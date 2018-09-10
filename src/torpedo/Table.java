package torpedo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import torpedo.Field.field_state;

public class Table extends Scene {

	//ArrayList<Integer> MyLittleShips = new ArrayList<>();
	ArrayList<ArrayList<Field>> MyTable = new ArrayList<>();
	ArrayList<ArrayList<Field>> UrTable = new ArrayList<>();
	boolean lovok = true;
	int hajohossz = 0;
	private Map<String, Button> map = new HashMap<String, Button>();
	private Map<String, Button> map2 = new HashMap<String, Button>();
	public Table(Node _node, int N,Integer [] Ships) {
		super((Parent) _node, 80+46*N, 50+23*N);
		
		for (int i = 0; i < N; i++) {
			ArrayList<Field> oszlop = new ArrayList<>();
			
			for (int j = 0; j < N; j++) {
				Field f = new Field(i, j, field_state.sea);
				oszlop.add(f);
			}
			MyTable.add(oszlop);
		}
		
		for (int i = 0; i < N; i++) {
			ArrayList<Field> oszlop = new ArrayList<>();
			
			for (int j = 0; j < N; j++) {
				Field f = new Field(i, j, field_state.sea);
				oszlop.add(f);
			}
			UrTable.add(oszlop);
		}
		throwShips(N, Ships,MyTable);
		//throwShips(N, Ships,UrTable);
		
		Parent root = this.getRoot();
		AnchorPane h = new AnchorPane();
		Node grid1 = MakeGrid(N);
		Node grid2 = MakeGrid2(N);
		h.getChildren().add(grid1);
		h.setLeftAnchor(grid1, 20d);
		h.setTopAnchor(grid1, 20d);
		h.getChildren().add(grid2);
		h.setTopAnchor(grid2, 20d);
		h.setRightAnchor(grid2, 20d);
		this.setRoot(h);
		
		this.getStylesheets().add("style.css");
		
	}

	private void throwShips(int N,Integer [] ships,ArrayList<ArrayList<Field>> table) {
		Random r = new Random();
		
		for (int i = 0; i < ships.length; i++) {
			int meret = i+2;
			for (int j = 0; j < ships[i]; j++) {
				boolean letesz = false;
				while (!letesz) {
					int p = (r.nextInt(2));
					if (p%2 == 0) {
						int x = r.nextInt(N);
						int y = r.nextInt(N-(i+2));
						letesz = true;
						for (int k = 0; k < i+2; k++) {
							if (table.get(x).get(y).state==field_state.ship) {
								letesz = false;
							}
							y++;
						}
						if (letesz) {
							for (int k = 0; k < i+2; k++) {
								table.get(x).get(y).state=field_state.ship;
								y--;
							}
							
						}
						//System.out.println("Hajot tett ide:"+x+" "+y);
					}else {
						int x = r.nextInt(N-(i+2));
						int y = r.nextInt(N);
						letesz = true;
						for (int k = 0; k < i+2; k++) {
							if (table.get(x).get(y).state==field_state.ship) {
								letesz = false;
							}
							x++;
						}
						if (letesz) {
							for (int k = 0; k < i+2; k++) {
								table.get(x).get(y).state=field_state.ship;
								x--;
							}
							
						}
						//System.out.println("Hajot tett ide:"+x+" "+y);
					}
				}
				
			}
		}
		
		for (int i = 0; i < ships.length; i++) {
			hajohossz += ships[i]*(i+2);
		}
		
	}
	private Node addButton(int j, int i) {
		
		Button b = new Button();
		b.setPrefHeight(23d);
		b.setPrefWidth(23d);
		
		if (MyTable.get(j).get(i).state==field_state.sea) {
	 		b.getStyleClass().clear();
	 		b.getStyleClass().add("sea");
	 		
	 	}else {
	 		b.getStyleClass().clear();
	 		b.getStyleClass().add("ship");
	 	}
		 b.setOnAction((event) -> {
			 
			 System.out.println(MyTable.get(j).get(i).state.toString()+" "+j+" "+i);
			 	
	    	  
	      });
		 String s =j+""+i;
		 map.put(s, b);
		 return b;
		
		
	}
	
private Node addButton2(int j, int i) {
		
		Button b = new Button();
		b.setPrefHeight(23d);
		b.setPrefWidth(23d);
		b.getStyleClass().clear();
 		b.getStyleClass().add("grey");
		
		b.setOnAction((event) -> {
			 //Client.Write2Server("Katt"+j+" "+i);
			IshotU(j, i);
			 //System.out.println("Katt");
			 System.out.println(UrTable.get(j).get(i).state.toString()+" "+j+" "+i);
			
				 if ((UrTable.get(j).get(i).state==field_state.sea) && (lovok)) {
				 		b.getStyleClass().clear();
				 		b.getStyleClass().add("sea");
				 		
				 	}else if ((UrTable.get(j).get(i).state==field_state.ship)  && (lovok)){
				 		b.getStyleClass().clear();
				 		b.getStyleClass().add("ship");
				 	}
			
			 
	    	  
	      });
		String s2 =j+""+i;
		map2.put(s2,b);
		 return b;
		
		
	}
	private Node MakeGrid(int N) {
		GridPane root = new GridPane();
		root.getStyleClass().add("blackgrid");
		for (int i =0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				
				root.add(addButton(j, i), j, i);
			}
		}
		return root;
	}
	private Node MakeGrid2(int N) {
		GridPane root = new GridPane();
		root.getStyleClass().add("blackgrid");
		for (int i =0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				
				root.add(addButton2(j, i), j, i);
			}
		}
		return root;
	}
	void IwasShot(int x, int y) {
		if (!lovok) {
			String s = x+""+y;
			Button b = map.get(s);
			if (MyTable.get(x).get(y).state == field_state.ship) {
				
				
				b.getStyleClass().clear();
		 		b.getStyleClass().add("shot");
		 		System.out.println("Valaszjott");
		 		Client.Write2Server("ship "+x+" "+y);
		 		
			}else {
				b.getStyleClass().clear();
		 		b.getStyleClass().add("fail");
		 		Client.Write2Server("sea "+x+" "+y);
		 		System.out.println("Valaszjott");
			}
			lovok = true;
		}
		
	} 
	public void IshotU(int x, int y) {
		if (lovok) {
			String s = "shot "+x+" "+y;
			Client.Write2Server(s);
			lovok = false;
		}
		
		
		
	}
	public void aftershot(int x,int y,String valasz) {
		
		
			Button b = map2.get(x+""+y);
			if (valasz == "ship") {
				b.getStyleClass().clear();
		 		b.getStyleClass().add("ship");
		 		
			}else if(valasz == "sea") {
				b.getStyleClass().clear();
		 		b.getStyleClass().add("sea");
			}
			
		}
		
	
	

}
