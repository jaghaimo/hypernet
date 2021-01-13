package hypernet.handler.button;

import java.util.List;

import com.fs.starfarer.api.campaign.InteractionDialogPlugin;
import com.fs.starfarer.api.ui.IntelUIAPI;

import hypernet.DialogPlugin;
import hypernet.IntelQuery;
import hypernet.handler.ButtonHandler;

public class Add implements ButtonHandler {

    @Override
    public void handle(List<IntelQuery> queries, IntelUIAPI ui) {
        InteractionDialogPlugin intelDialog = new DialogPlugin(queries);
        ui.showDialog(null, intelDialog);
    }
}
