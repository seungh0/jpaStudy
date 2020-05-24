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
			Member findMember = entityManager.find(Member.class, member.getId());
			findMember.changeAddress(new Address("city2", "street2", "zipCode2"));

			findMember.getFavoriteFoods().remove("치킨");
			findMember.getFavoriteFoods().add("한식");
			// 실제 DB 쿼리가 날라감

			// equals로 비교후 삭제 (equals(), hashCode() 재정의)
			findMember.getAddressHistory().remove(new Address("city1", "street1", "zipCode1"));
			findMember.getAddressHistory().add(new Address("city3", "street3", "zipCode3"));
			/**
			 * 값은 변경하면 추적이 어렵다 (엔티티와 다르게 식별자 개념이 없다)
			 * 값 타입 컬렉션에 변경사항이 발생하면, 주인 엔티티와 연관된 모든 데이터를 삭제하고,
			 * 값 타입 컬렉션에 있는 현재 값을 모두 다시 저장한다.
			 * => 쿼리수 문제....
			 * 쓰면 문제가 많음
			 */

			transaction.commit();
		} catch (Exception e) {
			entityManager.close();
			e.printStackTrace();
		} finally {
			entityManagerFactory.close();
		}
	}

}
