package will.seungho.jpastudy.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import will.seungho.jpastudy.BaseEntity;
import will.seungho.jpastudy.team.Team;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Member extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	/**
	 * Member를 조회할때, Team도 함께 조회해야ㄴ하나?
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TEAM_ID")
	private Team team;

	/**
	 * 가급적 지연 로딩만 사용 (특히 실무에서)
	 * 즉시 로딩을 적용하면 예상하지 못한 SQL이 발생한다.
	 * + 즉시 로딩은 JPQL에서 N+1 문제를 일으킨다!!!
	 */

	@Builder
	public Member(String name, Team team) {
		this.name = name;
		this.team = team;
	}

}
