package socket.rocket.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import socket.rocket.RocketApp;

@Controller
public class AppController {

	@Autowired
	private RocketApp rocketApp;

	@RequestMapping(method = RequestMethod.POST, path = "/start")
	@ResponseStatus(code = HttpStatus.OK)
	public void startApp() {
		rocketApp.startSimulation();
	}

	@RequestMapping(method = RequestMethod.POST, path = "/stop")
	@ResponseStatus(code = HttpStatus.OK)
	public void stopApp() {
		rocketApp.stopSimulation();
	}

}
