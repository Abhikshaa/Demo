package com.pagination;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class PaginationApplicationTests {

	private Calculator c = new Calculator();

	@Test
	@Disabled
	void contextLoads() {
	}
	@Test
	public void doSum(){
		int expected=8;

		int actualResult = c.doSum(3, 2, 3);
		assertThat(actualResult).isEqualTo(expected);
	}
	@Test
	public void doMul(){
		int expected=8;

		int actual = c.doMul(2, 2, 2);
		assertThat(actual).isEqualTo(expected);
	}

}
