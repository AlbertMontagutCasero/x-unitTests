package usantatecla.closedInterval.v3.models;

public class ClosedInterval extends usantatecla.closedInterval.v3.ClosedInterval {

	public ClosedInterval(double min, double max) {
		super(min, max);
	}

	public double getMin() {
		return this.getMiddlePoint() - this.getLength()/2;
	}

	public double getMax() {
		return this.getMiddlePoint() + this.getLength()/2;
	}
}
