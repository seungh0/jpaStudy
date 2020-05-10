package will.seungho.jpastudy;

import will.seungho.jpastudy.member.Member;
import will.seungho.jpastudy.team.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

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
					.name("seungho")
					.team(team)
					.build();
			entityManager.persist(member); // 1차 캐시 저장

			/**
			 * 객체지향적으로 생각하면 양쪽에 모두 값을 넣어줘야한다!!
			 * 안넣어줘도 아래처럼 검색 가능함!
			 * but
			 * flush(), clear()를 안해주면
			 */
			team.getMembers().add(member); // 읽기 전용! 둘다 매핑해주자 그냥
//			entityManager.flush();
//			entityManager.clear();

			/**
			 * 지연로딩.
			 * Team.members를 실제로 사용하는 시점에 쿼리를 한번 날림.
			 */
			Team findTeam = entityManager.find(Team.class, team.getId());
			/**
			 * 1차 캐시에 있음 => 그래서 team.getMembers().add(member) 안해주고
			 * 플러쉬 clear로 안해주면 안나옴 !
			 * 고래서 고냥 양쪽으로 값을 세팅해주자!
			 */

			/**
			 * 테스트 케이스 작성할떄,
			 * JPA와 분리해서 작성하는데
			 * null이 나오니깐 그냥 양방향 연관관계 매핑시 양쪽에다 모두 값을 세팅해주자!!!!!
			 */
			List<Member> memberList = findTeam.getMembers();
			for (Member m : memberList) {
				System.out.println(m.getName());
			}
			transaction.commit();
		} catch (Exception e) {
			entityManager.close();
		} finally {
			entityManagerFactory.close();
		}
	}

}
