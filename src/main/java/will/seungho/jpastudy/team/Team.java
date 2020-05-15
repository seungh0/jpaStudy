package will.seungho.jpastudy.team;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import will.seungho.jpastudy.BaseEntity;
import will.seungho.jpastudy.member.Member;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;


/**
 * 객체 참조 vs 테이블 참조 (양방향)
 * 테이블의 연관관계에서는 FK JOIN으로 양쪽을 다 알 수 있다.
 * 하지만, 객체에서는 한쪽 방향만 알 수 있다.
 *
 * mappedBy?
 *
 * 객체와 테이블이 관계를 맺는 차이
 *
 * 객체는 = 연관관계 2개
 * 회원 -> 팀 연관관계 1개(단방향)
 * 팀 -> 회원 연관관계 1개 (단방향)
 *
 * 테이블은 = 연관관계 1개
 * 회원 <-> 팀의 연관관계 1개 (양방향)
 */
@Getter
@NoArgsConstructor
@Entity
public class Team extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	// 반대 편 사이트에는 Member.team으로 매핑이 되있다고 알려주는 mappedBy
	@OneToMany(mappedBy = "team")
	private List<Member> members = new ArrayList<>();

	@Builder
	public Team(String name) {
		this.name = name;
	}

	/**
	 * 이쪽으로도 해도됨!
	 * 한쪽에서만 정의!!!
	 * (아니면 헷갈려서 문제가 생길 확률 높음)
	 *
	 * Member.changeTeam() or Team.addMember()
	 */
	public void addMember(Member member) {
		member.setTeam(this);
		members.add(member);
	}


}
