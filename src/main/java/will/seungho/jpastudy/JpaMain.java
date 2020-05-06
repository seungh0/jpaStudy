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
			Member member = new Member(202L, "Member200");
			entityManager.persist(member);
			entityManager.flush();
			System.out.println("+===========================");

			/**
			 * flush를 해도 1차 캐쉬는 그대로 유지한다.
			 * (영속성 컨텍스트를 비우지 않음!!)
			 *
			 * 영속성 컨텍스트의 변경내용을 데이터베이스에 동기화.
			 *
			 * 영속성 컨텍스트 내의 쓰기지연 SQL 저장소의 쿼리들이 나간다.
			 * 변경감지.
			 *
			 * 영속성 컨텍스틀 플러시하는 3가지 방법
			 * 1. entityManager.flush() 직접 호출
			 * 2. 트랜잭션 커밋
			 * 3. JPQL 쿼리 실행
			 *
			 * cf) JPQL 쿼리 실행시 플러시가 자동으로 호출 되는 이유
			 * 예를 들어 검색하려면 DB에서 가져와야하는데... 그 전에 반영을 해야함!
			 */

//			entityManager.setFlushMode(FlushModeType.AUTO);
//			entityManager.setFlushMode(FlushModeType.COMMIT);
			/**
			 * 플러시 모드 옵션
			 * AUTO = 커밋, 쿼리 실행시 플러시(기본값)
			 * COMMIT = 커밋할 대만 플러시
			 */


			transaction.commit();
		} catch (Exception e) {
			entityManager.close();
		} finally {
			entityManagerFactory.close();
		}
	}

}
