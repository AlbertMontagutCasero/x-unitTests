package usantatecla.factorial.v2.completeTest;

import static org.junit.Assert.assertThat;

import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;


public class MathTest {

	@Test
	public void testFactorial() {
		assertThat(Math.factorial(0), is(1L));
		assertThat(Math.factorial(Math.FACTORIAL_MAX_VALUE), is(2432902008176640000L));
	}

	@Test(expected = AssertionError.class)
	public void testFactorialWithException() {
		assertThat(Math.factorial(Math.FACTORIAL_MAX_VALUE + 1), is(0L));
	}

}


