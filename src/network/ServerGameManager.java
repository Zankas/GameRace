package network;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import core.GameManager;

class ServerGameManager {
	private final Set<ClientManager> clients = new HashSet<ClientManager>();

	private GameManager gameManager;
	private final Set<ClientManager> readyClients = new HashSet<ClientManager>();

	public void add(final ClientManager cm) {
		clients.add(cm);
	}

	public void dispatch(final String message,
			final ClientManager senderClientManager) {
		for (final ClientManager cm : clients) {
			if (cm != senderClientManager) {
				cm.dispatch(message);
			}
		}
	}

	public String getConnectedClientNames() {
		final StringBuilder sb = new StringBuilder();
		for (final ClientManager cm : clients) {
			if (cm.getName() != null) {
				sb.append(cm.getName());
				sb.append(";");
			}
		}
		return sb.toString();
	}

	public void received(String buffer) {
		String[] split = buffer.split(":");
//		Direction direction = Direction.valueOf(split[1]);
		boolean fire = Boolean.parseBoolean(split[2]);
//		gameManager.getTank(split[0]).setDirection(direction);
//		if (fire) {
//			gameManager.shootTank(split[0]);
//		}
	}

	public void setReady(final ClientManager clientManager) {
		synchronized (readyClients) {
			readyClients.add(clientManager);
			if (readyClients.size() == 2) {
				dispatch("#START", null);
				System.out.println("ServerGameManager.setReady()");
			}
		}
	}

	public void startGame() throws IOException {
		List<String> names = new ArrayList<>();
		for (final ClientManager cm : clients) {
			cm.setup();
			new Thread(cm, cm.toString()).start();
			names.add(cm.getName());
		}
		gameManager = new GameManager();
//		gameManager.start(new Runnable() {
//			@Override
//			public void run() {
//				String statusToString = gameManager.statusToString();
//				dispatch(statusToString, null);
//			}
//		}, names);
	}

}
