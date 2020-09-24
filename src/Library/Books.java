/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Library;

import java.sql.Date;
import java.time.LocalDate;
import static java.time.LocalDate.now;
import javafx.fxml.Initializable;

/**
 *
 * @author sony
 */
public class Books extends BooksController implements Initializable {

    String b_id, b_name, b_publisher, action ;
    Date IssuedDate;
    
     public String getB_name() {
        return b_name;
    }

    public void setB_name(String b_name) {
        this.b_name = b_name;
    }
    
    
    public String getB_id() {
        return b_id;
    }

    public void setB_id(String b_id) {
        this.b_id = b_id;
    }
    
    
  public String getAction() {
       return action;
   }
  public void setAction(String action) {
      this.action = action;
   }
   
  public String getPublisher() {
       return b_publisher;
   }
  public void setPublisher(String b_publisher) {
      this.b_publisher = b_publisher;
   }
  
     public LocalDate getDate() {
        
        return curdate();
    }

    public void setDate( Date IssuedDate) {
        this.IssuedDate = IssuedDate;
    }
  
    
 public Books(String b_id, String b_name, String b_publisher) {
     this.b_id = b_id;   
     this.b_name = b_name;
        this.b_publisher = b_publisher;
        //this.action=action;
        //this.renew=renew;
     
    }

    private LocalDate curdate() {
        return now();
    }
    

}
