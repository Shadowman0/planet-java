package socket.rocket.model;

import java.util.HashSet;
import java.util.Set;

public abstract class Rocket extends AbstractBody {

	private Set<Strut> struts = new HashSet<Strut>();
	private Set<SimpleBody> rocketParts = new HashSet<SimpleBody>();

	public void addStrut(SimpleBody firstBody, SimpleBody secondBody) {
		struts.add(new Strut(firstBody, secondBody));
		rocketParts.add(firstBody);
		rocketParts.add(secondBody);
	}

	@Override
	public void calcInnerForces() {
		for (Strut strut : struts) {
			strut.pushPull();
		}
		struts.forEach(Strut::pushPull);
	}

	@Override
	public void setVelocity(Vector velocity) {
		for (SimpleBody part : rocketParts) {
			part.setVelocity(velocity);
		}
		rocketParts.forEach(t -> t.setVelocity(velocity));
	}

	@Override
	public double getMass() {
		double mass = 0;
		for (SimpleBody part : rocketParts) {
			mass += part.getMass();
		}
		return mass;
	}

	@Override
	public Vector getVelocity() {
		Vector velocity = new Vector(0, 0);
		for (SimpleBody part : rocketParts) {
			velocity = velocity.addVector2(part.getVelocity(), part.getMass());
		}
		return velocity.multByScalar(1 / getMass());
	}

	@Override
	public Vector getPosition() {
		Vector position = new Vector(0, 0);
		for (SimpleBody part : rocketParts) {
			position = position.addVector2(part.getPosition(), part.getMass());
		}
		return position.multByScalar(1 / getMass());
	}

	@Override
	public void setPosition(Vector position) {
		Vector translationVector = new Vector(0, 0);
		translationVector = position.addVector2(getPosition(), -1);
		for (SimpleBody part : rocketParts) {
			part.setPosition(part.getPosition().addVector2(translationVector));
		}
	}

	@Override
	public void resetAcceleration() {
		for (SimpleBody part : rocketParts) {
			part.resetAcceleration();
		}
	}

	@Override
	public void addAccelerationByForce(Vector forceVector) {
		for (SimpleBody part : rocketParts) {
			part.addAccelerationByForce(forceVector);
		}
	}

	@Override
	public void doTimeStep(double h) {
		for (SimpleBody part : rocketParts) {
			part.doTimeStep(h);
		}
	}

	@Override
	public void doTimeStepLeapFrog(double h) {
		for (SimpleBody part : rocketParts) {
			part.doTimeStepLeapFrog(h);
		}
	}

	@Override
	public boolean isColliding(AbstractBody body) {
		// TODO Auto-generated method stub
		return false;
	}

}
