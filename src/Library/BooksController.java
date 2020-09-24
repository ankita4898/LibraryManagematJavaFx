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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JOptionPane;
//import java.util.*;

/**
 * FXML Controller class
 *
 * @author sony
 */
public class BooksController extends  NextController implements Initializable {

    //PreparedStatement pst1;
    ResultSet rs2;
//    String id;//= txtid.getText();
//    String pass;// = txtpass.getText();   

    @FXML
    private TableView<Books> Books;
    @FXML
    private TableColumn<Books, String> action;
    @FXML
    private TableColumn<Books, String> b_id;
    @FXML
    private TableColumn<Books, String> b_name;
    @FXML
    private TableColumn<Books, String> b_publisher;
    @FXML
    private JFXButton bookBack;

    String bookId;
    
    Date date = new Date (new java.util.Date().getTime());
    Date newDate =new Date(date.getTime()+15*24*60*60*1000);
    

    
    /**
     * Initializes the controller class.
     */

    
    public ObservableList<Books> list = FXCollections.observableArrayList();
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
      
        FXMLDocumentController fxd = new FXMLDocumentController(); 
        BooksController ibc = new BooksController();
        
        try {
            Connection con1;
            con1 = DBConnector.getConnection();
            
            ResultSet rs1 = con1.createStatement().executeQuery("select*from Books");
            while(rs1.next()){
                list.add(new Books(rs1.getString("b_id"),rs1.getString("b_name"),rs1.getString("b_publisher")));
            }
       
        } catch (SQLException ex) {
            Logger.getLogger(BooksController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        b_id.setCellValueFactory(new PropertyValueFactory<>("b_id"));
        b_name.setCellValueFactory(new PropertyValueFactory<>("b_name"));
        b_publisher.setCellValueFactory(new PropertyValueFactory<>("Publisher"));
        
        //action.setCellValueFactory(new PropertyValueFactory<>("action"));
        Books.setItems(list);
        
        Callback<TableColumn<Books,String>,TableCell<Books,String>> cellFactory=(param)->{
        final TableCell<Books,String> cell=new TableCell<Books,String>(){
            
             @Override
                public void updateItem(String item,boolean empty){
                    super.updateItem(item,empty);
                    if(empty){
                        setGraphic(null);
                        setText(null);
                    }
                    else{
               
                    final Button btn=new Button(" Issue ");
                    btn.setOnAction(event ->{
                     try{
                        //book=ibc.getB_id();
                            Books book=getTableView().getItems().get(getIndex()); 
                            bookId=book.getB_id();
                            
//                            Books book1=getTableView().getItems().get(getIndex()); 
//                         // Books book1 = new Books();
//                          bissued=book1.getDate();
                            
                            Connection con1;
                            Class.forName("com.mysql.cj.jdbc.Driver");
                            con1 = DriverManager.getConnection("jdbc:mysql://localhost/library","root","4898");
                       Statement stmt=con1.createStatement();
                       Statement stmt1 = con1.createStatement();
                       Statement stmt2 = con1.createStatement();
                       Statement stmt3 = con1.createStatement();
                       
                       ResultSet rs2 = stmt.executeQuery("select * from accounts where bk_id='"+bookId+"'");
                    
                       
                       //ResultSet rs3 =stmt.executeQuery("select * from account where b_id='"+b_id+"'");
                       //b_id.setText(rs2.getString("b_id"));
                       
		    if(rs2.next())
                            {   
                               // b_id.setText(rs2.getString("bk_id"));
                     JOptionPane.showMessageDialog(null, "This Book is Already Issued !");
		    }
                    else { 
                        Alert alert = new Alert(AlertType.CONFIRMATION);
                        alert.setTitle("Confirm Issue");
                        alert.setHeaderText("Do you want to issue this book "+ fxd.id+" ?");
                        alert.setContentText(" Confirm ?");
                        Optional<ButtonType> result = alert.showAndWait();
                
                        if(result.get() == ButtonType.OK)
                        {       //Timestamp IssuedDate = rs2.getObject("IssuedDate",Timestamp.class);
                           
                           // SimpleDateFormat CURRENT_TIMESTAMP= new SimpleDateFormat("dd-MM-yyyy");
                         
//                            Declare Returndate Date=null;
//                            SET ReturnDate = ();
                                stmt1.executeUpdate("insert into accounts(l_id, bk_id,IssuedDate , due_date) values ('"+fxd.id+"','"+bookId+"','"+date+"','"+newDate+"')");
                                //stmt2.executeUpdate("update accounts set IssuedDate = GETDATE() where bk_id ='"+bookId+"'");
                              //stmt2.executeUpdate("insert into accounts( ) values()");
                               // stmt3.executeQuery("select"+bookId+"  IssuedDate , DATEADD(day,10,"+bissued+") AS ReturnDate FROM accounts)");
                                //stmt2.executeQuery("select ReturnDate , ReturnDate+10 add_10_days from accounts");
                                Parent root = FXMLLoader.load(getClass().getResource("BookDone.fxml"));
                                Scene scene = new Scene(root);
                                Stage stage2 =(Stage) ((Node)event.getSource()).getScene().getWindow();

                                stage2.setScene(scene);
                                stage2.show();
                   
//                   //}catch (IOException ex) {
//                            Logger.getLogger(IssuedBooksController.class.getName()).log(Level.SEVERE, null, ex);
//                            }                       

                        }
                        
                        else if(result.get() == ButtonType.CANCEL)
                                    event.consume();
                                }
				   							
                         
                        } catch (ClassNotFoundException | SQLException | IOException ex) {
                            Logger.getLogger(BooksController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
					
                    });
                    setGraphic(btn);
                    setText(null);
                       
                }       
        };
        };
            return cell;
                };
         action.setCellFactory(cellFactory);
    }    

    @FXML
    private void bookBack(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("NextController.fxml"));
                                Scene scene = new Scene(root);
                                Stage stage2 =(Stage) ((Node)event.getSource()).getScene().getWindow();
                                stage2.setScene(scene);
                                stage2.show();
                   
        
    }

//    private void initClock() {
//
//   
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
//        bissued.setDate(LocalDateTime.now().format(formatter));
//    
//}

    
}
