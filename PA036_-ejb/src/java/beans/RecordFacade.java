/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Band;
import entities.Record;
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
public class RecordFacade extends AbstractFacade<Record> {
    @PersistenceContext(unitName = "PA036_-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RecordFacade() {
        super(Record.class);
    }
    
    public void insert(Record record)
    {
      try
      {
        em.persist(record);
        em.flush();
      } catch(Exception e)
      {
        
      }
    }
    
    public List<Record> findByBand(Band band)
    {
      Query q = em.createNamedQuery("Record.findByBand");
      q.setParameter("band", band.getId());
      List<Record> result = q.getResultList();
      return result;
    }
    
}
