package hypernet;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.impl.campaign.intel.BaseIntelPlugin;
import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.ui.SectorMapAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;

import hypernet.handler.ButtonHandler;
import hypernet.handler.ButtonHandlerFactory;
import hypernet.panel.BoardRow;
import hypernet.panel.ControlRow;
import hypernet.panel.EmptyQueriesRow;
import hypernet.panel.HeaderRow;
import hypernet.panel.QueryRow;

/**
 * HyperNET intel board for managing displayed HyperNET intel.
 *
 * Use this board to add, remove, refresh, disable, or enable intel queries to
 * dynamically update displayed intel.
 */
public class HypernetBoard extends BaseIntelPlugin {

    private List<IntelQuery> queries = new ArrayList<IntelQuery>();

    public static HypernetBoard getInstance() {
        IntelInfoPlugin intel = Global.getSector().getIntelManager().getFirstIntel(HypernetBoard.class);
        if (intel == null) {
            HypernetBoard board = new HypernetBoard();
            Global.getSector().getIntelManager().addIntel(board);
        }
        return (HypernetBoard) intel;
    }

    public static void refresh() {
        // this is a hack to fix IntelUIAPI.recreateIntelUI() large description bug
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_E);
            robot.keyRelease(KeyEvent.VK_E);
        } catch (AWTException exception) {
        }
    }

    @Override
    public void buttonPressConfirmed(Object buttonId, IntelUIAPI ui) {
        ButtonHandler handler = ButtonHandlerFactory.get(buttonId);
        handler.handle(queries, ui);
    }

    @Override
    public void createIntelInfo(TooltipMakerAPI info, ListInfoMode mode) {
        Color bulletColor = getBulletColorForMode(mode);
        Color highlightColor = Misc.getHighlightColor();
        info.addPara("Hyperspace Network", getTitleColor(mode), 0);
        int queriesPresent = queries.size();
        if (queriesPresent == 1) {
            info.addPara("Managing %s intel query.", 1f, bulletColor, highlightColor, "1");
        } else if (queriesPresent > 1) {
            info.addPara("Managing %s intel queries.", 1f, bulletColor, highlightColor, String.valueOf(queriesPresent));
        } else {
            info.addPara("No intel queries present.", bulletColor, 1f);
        }
        info.addPara("", 1f);
    }

    @Override
    public void createLargeDescription(CustomPanelAPI panel, float width, float height) {
        TooltipMakerAPI outer = panel.createUIElement(width, height, true);
        float innerWidth = width - 10;
        CustomPanelAPI inner = panel.createCustomPanel(innerWidth, height, null);

        float currentHeight = 0;
        for (BoardRow row : getFreshBoardRows(inner, innerWidth)) {
            row.render(currentHeight);
            currentHeight += row.getHeight();
        }

        inner.getPosition().setSize(innerWidth, currentHeight);
        outer.addCustom(inner, 0);
        panel.addUIElement(outer).inTL(0, 0);
    }

    @Override
    public String getIcon() {
        return Global.getSettings().getSpriteName("hypernet", "board");
    }

    @Override
    public Set<String> getIntelTags(SectorMapAPI map) {
        Set<String> tags = super.getIntelTags(map);
        tags.add(HypernetIntel.TAG);
        return tags;
    }

    @Override
    public IntelSortTier getSortTier() {
        return IntelSortTier.TIER_0;
    }

    @Override
    public boolean hasSmallDescription() {
        return false;
    }

    @Override
    public boolean hasLargeDescription() {
        return true;
    }

    private List<BoardRow> getFreshBoardRows(CustomPanelAPI panel, float width) {
        List<BoardRow> elements = new ArrayList<>();
        elements.add(new ControlRow(panel, width, !queries.isEmpty()));
        elements.add(new HeaderRow(panel, width));

        if (queries.isEmpty()) {
            elements.add(new EmptyQueriesRow(panel, width));
        }

        for (int i = 0; i < queries.size(); i++) {
            IntelQuery query = queries.get(i);
            elements.add(new QueryRow(panel, width, i, query));
        }

        return elements;
    }
}
