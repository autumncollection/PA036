/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
@Table(name = "BAND")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Band.findAll", query = "SELECT b FROM Band b"),
    @NamedQuery(name = "Band.findById", query = "SELECT b FROM Band b WHERE b.id = :id"),
    @NamedQuery(name = "Band.findByGenreid", query = "SELECT b FROM Band b WHERE b.genreid = :genreid"),
    @NamedQuery(name = "Band.findByBandname", query = "SELECT b FROM Band b WHERE b.bandname = :bandname"),
    @NamedQuery(name = "Band.findByBanddescription", query = "SELECT b FROM Band b WHERE b.banddescription = :banddescription")})
public class Band implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "GENREID")
    private BigInteger genreid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "BANDNAME")
    private String bandname;
    @Size(max = 4000)
    @Column(name = "BANDDESCRIPTION")
    private String banddescription;
    @JoinTable(name = "RECORDBANDS", joinColumns = {
        @JoinColumn(name = "BANDID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "RECORDID", referencedColumnName = "ID")})
    @ManyToMany
    private Collection<Record> recordCollection;
    @JoinTable(name = "BANDGENRES", joinColumns = {
        @JoinColumn(name = "BANDID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "GENREID", referencedColumnName = "ID")})
    @ManyToMany
    private Collection<Genre> genreCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "band")
    private Bandmembership bandmembership;

    public Band() {
    }

    public Band(BigDecimal id) {
        this.id = id;
    }

    public Band(BigDecimal id, BigInteger genreid, String bandname) {
        this.id = id;
        this.genreid = genreid;
        this.bandname = bandname;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigInteger getGenreid() {
        return genreid;
    }

    public void setGenreid(BigInteger genreid) {
        this.genreid = genreid;
    }

    public String getBandname() {
        return bandname;
    }

    public void setBandname(String bandname) {
        this.bandname = bandname;
    }

    public String getBanddescription() {
        return banddescription;
    }

    public void setBanddescription(String banddescription) {
        this.banddescription = banddescription;
    }

    @XmlTransient
    public Collection<Record> getRecordCollection() {
        return recordCollection;
    }

    public void setRecordCollection(Collection<Record> recordCollection) {
        this.recordCollection = recordCollection;
    }

    @XmlTransient
    public Collection<Genre> getGenreCollection() {
        return genreCollection;
    }

    public void setGenreCollection(Collection<Genre> genreCollection) {
        this.genreCollection = genreCollection;
    }

    public Bandmembership getBandmembership() {
        return bandmembership;
    }

    public void setBandmembership(Bandmembership bandmembership) {
        this.bandmembership = bandmembership;
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
        if (!(object instanceof Band)) {
            return false;
        }
        Band other = (Band) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Band[ id=" + id + " ]";
    }
    
}
