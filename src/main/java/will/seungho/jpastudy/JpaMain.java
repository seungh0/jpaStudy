package will.seungho.jpastudy;

import will.seungho.jpastudy.member.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * EntityMangerFactory는 Application 전체에서의 단 하나만 생성.
 * EntityManger는 계속 생성 후 버려짐. (쓰레드 간에 공유해서는 절대 안된다)
 *
 * JPA의 모든 데이터 변경은 트랜잭션 내에서 실행됨.
 */
public class JpaMain {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		try {
			Member findMember1 = entityManager.find(Member.class, 100L);

			Member findMember2 = entityManager.find(Member.class, 100L);

			System.out.println(findMember1 == findMember2); // true

			/**
			 * 영속 엔티티의 동일성 보장 (1차 캐시가 있어서 가능한 사항~)
			 *
			 * 1차캐시로 반복가능한 읽기(REPEATABLE READ) 등급의 Transaction 격리 수준을
			 * 데이터베이스가 아닌 Application 차원에서 제공해줌.
			 */

			transaction.commit();
		} catch (Exception e) {
			entityManager.close();
		} finally {
			entityManagerFactory.close();
		}
	}

}
