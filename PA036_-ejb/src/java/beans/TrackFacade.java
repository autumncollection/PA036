/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Record;
import entities.Track;
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
public class TrackFacade extends AbstractFacade<Track> {
    @PersistenceContext(unitName = "PA036_-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TrackFacade() {
        super(Track.class);
    }
    
    public void insert(Record record, Track track)
    {
      track.setRecordid(record);
      em.persist(track);
    }
    
    public Collection<Track> get(Record record)
    {
      Query q = em.createNamedQuery("Track.find");
      q.setParameter("id", record);
      return q.getResultList();
    }
}
