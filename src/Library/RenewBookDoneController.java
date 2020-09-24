/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Library;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sony
 */
public class RenewBookDoneController extends StatisticsController implements Initializable {

    @FXML
    private JFXButton rOut;
    @FXML
    private JFXButton rBack;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void rOut(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            Scene scene = new Scene(root);
            Stage stage2 =(Stage) ((Node)event.getSource()).getScene().getWindow();
            stage2.setScene(scene);
            stage2.show();
                   
    }

    @FXML
    private void rBack(ActionEvent event) throws IOException {
                Parent root = FXMLLoader.load(getClass().getResource("NextController.fxml"));

                    Scene scene = new Scene(root);
                    Stage stage2 =(Stage) ((Node)event.getSource()).getScene().getWindow();
                    stage2.setScene(scene);
                    stage2.show();
                   
    }
    
}
