package will.seungho.jpastudy;

import will.seungho.jpastudy.cascade.Child;
import will.seungho.jpastudy.cascade.Parent;

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
			Child child1 = new Child("child5");
			Child child2 = new Child("child6");

			Parent parent = new Parent("parent2");
			parent.addChild(child1);
			parent.addChild(child2);

			entityManager.persist(parent);
//			entityManager.persist(child1);
//			entityManager.persist(child2);

			/**
			 * persist * 3번 하기 싫음.
			 * => CasCade
			 *
			 * CascadeType.ALL
			 * CascadeType.persist
			 *
			 * Parent만 child와 연관관계가 있을때만
			 * 사용해야함!
			 */

			transaction.commit();
		} catch (Exception e) {
			entityManager.close();
			e.printStackTrace();
		} finally {
			entityManagerFactory.close();
		}
	}

}
