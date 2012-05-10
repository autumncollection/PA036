/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Band;
import entities.Musician;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author tom
 */
@Stateless
public class MusicianFacade extends AbstractFacade<Musician> {
    @PersistenceContext(unitName = "PA036_-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MusicianFacade() {
        super(Musician.class);
    }

    public Collection<Musician> ff(Band band)
    {
      System.err.print(band.getId());
      Query q = em.createQuery("SELECT t1.musicianid FROM Bandmembership t1 WHERE (t1.bandid = 22))");
      //q.setParameter("id", band.getId());
      return q.getResultList();
    }
    
}
