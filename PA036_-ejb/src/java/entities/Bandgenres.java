/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tom
 */
@Entity
@Table(name = "BANDGENRES")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Bandgenres.findAll",   query = "SELECT b FROM Bandgenres b"),
  @NamedQuery(name = "Bandgenres.findById",   query = "SELECT b FROM Bandgenres b WHERE b.id = :id"),
  @NamedQuery(name = "Bandgenres.findByBand", query = "SELECT b FROM Bandgenres b WHERE b.id = :id"),
  @NamedQuery(name = "Bandgenres.remove", query= "DELETE FROM Bandgenres b WHERE b.bandid = :id")})

public class Bandgenres implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @NotNull
  @Column(name = "ID")
  private Integer id;
  @JoinColumn(name = "GENREID", referencedColumnName = "ID")
  @ManyToOne(optional = false)
  private Genre genreid;
  @JoinColumn(name = "BANDID", referencedColumnName = "ID")
  @ManyToOne(optional = false)
  private Band bandid;

  public Bandgenres() {
  }

  public Bandgenres(Integer id) {
    this.id = id;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Genre getGenreid() {
    return genreid;
  }

  public void setGenreid(Genre genreid) {
    this.genreid = genreid;
  }

  public Band getBandid() {
    return bandid;
  }

  public void setBandid(Band bandid) {
    this.bandid = bandid;
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
    if (!(object instanceof Bandgenres)) {
      return false;
    }
    Bandgenres other = (Bandgenres) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "entities.Bandgenres[ id=" + id + " ]";
  }
  
}
