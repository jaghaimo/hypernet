package hypernet.handler;

import hypernet.handler.button.Add;
import hypernet.handler.button.Delete;
import hypernet.handler.button.DeleteAll;
import hypernet.handler.button.Disable;
import hypernet.handler.button.DisableAll;
import hypernet.handler.button.Dummy;
import hypernet.handler.button.Enable;
import hypernet.handler.button.EnableAll;
import hypernet.handler.button.Refresh;
import hypernet.handler.button.RefreshAll;
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

        switch (buttonType) {
            case QueryRow.BUTTON_DELETE:
                return new Delete(queryNumber);

            case QueryRow.BUTTON_DISABLE:
                return new Disable(queryNumber);

            case QueryRow.BUTTON_ENABLE:
                return new Enable(queryNumber);

            case QueryRow.BUTTON_REFRESH:
                return new Refresh(queryNumber);
        }

        return new Dummy();
    }

    private static ButtonHandler getQueriesHandler(String buttonType) {
        switch (buttonType) {
            case ControlRow.BUTTON_ADD:
                return new Add();

            case ControlRow.BUTTON_DELETE_ALL:
                return new DeleteAll();

            case ControlRow.BUTTON_DISABLE_ALL:
                return new DisableAll();

            case ControlRow.BUTTON_ENABLE_ALL:
                return new EnableAll();

            case ControlRow.BUTTON_REFRESH_ALL:
                return new RefreshAll();
        }

        return new Dummy();
    }
}
