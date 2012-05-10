/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Genre;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author tom
 */
@Stateless
public class GenreFacade extends AbstractFacade<Genre> {
  @PersistenceContext(unitName = "PA036_-ejbPU")
  private EntityManager em;

  @Override
  protected EntityManager getEntityManager() {
    return em;
  }

  public GenreFacade() {
    super(Genre.class);
  }
  
  public String saveGenre(String genreName, String genreDescription)
  {
      Genre g = new Genre();
      g.setGenrename(genreName);
      g.setGenredescription(genreDescription);
      em.persist(g);
      return "ok";
  }

  public String updateGenre(int id, String genreName, String genreDescription)
  {

    Genre genre = em.find(Genre.class, toBigDecimal(id));
    if(!genre.getGenrename().equals(genreName))
    {
      genre.setGenrename(genreName);

    }
    if(!genre.getGenredescription().equals(genreDescription))
    {
      genre.setGenredescription(genreDescription);
    }

    em.persist(genre);
    return "ok";
  }

}
