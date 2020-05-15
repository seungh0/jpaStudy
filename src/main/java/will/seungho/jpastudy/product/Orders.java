package will.seungho.jpastudy.product;

import will.seungho.jpastudy.BaseEntity;
import will.seungho.jpastudy.member.Member;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class Orders extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "MEMBER_ID")
	private Member member;

	@ManyToOne
	@JoinColumn(name = "PRODUCT_ID")
	private Product product;

	/**
	 * 일대 다 - 일대다로 풀어야
	 * 아래와 같이 추가 정보를 넣을 수 있다.
	 */
	private int count;

	private int price;

	private LocalDateTime orderDateTime;

}
