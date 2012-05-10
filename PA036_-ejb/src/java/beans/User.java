package beans;

import beans.AdministratorFacade;
import beans.Message;
import entities.Administrator;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.mail.MessagingException;

/** 
 * User.java
 * Purpose: Managed bean whose purpose is to offer method used in User and Order management.
 * 
 * @author Tomáš Hrabal, Tomáš Sezima
 * @version 1.0
 */
@ManagedBean(name = "user")
@SessionScoped
public class User {

    @EJB
    private AdministratorFacade administratorFacade;

    private String login;
    private String password;
    private String password2;
    private int idAdministrator;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }
    
    public int getIdAdministrator() {
        return idAdministrator;
    }

    public void setIdAdministrator(int idAdministrator) {
        this.idAdministrator = idAdministrator;
    }



    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

 
    /**
     * Tries to log user in.
     * 
     * @return String Specification of web page designed to interpret result.
     */
    public String isLogin() {
         System.err.print(this.login);
        if (this.login.length() > 0) {
           
            Message m = new Message();
            Object a = administratorFacade.isLogin(this.login, this.password);
            
            if (a != null) {
                // reading attributes from user
                Administrator user = (entities.Administrator) a;
                setIdAdministrator(user.getId());
                setName(user.getName());
                m.addInfo("Přihlášení proběhlo úspěšně");

                return "books";
 
            } else {
                m.addWarning("Chybný login / heslo");
                setIdAdministrator(0);
                return "home";
            }

        }

        return "logged";
    }

    
    /**
     * Finds out wheter user is loged in.
     * 
     * @return True on is loged in, False otherwise.
     */
    public boolean userIsLogIn() {
        if (this.idAdministrator > 0) {
            return true;
        }
        return false;
    }

    /**
     * Logs user out.
     * 
     * @return String Specification of web page designed to interpret result.
     */
    public String logout() {
        this.idAdministrator = 0;
        return "home";
    }
    
    public String updateUser()
    {
        Message m = new Message();
        String result = administratorFacade.updateUser(getIdAdministrator(), getName());
        if(result.equals("ok") == true)
        {
            m.addOk();
        }
        else
        {   
            m.addWarning(result);
        }
        return "user";
    }
    
    public String updatePassword()
    {
        Message m = new Message();
        if(!getPassword().equals(getPassword2()))
        {
            m.addWarning("Rozdílná hesla");
            return "user";
        }
        
        String result = administratorFacade.updatePassword(getIdAdministrator(), getPassword());
        if(result.equals("ok") == true)
        {
            m.addOk();
        }
        else
        {   
            m.addWarning(result);
        }
        return "user";
    }    
}
