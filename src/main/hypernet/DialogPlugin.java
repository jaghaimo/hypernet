package hypernet;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.CargoPickerListener;
import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.FleetMemberPickerListener;
import com.fs.starfarer.api.campaign.InteractionDialogAPI;
import com.fs.starfarer.api.campaign.InteractionDialogPlugin;
import com.fs.starfarer.api.campaign.OptionPanelAPI;
import com.fs.starfarer.api.campaign.TextPanelAPI;
import com.fs.starfarer.api.campaign.VisualPanelAPI;
import com.fs.starfarer.api.campaign.rules.MemoryAPI;
import com.fs.starfarer.api.combat.EngagementResultAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.util.Misc;

import org.lwjgl.input.Keyboard;

import hypernet.filter.MutableFilterManager;
import hypernet.handler.DialogHandler;
import hypernet.handler.DialogHandlerFactory;
import hypernet.provider.AdminIntelProvider;
import hypernet.provider.CargoIntelProvider;
import hypernet.provider.OfficerIntelProvider;
import hypernet.provider.ShipIntelProvider;

public class DialogPlugin implements InteractionDialogPlugin {

    private DialogOption lastOption;
    private MutableFilterManager filterManager;
    private List<IntelQuery> queries;

    private InteractionDialogAPI dialog;
    private OptionPanelAPI options;
    private TextPanelAPI textPanel;
    private VisualPanelAPI visual;

    public DialogPlugin(List<IntelQuery> q) {
        filterManager = new MutableFilterManager();
        queries = q;
    }

    @Override
    public void advance(float amount) {
    }

    @Override
    public void backFromEngagement(EngagementResultAPI result) {
    }

    @Override
    public Object getContext() {
        return null;
    }

    @Override
    public Map<String, MemoryAPI> getMemoryMap() {
        return null;
    }

    @Override
    public void init(InteractionDialogAPI d) {
        dialog = d;
        options = d.getOptionPanel();
        textPanel = d.getTextPanel();
        visual = d.getVisualPanel();
        visual.showImagePortion("illustrations", "hypernet", 640, 400, 0, 0, 480, 300);

        addTitle("HyperNET");
        textPanel.addPara("Welcome to Hyperspace Network!");
        showMenu();
    }

    @Override
    public void optionMousedOver(String text, Object optionData) {
    }

    @Override
    public void optionSelected(String text, Object optionData) {
        if (optionData == null) {
            return;
        }

        DialogOption option = (DialogOption) optionData;
        DialogHandler handler = DialogHandlerFactory.getHandler(option, lastOption);
        lastOption = handler.handle(this);
    }

    public void addIntelQuery(CargoStackAPI cargoStack) {
        IntelProvider provider = new CargoIntelProvider(cargoStack);
        addNewQuery(provider);
    }

    public void addIntelQuery(FleetMemberAPI fleetMember) {
        IntelProvider provider = new ShipIntelProvider(fleetMember);
        addNewQuery(provider);
    }

    public void addIntelQuery(String personality) {
        IntelProvider provider = new OfficerIntelProvider(personality);
        addNewQuery(provider);
    }

    public void addIntelQuery() {
        IntelProvider provider = new AdminIntelProvider();
        addNewQuery(provider);
    }

    public void addOptions(DialogOption... intelOptions) {
        options.clearOptions();
        for (DialogOption option : intelOptions) {
            options.addOption(option.getName(), option);
        }
    }

    public void addText(String update) {
        textPanel.addPara(update);
    }

    public void addTitle(String title) {
        Color colorHighlight = Misc.getHighlightColor();
        textPanel.addPara(title, colorHighlight);
    }

    public void dismiss() {
        dialog.dismiss();
        HypernetBoard.refresh();
    }

    public MutableFilterManager getFilterManager() {
        return filterManager;
    }

    public void setEscShortcut(DialogOption option) {
        options.setShortcut(option, Keyboard.KEY_ESCAPE, false, false, false, false);
    }

    public void showCargoPicker(String title, String okText, String cancelText, boolean small, float textPanelWidth,
            CargoAPI cargo, CargoPickerListener listener) {
        dialog.showCargoPickerDialog(title, okText, cancelText, small, textPanelWidth, cargo, listener);
    }

    public void showFleetPicker(String title, String okText, String cancelText, int rows, int cols, float iconSize,
            boolean canPickNotReady, boolean canPickMultiple, List<FleetMemberAPI> pool,
            FleetMemberPickerListener listener) {
        dialog.showFleetMemberPickerDialog(title, okText, cancelText, rows, cols, iconSize, canPickNotReady,
                canPickMultiple, pool, listener);
    }

    public void showMenu() {
        lastOption = DialogOption.INIT;
        addOptions(DialogOption.STAFF, DialogOption.CARGO, DialogOption.SHIP, DialogOption.EXIT);
        setEscShortcut(DialogOption.EXIT);
    }

    private void addNewQuery(IntelProvider provider) {
        queries.add(new IntelQuery(provider, filterManager));
    }
}
