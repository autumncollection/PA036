/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import beans.MusicianFacade;
import entities.Band;
import entities.Musician;
import java.util.Collection;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author obojzivelnik
 */
@ManagedBean (name="musicianBean")
@RequestScoped
public class MusicianView {
    @EJB
    private MusicianFacade musicianFacade;
   
    private Musician musician;

    public Musician getMusician() {
      return musician;
    }

    public void setMusician(Musician musician) {
      this.musician = musician;
    }

    public MusicianFacade getMusicianFacade() {
      return musicianFacade;
    }

    public void setMusicianFacade(MusicianFacade musicianFacade) {
      this.musicianFacade = musicianFacade;
    }

    
    
    /**
     * Creates a new instance of MusicianView
     */
    public MusicianView() {
      this.musician = new Musician();
    }

    public Collection<Musician> findAllMusician()
    {
      return musicianFacade.findAll();
    }
    
    public String saveRecord()
    {
      Message m = new Message();
      m.addOk();
      musicianFacade.create(musician);
      return "bands";
    }
    
    public Collection<Musician> getMusician(Band band)
    {
      return musicianFacade.ff(band);
    }
}
