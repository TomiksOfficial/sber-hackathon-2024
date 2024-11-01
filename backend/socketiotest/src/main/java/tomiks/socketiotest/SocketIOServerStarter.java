package tomiks.socketiotest;

import com.corundumstudio.socketio.SocketIOServer;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SocketIOServerStarter implements CommandLineRunner {
	private final SocketIOServer sio;

	@Override
	public void run(String... args) throws Exception {
		sio.start();
	}
}
