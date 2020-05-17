package will.seungho.jpastudy;

import will.seungho.jpastudy.member.Member;
import will.seungho.jpastudy.team.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		try {
			Team team = Team.builder()
					.name("team")
					.build();
			entityManager.persist(team);

			Member member = Member.builder()
					.name("name")
					.team(team)
					.build();
			entityManager.persist(member);

			Member findMember = entityManager.find(Member.class, member.getId());
			// 멤버 + 팀을 가져옴
			printMemberAndTeam(findMember);

			// 멤버만 가져옴 (팀을 가져오는 쿼리가 나가지 않아도 됨)
			printMember(findMember);

			transaction.commit();
		} catch (Exception e) {
			entityManager.close();
		} finally {
			entityManagerFactory.close();
		}
	}

	private static void printMember(Member findMember) {
		System.out.println(findMember.getName());
	}

	private static void printMemberAndTeam(Member findMember) {
		String userName = findMember.getName();
		System.out.println(userName);

		Team team = findMember.getTeam();
		System.out.println(team);
	}

}
