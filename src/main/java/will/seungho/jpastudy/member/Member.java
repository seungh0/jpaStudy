package will.seungho.jpastudy.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
/**
 * JPA는 내부적으로 리플렉션을 사용하기 때문에 동적으로 객체를 생성해내야 한다!
 * 그래서 기본생성자가 필요함!
 */
@Entity
public class Member {

	@Id
	private Long id;

	@Column(name = "name")
	private String userName;

	private Integer age;

	@Enumerated(EnumType.STRING)
	private RoleType roleType;

	private LocalDateTime createdDateTime;

	private LocalDateTime lastModifiedDateTime;

	@Lob
	private String description;

	@Builder
	public Member(Long id, String userName, Integer age, RoleType roleType, LocalDateTime createdDateTime, LocalDateTime lastModifiedDateTime, String description) {
		this.id = id;
		this.userName = userName;
		this.age = age;
		this.roleType = roleType;
		this.createdDateTime = createdDateTime;
		this.lastModifiedDateTime = lastModifiedDateTime;
		this.description = description;
	}

}
