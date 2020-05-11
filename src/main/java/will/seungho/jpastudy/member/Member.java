package will.seungho.jpastudy.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import will.seungho.jpastudy.locker.Locker;
import will.seungho.jpastudy.team.Team;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


/**
 * 양방향 매핑시 무한루프를 조심하자!!!
 * toString(), lombok, JSON 생성 라이브러리
 */

/**
 * 아 그래서 ToString() 양방향 매핑 엔티티에 정의 안하고...
 * (Controller에서 절대 엔티티를 반환하지 않아야하는 이유!!!!
 * 그래서 엔티티를 DTO로 반환후 Controller에서 반환한다.
 * Controller에서 Entity를 쓸때의 문제점
 * 1. 문제 무한루프 가능
 * 2. 엔티티는 변경될 수 있음 근데, 엔티티를 변경하는 순간 API가 바뀌어버림
 */

@Getter
@NoArgsConstructor
@Entity
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@ManyToOne
	@JoinColumn(name = "TEAM_ID", insertable = false, updatable = false)
	private Team team;

	@OneToOne
	@JoinColumn(name = "LOCKER_ID")
	private Locker locker;

	@Builder
	public Member(String name, Team team) {
		this.name = name;
		this.team = team;
		team.getMembers().add(this);
	}

	public void setTeam(Team team) {
		this.team = team;
	}

}
