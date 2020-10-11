package hypernet.handler.dialog;

import hypernet.DialogOption;
import hypernet.DialogPlugin;
import hypernet.handler.DialogHandler;

public class Dismiss implements DialogHandler {

    @Override
    public DialogOption handle(DialogPlugin plugin) {
        plugin.dismiss();
        return DialogOption.EXIT;
    }
}
