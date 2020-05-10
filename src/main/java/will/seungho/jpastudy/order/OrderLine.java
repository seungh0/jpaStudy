package will.seungho.jpastudy.order;

import javax.persistence.Embeddable;

@Embeddable
public class OrderLine {

	private Long productId;

	private Money price;

	private int quantity;

	private Money amount;

}
