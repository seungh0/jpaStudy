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
			System.out.println(findMember1.getId());
			System.out.println(findMember1.getName());

			Member findMember2 = entityManager.find(Member.class, 100L);
			System.out.println(findMember2.getId());
			System.out.println(findMember2.getName());

			/**
			 * 영속성 컨텍스트에 없는 경우는
			 * DB에서 가져와서 1차 캐시에 저장..
			 * 그 이후로는 1차캐시에서 값을 가져오기 떄문에
			 *
			 * SELECT 쿼리가 첫번째에만 나가고 두번쨰 검색때는 쿼리가 나가지 않음
			 * (1차 캐시에서 가져오기 때문)
			 */

			transaction.commit();
		} catch (Exception e) {
			entityManager.close();
		} finally {
			entityManagerFactory.close();
		}
	}

}
