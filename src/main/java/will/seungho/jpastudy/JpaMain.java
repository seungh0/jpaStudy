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

			team.addMember(member);


			Team findTeam = entityManager.find(Team.class, team.getId());

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
