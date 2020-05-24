package will.seungho.jpastudy.common;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "ADDRESS")
public class AddressEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Address address;

	public AddressEntity(String city, String street, String zipCode) {
		this.address = new Address(city, street, zipCode);
	}
	
}
