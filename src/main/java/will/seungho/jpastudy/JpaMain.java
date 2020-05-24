package will.seungho.jpastudy;

import will.seungho.jpastudy.common.Address;
import will.seungho.jpastudy.common.AddressEntity;
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

			member.getAddressHistory().add(new AddressEntity("city1", "street1", "zipCode1"));
			member.getAddressHistory().add(new AddressEntity("city2", "street2", "zipCode2"));

			entityManager.persist(member);

			entityManager.flush();
			entityManager.clear();

			System.out.println("=====================================");
			Member findMember = entityManager.find(Member.class, member.getId());
			findMember.getAddressHistory().add(new AddressEntity("good", "good", "good"));


			transaction.commit();
		} catch (Exception e) {
			entityManager.close();
			e.printStackTrace();
		} finally {
			entityManagerFactory.close();
		}
	}

}
