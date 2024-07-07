package com.secondantidiscconnect;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;

public class SecondyouNoMissedDisconnect implements ModInitializer {
    public static SecondyouNoMissedDisconnectConfig CONFIG;

    @Override
    public void onInitialize() {
        AutoConfig.register(SecondyouNoMissedDisconnectConfig.class, GsonConfigSerializer::new);
        CONFIG = AutoConfig.getConfigHolder(SecondyouNoMissedDisconnectConfig.class).getConfig();
        System.out.println("Initializing Secondyou No Missed Disconnect mod");
    }
}
