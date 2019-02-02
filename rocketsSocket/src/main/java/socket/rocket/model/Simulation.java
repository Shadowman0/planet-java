package socket.rocket.model;

public class Simulation {
	public BodyList bodyList = new BodyList();

	public Simulation() {
		bodyList.createPlanet(50000, new Vector(0, 0), new Vector(0, 0), 100);
		bodyList.createPlanet(10, new Vector(100, 0), new Vector(0, 100), 1);
		bodyList.createPlanet(10, new Vector(-200, 0), new Vector(0, -25), 1);
	}

}
