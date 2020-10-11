package hypernet.panel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fs.starfarer.api.ui.CustomPanelAPI;

public class HeaderRow extends BoardRow {

    private String title;

    public HeaderRow(CustomPanelAPI panel, float width, String title) {
        super(panel, width);
        this.title = title;
    }

    @Override
    protected List<BoardElement> getLeftElements() {
        List<BoardElement> elements = new ArrayList<>();
        elements.add(new HeaderElement(0, 25f, title));
        return elements;
    }

    @Override
    protected List<BoardElement> getRightElements() {
        return Collections.emptyList();
    }
}
