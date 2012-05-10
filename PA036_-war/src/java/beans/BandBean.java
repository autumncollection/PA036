/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import entities.Band;
import entities.Bandgenres;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;



/**
 *
 * @author tom
 */
@ManagedBean(name = "band")
@SessionScoped
public class BandBean {

    @EJB
    private BandFacade bandFacade;
    private Band band;
    @EJB
    private BandgenresFacade bandGenres;
    @EJB
    private BandmembershipFacade memberFacade;
    
    private Bandgenres bg;
    
    public List<String> genresList;
    public List<String> musicianList;
    /**
     * Creates a new instance of BandView
     */
    public BandBean() {
        this.band = new Band();
    }

    public Bandgenres getBg() {
      return bg;
    }

  public List<String> getMusicianList() {
    return musicianList;
  }

  public void setMusicianList(List<String> musicianList) {
    this.musicianList = musicianList;
  }

    public void setBg(Bandgenres bg) {
      this.bg = bg;
    }
    
    public Band getBand() {
        return band;
    }

    public String detail(Band bands)
    {
      this.band = bands;
      return "bandDetail";
    }
    
    public BandFacade getBandFacade() {
      return bandFacade;
    }

    public void setBandFacade(BandFacade bandFacade) {
      this.bandFacade = bandFacade;
    }

    public BandgenresFacade getBandGenres() {
      return bandGenres;
    }

    public void setBandGenres(BandgenresFacade bandGenres) {
      this.bandGenres = bandGenres;
    }

    public List<String> getGenresList() {
      return genresList;
    }

    public void setGenresList(List<String> genresList) {
      this.genresList = genresList;
    }
    
    public int getNumberOfBands() {
        return bandFacade.count();
    }
    public String saveBand() {
      Message m = new Message();
      m.addOk();
      bandFacade.create(band);
      return "bands";
    }

    public List<Band> allBands() {
      return bandFacade.findAll();
    }

    public BigDecimal getID() {
        return this.band.getId();
    }

    public String getBand(Band band)
    {
      this.band = band;
      return "editBand";
    }

    public String updateBand()
    {
      Message m = new Message();
      m.addOk();
      bandFacade.edit(band);
      bandGenres.editGenres(band, genresList);
      memberFacade.editGenres(band, musicianList);
      return "bands";
    }
    
    public String deleteBand(Band band)
    {
      Message m = new Message();
      m.addOk();
      bandFacade.remove(band);
      return "bands";
    }
    
    public String saveMusicians(Band band_)
    {

      Message m = new Message();
      m.addOk();
      return "";
    }
}
