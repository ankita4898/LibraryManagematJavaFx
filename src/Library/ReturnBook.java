/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Library;

import java.sql.Date;

/**
 *
 * @author sony
 */
public class ReturnBook extends ReturnBookController {
    
     String rbk_id, rb_name, rb_publisher, returnBk;
    
   Date due_date;
    
     public String getB_name() {
        return rb_name;
    }

    public void setB_name(String b_name) {
        this.rb_name = b_name;
    }
    
     public String getB_id() {
        return rbk_id;
    }

    public void setB_id(String b_id) {
        this.rbk_id = b_id;
    }
    
    
  public String getReturn() {
       return returnBk;
   }
  public void setReturn(String returnBk) {
      this.returnBk = returnBk;
   }
   
  public String getPublisher() {
       return rb_publisher;
   }
  public void setPublisher(String b_publisher) {
      this.rb_publisher = b_publisher;
   }
  
    /*public Date getDate() {
        return due_date;
    }

    public void setDate(Date due_date) {
        this.due_date = due_date;
    }*/
  
    
 public ReturnBook (String b_id, String b_name, String b_publisher ) {
     this.rbk_id = b_id;   
     this.rb_name = b_name;
        this.rb_publisher = b_publisher;
        //this.ib_iid= ib_iid;
        //this.action=action;
        //this.renew=renew;
     
    }
    

}

   