package will.seungho.jpastudy.common;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

/**
 * 임베디드 타입의 장점
 * 1. 재사용
 * 2. 높은 응집도
 * 3. Period.isWork()처럼 해당 값 타임만 사용하는 의미 있는 메소드를 만들 수 있다. => 객체지향
 * 4. 임베디드 타입을 포함한 모든 값 타임은 값 타입을 소유한 엔티티에 생명주기를 의존함
 *
 * 임베디드 타입은 엔티티의 값일 뿐이다.
 * 임베디드 타입을 사용하기 전 후에 매핑하는 테이블은 같다.
 * 객체와 테이블을 아주 세밀하게 매핑하는 것이 가능하다.
 * 잘 설계한 ORM 애플리케이션은 매핑한 테이블 수도바 클래스의 수가 더 많음.
 */

@Getter
@NoArgsConstructor
@Embeddable
public class Address {

	private String city;

	private String street;

	private String zipCode;

	public Address(String city, String street, String zipCode) {
		this.city = city;
		this.street = street;
		this.zipCode = zipCode;
	}

}
