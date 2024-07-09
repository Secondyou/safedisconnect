package net.secondyou.safedisconnect;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.client.gui.DrawContext;

public class ConfirmDisconnectScreen extends Screen {
    private final Screen parent;
    private final Runnable onConfirm;

    public ConfirmDisconnectScreen(Screen parent, Runnable onConfirm) {
        super(Text.translatable("menu.confirmDisconnect"));
        this.parent = parent;
        this.onConfirm = onConfirm;
    }

    @Override
    protected void init() {
        super.init();
        this.addDrawableChild(ButtonWidget.builder(Text.translatable("menu.confirm"), button -> {
            this.onConfirm.run();
            MinecraftClient.getInstance().setScreen(this.parent);
        }).dimensions(this.width / 2 - 100, this.height / 4 + 120, 200, 20).build());

        this.addDrawableChild(ButtonWidget.builder(Text.translatable("menu.cancel"), button -> {
            MinecraftClient.getInstance().setScreen(this.parent);
        }).dimensions(this.width / 2 - 100, this.height / 4 + 150, 200, 20).build());
    }

    @Override
    public void render(DrawContext drawContext, int mouseX, int mouseY, float delta) {
        this.renderBackground(drawContext);
        drawContext.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, this.height / 2 - 50, 0xFFFFFF);
        super.render(drawContext, mouseX, mouseY, delta);
    }

    private void renderBackground(DrawContext drawContext) {
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return false;
    }
}
