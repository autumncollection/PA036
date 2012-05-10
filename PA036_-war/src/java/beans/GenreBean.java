/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import entities.Genre;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.ejb.EJB;

/**
 *
 * @author tom
 */
@ManagedBean(name = "genre")
@SessionScoped
public class GenreBean {

    @EJB
    public GenreFacade genre;

    private String genreName;
    private String genreDescription;
    private int id;
    private List<Genre> genres;

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public String getGenreDescription() {
        return genreDescription;
    }

    public void setGenreDescription(String genredescription) {
        this.genreDescription = genredescription;
    }

    public int getId() {
      return id;
    }

    public void setId(int id) {
      this.id = id;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public String insertGenre()
    {
        String status = genre.saveGenre(getGenreName(), getGenreDescription());
        return "genres";
    }

    public List<Genre> allGenres() {
        return genre.findAll();
    }

    public String getGenre(Genre genre)
    {
      genreName = genre.getGenrename();
      genreDescription = genre.getGenredescription();
      setId(genre.getId().intValue());
      return "editGenre";
    }

    public String deleteGenre(Genre g)
    {
      Message m = new Message();

      genre.remove(g);
      m.addOk();
      return "genres";
    }

    public String updateGenre(int id)
    {
      Message m = new Message();

      if(id > 0)
      {
        String status = genre.updateGenre(
                id,
                getGenreName(),
                getGenreDescription()
        );

      m.addOk();
      }
      return "genres";
     
    }
}
