package will.seungho.jpastudy.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import will.seungho.jpastudy.team.Team;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@NoArgsConstructor
@Entity
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@ManyToOne
	@JoinColumn(name = "TEAM_ID")
	private Team team;

	/**
	 * 연관관계의 주인
	 * 연관관계의 주인만이 외래키를 관리 (등록 및 수정)
	 * 주인은 mappedBy 속성을 사용하면 안되며,
	 * mappedBy 쪽은 읽기만 가능하다.
	 */

	@Builder
	public Member(String name, Team team) {
		this.name = name;
		this.team = team;
		team.getMembers().add(this);
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public void changeTeam(Team team) {
		/**
		 * 연관관계 편의 메소드
		 * 팁!
		 */
		this.team = team;
		team.getMembers().add(this);
	}

}
