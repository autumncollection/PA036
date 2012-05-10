/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Band;
import entities.Bandgenres;
import entities.Genre;
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
public class BandgenresFacade extends AbstractFacade<Bandgenres> {
  @PersistenceContext(unitName = "PA036_-ejbPU")
  private EntityManager em;

  @Override
  protected EntityManager getEntityManager() {
    return em;
  }

  public BandgenresFacade() {
    super(Bandgenres.class);
  }
  
  public void editGenres(Band band, List<String> genres)
  {
   
    Query q = em.createNamedQuery("Bandgenres.remove");
    q.setParameter("id", band);
    q.executeUpdate();
    
    for(int i = 0; i < genres.size(); i++)
    {
      int id = Integer.parseInt(genres.get(i));
      Genre g = em.find(Genre.class, toBigDecimal(id));
      Bandgenres bg = new Bandgenres();
      bg.setBandid(band);
      bg.setGenreid(g);
      em.persist(bg);
    }
    System.err.print("bb" + genres.size());
  }
  
}
