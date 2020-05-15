package will.seungho.jpastudy.item;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
/**
 * 구현 클래스 마다 테이블 전략(TABLE_PER_CLASS)
 *
 * INSERT시는 좋은데,
 * SELECT시는 UNION을 이용한 복잡한 쿼리가 나감(비효율적임)
 */
public abstract class Items {

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	private int price;

}