/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Library;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author sony
 */
public class ReturnBookController extends NextController implements Initializable {

    @FXML
    private TableView<ReturnBook> returnBook;
    @FXML
    private TableColumn<ReturnBook, String> rbk_id;
    @FXML
    private TableColumn<ReturnBook, String> rb_name;
    @FXML
    private TableColumn<ReturnBook, String> rb_publisher;
    @FXML
    private TableColumn<ReturnBook, String> returnBk;
    @FXML
    private JFXButton returnBack;
   
    static String returnBookId;

    ObservableList<ReturnBook> submit = FXCollections.observableArrayList();
   
    Date dated = new Date (new java.util.Date().getTime());
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        StatisticsController sbc = new StatisticsController();
        FXMLDocumentController fxd3 = new FXMLDocumentController()
;        //con_name.setText(fxd1.id);
        try {

            Connection con;
            con = DBConnector.getConnection();
            Statement stmt=con.createStatement();
          
            ResultSet rs7 =stmt.executeQuery("select b_id , b_name , b_publisher from  accounts a inner join Books b where a.bk_id = b.b_id  and  a.l_id ='"+fxd3.id+"'");
            
            while(rs7.next()){
                
       submit.add(new ReturnBook( rs7.getString("b_id") ,rs7.getString("b_name"),rs7.getString("b_publisher")));
           }
            
           //ib_iid.setCellValueFactory(new PropertyValueFactory<>("ib_iid"));
            rbk_id.setCellValueFactory(new PropertyValueFactory<>("b_id"));
            rb_name.setCellValueFactory(new PropertyValueFactory<>("b_name"));
            rb_publisher.setCellValueFactory(new PropertyValueFactory<>("Publisher"));
        
        returnBook.setItems(submit);
        
        Callback<TableColumn<ReturnBook,String>,TableCell<ReturnBook,String>> cellFactory=(param)->{
        final TableCell<ReturnBook,String> cell=new TableCell<ReturnBook,String>(){
            
             @Override
                public void updateItem(String item,boolean empty){
                    super.updateItem(item,empty);
                    if(empty){
                        setGraphic(null);
                        setText(null);
                    }
                    else{
               
                    final Button btn2=new Button(" Return ");
                    btn2.setOnAction(event ->{
                     try{
                       
                            ReturnBook book4=getTableView().getItems().get(getIndex()); 
                            returnBookId=book4.getB_id();
                            Connection con1;
                            Class.forName("com.mysql.cj.jdbc.Driver");
                            con1 = DriverManager.getConnection("jdbc:mysql://localhost/library","root","4898");
                            
                       Statement stmt=con1.createStatement();
                       Statement stmt1 = con1.createStatement();
                       Statement stmt2 = con1.createStatement();
                       
                       ResultSet rs8 = stmt.executeQuery("select * from accounts where bk_id='"+returnBookId+"'");
                    
                       
                       //ResultSet rs3 =stmt.executeQuery("select * from account where b_id='"+b_id+"'");
                       //b_id.setText(rs2.getString("b_id"));
                       
		    if(rs8.next())
                            {   
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("Confirm Return");
                                alert.setHeaderText("Do you want to return this book "+ fxd3.id+" ?");
                                alert.setContentText(" Confirm ?");
                                Optional<ButtonType> result = alert.showAndWait();
                
                        if(result.get() == ButtonType.OK)
                        {
                            stmt1.executeUpdate("delete from accounts where bk_id ='"+returnBookId+"'");
                           //stmt2.executeUpdate("insert into returnBook values ("+fxd3.id+",'"+returnBookId+"','"+dated+"')");
                              
                            //stmt1.executeUpdate("update accounts set  due_date = '"+renewDate+"' where bk_id ='"+renewBookid+"' ");
                            try{
                                //stmt1.executeUpdate("delete from accounts where bk_id =' "+returnBookId+"' and l_id = "+fxd3.id+"");
                                
                                Parent root = FXMLLoader.load(getClass().getResource("ReturnBook.fxml"));
                                Scene scene = new Scene(root);
                                Stage stage2 =(Stage) ((Node)event.getSource()).getScene().getWindow();

                                stage2.setScene(scene);
                                stage2.show();
                   
                   }
                            catch (IOException ex) {
                            Logger.getLogger(BooksController.class.getName()).log(Level.SEVERE, null, ex);
                            }                       

                        
                                }
			 else if(result.get() == ButtonType.CANCEL)
                                    event.consume();
                    
		    }
                    else { 
                             JOptionPane.showMessageDialog(null, "This Book is Already returned !");
                           
                         
                        }
                        
                       	   							
                         
                        } catch (ClassNotFoundException | SQLException  ex) {
                            Logger.getLogger(BooksController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
					
                    });
                    setGraphic(btn2);
                    setText(null);
                       
                }       
        };
        };
            return cell;
                };
         returnBk.setCellFactory(cellFactory);

        
            
        } catch (SQLException ex) {
            Logger.getLogger(StatisticsController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }    

    @FXML
    private void returnBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("NextController.fxml"));
                Scene scene = new Scene(root);
                        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                        stage.setTitle("Statistics");
                        stage.setScene(scene);
                        stage.show();
    
    }
    
}
