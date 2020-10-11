package hypernet.panel;

import java.util.ArrayList;
import java.util.List;

import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.util.Misc;

public class ControlRow extends BoardRow {

    public final static String BUTTON_ADD = "HYPERNET_NEW_QUERY";
    public final static String BUTTON_DELETE_ALL = "HYPERNET_DELETE_ALL";
    public final static String BUTTON_DISABLE_ALL = "HYPERNET_DISABLE_ALL";
    public final static String BUTTON_ENABLE_ALL = "HYPERNET_ENABLE_ALL";
    public final static String BUTTON_REFRESH_ALL = "HYPERNET_REFRESH_ALL";

    private boolean isEnabled;

    public ControlRow(CustomPanelAPI panel, float width, boolean isEnabled) {
        super(panel, width);
        this.isEnabled = isEnabled;
    }

    @Override
    protected List<BoardElement> getLeftElements() {
        List<BoardElement> elements = new ArrayList<>();
        elements.add(new ButtonElement(120f, 20f, "New query", BUTTON_ADD, true, Misc.getPositiveHighlightColor()));

        return elements;
    }

    @Override
    protected List<BoardElement> getRightElements() {
        List<BoardElement> elements = new ArrayList<>();
        elements.add(new ButtonElement(120f, 20f, "Delete all", BUTTON_DELETE_ALL, isEnabled,
                Misc.getNegativeHighlightColor()));
        elements.add(
                new ButtonElement(120f, 20f, "Disable all", BUTTON_DISABLE_ALL, isEnabled, Misc.getButtonTextColor()));
        elements.add(
                new ButtonElement(120f, 20f, "Enable all", BUTTON_ENABLE_ALL, isEnabled, Misc.getButtonTextColor()));
        elements.add(
                new ButtonElement(120f, 20f, "Refresh all", BUTTON_REFRESH_ALL, isEnabled, Misc.getHighlightColor()));

        return elements;
    }
}
