package socket.rocket.view;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import socket.rocket.RocketApp;
import socket.rocket.model.Vector;
import socket.rocket.model.bodies.Planet;

@Controller
public class AppSocketController {
	@Autowired
	private RocketApp rocketApp;

	@MessageMapping("/socket")
	public String greet() {
		return "test";
	}

	@SendTo("/topic/bodies")
	public BodiesDto bodies() {
		List<Planet> bodies = rocketApp.getBodies();
		return new BodiesDto(map(bodies));
	}

	private List<VectorDto> map(List<Planet> bodies) {
		return bodies.stream().map(rocket -> map(rocket.getPosition())).collect(Collectors.toList());
	}

	private VectorDto map(Vector position) {
		return new VectorDto(position.value[0], position.value[1]);
	}
}
