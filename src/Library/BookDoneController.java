/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Library;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sony
 */
public class BookDoneController  extends BooksController implements Initializable {

    @FXML
    private AnchorPane BookDone;
    @FXML
    private JFXButton logOut;
    @FXML
    private JFXButton back;
    @FXML
    private Label IssDate;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    
  @Override
    public void initialize(URL url, ResourceBundle rb) {
     initClock();
     BooksController bc = new BooksController();
    }  
    
    
private void initClock() {

   
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy ");
        IssDate.setText(LocalDateTime.now().format(formatter));

        
}


    @FXML
    private void logOut(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("NextController.fxml"));
                Scene scene = new Scene(root);
                        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                      // scene.getStylesheets().add("/CSS/IssuedBooks.css");
                        stage.setTitle("Profile");
                        stage.setScene(scene);
                        stage.show();
    
                        
    }
    
}
