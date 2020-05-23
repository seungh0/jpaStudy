package will.seungho.jpastudy.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import will.seungho.jpastudy.BaseEntity;
import will.seungho.jpastudy.common.Address;
import will.seungho.jpastudy.common.Period;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class Member extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@Embedded
	private Period period;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "city",
					column = @Column(name = "HOME_CITY")),
			@AttributeOverride(name = "street",
					column = @Column(name = "HOME_STREET")),
			@AttributeOverride(name = "zipCode",
					column = @Column(name = "HOME_ZIP_CODE"))
	})
	private Address homeAddress;

	@Embedded
	private Address workAddress;

	@Builder
	public Member(String name, Period period, Address homeAddress, Address workAddress) {
		this.name = name;
		this.period = period;
		this.homeAddress = homeAddress;
		this.workAddress = workAddress;
	}

}
