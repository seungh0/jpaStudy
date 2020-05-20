package will.seungho.jpastudy.cascade;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@NoArgsConstructor
@Entity
public class Child {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	public Child(String name) {
		this.name = name;
	}

	@ManyToOne
	@JoinColumn(name = "PARENT_ID")
	private Parent parent;

	public void setParent(Parent parent) {
		this.parent = parent;
	}

}
