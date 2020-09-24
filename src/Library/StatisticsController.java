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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author sony
 */
public class StatisticsController extends NextController implements Initializable {

    @FXML
    private TableColumn<StatsTable,String > ib_id;
    @FXML
    private TableColumn<StatsTable, String> ib_name;
    @FXML
    private TableColumn<StatsTable, String> ib_publisher;
    @FXML
    private TableColumn<StatsTable, String> renew;
    @FXML
    private AnchorPane statsPane;
    @FXML
    private TableView<StatsTable> statsTable;
//   
    static String renewBookid;
    static Date dueDate;
    @FXML
    private JFXButton statsBack;
    
    Date date = new Date (new java.util.Date().getTime());
    Date newDate =new Date(date.getTime()+15*24*60*60*1000);
    Date renewDate =new Date(newDate.getTime()+7*24*60*60*1000);
    /**
     * Initializes the controller class.
     */
    ObservableList<StatsTable> stats = FXCollections.observableArrayList();    
    
    
    
    @FXML
    private TableColumn<StatsTable, Date> due_date;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        BooksController ibc = new BooksController();
        FXMLDocumentController fxd1 = new FXMLDocumentController()
;        //con_name.setText(fxd1.id);
        try {

            Connection con;
            con = DBConnector.getConnection();
            
            Statement stmt=con.createStatement();
          
            
            ResultSet rs6 =stmt.executeQuery("select b_id , b_name , b_publisher , due_date from  accounts a inner join Books b where a.bk_id = b.b_id  and  a.l_id ='"+fxd1.id+"'");
            
            while(rs6.next()){
                
       stats.add(new StatsTable( rs6.getString("b_id") ,rs6.getString("b_name"),rs6.getString("b_publisher"),rs6.getDate("due_date")));
           }
            
            ib_id.setCellValueFactory(new PropertyValueFactory<>("b_id"));
            ib_name.setCellValueFactory(new PropertyValueFactory<>("b_name"));
            ib_publisher.setCellValueFactory(new PropertyValueFactory<>("Publisher"));
            due_date.setCellValueFactory(new PropertyValueFactory<>("due_date"));
//            due_date.setCellValueFactory(cellData ->cellData.getValue().getDate());
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
//            due_date.setCellFactory(coumn -> {
//                return new TableColumn<StatsTable ,newDate>(){
//                @Override
//                    protected void updateItem(Date item, boolean empty){
//                    
//                    super.updateItem(item,empty);
//                    if(empty){
//                    
//                    setText(null);
//                    }else{
//                            this.setText(format.format(item));
//                        }
//                    } 
//                
//                
//                };
//            
//            });
        }
           
        catch (SQLException ex) {
            Logger.getLogger(StatisticsController.class.getName()).log(Level.SEVERE, null, ex);
        }
            //due_date.setCellValueFactory(new PropertyValueFactory<>("due_date"));
        
        statsTable.setItems(stats);
        
        Callback<TableColumn<StatsTable,String>,TableCell<StatsTable,String>> cellFactory=(param)->{
        final TableCell<StatsTable,String> cell=new TableCell<StatsTable,String>(){
            
             @Override
                public void updateItem(String item,boolean empty){
                    super.updateItem(item,empty);
                    if(empty){
                        setGraphic(null);
                        setText(null);
                    }
                    else{
               
                    final Button btn2=new Button(" Renew ");
                    btn2.setOnAction(event ->{
                     try{
                        //book=ibc.getB_id();
                             StatsTable book3=getTableView().getItems().get(getIndex()); 
                            renewBookid=book3.getB_id();
                            dueDate = book3.getDate();
                            Connection con1;
                            Class.forName("com.mysql.cj.jdbc.Driver");
                            con1 = DriverManager.getConnection("jdbc:mysql://localhost/library","root","4898");
                       Statement stmt=con1.createStatement();
                       Statement stmt1 = con1.createStatement();
                       Statement stmt2 = con1.createStatement();
                       
                       ResultSet rs5 = stmt.executeQuery("select * from accounts where bk_id='"+renewBookid+"' and due_date='"+dueDate+"'");
                    
                       
                       //ResultSet rs3 =stmt.executeQuery("select * from account where b_id='"+b_id+"'");
                       //b_id.setText(rs2.getString("b_id"));
                       
		    if(rs5.next())
                            {   
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("Confirm Renew");
                                alert.setHeaderText("Do you want to renew this book "+ fxd1.id+"?");
                                alert.setContentText(" Confirm ?");
                                Optional<ButtonType> result = alert.showAndWait();
                
                        if(result.get() == ButtonType.OK)
                        {
                               
                            stmt1.executeUpdate("update accounts set  due_date = '"+renewDate+"' where bk_id ='"+renewBookid+"' ");
                      try{
//                             //stmt1.executeUpdate("insert into account(l_id,bk_id) values ("+fxd.id+","+book.b_id+")");
                             Parent root = FXMLLoader.load(getClass().getResource("RenewBookDone.fxml"));
                                Scene scene = new Scene(root);
                                Stage stage3 =(Stage) ((Node)event.getSource()).getScene().getWindow();

                                stage3.setScene(scene);
                                stage3.show();
                   
//                               
                   }catch (IOException ex) {
                            Logger.getLogger(BooksController.class.getName()).log(Level.SEVERE, null, ex);
                            }                       

                        
                                }
			 else if(result.get() == ButtonType.CANCEL)
                                    event.consume();
                    
		    }
                    else { 
                             JOptionPane.showMessageDialog(null, "This Book is Already Renewed!");
                           
                         
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
         renew.setCellFactory(cellFactory);

        
            
    }
    

    @FXML
    private void statsBack(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("NextController.fxml"));
                Scene scene = new Scene(root);
                        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                        stage.setTitle("Statistics");
                        stage.setScene(scene);
                        stage.show();
    
    }
    
}
