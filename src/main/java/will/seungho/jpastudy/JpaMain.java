package will.seungho.jpastudy;

import will.seungho.jpastudy.item.Movie;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		try {
			Movie movie = new Movie();
			movie.setActor("actor");
			movie.setDirector("director");
			movie.setName("name");
			movie.setPrice(100000);
			entityManager.persist(movie);

			entityManager.flush();
			entityManager.clear();

			transaction.commit();
		} catch (Exception e) {
			entityManager.close();
		} finally {
			entityManagerFactory.close();
		}
	}

}
