/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.*;
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
public class RecordbandsFacade extends AbstractFacade<Recordbands> {
  @PersistenceContext(unitName = "PA036_-ejbPU")
  private EntityManager em;

  @Override
  protected EntityManager getEntityManager() {
    return em;
  }

  public RecordbandsFacade() {
    super(Recordbands.class);
  }
  
  public void editGenres(Record record, List<String> genres)
  {
   
    Query q = em.createNamedQuery("Recordbands.remove");
    q.setParameter("id", record);
    q.executeUpdate();
    
    for(int i = 0; i < genres.size(); i++)
    {
      int id = Integer.parseInt(genres.get(i));
      Band b = em.find(Band.class, toBigDecimal(id));
      Recordbands bg = new Recordbands();
      bg.setBandid(b);
      bg.setRecordid(record);
      em.persist(bg);
    }
    System.err.print("bb" + genres.size());
  }
  
}
