package will.seungho.jpastudy.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Setter
@Getter
@Entity
@DiscriminatorValue("M") // 커스터마이징 (안하면 Entity 명으로 됨)
public class Movie extends Items {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String director;

	private String actor;

}
