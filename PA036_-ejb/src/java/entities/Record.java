/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
    @NamedQuery(name = "Record.findByBandid", query = "SELECT r FROM Record r WHERE r.bandid = :bandid"),
    @NamedQuery(name = "Record.findByRecordname", query = "SELECT r FROM Record r WHERE r.recordname = :recordname"),
    @NamedQuery(name = "Record.findByRecorddescription", query = "SELECT r FROM Record r WHERE r.recorddescription = :recorddescription"),
    @NamedQuery(name = "Record.findByProduced", query = "SELECT r FROM Record r WHERE r.produced = :produced")})
public class Record implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRODUCED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date produced;
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BANDID")
    private BigInteger bandid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "RECORDNAME")
    private String recordname;
    @Size(max = 4000)
    @Column(name = "RECORDDESCRIPTION")
    private String recorddescription;
    @ManyToMany(mappedBy = "recordCollection")
    private Collection<Band> bandCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recordid")
    private Collection<Track> trackCollection;

    public Record() {
    }

    public Record(BigDecimal id) {
        this.id = id;
    }

    public Record(BigDecimal id, BigInteger bandid, String recordname, Date produced) {
        this.id = id;
        this.bandid = bandid;
        this.recordname = recordname;
        this.produced = produced;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigInteger getBandid() {
        return bandid;
    }

    public void setBandid(BigInteger bandid) {
        this.bandid = bandid;
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

    @XmlTransient
    public Collection<Band> getBandCollection() {
        return bandCollection;
    }

    public void setBandCollection(Collection<Band> bandCollection) {
        this.bandCollection = bandCollection;
    }

    @XmlTransient
    public Collection<Track> getTrackCollection() {
        return trackCollection;
    }

    public void setTrackCollection(Collection<Track> trackCollection) {
        this.trackCollection = trackCollection;
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
        return "entity.Record[ id=" + id + " ]";
    }

    public Date getProduced() {
        return produced;
    }

    public void setProduced(Date produced) {
        this.produced = produced;
    }
    
}
