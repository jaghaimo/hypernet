package hypernet.panel;

import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

public class HeaderElement extends BoardElement {

    private String title;

    public HeaderElement(float width, float height, String title) {
        super(width, height);
        this.title = title;
    }

    @Override
    public void render(TooltipMakerAPI inner) {
        inner.addSectionHeading("  " + title, Alignment.LMID, 0f);
    }
}
