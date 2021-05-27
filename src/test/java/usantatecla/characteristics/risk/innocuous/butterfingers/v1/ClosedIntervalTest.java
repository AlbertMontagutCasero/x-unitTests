package usantatecla.characteristics.risk.innocuous.butterfingers.v1;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;

import usantatecla.characteristics.risk.innocuous.v1.ClosedInterval;

public class ClosedIntervalTest {

	private ClosedInterval closedInterval;

	@Test(expected = AssertionError.class)
	public void testClosedIntervalWithInverseError() {
		closedInterval = new ClosedInterval(20, -30);
	}

	@Test
	public void testClosedInterval() {
		double min = -20;
		double max = 30;
		closedInterval = new ClosedInterval(min, max);
		assertThat(closedInterval.getMin(), is(min));
		assertThat(closedInterval.getMax(), is(max));
	}

	@Test
	public void testIncludesDouble() {
		closedInterval = new ClosedInterval(17, 71);
		assertThat(closedInterval.includes(-3), is(false));
		assertThat(closedInterval.includes(50), is(true));
		assertThat(closedInterval.includes(99), is(false));
	}

	@Test(expected = AssertionError.class)
	public void testIncludesClosedIntervalWithNullError() {
		closedInterval = new ClosedInterval(-1, 1);
		closedInterval.includes(null);
	}

	@Test
	public void testIncludesClosedInterval() {
		closedInterval = new ClosedInterval(-5, 5);
		assertThat(closedInterval.includes(new ClosedInterval(-7, -6)), is(false));
		assertThat(closedInterval.includes(new ClosedInterval(-7, 0)), is(false));
		assertThat(closedInterval.includes(new ClosedInterval(-1, 1)), is(true));
		assertThat(closedInterval.includes(new ClosedInterval(0, 7)), is(false));
		assertThat(closedInterval.includes(new ClosedInterval(7, 9)), is(false));
	}

	@Test(expected = AssertionError.class)
	public void testIntersectedWhitNullError() {
		closedInterval = new ClosedInterval(-1, 1);
		closedInterval.intersected(null);
	}

	@Test
	public void testIntersected() {
		closedInterval = new ClosedInterval(10, 20);
		assertThat(closedInterval.intersected(new ClosedInterval(-10, 0)), is(false));
		assertThat(closedInterval.intersected(new ClosedInterval(5, 15)), is(true));
		assertThat(closedInterval.intersected(new ClosedInterval(10, 20)), is(true));
		assertThat(closedInterval.intersected(new ClosedInterval(15, 25)), is(true));
		assertThat(closedInterval.intersected(new ClosedInterval(30, 40)), is(false));
		assertThat(closedInterval.intersected(new ClosedInterval(0, 30)), is(true));
	}

	public void m() {

		Object[][] data = { { 
			"----[-----]-----", 
			"-[-]------------", 
			false, false,
			"----------------",
			"----------------" 
		},{ 
			"----[-----]-----", 
			"--[-]-----------",
			false, true,
			"----X----------",
			"--[-------]-----" 
		},{ 
			"----[-----]-----", 
			"---[-]----------", 
			false, true,
			"-----[]----------",
			"---[------]-----" 
		},{ 
			"----[----]-------", 
			"-----[-]---------", 
			false, true,
			"-----[-]---------",
			"----[-----]-----" 
		},{ 
			"-----[----]-----", 
			"------[-]-------", 
			true, true, 
			"-----[--]--------",
			"-----[----]-----" 
		},{ 
			"-----[----]-----", 
			"-------[-]------",
			true, true,
			"------[--]-------",
			"-----[----]-----" 
		},{ 
			"-----[----]-----", 
			"--------[-]-----",
			true, true,
			"-------[--]------",
			"-----[----]-----" 
		},{ 
			"-----[----]-----", 
			"---------[-]----",
			true, true,
			"--------[-]-----",
			"-----[----]-----" 
		},{ 
			"-----[----]-----", 
			"----------[-]---",
			false, true,
			"---------[-]-----",
			"-----[------]----" 
		},{ 
			"-----[----]-----", 
			"----------[-]---",
			false, true,
			"----------[]-----",
			"-----[-------]---" 
		},{ 
			"-----[----]-----", 
			"-----------[-]--",
			false, true,
			"-----------X-----",
			"-----[--------]--" 
		},{ 
			"-----[-----]-----", 
			"------------[--]-", 
			false, false,
			"-----------------",
			"-----------------" } };
	}

	@Test
	public void xxx(){
		ClosedInterval thiz = this.create("-----[-----]-----"); 
		ClosedInterval that = this.create("-----------[--]--");
		assertThat(thiz.includes(that), is(false));
		assertThat(thiz.intersection(that), is(this.create("-----------X-----")));
	}

	private ClosedInterval create(String format){
		double point = format.indexOf("X");
		if (point != -1){
			return new ClosedInterval(point, point);
		}
		double min = format.indexOf("[");
		double max = format.indexOf("]");
		if (min == -1 || max == -1){
			return null;
		}
		return new ClosedInterval(min, max);
	}



}
