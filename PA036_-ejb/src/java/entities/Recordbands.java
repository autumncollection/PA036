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
@Table(name = "RECORDBANDS")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Recordbands.findAll", query = "SELECT r FROM Recordbands r"),
  @NamedQuery(name = "Recordbands.findById", query = "SELECT r FROM Recordbands r WHERE r.id = :id"),
  @NamedQuery(name = "Recordbands.remove", query = "DELETE FROM Recordbands b WHERE b.recordid = :id")
})
public class Recordbands implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @NotNull
  @Column(name = "ID")
  private Integer id;
  @JoinColumn(name = "RECORDID", referencedColumnName = "ID")
  @ManyToOne(optional = false)
  private Record recordid;
  @JoinColumn(name = "BANDID", referencedColumnName = "ID")
  @ManyToOne(optional = false)
  private Band bandid;

  public Recordbands() {
  }

  public Recordbands(Integer id) {
    this.id = id;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Record getRecordid() {
    return recordid;
  }

  public void setRecordid(Record recordid) {
    this.recordid = recordid;
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
    if (!(object instanceof Recordbands)) {
      return false;
    }
    Recordbands other = (Recordbands) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "entities.Recordbands[ id=" + id + " ]";
  }
  
}
