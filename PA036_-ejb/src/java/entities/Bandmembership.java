/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tom
 */
@Entity
@Table(name = "BANDMEMBERSHIP")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Bandmembership.findAll", query = "SELECT b FROM Bandmembership b"),
  @NamedQuery(name = "Bandmusicians.remove", query= "DELETE FROM Bandmembership b WHERE b.bandid = :id"),
  @NamedQuery(name = "Bandmembership.findByBandid", query = "SELECT b FROM Bandmembership b WHERE b.bandid = :bandid"),
  @NamedQuery(name = "Bandmembership.findBySince", query = "SELECT b FROM Bandmembership b WHERE b.since = :since"),
  @NamedQuery(name = "Bandmembership.findByEnd", query = "SELECT b FROM Bandmembership b WHERE b.end = :end")})
public class Bandmembership implements Serializable {
  private static final long serialVersionUID = 1L;
  // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
  @Id
  @Basic(optional = false)
  @NotNull
  @Column(name = "BANDID")
  private BigDecimal bandid;
  @Basic(optional = false)
  @Column(name = "SINCE")
  @Temporal(TemporalType.TIMESTAMP)
  private Date since;
  @Column(name = "END")
  @Temporal(TemporalType.TIMESTAMP)
  private Date end;
  @JoinColumn(name = "MUSICIANID", referencedColumnName = "ID")
  @ManyToOne(optional = false)
  private Musician musicianid;
  @JoinColumn(name = "BANDID", referencedColumnName = "ID", insertable = false, updatable = false)
  @OneToOne(optional = false)
  private Band band;

  public Bandmembership() {
  }

  public Bandmembership(BigDecimal bandid) {
    this.bandid = bandid;
  }

  public Bandmembership(BigDecimal bandid, Date since) {
    this.bandid = bandid;
    this.since = since;
  }

  public BigDecimal getBandid() {
    return bandid;
  }

  public void setBandid(BigDecimal bandid) {
    this.bandid = bandid;
  }

  public Date getSince() {
    return since;
  }

  public void setSince(Date since) {
    this.since = since;
  }

  public Date getEnd() {
    return end;
  }

  public void setEnd(Date end) {
    this.end = end;
  }

  public Musician getMusicianid() {
    return musicianid;
  }

  public void setMusicianid(Musician musicianid) {
    this.musicianid = musicianid;
  }

  public Band getBand() {
    return band;
  }

  public void setBand(Band band) {
    this.band = band;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (bandid != null ? bandid.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Bandmembership)) {
      return false;
    }
    Bandmembership other = (Bandmembership) object;
    if ((this.bandid == null && other.bandid != null) || (this.bandid != null && !this.bandid.equals(other.bandid))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "entities.Bandmembership[ bandid=" + bandid + " ]";
  }
  
}
