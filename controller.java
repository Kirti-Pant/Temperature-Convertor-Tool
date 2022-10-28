package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class controller implements Initializable {
	@FXML
	public Label welcome;

	@FXML
	public ChoiceBox<String> choice;
	//CONSTANT string value
	private static final String cf="Celsius to Fahrenhite";
	private static final String fc="Fahrenhite to Celsius";
	private boolean iscf=true;

	@FXML
	public Button Convert;

	@FXML
	public TextField tf;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		choice.getItems().add(cf);
		choice.getItems().add(fc);

		choice.setValue(cf);
		choice.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				System.out.println(newValue);
				if (newValue.equals(cf)) {
					iscf = true;
				} else {
					iscf = false;
				}
			}
		});

		Convert.setOnAction(event -> {
			convert();
		});
	}


		//creating convert method
		private void convert() {
			String input = tf.getText();              //WILL BE A STRING VALUE
			//if user entered a string a its value
			float ct=0.0f;
			try {
				ct = Float.parseFloat(input);   //STRING TO CONVERT INTO FLOAT
			} catch (Exception exception) {
				warnuser();
				return;
				//AFTER RETURN NO CODE IS ACTUALLY EXECUTED
			}
			float nt = 0.0f;
			if (iscf) {
				nt = (ct * 9 / 5) + 32;
			} else {
				nt = (ct - 32) * 5 / 9;
			}
			display(nt);
			}


	private void warnuser() {
		Alert alert=new Alert(Alert.AlertType.ERROR);
		alert.setTitle("ERROR!!!!");
		alert.setContentText("YOU ENTERED STRING AS AN INPUT WHICH IS INVALID");
		alert.show();
	}

	private void display(float nt) {
			String unit=iscf?"C":"F";
		System.out.println("NEW TEMPERATURE IS "+nt+unit);
		//CRESTING ALERT DIALOG BOX TO SHOW THE RESULT IN DESKTOP APPLICATION
		Alert alert=new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("RESULT");
		alert.setContentText("NEW TEMPERATURE IS "+nt+unit);
		alert.show();
	}


}
