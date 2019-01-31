package socket.rocket.model;

import java.util.ArrayList;
import java.util.List;

public class RocketSmall extends Rocket {
	private RocketEngine engine;
	private RocketFuelTank tank;
	private SimpleBody cargo;

	public RocketSmall(RocketEngine engine, RocketFuelTank tank, SimpleBody cargo) {
		super();
		init(engine, tank, cargo);
	}

	public RocketSmall(double mass, Vector position, Vector velocity, double angle) {
		super();
		double partMass = mass / 5;
		double strutLength = 1;

		Vector defaultPosition = new Vector(0, 0);
		Vector defaultVelocity = new Vector(0, 0);
		RocketEngine engine = new RocketEngine(partMass, defaultPosition.addVector2(new Vector(0, -1).rotate(angle)),
				defaultVelocity, 1);
		RocketFuelTank tank = new RocketFuelTank(partMass, new Vector(defaultPosition), defaultVelocity,
				partMass / 9 * 8, partMass / 9 * 8);
		SimpleBody cargo = new SimpleBody(partMass, defaultPosition.addVector2(new Vector(0, 1).rotate(angle)),
				defaultVelocity);
		init(engine, tank, cargo);

		List<SimpleBody> realRocketParts = new ArrayList();
		realRocketParts.add(engine);
		realRocketParts.add(tank);
		realRocketParts.add(cargo);

		SimpleBody helpBody1 = new SimpleBody(partMass, defaultPosition.addVector2(new Vector(0.5, 0).rotate(angle)),
				defaultVelocity);
		SimpleBody helpBody2 = new SimpleBody(partMass, defaultPosition.addVector2(new Vector(-0.5, 0).rotate(angle)),
				defaultVelocity);

		for (SimpleBody part : realRocketParts) {
			addStrut(helpBody1, part);
		}
		for (SimpleBody part : realRocketParts) {
			addStrut(helpBody2, part);
		}
		setPosition(new Vector(position));
		setVelocity(new Vector(velocity));

	}

	private void init(RocketEngine engine, RocketFuelTank tank, SimpleBody cargo) {
		this.engine = engine;
		this.tank = tank;
		this.cargo = cargo;
		addStrut(engine, tank);
		addStrut(tank, cargo);
	}

}
