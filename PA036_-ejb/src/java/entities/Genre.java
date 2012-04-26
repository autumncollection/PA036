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
@Table(name = "GENRE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Genre.findAll", query = "SELECT g FROM Genre g"),
    @NamedQuery(name = "Genre.findById", query = "SELECT g FROM Genre g WHERE g.id = :id"),
    @NamedQuery(name = "Genre.findByGenrename", query = "SELECT g FROM Genre g WHERE g.genrename = :genrename"),
    @NamedQuery(name = "Genre.findByGenredescription", query = "SELECT g FROM Genre g WHERE g.genredescription = :genredescription")})
public class Genre implements Serializable {
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
    @Column(name = "GENRENAME")
    private String genrename;
    @Size(max = 4000)
    @Column(name = "GENREDESCRIPTION")
    private String genredescription;
    @ManyToMany(mappedBy = "genreCollection")
    private Collection<Band> bandCollection;

    public Genre() {
    }

    public Genre(BigDecimal id) {
        this.id = id;
    }

    public Genre(BigDecimal id, String genrename) {
        this.id = id;
        this.genrename = genrename;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getGenrename() {
        return genrename;
    }

    public void setGenrename(String genrename) {
        this.genrename = genrename;
    }

    public String getGenredescription() {
        return genredescription;
    }

    public void setGenredescription(String genredescription) {
        this.genredescription = genredescription;
    }

    @XmlTransient
    public Collection<Band> getBandCollection() {
        return bandCollection;
    }

    public void setBandCollection(Collection<Band> bandCollection) {
        this.bandCollection = bandCollection;
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
        if (!(object instanceof Genre)) {
            return false;
        }
        Genre other = (Genre) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Genre[ id=" + id + " ]";
    }
    
}
