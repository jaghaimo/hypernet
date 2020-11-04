package hypernet.panel;

import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;

import hypernet.IntelQuery;

public class ParaElement extends BoardElement {

    private boolean isStale;
    private String text;

    public ParaElement(float width, float height, IntelQuery query) {
        super(width, height);
        this.isStale = query.isStale();
        this.text = query.getDescription() + (isStale ? " (stale)" : "");
    }

    public ParaElement(float width, float height, boolean isStale, String description) {
        super(width, height);
        this.isStale = isStale;
        this.text = description;
    }

    @Override
    public void render(TooltipMakerAPI inner) {
        if (isStale) {
            inner.addPara(text, Misc.getGrayColor(), 8f);
        } else {
            inner.addPara(text, 8f);
        }
    }
}
