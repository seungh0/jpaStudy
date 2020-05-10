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
			Team team = new Team("teamA");
			entityManager.persist(team);

			Member member = Member.builder()
					.name("승호")
					.team(team)
					.build();
			entityManager.persist(member);

			entityManager.flush();
			entityManager.clear();

			Member findMember = entityManager.find(Member.class, member.getId());

			Team findTeam = findMember.getTeam();

			System.out.println(findTeam.getId());
			System.out.println(findTeam.getName());

			transaction.commit();
		} catch (Exception e) {
			entityManager.close();
		} finally {
			entityManagerFactory.close();
		}
	}

}
