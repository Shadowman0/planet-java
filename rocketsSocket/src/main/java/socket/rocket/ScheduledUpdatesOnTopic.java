package socket.rocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ScheduledUpdatesOnTopic {

	@Autowired
	private SimpMessagingTemplate template;

	@Autowired
	private AppSocketController appSocketController;

	ObjectMapper objectMapper = ObjectMapping.jsonBuilder();

	@Scheduled(fixedDelay = 30)
	public void publishUpdates() throws JsonProcessingException {
		String bodies = objectMapper.writeValueAsString(appSocketController.bodies());
		template.convertAndSend("/topic/bodies", bodies);
	}
}