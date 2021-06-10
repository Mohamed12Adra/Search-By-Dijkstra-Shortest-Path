import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public  class Controller implements Initializable{
	@FXML
	Pane pane1;
	
	@FXML
	Button CitiesCor;
	@FXML
	Button CitiesAdj;
	@FXML
	Button Run;
	@FXML
	Button PressWhenD;
	
	public static ArrayList<Vertix> al = new ArrayList<>();
	Stage secondStage;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
	}
	@FXML
	public void fileCitiesWithCor(ActionEvent e) {
		try {
		FileChooser filechooser = new FileChooser();
		File file = filechooser.showOpenDialog(null);
		Scanner in = new Scanner(file);
		while(in.hasNextLine()) {
			String str = in.nextLine();
			String[] arrstr = str.split(" ");
			Vertix v = new Vertix(arrstr[0],Integer.parseInt(arrstr[1]),Integer.parseInt(arrstr[2]));
			al.add(v);
		}
		
		}catch(FileNotFoundException x) {
			
		}
	}
	@FXML
	public void fileCitiesWithAdj(ActionEvent e) {
		try {
		FileChooser filechooser = new FileChooser();
		File file = filechooser.showOpenDialog(null);
		Scanner in = new Scanner(file);
		while(in.hasNextLine()) {
			String str = in.nextLine();
			String[] arrstr = str.split(" ");
			ArrayList<Vertix> al2 = new ArrayList<>();
			for(int i=0; i<al.size(); i++) {
				if(arrstr[0].contains(al.get(i).getName())) {
					for(int j=0; j<al.size(); j++) {
						if(arrstr[1].contains(al.get(j).getName())) {
							al.get(i).addNeighbor(al.get(j), Double.parseDouble(arrstr[2]));
							break;
				}
						
			}
			
			break;
		}
			}
		}
		
		}catch(FileNotFoundException x) {
			
		}catch(NullPointerException x) {
			
		}
	}
	@FXML
	public void PressWhenDone(ActionEvent e) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Map.fxml"));
		Scene scene = new Scene(root);
		Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
		
		window.setScene(scene);
		window.show();
	}
	
}
