package actionsupportpackage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

class LoginBean 
{
    private HashMap validUsers = new HashMap();
    public static byte intentos = 0;
    private int tipo;
    /**
     * Constructor for LoginBean
     * Initializes the list of usernames/passwords
     */
    public LoginBean()
    {
            validUsers.put("ruben","rpv");
            validUsers.put("administrator","admin");
    }
    
    

    /**
     * determine if the username/password combination are
     * present in the validUsers repository.
     * @param userName
     * @param password
     * @return boolean true if valid, false otherwise
     */
    public boolean validateUser(String userName, String password)
    {
        
        ResultSet rs = null;
        // check to see if this user/password combination are valid
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Este metodo nos permite cargar clases dinamicas
            Connection db = DriverManager.getConnection("jdbc:mysql://localhost:3306/login?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root", "kirisutegomen");
            Statement s = db.createStatement();
            rs=s.executeQuery("SELECT * FROM usuario WHERE user = '"+userName+"'");
            while(rs.next())
            {
                String usuario = rs.getString("user");
                String pass = rs.getString("pass");
                tipo = rs.getInt("tipo");
                System.out.println("Usuario"+usuario);
                System.out.println("pass"+pass);
                validUsers.put(usuario, pass);
                if(validUsers.containsKey(userName))
                {
                    String thePassword = (String)validUsers.get(userName);
                    if(thePassword.equals(password))
                    return true;
                }
            } 
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No se conectó a la bd");
        }
            
        return false;
    }

    public int getTipo() {
        return tipo;
    }

    
}
