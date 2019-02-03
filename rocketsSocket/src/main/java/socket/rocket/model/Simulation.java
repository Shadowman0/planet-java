package socket.rocket.model;

public class Simulation {
	public BodyList bodyList = new BodyList();

	public Simulation() {
		bodyList.createPlanet(50000, new Vector(0, 0), new Vector(0, 0), 100);
		bodyList.createPlanet(1, new Vector(100, 0), new Vector(0, 100), 1);
		bodyList.createPlanet(1, new Vector(-200, 0), new Vector(0, -100), 1);
		bodyList.createPlanet(1, new Vector(-300, 0), new Vector(0, -100), 1);
		bodyList.createPlanet(1, new Vector(-400, 0), new Vector(0, -100), 1);
		bodyList.createPlanet(1, new Vector(-500, 0), new Vector(0, -100), 1);
	}

}
