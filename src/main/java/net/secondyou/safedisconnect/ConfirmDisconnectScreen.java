package net.secondyou.safedisconnect;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.client.gui.DrawContext;

import java.util.function.Consumer;

public class ConfirmDisconnectScreen extends Screen {
    private final Screen parent;
    private final Consumer<ButtonWidget> onConfirm;

    public ConfirmDisconnectScreen(Screen parent, Consumer<ButtonWidget> onConfirm) {
        super(Text.translatable("menu.confirmDisconnect"));
        this.parent = parent;
        this.onConfirm = onConfirm;
    }

    @Override
    protected void init() {
        this.addDrawableChild(ButtonWidget.builder(Text.translatable("gui.yes"), button -> {
            this.onConfirm.accept(button);
            this.client.setScreen(null);
        }).dimensions(this.width / 2 - 155, this.height / 6 + 96, 150, 20).build());

        this.addDrawableChild(ButtonWidget.builder(Text.translatable("gui.no"), button ->
                this.client.setScreen(this.parent)
        ).dimensions(this.width / 2 + 5, this.height / 6 + 96, 150, 20).build());
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);
        context.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, 70, 0xFFFFFF);
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return false;
    }
}