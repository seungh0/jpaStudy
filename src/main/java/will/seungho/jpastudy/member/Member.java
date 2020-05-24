package will.seungho.jpastudy.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import will.seungho.jpastudy.BaseEntity;
import will.seungho.jpastudy.common.Address;
import will.seungho.jpastudy.common.AddressEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor
@Entity
public class Member extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MEMBER_ID")
	private Long id;

	private String name;

	@Embedded
	private Address workAddress;

	/**
	 * 값 타입 컬렉션
	 *
	 * @ElementCollection, @CollectionTable 사용
	 * 데이터베이스는 컬렉션을 같은 테이블에 저장할 수 없다.
	 * 컬렉션을 저장하기 위한 별도의 테이블이 필요하다.
	 */
	@ElementCollection
	@CollectionTable(name = "FAVORITE_FOOD",
			joinColumns = @JoinColumn(name = "MEMBER_ID"))
	@Column(name = "FOOD_NAME")
	private Set<String> favoriteFoods = new HashSet<>();

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "MEMBER_ID")
	private List<AddressEntity> addressHistory = new ArrayList<>();

	@Builder
	public Member(String name, Address workAddress) {
		this.name = name;
		this.workAddress = workAddress;
	}

	public void changeAddress(Address address) {
		this.workAddress = address;
	}

}
