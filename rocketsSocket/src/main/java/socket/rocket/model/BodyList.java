package socket.rocket.model;

import java.util.ArrayList;
import java.util.List;

import socket.rocket.model.bodies.Planet;
import socket.rocket.model.bodies.Rocket;
import socket.rocket.model.bodies.RocketSmall;
import socket.rocket.model.bodies.SimpleBody;

public class BodyList {
	private List<Planet> planets = new ArrayList<Planet>();;
	private List<Rocket> rockets = new ArrayList<Rocket>();;

	public List<Planet> getPlanets() {
		return planets;
	}

	public List<Rocket> getRockets() {
		return rockets;
	}

	public BodyList() {
		super();
	}

	@Override
	public String toString() {
		return "BodyList [planets=" + planets + ", rockets=" + rockets + "]";
	}

	public void createPlanet(double mass, Vector position, Vector velocity, double radius) {
		Planet planet = new Planet(mass, position, velocity, radius);
		planets.add(planet);

	}

	public void createRocketSmall(double mass, Vector position, Vector velocity, double angle) {
		Rocket rocket = new RocketSmall(mass, position, velocity, angle);
		rockets.add(rocket);
	}

	public void createRocketSmall(double mass, Vector position, Vector velocity) {
		createRocketSmall(mass, position, velocity, 0);
	}

	public void calculateAccelerations() {
		Vector forceVector;
		for (Planet planet1 : planets) {
			for (Planet planet2 : planets) {
				if (planet1 != planet2) {
					forceVector = Forces.gravityForceBetweenBodys(planet1, planet2);
					planet1.addAccelerationByForce(forceVector);
				}
			}
		}

		for (Rocket rocket : rockets) {
			rocket.calcInnerForces();
			for (SimpleBody planet : planets) {
				forceVector = Forces.gravityForceBetweenBodys(rocket, planet);
				rocket.addAccelerationByForce(forceVector);

				forceVector = Forces.dragForceBetweenBodys(rocket, planet);
				rocket.addAccelerationByForce(forceVector);
			}
		}

	}

	public void doIterationStep(double h) {
		calculateAccelerations();
		for (SimpleBody body : planets) {
			body.doTimeStep(h);
			// body.doTimeStepLeapFrog(h);
		}
		for (Rocket body : rockets) {
			body.doTimeStep(h);
			System.out.println(body.getPosition());
			// body.doTimeStepLeapFrog(h);
		}
		for (Rocket body : rockets) {
			for (Planet planet : planets) {
				planet.projectBody(body);
			}
		}
		resetAccelerations();

	}

	private void resetAccelerations() {
		for (SimpleBody body : planets) {
			body.resetAcceleration();
		}
		for (Rocket body : rockets) {
			body.resetAcceleration();
		}

	}

}
