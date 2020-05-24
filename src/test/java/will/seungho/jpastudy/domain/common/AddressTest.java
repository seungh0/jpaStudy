package will.seungho.jpastudy.domain.common;

import org.junit.jupiter.api.Test;
import will.seungho.jpastudy.common.Address;

import static org.assertj.core.api.Assertions.assertThat;

class AddressTest {

	@Test
	void test_equals() {
		// given
		Address address = new Address("city", "street", "zipCode");
		Address newAddress = new Address("city", "street", "zipCode");

		// when
		boolean result = address.equals(newAddress);

		// then
		assertThat(result).isTrue();
	}

	@Test
	void test_equals2() {
		// given
		Address address = new Address("city", "street", "zipCode");
		Address newAddress = new Address("city", "street", "zipCode1");

		// when
		boolean result = address.equals(newAddress);

		// then
		assertThat(result).isFalse();
	}

}
