package hypernet.panel;

import com.fs.starfarer.api.ui.TooltipMakerAPI;

public class ParaElement extends BoardElement {

    private String description;

    public ParaElement(float width, float height, String description) {
        super(width, height);
        this.description = description;
    }

    @Override
    public void render(TooltipMakerAPI inner) {
        inner.addPara(description, 8f);
    }
}
