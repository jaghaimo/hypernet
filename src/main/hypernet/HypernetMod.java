package hypernet;

import com.fs.starfarer.api.BaseModPlugin;

public class HypernetMod extends BaseModPlugin {

    @Override
    public void onNewGameAfterEconomyLoad() {
        HypernetBoard.getInstance();
    }

    @Override
    public void onGameLoad(boolean newGame) {
        HypernetBoard.getInstance();
    }
}
