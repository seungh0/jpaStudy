package will.seungho.jpastudy;

import will.seungho.jpastudy.common.Address;
import will.seungho.jpastudy.member.Member;

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
					.name("name")
					.workAddress(new Address("city", "street", "zipCode"))
					.build();

			member.getFavoriteFoods().add("치킨");
			member.getFavoriteFoods().add("피자");
			member.getFavoriteFoods().add("족발");

			member.getAddressHistory().add(new Address("city1", "street1", "zipCode1"));
			member.getAddressHistory().add(new Address("city2", "street2", "zipCode2"));

			entityManager.persist(member);

			entityManager.flush();
			entityManager.clear();

			System.out.println("=====================================");
			Member findMembmer = entityManager.find(Member.class, member.getId());
			/**
			 * 컬렉션들은 지연로딩으로 가지옴
			 */
			for (Address address : findMembmer.getAddressHistory()) {
				System.out.println(address.getCity());
			}

			for (String favoriteFood : findMembmer.getFavoriteFoods()) {
				System.out.println(favoriteFood);
			}

			transaction.commit();
		} catch (Exception e) {
			entityManager.close();
			e.printStackTrace();
		} finally {
			entityManagerFactory.close();
		}
	}

}
