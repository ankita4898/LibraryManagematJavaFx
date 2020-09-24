/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Library;

import java.sql.Date;
import javafx.fxml.Initializable;

/**
 *
 * @author sony
 */
public class StatsTable extends StatisticsController implements Initializable{

    String ib_id, ib_name, ib_publisher, renew ;
    Date due_date;
    
   
    
     public String getB_name() {
        return ib_name;
    }

    public void setB_name(String b_name) {
        this.ib_name = b_name;
    }
    
     public String getB_id() {
        return ib_id;
    }

    public void setB_id(String b_id) {
        this.ib_id = b_id;
    }
    
    
  public String getRenew() {
       return renew;
   }
  public void setRenew(String renew) {
      this.renew = renew;
   }
   
  public String getPublisher() {
       return ib_publisher;
   }
  public void setPublisher(String b_publisher) {
      this.ib_publisher = b_publisher;
   }
  
    public Date getDate() {
        return due_date;
    }

    public void setDate(Date due_date) {
        this.due_date = due_date;
    }
  
    
 public StatsTable (String b_id, String b_name, String b_publisher , Date due_date) {
     this.ib_id = b_id;   
     this.ib_name = b_name;
        this.ib_publisher = b_publisher;
        this.due_date= due_date;
        //this.action=action;
        //this.renew=renew;
     
    }
    

}
