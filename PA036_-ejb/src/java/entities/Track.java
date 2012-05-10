/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tom
 */
@Entity
@Table(name = "TRACK")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Track.findAll", query = "SELECT t FROM Track t"),
  @NamedQuery(name = "Track.find", query = "SELECT t FROM Track t WHERE t.recordid = :id"),
  @NamedQuery(name = "Track.findById", query = "SELECT t FROM Track t WHERE t.id = :id"),
  @NamedQuery(name = "Track.findByTrackname", query = "SELECT t FROM Track t WHERE t.trackname = :trackname"),
  @NamedQuery(name = "Track.findByTrackdescription", query = "SELECT t FROM Track t WHERE t.trackdescription = :trackdescription")})
public class Track implements Serializable {
  private static final long serialVersionUID = 1L;
  // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
  @Id
  @Basic(optional = false)
  @NotNull
  @Column(name = "ID")
  private BigDecimal id;
  @Size(max = 100)
  @Column(name = "TRACKNAME")
  private String trackname;
  @Size(max = 4000)
  @Column(name = "TRACKDESCRIPTION")
  private String trackdescription;
  @JoinColumn(name = "RECORDID", referencedColumnName = "ID")
  @ManyToOne(optional = false)
  private Record recordid;

  public Track() {
  }

  public Track(BigDecimal id) {
    this.id = id;
  }

  public BigDecimal getId() {
    return id;
  }

  public void setId(BigDecimal id) {
    this.id = id;
  }

  public String getTrackname() {
    return trackname;
  }

  public void setTrackname(String trackname) {
    this.trackname = trackname;
  }

  public String getTrackdescription() {
    return trackdescription;
  }

  public void setTrackdescription(String trackdescription) {
    this.trackdescription = trackdescription;
  }


  public Record getRecordid() {
    return recordid;
  }

  public void setRecordid(Record recordid) {
    this.recordid = recordid;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (id != null ? id.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Track)) {
      return false;
    }
    Track other = (Track) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "entities.Track[ id=" + id + " ]";
  }
  
}
