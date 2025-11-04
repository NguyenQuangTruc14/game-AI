package Kiet;

import java.util.HashMap;
import java.util.Map;

public class Game {
	private Player player;
	private Player ai; // ở diagram là AI; ở đây tạm dùng Player cho đơn giản
	private int round;
	// Danh sách tất cả chiêu trong game (id -> chiêu) nếu cần quản lý trung tâm
	private Map<Integer, ChieuThuc> listAllChieuThuc;

	public Game(Player player, Player ai) {
		this.player = player;
		this.ai = ai;
		this.round = 0;
		this.listAllChieuThuc = new HashMap<>();
	}

	public static Game newGame() {

	}
}
