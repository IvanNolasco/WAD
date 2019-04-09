package actionsupportpackage;

import com.opensymphony.xwork2.ActionSupport;

public class LoginActionSupport extends ActionSupport 
{
 String userName,password;
 
 public String execute() throws Exception 
 {
        LoginBean lb = new LoginBean();
        // check to see if this user/password combination are valid
        if(lb.validateUser(userName,password))
        {
            switch (lb.getTipo()) {
                case 1:
                    return SUCCESS; 
                case 2:
                    return "profesor";
                case 3:
                    return "administrador";
            }
        
        }
        else	// username/password not validated
        {
        addActionError("User Name or password does not exist");
        return INPUT;
        }  
     return null;
 }
 public String getPassword() {
 return password;
 }
 public void setPassword(String password) {
 this.password = password;
 }
 public String getUserName() {
 return userName;
 }
 public void setUserName(String userName) {
 this.userName = userName;
 }
}