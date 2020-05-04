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
			Member member = entityManager.find(Member.class, 150L);
			member.setName("변경감지 테스트");
			System.out.println("============");

			/**
			 * 변경감지 (Dirty Checking)
			 * 값만 바꿧는데? UPDATE QUERY가 나간다!
			 *
			 * flush() 되면
			 * 엔티티와 스냅샷을 비교한다!
			 *
			 * 변경이 있으면 UPDATE QUERY를 쓰기 지연 SQL 저장소에 저장
			 * 커밋떄 UPDATE QUERY 날라감~
			 */

			transaction.commit();
		} catch (Exception e) {
			entityManager.close();
		} finally {
			entityManagerFactory.close();
		}
	}

}
