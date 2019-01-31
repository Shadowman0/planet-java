package socket.rocket.view;

import java.util.List;

public class BodiesDto {

	private final List<VectorDto> bodies;

	public BodiesDto(List<VectorDto> bodies) {
		this.bodies = bodies;
	}

	public List<VectorDto> getBodies() {
		return bodies;
	}
}
