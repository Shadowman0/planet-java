package socket.rocket.model;

public class Simulation {
    public BodyList bodyList;

    public Simulation() {
        bodyList = new BodyList();
        bodyList.createPlanet(50000, new Vector(800, 500), new Vector(0, 0), 100);
        // bodyList.createPlanet(100000, new Vector(1000, 1000), new Vector(0, -100),
        // 100);
        bodyList.createRocketSmall(10, new Vector(800, 300), new Vector(0, 100));
        bodyList.createRocketSmall(1, new Vector(20, 10), new Vector(0, 200));
        bodyList.createRocketSmall(1, new Vector(40, 10), new Vector(100, 0));
        bodyList.createRocketSmall(10, new Vector(500, 500), new Vector(0, 400));
        bodyList.createRocketSmall(1, new Vector(600, 500), new Vector(0, 400));
        bodyList.createRocketSmall(1, new Vector(400, 500), new Vector(0, 300));
        bodyList.createRocketSmall(1, new Vector(300, 500), new Vector(100, 0));
        bodyList.createRocketSmall(1, new Vector(500, 500), new Vector(0, 100));
        // System.out.println(bodyList.rockets.size());

    }

}
