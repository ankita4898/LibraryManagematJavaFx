/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Library;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sony
 */
public class NextController extends FXMLDocumentController implements Initializable {

    FXMLDocumentController fxd2 = new FXMLDocumentController();
    
    @FXML
    private StackPane operation;
    @FXML
    private JFXButton issueBtn;
    @FXML
    private JFXButton statsBtn;
    @FXML
    private AnchorPane profile;
    @FXML
    private ImageView img1;
    @FXML
    private ImageView img2;
   
    @FXML
    private JFXButton backWelcome;
    @FXML
    private Label welcome;
    @FXML
    private JFXButton returnBtn;
    @FXML
    private ImageView img11;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    
    //String label;
   
    @FXML
    private void bookIssue(ActionEvent event) throws IOException {
        
                Parent root = FXMLLoader.load(getClass().getResource("Books.fxml"));
                Scene scene = new Scene(root);
                        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                        scene.getStylesheets().add("/CSS/IssuedBooks.css");
                        stage.setTitle("Books");
                        stage.setScene(scene);
                        stage.show();
    

    }

    @FXML
    private void statistics(ActionEvent event) throws SQLException, IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Statistics.fxml"));
        
                        Scene scene = new Scene(root);
                        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                        stage.setTitle("Statistics");
                        stage.setScene(scene);
                        stage.show();
    
            }
    
    @FXML
    private void bookReturn(ActionEvent event) throws SQLException ,IOException {
         Parent root = FXMLLoader.load(getClass().getResource("ReturnBook.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Return Book");
        stage.setScene(scene);
        stage.show();
    
    }
    
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image img = new Image(getClass().getResourceAsStream("/gallery/book.png"));
        img1.setImage(img);
        
        Image image = new Image(getClass().getResourceAsStream("/gallery/stats.png"));
        img2.setImage(image);
        welcome.setText(fxd2.s1);
    }    

    @FXML
    private void backWelcome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                Scene scene = new Scene(root);
                        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                        //scene.getStylesheets().add("/CSS/IssuedBooks.css");
                        //stage.setTitle("Profile");
                        stage.setScene(scene);
                        stage.show();
    
    }


    

    
}
