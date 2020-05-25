package will.seungho.jpastudy;

import will.seungho.jpastudy.common.Address;
import will.seungho.jpastudy.member.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class JpaMain {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		try {
			Member member = Member.builder()
					.name("name")
					.workAddress(new Address("city", "street", "zipCode"))
					.build();
			entityManager.persist(member);

			/**
			 * Criteria
			 */
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Member> query = cb.createQuery(Member.class);
			Root<Member> m = query.from(Member.class);
			CriteriaQuery<Member> cq = query.select(m).where(cb.equal(m.get("name"), "kim"));

			List<Member> members = entityManager.createQuery(cq)
					.getResultList();

			transaction.commit();
		} catch (Exception e) {
			entityManager.close();
			e.printStackTrace();
		} finally {
			entityManagerFactory.close();
		}
	}

}
