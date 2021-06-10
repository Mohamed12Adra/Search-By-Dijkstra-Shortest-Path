
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class Controller2 implements Initializable {
	@FXML
	Pane pane1;
	@FXML
	Pane pane2;
	@FXML
	ComboBox<String> combo1;
	@FXML
	ComboBox<String> combo2;
	@FXML
	Button Run1;
	@FXML
	TextArea textarea;
	@FXML
	TextField textfield;
	@FXML
	Button clear;
	int counter = 0;
	double counter1=0;
	int counterr=0;
	Controller c = new Controller();
	ArrayList<RadioButton> arraylist = new ArrayList<>();
	ArrayList<String[]> arrl = new ArrayList<>();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		for (int i = 0; i < c.al.size(); i++) {
			combo1.getItems().add(c.al.get(i).getName());
			combo2.getItems().add(c.al.get(i).getName());
			// System.out.println();
			RadioButton rd = new RadioButton();
			Label l = new Label();
			l.setText(c.al.get(i).getName());
			l.setTranslateX(c.al.get(i).getX());
			l.setTranslateY(c.al.get(i).getY());
			l.setStyle("-fx-font-size:7px;");
			rd.setId(c.al.get(i).getName());
			rd.setTranslateX(c.al.get(i).getX());
			rd.setTranslateY(c.al.get(i).getY());
			rd.setStyle("-fx-font-size:7px;");
			arraylist.add(rd);
			rd.setOnAction(new EventHandler<ActionEvent>() {

				/*
				 * @Override public void handle(ActionEvent e) { for(int i=0;
				 * i<arraylist.size(); i++) { if(arraylist.get(i).isPressed()) { for(int j=0;
				 * j<c.al.size(); j++) { if(c.al.get(j).getX()==arraylist.get(i).getTranslateX()
				 * && c.al.get(j).getY()==arraylist.get(i).getTranslateY()) {
				 * if(combo1.getValue()==null) { combo1.setValue((c.al.get(j).getName())); }else
				 * if(combo2.getValue()==null) { combo2.setValue((c.al.get(j).getName())); } } }
				 * } }
				 * 
				 * }
				 */
				@Override
				public void handle(ActionEvent e) {
					if (counter == 0) {
						String city = rd.getId();
						for (int x = 0; x < combo1.getItems().size(); x++) {
							if (combo1.getItems().get(x).equals(city)) {
								combo1.getSelectionModel().select(x);
								break;
							}
						}
						counter++;
					} else if (counter == 1) {
						String city = rd.getId();
						for (int x = 0; x < combo2.getItems().size(); x++) {
							if (combo2.getItems().get(x).equals(city)) {
								combo2.getSelectionModel().select(x);
								break;
							}
						}
						counter++;
					}
				}

			});
			pane2.getChildren().add(rd);
			pane2.getChildren().add(l);
		}
		/*
		 * Circle circle1 = new Circle(10); circle1.setTranslateX(150);
		 * circle1.setTranslateY(130); circle1.setFill(Color.GREENYELLOW);
		 * pane2.getChildren().add(circle1);
		 */
	}

	@FXML
	public void RunOnAction(ActionEvent e) {
		Graph graph = new Graph();
		for (int i = 0; i < c.al.size(); i++) {
			graph.add(c.al.get(i));
		}
		for (int i = 0; i < c.al.size(); i++) {
			if (combo1.getSelectionModel().getSelectedItem().toString().contains(c.al.get(i).getName())) {
				for (int j = 0; j < c.al.size(); j++) {
					if (combo2.getSelectionModel().getSelectedItem().toString().contains(c.al.get(j).getName())) {
						if(graph.shortestPath(c.al.get(i), c.al.get(j))!=Integer.MAX_VALUE) {
							textfield.setText(Double.toString(graph.shortestPath(c.al.get(i), c.al.get(j))));
							String[] arr = graph.path(c.al.get(j));
							arrl.add(arr);
							counter1=graph.shortestPath(c.al.get(i), c.al.get(j));
						}else{
							
							counter1=graph.shortestPath(c.al.get(i), c.al.get(j));
						}
						//String[] arr = graph.path(c.al.get(j));
						//arrl.add(arr);
						//break;
					}

				}
				break;
			}

		}
		String str = "";
		if(arrl.size()!=counterr && counter1!=0) {
		String[] arrstr = arrl.get(counterr);
		for (int i = graph.getParents().length - 1; i >= 0; i--) {
			if(arrstr[i]!=null) {
				if (i != 0)
					str += arrstr[i] + "->";
				else
					str += arrstr[i];
			}
		}
		/*for(int i=0; i<arrstr.length; i++) {
			System.out.println(arrstr[i]);
		}*/
		for (int x = 1; x < arrstr.length; x++) {
			if(arrstr[x]!=null) {
			int x1 = 0;
			int y1 = 0;
			x1 = c.al.get(getIndex(arrstr[x-1])).getX();
			y1 = c.al.get(getIndex(arrstr[x-1])).getY();
			int x2 = 0;
			int y2 = 0;
			x2 = c.al.get(getIndex(arrstr[x])).getX();
			y2 = c.al.get(getIndex(arrstr[x])).getY();
			Line l = new Line();
			l.setStartX(x1);
			l.setStartY(y1);
			l.setEndX(x2);
			l.setEndY(y2);
			l.setStrokeWidth(2);
			pane2.getChildren().add(l);
			}
		}
		textarea.setText(str);
		}else if(counter1==Integer.MAX_VALUE) {
			textarea.setText("No Path");
			textfield.setText("No Distance");
		}
		
		//textarea.setText(str);
		//++counterr;
		// System.out.println(combo1.getSelectionModel().getSelectedItem().toString());
	}
	public int getIndex(String str) {
		for(int i=0; i<c.al.size(); i++) {
			if(str.contains(c.al.get(i).getName())) {
				return i;
			}
		}
		return 0;
	}
	public void ClearOnAction(ActionEvent e) {
		combo1.getSelectionModel().clearSelection();
		combo2.getSelectionModel().clearSelection();
		int counter1 = (pane2.getChildren().size())-101;
		if(pane2.getChildren().size()!=52) {
			for(int i=0; i<counter1; i++) {
				pane2.getChildren().remove(pane2.getChildren().size()-1);
			}
		}
		if(arrl.size()==1) {
			arrl.remove(0);
		}
		textarea.setText("");
		textfield.setText("");
		counter = 0;
		System.out.println();
		for (int i = 0; i < arraylist.size(); i++) {
			System.out.println(i);
			if (arraylist.get(i).isSelected() == true) {
				arraylist.get(i).setSelected(false);
			}
		}
	}
}
