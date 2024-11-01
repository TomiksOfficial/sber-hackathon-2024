package tomiks.socketiotest.socketio.service.TestSIO;

import com.corundumstudio.socketio.HandshakeData;
import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestSIOService {
	private final SocketIONamespace namespace;

	@Autowired
	public TestSIOService(SocketIOServer sio) {
		this.namespace = sio.addNamespace("/test");
		this.namespace.addConnectListener(onConnected());
		this.namespace.addDisconnectListener(onDisconnected());
		this.namespace.addEventListener("tomiks", TestData.class, onTestReceive());
	}

	private ConnectListener onConnected() {
		return client -> {
			HandshakeData handshakeData = client.getHandshakeData();
			System.out.printf("Client[%s] - Connected through '%s'%n", client.getSessionId().toString(), handshakeData.getUrl());
		};
	}

	private DisconnectListener onDisconnected() {
		return client -> {
			System.out.printf("Client[%s] - Disconnected'%n", client.getSessionId().toString());
		};
	}

	private DataListener<TestData> onTestReceive() {
		return (client, data, ackSender) -> {
			System.out.printf("Client[%s] - Received '%s'%n", client.getSessionId().toString(), data);
			namespace.getBroadcastOperations().sendEvent("tomiks_client", data);
		};
	}
}
