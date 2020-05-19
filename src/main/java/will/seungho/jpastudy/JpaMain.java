package will.seungho.jpastudy;

import will.seungho.jpastudy.member.Member;
import will.seungho.jpastudy.team.Team;

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
			Team team = Team.builder()
					.name("teamA")
					.build();
			entityManager.persist(team);

			Member member = Member.builder()
					.name("nameA")
					.team(team)
					.build();
			entityManager.persist(member);

			entityManager.flush();
			entityManager.clear();

			/**
			 * Eager 로딩 (즉시 로딩)
			 * em.find()떄 팀과 멤버를 모두 가져옴
			 *
			 * 전반적으로 팀과 멤버를 같이 쓴다하면,
			 * 지연 로딩을 사용할떄는 쿼리가 * 2번 나가니깐,
			 * 즉시로딩으로 설정해서 애초에 처음에 다 가져오도록 설정.
			 */
			Member findMember = entityManager.find(Member.class, member.getId());
			System.out.println("요 시점에 Membmer, Team을 모두 쿼리로 가져온다");
			System.out.println(findMember.getTeam().getClass()); // 실제 객체

			System.out.println(findMember.getTeam().getName()); // 실제 Team을 사용하는 시점

			transaction.commit();
		} catch (Exception e) {
			entityManager.close();
			e.printStackTrace();
		} finally {
			entityManagerFactory.close();
		}
	}

}
