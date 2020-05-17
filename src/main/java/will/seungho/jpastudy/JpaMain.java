package will.seungho.jpastudy;

import org.hibernate.Hibernate;
import will.seungho.jpastudy.member.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * 프록시 기초
 *
 * em.find() vs em.getReference()
 *
 * em.find() => 데이터베이스를 통해서 실제 엔티티 객체를 조회
 * em.getReference() => 데이터 베이스 조회를 미루는 가짜(프록시) 엔티티 객체 조회
 */

public class JpaMain {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		try {
			Member member = Member.builder()
					.name("name")
					.build();
			entityManager.persist(member);

			entityManager.flush();
			entityManager.clear();

//			Member member1 = entityManager.getReference(Member.class, member.getId());
//			System.out.println(member1.getClass());
//
//			Member reference = entityManager.getReference(Member.class, member1.getId());
//			System.out.println(reference.getClass());
//
//			System.out.println(member1 == reference); // 같은 프록시

			Member member1 = entityManager.getReference(Member.class, member.getId());
			System.out.println(member1.getClass()); // 프록시

			Hibernate.initialize(member1); // 강제 초기화

			entityManager.detach(member1); // or entityManger.close()
			// 준영속 상태를 만듬

			System.out.println(member1.getName());
			//org.hibernate.LazyInitializationException: could not initialize proxy [will.seungho.jpastudy.member.Member#30] - no Session

			/**
			 * 영속성 컨택스트의 도움을 받을 수 없는 준 영속 상태일 경우
			 * LazyInitalizationException 예외를 터트림!
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
