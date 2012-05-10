/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Band;
import entities.Bandmembership;
import entities.Musician;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author tom
 */
@Stateless
public class BandmembershipFacade extends AbstractFacade<Bandmembership> {
    @PersistenceContext(unitName = "PA036_-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BandmembershipFacade() {
        super(Bandmembership.class);
    }
    
     public void editGenres(Band band, List<String> genres)
  {
   
    Query q = em.createNamedQuery("Bandmusicians.remove");
    q.setParameter("id", band.getId());
    q.executeUpdate();
    
    for(int i = 0; i < genres.size(); i++)
    {
      int id = Integer.parseInt(genres.get(i));
      Musician g = em.find(Musician.class, toBigDecimal(id));
      Bandmembership bg = new Bandmembership();
      bg.setBand(band);
      bg.setBandid(band.getId());
      bg.setMusicianid(g);
      em.persist(bg);
    }
    System.err.print("bb" + genres.size());
  }
    
}
