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
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn() // DTYPE = ALBUM & MOVIE & BOOK 필드 추가
public class Items {

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	private int price;

}
