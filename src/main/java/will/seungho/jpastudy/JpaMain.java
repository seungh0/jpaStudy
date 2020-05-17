package will.seungho.jpastudy;

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

			/**
			 * 	em.find()
			 */
//			Member findMember = entityManager.find(Member.class, member.getId());
//			System.out.println(findMember.getName());

			/**
			 * em.getReference()
			 */
//			entityManager.getReference(Member.class, member.getId());  // => SELECT 쿼리가 안나감!

			Member findMember = entityManager.getReference(Member.class, member.getId());
			System.out.println(findMember.getClass());
			// class will.seungho.jpastudy.member.Member$HibernateProxy$B1yNbYc7
			// 프록시 클래스

			System.out.println(findMember.getId());
			System.out.println(findMember.getName()); // => 실제 데이터를 사용하는 경우 SELECT 쿼리가 나감

			/**
			 * id 호출 후 (id는 있어서)
			 * name의 실제 데이터 값이 없으니깐
			 * SELECT 쿼리를 해서 데이터를 가져옴
			 */

			transaction.commit();
		} catch (Exception e) {
			entityManager.close();
		} finally {
			entityManagerFactory.close();
		}
	}

}
