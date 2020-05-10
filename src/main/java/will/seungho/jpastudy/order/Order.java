package will.seungho.jpastudy.order;

import javax.persistence.*;

@Entity
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "total_amounts")
	@Convert(converter = MoneyConverter.class)
	private Money totalAmounts;

}
