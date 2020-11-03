package hypernet;

import com.fs.starfarer.api.BaseModPlugin;

public class HypernetMod extends BaseModPlugin {

    @Override
    public void onNewGameAfterEconomyLoad() {
        init();
    }

    @Override
    public void onGameLoad(boolean newGame) {
        init();
    }

    private void init() {
        HypernetBoard.getInstance();
        MonthEndListener.register();
    }
}
