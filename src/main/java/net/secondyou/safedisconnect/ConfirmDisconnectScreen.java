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
            this.client.setScreen(null); // Disconnect
        }).dimensions(this.width / 2 - 155, this.height / 6 + 96, 150, 20).build());

        this.addDrawableChild(ButtonWidget.builder(Text.translatable("gui.no"), button -> {
            this.client.setScreen(this.parent); // Return to parent screen
        }).dimensions(this.width / 2 + 5, this.height / 6 + 96, 150, 20).build());
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        // Render the background manually if needed
        context.fill(0, 0, this.width, this.height, 0xFF000000); // Example: Fill the screen with black color

        // Draw the centered title text
        context.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, 70, 0xFFFFFF);

        // Call the superclass's render method
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return false;
    }
}
