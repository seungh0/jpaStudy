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

			Member member = new Member(150L, "A");
			Member member1 = new Member(160L, "B");

			entityManager.persist(member);
			entityManager.persist(member1);
			System.out.println("===== 쿼리가 안날라가고 쓰기 지연 SQL 저장소에 저장됨!!!=========");
			System.out.println("===============");

			/**
			 * 트랜잭션을 지원하는 쓰기 지연
			 * 커밋하는 시점에 쓰기 지연 SQL 저장소에 있는 쿼리들이 flush() 되며 날라감!
			 * JDBC Batch
			 */

			transaction.commit();
		} catch (Exception e) {
			entityManager.close();
		} finally {
			entityManagerFactory.close();
		}
	}

}
