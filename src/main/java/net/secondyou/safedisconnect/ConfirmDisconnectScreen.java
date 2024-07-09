package net.secondyou.safedisconnect;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public abstract class ConfirmDisconnectScreen extends Screen implements ConfirmDisconnectScreenBackground {
    private final Screen parent;
    private final ButtonWidget.PressAction disconnectAction;

    public ConfirmDisconnectScreen(Screen parent, ButtonWidget.PressAction disconnectAction) {
        super(Text.literal("Confirm Disconnect"));
        this.parent = parent;
        this.disconnectAction = disconnectAction;
    }

    @Override
    protected void init() {
        this.addDrawableChild(ButtonWidget.builder(Text.literal("Confirm Disconnect"), (button) -> {
            this.disconnectAction.onPress(button);
        }).dimensions(this.width / 2 - 100, this.height / 2, 200, 20).build());

        this.addDrawableChild(ButtonWidget.builder(Text.literal("Cancel"), (button) -> {
            this.client.setScreen(this.parent);
        }).dimensions(this.width / 2 - 100, this.height / 2 + 30, 200, 20).build());
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context, mouseX, mouseY, delta);
        context.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, 70, 0xFFFFFF);
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public void renderBackground(DrawContext context, int mouseX, int mouseY, float delta) {
        super.renderBackground(context, mouseX, mouseY, delta);
    }
}