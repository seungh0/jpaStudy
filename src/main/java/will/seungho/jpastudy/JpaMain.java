package will.seungho.jpastudy;

import will.seungho.jpastudy.member.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

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
			// 비영속
			Member member = new Member();
			member.setId(100L);
			member.setName("Will");

			// 영속
			entityManager.persist(member);
			// 이때 DB에 저장되지 않고, 트랜잭션 커밋시 DB에 실제로 저장이 된다!

			Member findMember = entityManager.find(Member.class, 100L);
			System.out.println(findMember.getId());
			System.out.println(findMember.getName());
			/**
			 * SELECT 쿼리가 안나감!
			 * 왜냐 => 1차 캐시에 저장되서 1차 캐쉬에 있는것을 가져오기 때문에!
			 */

			transaction.commit();
		} catch (Exception e) {
			entityManager.close();
		} finally {
			entityManagerFactory.close();
		}
	}

}
