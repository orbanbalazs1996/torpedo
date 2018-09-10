package torpedo;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GameSetter extends Stage{

	
	int tablesize;
	int shipnum = 0;
	Integer [] ShipSize = {1,1,1,1};
	int index = 0;
	int players_number = 2;
	
	private Node setThings(int i) {
		HBox h = new HBox(2);
		Button b = new Button((i+2)+" Hosszú hajó");
    	 
	      h.getChildren().add(b);
	      Label l = new Label((this.ShipSize[i])+" darab "+(i+2)+" hosszú hajó van.");
	      h.getChildren().add(l);
	      b.setOnAction((event) -> {
		      	this.ShipSize[i]++;
		      	//System.out.println(this.shipnum);	
		      	 l.setText(this.ShipSize[i]+" darab "+(i+2)+" hosszú hajó van.");
	      });
	      return h;
	}
	
	public GameSetter() {
		// TODO Auto-generated constructor stub
		
		
		setTitle("Torpedo");
	    VBox root = new VBox(10);
	    Label label1 = new Label("Torpedo");
	    label1.setFont(Font.font(42));
	    root.getChildren().add(label1);
	    
	    HBox h = new HBox(2);
		Button b = new Button("Játékos hozzáadása:");
    	 
	      h.getChildren().add(b);
	      Label l = new Label(players_number+" db játékosok van.");
	      h.getChildren().add(l);
	      b.setOnAction((event) -> {
	    	  players_number++;
		      	//System.out.println(this.shipnum);	
		      	 l.setText(players_number+" db játékosok van.");
	      });
	      root.getChildren().add(h);
	    for (int i = 0; i < 4; ++i) {
	    	root.getChildren().add(setThings(i));
	    	
	    }
	    
	    HBox h1 = new HBox(1);
	    Button b1 = new Button("Induljon a móka");
	    
	    b1.setOnAction((event) -> {
	    Node root2 = new HBox(2);
	    int sumshipsize = 0;
	    for (int i = 0; i < ShipSize.length; i++) {
			sumshipsize+= ((i+1)*(ShipSize[i]));
			//System.out.println(ShipSize[i]+" "+sumshipsize);
		}
	    
	    tablesize = (int) Math.ceil((sumshipsize * Math.ceil((double)players_number / 2.0) + 1 ) / 2.0);
	    //System.out.println(sumshipsize+" "+players_number+" "+tablesize);
	    Client.tablapeldanyositaniszeretnek(root2,tablesize,ShipSize);
	      });
		h1.getChildren().add(b1);
		h1.setAlignment(Pos.CENTER);
	      root.getChildren().add(h1);
	    Scene scene = new Scene(root);
	    
	    setScene(scene);
	    show();
	}
	Integer [] getShipsize() {
		return this.ShipSize;
	}

	
}
