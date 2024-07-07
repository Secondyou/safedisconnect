package com.secondantidiscconnect;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.client.util.math.MatrixStack;

public class SecondyouNoMissedDisconnectClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ScreenEvents.AFTER_INIT.register((client, screen, scaledWidth, scaledHeight) -> {
            if (screen instanceof GameMenuScreen) {
                screen.buttons.removeIf(button -> button.getMessage().getString().equals("Disconnect"));
                
                screen.addDrawableChild(ButtonWidget.builder(Text.of("Safe Disconnect"), button -> 
                    client.setScreen(new ConfirmDisconnectScreen(screen)))
                    .dimensions(screen.width / 2 - 100, screen.height / 4 + 120 + -16, 200, 20)
                    .build()
                );
            }
        });
    }
}

class ConfirmDisconnectScreen extends Screen {
    private final Screen parent;

    protected ConfirmDisconnectScreen(Screen parent) {
        super(Text.of("Confirm Disconnect"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        this.addDrawableChild(ButtonWidget.builder(Text.of("Confirm Disconnect"), button -> 
            this.client.world.disconnect())
            .dimensions(this.width / 2 - 100, this.height / 6 + 96, 200, 20)
            .build()
        );

        this.addDrawableChild(ButtonWidget.builder(Text.of("Cancel"), button -> 
            this.client.setScreen(this.parent))
            .dimensions(this.width / 2 - 100, this.height / 6 + 120, 200, 20)
            .build()
        );
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 70, 16777215);
        drawCenteredText(matrices, this.textRenderer, Text.of("Are you sure you want to disconnect?"), this.width / 2, 90, 16777215);
        super.render(matrices, mouseX, mouseY, delta);
    }
}