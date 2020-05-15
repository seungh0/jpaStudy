package will.seungho.jpastudy.item;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Book extends Items {

	private String author;

	private String isbn;

}
