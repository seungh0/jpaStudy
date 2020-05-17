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

			Member member2 = Member.builder()
					.name("name2")
					.build();
			entityManager.persist(member2);

			entityManager.flush();
			entityManager.clear();

			Member findMember = entityManager.getReference(Member.class, member.getId());
			System.out.println(findMember.getClass());
			// class will.seungho.jpastudy.member.Member$HibernateProxy$B1yNbYc7
			// 프록시 클래스

			System.out.println(findMember.getId());
			System.out.println(findMember.getName()); // => 실제 데이터를 사용하는 경우 SELECT 쿼리가 나감
			System.out.println(findMember.getClass()); // 프록시 객체가 변경되지 않음

//			Member findMember2 = entityManager.find(Member.class, member2.getId());
//			System.out.println("m1 == m2" + (findMember.getClass() == findMember2.getClass())); // true

			Member findMember2 = entityManager.find(Member.class, member2.getId());
			System.out.println("m1 == m2" + (findMember.getClass() == findMember2.getClass())); // false

			/**
			 * 절대 == 으로 비교하면 안된다
			 * 프록시 객체인지 실제 엔티티 객체인지 모르니깐~
			 */
			System.out.println("m1 == m2" + (findMember instanceof Member)); // true
			System.out.println("m1 == m2" + (findMember2 instanceof Member)); // true

			/**
			 *
			 */
			System.out.println(findMember2.getClass());
			Member reference = entityManager.getReference(Member.class, member2.getId()); // 실제 엔티티 클래스
			System.out.println(reference.getClass());  // 실제 엔티티 클래스

			/**
			 * WHY 프록시 객체가 아닌가?
			 * 1. 이미 실제 엔티티 원본이 있는데 굳이 프록시를 만들 필요 없음.
			 * 2. JPA에서는 실제 엔티티든 프록시든 같은 영속성 컨텍스트에서는 항상 동일함.(==)
			 *   JPA는 한 트랜잭션에서 동일성를 보장해줌
			 */

			/**
			 * Proxy 내부는
			 * 껍대기는 같은데, 안의 데이터는 비어있음.
			 * 내부에 실제 객체의 참조(target)를 보관하고 있음. => 프록시 객체를 호출하면 프록시 객체는 실제 객체의 메소드를 호출.
			 *
			 * 프록시의 특징
			 *
			 * 실제 클래스를 상속받아서 만들어짐.
			 * 따라서 실제 클래스와 겉 모양이 같다 (하이버 네이트 내부적으로 여러 프록시 라이브러리를 사용해서)
			 *
			 * 프록시 객체는 처음 사용할때 한번만 초기화된다.
			 * 프록시 객체를 초기화 할때, 프록시 객체가 실제 엔티티로 바뀌는 것이 아님, 초기화 되면 프록시 객체를 통해
			 * 실제 엔티티에 접근이 가능.
			 *
			 * => 프록시 객체는 원본 엔티티를 상속받음, 따라서 타입 체크시 주의해야함 (== 비교 실패, 대신 instance of 사용)
			 */

			transaction.commit();
		} catch (Exception e) {
			entityManager.close();
		} finally {
			entityManagerFactory.close();
		}
	}

}
