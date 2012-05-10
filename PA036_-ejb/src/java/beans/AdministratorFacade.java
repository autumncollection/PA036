/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Administrator;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.ejb.Stateless;
import javax.persistence.*;
import javax.validation.ValidationException;

/**
 *
 * @author tom
 */
@Stateless
public class AdministratorFacade extends AbstractFacade<Administrator> {
    @PersistenceContext(unitName = "PA036_-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdministratorFacade() {
        super(Administrator.class);
    }
    
    /**
     * Determines wheather is user logged in, or not.
     * 
     * @param login Login of user.
     * @param password Password of user.
     * @return Object Logged user or null.
     */
    public Object isLogin(String login, String password) {
        try {
            Query q = em.createNamedQuery("Administrator.findByLoginPassword");
            q.setParameter("login", login);
            q.setParameter("passwordhash", hash(password));
            Administrator user = (Administrator) q.getSingleResult();
            

            if (user.getId() < 1) {
                return null;
            }

            return user;
        } catch (NoResultException e) {
            return null;
        }
    }
    
    public String updateUser(int idUser, String newName) {
        try {
            BigDecimal i = new BigDecimal(idUser);
            // prepare query
            Administrator user = em.find(Administrator.class, i);
            // changing variables
            if (!user.getName().equals(newName)) {
                user.setName(newName);
            }
            em.flush();

        } catch (ValidationException ex) {
            return ex.getLocalizedMessage();
        }

        return "ok";
    }
    
    public String updatePassword(int idUser, String password) {
        try {
            Administrator u = em.find(Administrator.class, toBigDecimal(idUser));
            
            password = hash(password);
            if(!u.getPasswordhash().equals(password))
            {
                u.setPasswordhash(password);
            }
            em.flush();

        } catch (PersistenceException e) {
            System.err.print(e.getLocalizedMessage());
            return "false";
        }
        return "ok";
    }    
   
    /*
     * Hashes text into MD5 cipher.
     * 
     * @param String Input string.
     * @return String MD5 cipher of input text.
     */
    private String hash(String s) {
        try {

            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(s.getBytes(), 0, s.length());
            return new BigInteger(1, m.digest()).toString(16);
        } catch (NoSuchAlgorithmException ex) {
            
        }

        return "";
    }    
}
