package socket.rocket;

import java.util.List;

import javax.swing.Timer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import socket.rocket.model.Simulation;
import socket.rocket.model.bodies.Planet;
import socket.rocket.view.AppSocketController;

@Component
public class RocketApp {
	@Autowired
	private AppSocketController appSocketController;
	private Timer timer = new Timer(20, e -> timeStepAction());
	private Simulation simulation = new Simulation();;

	public void startSimulation() {
		timer.start();

	}

	public void stopSimulation() {
		timer.stop();

	}

	private void timeStepAction() {
		simulation.bodyList.doIterationStep(0.01);
		appSocketController.bodies();
	}

	public List<Planet> getBodies() {
		return simulation.bodyList.getPlanets();
	}

}
