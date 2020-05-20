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

			entityManager.persist(parent); // Parent만 persist()함

			entityManager.flush();
			entityManager.clear();

			Parent findParent = entityManager.find(Parent.class, parent.getId());
			entityManager.remove(findParent); // Parent만 remove()함ㄴ

			/**
			 * CasCade.ALL + orphanRemove = true 둘다 설정하면
			 * 1. Child의 생명주기를 Partent가 관리하게 된다. (Child repository가 필요 없음)
			 * 2. 부모 엔티티를 통해서 자식의 생명 주기를 관리할 수 있다.
			 * 3. DDD의 Aggregate Root 개념을 구현할때 유용하다.
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
