package tomiks.socketiotest.socketio.config;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class SocketIOConfig {
	@Value("${socketio.host}")
	private String host;

	@Value("${socketio.port}")
	private Integer port;

	@Bean
	public SocketIOServer socketIOServer() {
		Configuration cfg = new Configuration();
		cfg.setHostname(host);
		cfg.setPort(port);

		return new SocketIOServer(cfg);
	}
}
