/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author tom
 */
@Entity
@Table(name = "MUSICIAN")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Musician.findAll", query = "SELECT m FROM Musician m"),
  @NamedQuery(name = "Musician.findById", query = "SELECT m FROM Musician m WHERE m.id = :id"),
  @NamedQuery(name = "Musician.findBy", query = "SELECT m FROM Musician m WHERE m IN ( SELECT b.musicianid FROM Bandmembership b WHERE b.bandid = :id)"),
  @NamedQuery(name = "Musician.findByMusicianname", query = "SELECT m FROM Musician m WHERE m.musicianname = :musicianname"),
  @NamedQuery(name = "Musician.findByMusiciandescription", query = "SELECT m FROM Musician m WHERE m.musiciandescription = :musiciandescription")})
public class Musician implements Serializable {
  private static final long serialVersionUID = 1L;
  // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
  @Id
  @Basic(optional = false)
  @NotNull
  @Column(name = "ID")
  private BigDecimal id;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 100)
  @Column(name = "MUSICIANNAME")
  private String musicianname;
  @Size(max = 4000)
  @Column(name = "MUSICIANDESCRIPTION")
  private String musiciandescription;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "musicianid")
  private Collection<Bandmembership> bandmembershipCollection;

  public Musician() {
  }

  public Musician(BigDecimal id) {
    this.id = id;
  }

  public Musician(BigDecimal id, String musicianname) {
    this.id = id;
    this.musicianname = musicianname;
  }

  public BigDecimal getId() {
    return id;
  }

  public void setId(BigDecimal id) {
    this.id = id;
  }

  public String getMusicianname() {
    return musicianname;
  }

  public void setMusicianname(String musicianname) {
    this.musicianname = musicianname;
  }

  public String getMusiciandescription() {
    return musiciandescription;
  }

  public void setMusiciandescription(String musiciandescription) {
    this.musiciandescription = musiciandescription;
  }

  @XmlTransient
  public Collection<Bandmembership> getBandmembershipCollection() {
    return bandmembershipCollection;
  }

  public void setBandmembershipCollection(Collection<Bandmembership> bandmembershipCollection) {
    this.bandmembershipCollection = bandmembershipCollection;
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
    if (!(object instanceof Musician)) {
      return false;
    }
    Musician other = (Musician) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "entities.Musician[ id=" + id + " ]";
  }
  
}
