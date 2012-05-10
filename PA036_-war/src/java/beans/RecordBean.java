/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import entities.Band;
import entities.Record;
import entities.Track;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
/**
 *
 * @author tom
 */
@ManagedBean(name = "recordBean")
@SessionScoped
public class RecordBean {

  @EJB
  private RecordFacade recordFacade;
  
  @EJB
  private RecordbandsFacade recordBandsFacade;
  
  @EJB
  private TrackFacade trackFacade;
  
  private Record record;
  private String name;
  private Track track;
  private Collection<Band> bandCollection;
  private List<String> recordBand;

  public Track getTrack() {
    return track;
  }

  public void setTrack(Track track) {
    this.track = track;
  }
  
  
  
  public String getName() {
    return name;
  }

  public List<String> getRecordBand() {
    return recordBand;
  }

  public void setRecordBand(List<String> recordBand) {
    this.recordBand = recordBand;
  }
  
  public void setName(String name) {
    this.name = name;
  }

  public Collection<Band> getBandCollection() {
    return bandCollection;
  }

  public void setBandCollection(Collection<Band> bandCollection) {
    this.bandCollection = bandCollection;
  }
  
  
  
  public RecordBean ()
  {
    record = new Record();
    track = new Track();
  }

  public RecordFacade getBandFacade() {
    return recordFacade;
  }

  public void setBandFacade(RecordFacade bandFacade) {
    this.recordFacade = bandFacade;
  }

  public Record getRecord() {
    return record;
  }

  public void setRecord(Record record) {
    record = record;
  }
  
  public String saveRecord()
  {
    Message m = new Message();
    m.addOk();
    recordFacade.insert(record);
    Record record_ = recordFacade.findAll().get(recordFacade.count() - 1);
    recordBandsFacade.editGenres(record_, recordBand);
    return "albums"; 
  }
    
  public Collection<Record> getRecords(Band band)
  {
    return band.getRecordCollection();
  }
  
  public Collection<Record> findAll()
  {
    return recordFacade.findAll();
  }
  
  public String getDetail(Record record)
  {
    this.record = record;
    return "detailRecord";
  }
  
  public String delete(Record record)
  {
    Message m = new Message();
    m.addOk();
    recordFacade.remove(record);
    return "albums";
  }
  
  public void addTrack()
  {
    Message m = new Message();
    m.addOk();
    trackFacade.insert(record, track);
  }
  
  public Collection<Track> tracks()
  {
    return trackFacade.get(record);
  }
  
  public void deleteTrack(Track track)
  {
    Message m = new Message();
    m.addOk();
    trackFacade.remove(track);
    
  }
}
