package will.seungho.jpastudy.locker;

import lombok.Getter;
import lombok.NoArgsConstructor;
import will.seungho.jpastudy.BaseEntity;
import will.seungho.jpastudy.member.Member;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Getter
@NoArgsConstructor
@Entity
public class Locker extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@OneToOne(mappedBy = "locker")
	private Member member;

}
