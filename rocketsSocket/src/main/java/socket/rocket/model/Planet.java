package socket.rocket.model;

public class Planet extends SimpleBody {
	private double radius;

	public Planet(double mass, Vector position, Vector velocity, double radius) {
		super(mass, position, velocity);
		setRadius(radius);
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public boolean isColliding(AbstractBody body) {
		return distance(body) <= radius;
	}

	public void projectBody(AbstractBody body) {

		if (isColliding(body)) {
			Vector distanceVector = new Vector(body.getPosition());
			distanceVector.addVector(position, -1);
			double norm = distanceVector.norm();
			Vector newPosition = new Vector(getPosition());
			newPosition.addVector(distanceVector, radius / norm);
			body.setPosition(newPosition);
			body.setVelocity(velocity);

		}
	}

}
