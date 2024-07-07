package com.secondantidiscconnect;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.client.util.math.MatrixStack;

public class ConfirmDisconnectScreen extends Screen {
    private final Screen parent;

    public ConfirmDisconnectScreen(Screen parent) {
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
