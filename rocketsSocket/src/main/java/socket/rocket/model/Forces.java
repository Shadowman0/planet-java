package socket.rocket.model;

public abstract class Forces {

	public static final double GRAVITY_CONSTANT = 100;
	public static final double HOOK_CONSTANT = 1;

	public static Vector gravityForceBetweenBodys(AbstractBody body1, AbstractBody body2) {
		Vector deltaA = body1.distanceVector(body2);
		if (!(deltaA.norm() == 0)) {
			double scalar = -Math.pow(deltaA.norm(), -3) * GRAVITY_CONSTANT * body1.getMass() * body2.getMass();
			deltaA = deltaA.multByScalar(scalar);
			if (!body2.isColliding(body1)) {
				return deltaA;
			}
		}
		return new Vector(0, 0);
	}

	public static Vector hookForceBetweenBodys(AbstractBody body1, AbstractBody body2, double distance) {
		Vector deltaA = body1.distanceVector(body2);
		return deltaA.normalize(HOOK_CONSTANT * (deltaA.norm() - distance));

	}

	public static Vector dragForceBetweenBodys(AbstractBody body1, AbstractBody body2) {
		Vector deltaA = body1.distanceVector(body2);

		return new Vector(0, 0);
	}

}
