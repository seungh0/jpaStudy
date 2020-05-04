package will.seungho.jpastudy.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Setter
@Getter
@Entity
@NoArgsConstructor
/**
 * JPA는 내부적으로 리플렉션을 사용하기 때문에 동적으로 객체를 생성해내야 한다!
 * 그래서 기본생성자가 필요함!
 */
public class Member {

	@Id
	private Long id;

	private String name;

	public Member(Long id, String name) {
		this.id = id;
		this.name = name;
	}

}
