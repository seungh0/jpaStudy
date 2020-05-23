package will.seungho.jpastudy;

import will.seungho.jpastudy.common.Address;
import will.seungho.jpastudy.common.Period;
import will.seungho.jpastudy.member.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

public class JpaMain {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		try {

			/**
			 * 엔티티 타입이란? 데이터가 변해도 식별자로 지속해서 추적 가능.
			 * 데이터가 변해도 식별자로 지속해서 추적 가능
			 *
			 * 값 타임이란? int, integer, String 처럼 단순히 값으로 사용하는 자바 기본 타입이나 객체.
			 * 식별자가 없고, 값만 있으므로 변경시 추적 불가.
			 *
			 * 값 타입의 분류:
			 * - 기본 값 타입
			 * - 임베디드 타입
			 * - 컬렉션 값 타임
			 */
			Member member = Member.builder()
					.name("name")
					.period(new Period(LocalDateTime.of(1998, 2, 12, 0, 0), LocalDateTime.of(2020, 5, 23, 0, 0)))
					.homeAddress(new Address("city", "street", "100"))
					.workAddress(new Address("city2", "street2", "200"))
					.build();

			entityManager.persist(member);

			transaction.commit();
		} catch (Exception e) {
			entityManager.close();
			e.printStackTrace();
		} finally {
			entityManagerFactory.close();
		}
	}

}
