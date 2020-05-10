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

			Member member = new Member("member1", team.getId());
			// 객체 지향적이지 않다!!  setTeam()이 되어야 객체지향적이지 않을까?
			entityManager.persist(member);

			Member findMember = entityManager.find(Member.class, member.getId());
			Long findTeamId = findMember.getTeamId();
			Team findTeam = entityManager.find(Team.class, findTeamId);

			System.out.println(findTeam.getId());
			System.out.println(findTeam.getName());

			/**
			 * 객체를 테이블에 맞추어 데이터 중심으로 모델링을 하게 되면, 협력 관계를 만들 수 없다.
			 * 테이블 => FK로 JOIN을 사용해서 연관된 테이블을 찾는 반면,
			 * 객체는 참조를 통해 연관된 객체를 찾는다!
			 */

			transaction.commit();
		} catch (Exception e) {
			entityManager.close();
		} finally {
			entityManagerFactory.close();
		}
	}

}
