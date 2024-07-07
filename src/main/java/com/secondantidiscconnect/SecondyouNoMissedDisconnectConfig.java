package com.secondantidiscconnect;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "secondyou-no-missed-disconnect")
public class SecondyouNoMissedDisconnectConfig implements ConfigData {

    @ConfigEntry.Gui.Tooltip
    public boolean enableSafeDisconnect = true;

    @ConfigEntry.Gui.Tooltip
    public String safeDisconnectButtonText = "Safe Disconnect";

    @ConfigEntry.Gui.Tooltip
    public String confirmDisconnectButtonText = "Confirm Disconnect";

    @ConfigEntry.Gui.Tooltip
    public String cancelButtonText = "Cancel";
}
