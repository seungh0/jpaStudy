package will.seungho.jpastudy;

import will.seungho.jpastudy.member.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
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
			Member member = entityManager.find(Member.class, 150L); // 영속성 컨텍스트에 올려서 영속상태가 됨!
			member.setName("BBBBB"); // 변경감지 적용
//			entityManager.detach(member);
//			entityManager.clear();
//			entityManager.close();
			
			/**
			 * 준영속 상태로 만들면, 영속성 컨텍스트에서 엔티티를 관리를 하지 않아
			 * 변경감지 적용 X
			 * 따라서 UPDATE 쿼리가 나가지 않는다~
			 */

			/**
			 * 준영속 상태로 만드는 방법
			 * 1. em.detach(entity) - 특정 엔티티만 준영속상태로 전환
			 * 2. em.clear() - 영속성 컨텍스트를 완전히 초기화
			 * 3. em.close() - 영속성 컨텍스트를 종료
			 */

			transaction.commit();
		} catch (Exception e) {
			entityManager.close();
		} finally {
			entityManagerFactory.close();
		}
	}

}
