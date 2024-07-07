package com.secondantidiscconnect;

import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class SecondyouNoMissedDisconnectClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // Add config button to game menu
        ScreenEvents.AFTER_INIT.register((client, screen, scaledWidth, scaledHeight) -> {
            if (screen instanceof GameMenuScreen) {
                screen.addDrawableChild(ButtonWidget.builder(Text.of("No Missed Disconnect Config"), button -> 
                    client.setScreen(AutoConfig.getConfigScreen(SecondyouNoMissedDisconnectConfig.class, screen).get()))
                    .dimensions(screen.width / 2 - 100, screen.height / 4 + 144 + -16, 200, 20)
                    .build()
                );
            }
        });
    }
}
