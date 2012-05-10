/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
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
@Table(name = "RECORD")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Record.findAll", query = "SELECT r FROM Record r"),
  @NamedQuery(name = "Record.findById", query = "SELECT r FROM Record r WHERE r.id = :id"),
  @NamedQuery(name = "Record.findByBand", query = "SELECT r FROM Record r, Recordbands rb WHERE r.id = rb.bandid AND r.id = :band"),
  @NamedQuery(name = "Record.findByRecordname", query = "SELECT r FROM Record r WHERE r.recordname = :recordname"),
  @NamedQuery(name = "Record.findByRecorddescription", query = "SELECT r FROM Record r WHERE r.recorddescription = :recorddescription"),
  @NamedQuery(name = "Record.findByProduced", query = "SELECT r FROM Record r WHERE r.produced = :produced")})
public class Record implements Serializable {
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
  @Column(name = "RECORDNAME")
  private String recordname;
  @Size(max = 4000)
  @Column(name = "RECORDDESCRIPTION")
  private String recorddescription;
  @Basic(optional = false)
  @NotNull
  @Column(name = "PRODUCED")
  @Temporal(TemporalType.TIMESTAMP)
  private Date produced;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "recordid")
  private Collection<Recordbands> recordbandsCollection;

  public Record() {
  }

  public Record(BigDecimal id) {
    this.id = id;
  }

  public Record(BigDecimal id, String recordname, Date produced) {
    this.id = id;
    this.recordname = recordname;
    this.produced = produced;
  }

  public BigDecimal getId() {
    return id;
  }

  public void setId(BigDecimal id) {
    this.id = id;
  }

  public String getRecordname() {
    return recordname;
  }

  public void setRecordname(String recordname) {
    this.recordname = recordname;
  }

  public String getRecorddescription() {
    return recorddescription;
  }

  public void setRecorddescription(String recorddescription) {
    this.recorddescription = recorddescription;
  }

  public Date getProduced() {
    return produced;
  }

  public void setProduced(Date produced) {
    this.produced = produced;
  }

  @XmlTransient
  public Collection<Recordbands> getRecordbandsCollection() {
    return recordbandsCollection;
  }

  public void setRecordbandsCollection(Collection<Recordbands> recordbandsCollection) {
    this.recordbandsCollection = recordbandsCollection;
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
    if (!(object instanceof Record)) {
      return false;
    }
    Record other = (Record) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "entities.Record[ id=" + id + " ]";
  }
  
}
