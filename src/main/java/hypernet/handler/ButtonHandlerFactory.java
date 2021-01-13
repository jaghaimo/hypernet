package hypernet.handler;

import hypernet.handler.button.Add;
import hypernet.handler.button.Delete;
import hypernet.handler.button.DeleteAll;
import hypernet.handler.button.Disable;
import hypernet.handler.button.Dummy;
import hypernet.handler.button.Enable;
import hypernet.handler.button.RefreshAll;
import hypernet.handler.button.ToggleAll;
import hypernet.panel.ControlRow;
import hypernet.panel.QueryRow;

public class ButtonHandlerFactory {

    public static ButtonHandler get(Object buttonId) {
        if (buttonId == null) {
            return new Dummy();
        }
        String buttonType = String.valueOf(buttonId);
        if (buttonType.contains("#")) {
            return getIntelHandler(buttonType);
        }
        return getQueriesHandler(buttonType);
    }

    private static ButtonHandler getIntelHandler(String b) {
        String[] buttonIds = b.split("#");
        String buttonType = buttonIds[0];
        int queryNumber = Integer.parseInt(buttonIds[1]);
        ButtonHandler handler = new Dummy();
        if (buttonType.equals(QueryRow.BUTTON_DELETE)) {
            handler = new Delete(queryNumber);
        } else if (buttonType.equals(QueryRow.BUTTON_DISABLE)) {
            handler = new Disable(queryNumber);
        } else if (buttonType.equals(QueryRow.BUTTON_ENABLE)) {
            handler = new Enable(queryNumber);
        }
        return handler;
    }

    private static ButtonHandler getQueriesHandler(String buttonType) {
        ButtonHandler handler = new Dummy();
        if (buttonType.equals(ControlRow.BUTTON_ADD)) {
            handler = new Add();
        } else if (buttonType.equals(ControlRow.BUTTON_DELETE_ALL)) {
            handler = new DeleteAll();
        } else if (buttonType.equals(ControlRow.BUTTON_REFRESH_ALL)) {
            handler = new RefreshAll();
        } else if (buttonType.equals(ControlRow.BUTTON_TOGGLE_ALL)) {
            handler = new ToggleAll();
        }
        return handler;
    }
}
