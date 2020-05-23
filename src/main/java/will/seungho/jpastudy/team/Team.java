package will.seungho.jpastudy.team;

import lombok.Getter;
import lombok.NoArgsConstructor;
import will.seungho.jpastudy.BaseEntity;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Team extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

}
