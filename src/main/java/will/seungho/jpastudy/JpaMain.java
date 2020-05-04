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
//			Member member = new Member();
//			member.setName("JPA");
//			entityManager.persist(member);

//			Member member = entityManager.find(Member.class, 1L);
//			System.out.println(member.getName());

//			entityManager.remove(member);

			/**
			 * 변경이 되었는지 트랜잭션이 커밋하는 시점에 체크를 한다.
			 * 따라서 값만 변경해주면 자동으로 UPDATE QUERY 나가게 됨.
			 */
//			Member member = entityManager.find(Member.class, 2L);
//			member.setName("GOOOOOOOD");

			List<Member> members = entityManager.createQuery("select m from Member as m", Member.class)
					.setFirstResult(0)
					.setMaxResults(5)
					.getResultList();

			members.stream()
					.map(Member::getName)
					.forEach(System.out::println);

			transaction.commit();
		} catch (Exception e) {
			entityManager.close();
		} finally {
			entityManagerFactory.close();
		}
	}

}
