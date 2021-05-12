package usantatecla.characteristics.risk.innocuous.butterfingers.v2;

public class ClosedInterval extends usantatecla.blackBox.equivalenceClasses.closedInterval.ClosedInterval {

	private double min;

	private double max;

	public ClosedInterval(double min, double max) {
		super(min, max);
	}

	public double getMin() {
		return min;
	}

	public double getMax() {
		return max;
	}
}
