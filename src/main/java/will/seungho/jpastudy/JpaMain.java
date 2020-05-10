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
			Member member = Member.builder()
					.name("seungho")
					.build();
			entityManager.persist(member);

			Team team = Team.builder()
					.name("teamA")
					.build();
			team.getMembers().add(member); // Member.teamID은 null
			entityManager.persist(team);

			/**
			 * Why?
			 *
			 * 연관관계 주인이 아닌 Team.member (mappedBy)은 읽기만 가능하다.
			 * 따라서 team.getMembers().add(member) 해봤자
			 * 안들어감!
			 */

			transaction.commit();
		} catch (Exception e) {
			entityManager.close();
		} finally {
			entityManagerFactory.close();
		}
	}

}
