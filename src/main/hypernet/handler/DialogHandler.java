package hypernet.handler;

import hypernet.DialogOption;
import hypernet.DialogPlugin;

public interface DialogHandler {

    public DialogOption handle(DialogPlugin plugin);
}
