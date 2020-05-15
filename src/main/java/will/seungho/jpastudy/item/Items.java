package will.seungho.jpastudy.item;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn
/**
 * SINGLE_TABLE 전략
 * ITEM 테이블에 모든 하위 클래스의 필드가 들어감
 *
 * 성능상은 이점이 있음
 */
public class Items {

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	private int price;

}