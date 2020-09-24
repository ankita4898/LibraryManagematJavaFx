package Library;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javax.swing.JOptionPane;

/**
 *
 * @author sony
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private JFXTextField txtid;
    @FXML
    private JFXPasswordField txtpass;
    @FXML
    private JFXButton btnLogin;
    @FXML
    private JFXButton btnCancel;
    
    @FXML private AnchorPane rootPane;
    
    static String id;
    public static String s1;

    @FXML
    private void login(ActionEvent event) throws SQLException, IOException{
        s1=txtid.getText();
        Connection con;
    PreparedStatement pst;
    
    ResultSet rs;
    
         id = txtid.getText();
        String pass = txtpass.getText();
        
        if(id.equals("") && pass.equals("")){
        
             JOptionPane.showMessageDialog(null, "ID or Password is blank");
             txtpass.setText("");
              txtid.setText("");
             
        }else{
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:mysql://localhost/library","root","4898");
                    
                    pst = con.prepareStatement("select * from login where id=? and l_password=?");
                    pst.setString(1, id);
                    pst.setString(2, pass);
                
                rs = pst.executeQuery();
                
                if(rs.next()){
                    
                    JOptionPane.showMessageDialog(null, "Login Success");
                    txtid.setText("");
                    txtpass.setText("");
                    
                    
                     Parent root1 = FXMLLoader.load(getClass().getResource("NextController.fxml"));
        Scene scene3 = new Scene(root1);
        Stage stage3 = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage3.setScene(scene3);
        stage3.show();
      
                   // Parent root = FXMLLoader.load(getClass().getResource("NextController.fxml"));
                        
//                        Scene scene = btnLogin.getScene();
//                        root.translateYProperty().set(scene.getHeight());
//                        rootPane.getChildren().add(root);
//                        Timeline time = new Timeline();
//                        KeyValue kv = new KeyValue(root.translateYProperty(),0, Interpolator.EASE_IN);
//                        KeyFrame kf = new KeyFrame(Duration.seconds(1),kv );
//                        time.getKeyFrames().add(kf);
//                        time.setOnFinished(event1 ->{
//                        rootPane.getChildren().remove(rootPane);});
//                        time.play();
//                       rootPane.getChildren().setAll(root);
//                        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
//
//                        stage.setScene(scene);
//                        stage.show();
    
                    
                        
//                AnchorPane pane= FXMLLoader.load(getClass().getResource("NextController.fxml"));
//               rootPane.getChildren().setAll(pane);
                    
                    
                     }else {
                    JOptionPane.showMessageDialog(null, "Login Failed");
                    txtid.setText("");
                    txtpass.setText("");
                    txtid.requestFocus();
                }
                
           
        

//                                  Parent root = FXMLLoader.load(getClass().getResource("IssuedBooks.fxml"));
//                   Scene scene = new Scene(root);
//                    Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
//                    stage.setScene(scene);
//                   stage.show();} catch (ClassNotFoundException ex) { 
//                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            } 
                  
           
               
                    
//                } catch (ClassNotFoundException ex) { 
//                 Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
             } 
    }


    @FXML
    private void cancel(ActionEvent event) {
        System.exit(0);
    }
    
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
   
}
