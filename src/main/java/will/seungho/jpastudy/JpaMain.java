package will.seungho.jpastudy;

import will.seungho.jpastudy.common.Address;
import will.seungho.jpastudy.member.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
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
			 * JPQL
			 * 테이블이 아닌 객체를 대상으로 검색하는 객체지향 쿼리
			 * SQL을 추상화해서 특정 데이터베이스 SQL에 의존X
			 * JPQL을 한마디로 정의하면 객체 지향 SQL
			 */
			List<Member> members = entityManager.createQuery("select m From Member m where m.name like '%name%'", Member.class)
					.getResultList();

			for (Member findMember : members) {
				System.out.println(findMember.getName());
			}

			transaction.commit();
		} catch (Exception e) {
			entityManager.close();
			e.printStackTrace();
		} finally {
			entityManagerFactory.close();
		}
	}

}
