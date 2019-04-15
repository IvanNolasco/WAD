package actionsupportpackage;

import static com.opensymphony.xwork2.Action.INPUT;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import entity.Login;
import entity.HibernateUtil;
import org.hibernate.Session;

public class LoginActionSupport extends ActionSupport {
 String userName,password;
 int tipo;
 Session hibernateSession;
 Login login;
 
 @Override
 public String execute() throws Exception {
 hibernateSession=HibernateUtil.getSessionFactory().openSession();
 hibernateSession.beginTransaction();
 System.out.println("session get");
 if(userName!=null && password!=null &&(!userName.equals(""))&&(!password.equals(""))){
 login=(Login) hibernateSession.createQuery("from Login where userName='"+userName+"' AND password='"+password+"'").uniqueResult();
 }else{
 addActionError("User Name does not exist");
 return INPUT;
 }
 if(login==null){
 addActionError("User Name does not exist");
 return INPUT;
 }
 addActionMessage("Welcome you logined");
 tipo = login.getTipo();
 if(tipo==0){
     return "admin";
 } else if(tipo==1){
     return "teacher";
 } else {
     return "student";
 }
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
 public int getTipo(){
     return tipo;
 }
 public void setTipo(int tipo){
     this.tipo = tipo;
 }

}