package Controllers;

import Models.Game;
import Models.Player;

public class KiemTraGameOverController {
    public boolean kiemTraGameOver(Game game) {
        int mauTa = game.getPlayer().getMau();
        int mauAi = game.getAi().getMau();
        if (mauTa == 0) {
            return true;
        }
        if (mauAi == 0) {
            return true;
        }

        return false;
    }
}
