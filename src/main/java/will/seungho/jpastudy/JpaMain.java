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

			entityManager.flush();
			entityManager.clear();

			Parent findParent = entityManager.find(Parent.class, parent.getId());
			findParent.getChildren().remove(0); // Case 1.  Child 필드가 고아 객체가 되어서 삭제된다.

			// Case 2. Childs 모두 제거된다.
			entityManager.remove(findParent);

			transaction.commit();
		} catch (Exception e) {
			entityManager.close();
			e.printStackTrace();
		} finally {
			entityManagerFactory.close();
		}
	}

}
