package socket.rocket.model;

public class Strut {

	private SimpleBody firstBody;
	private SimpleBody secondBody;
	private double distance;

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public Strut(SimpleBody firstBody, SimpleBody secondBody, double distance) {
		super();
		this.firstBody = firstBody;
		this.secondBody = secondBody;
		this.distance = distance;
	}

	public Strut(SimpleBody firstBody, SimpleBody secondBody) {
		super();
		this.firstBody = firstBody;
		this.secondBody = secondBody;
		this.distance = firstBody.distance(secondBody);
	}

	public void pushPull() {
		Vector forceVector = Forces.hookForceBetweenBodys(firstBody, secondBody, distance);
		firstBody.addAccelerationByForce(forceVector.multByScalar(-1));
		secondBody.addAccelerationByForce(forceVector);
	}

}
