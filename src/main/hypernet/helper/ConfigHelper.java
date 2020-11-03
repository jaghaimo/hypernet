package hypernet.helper;

import com.fs.starfarer.api.Global;

public class ConfigHelper {

    public static boolean marketHasToBeNotHidden() {
        return Global.getSettings().getBoolean("marketHasToBeNotHidden");
    }

    public static boolean marketHasToBeDiscovered() {
        return Global.getSettings().getBoolean("marketHasToBeDiscovered");
    }
}
