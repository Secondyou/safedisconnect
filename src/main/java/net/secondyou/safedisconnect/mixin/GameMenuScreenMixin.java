package net.secondyou.safedisconnect.mixin;

import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.secondyou.safedisconnect.ConfirmDisconnectScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameMenuScreen.class)
public class GameMenuScreenMixin extends Screen {

    protected GameMenuScreenMixin(Text title) {
        super(title);
    }

    @Inject(method = "initWidgets", at = @At("RETURN"))
    private void initWidgets(CallbackInfo ci) {
        // Find and replace the disconnect button
        this.children().stream()
                .filter(element -> element instanceof ButtonWidget)
                .map(element -> (ButtonWidget) element)
                .filter(button -> button.getMessage().equals(Text.translatable("menu.disconnect")))
                .findFirst()
                .ifPresent(button -> {
                    int index = this.children().indexOf(button);
                    ButtonWidget newButton = ButtonWidget.builder(Text.translatable("menu.disconnect"), b ->
                                    this.client.setScreen(new ConfirmDisconnectScreen(this, confirmButton -> {
                                        if (this.client.world != null) {
                                            this.client.world.disconnect();
                                        }
                                        this.client.disconnect();
                                    })))
                            .dimensions(button.getX(), button.getY(), button.getWidth(), button.getHeight())
                            .build();
                    this.remove(button);
                    this.addDrawableChild(newButton);
                });
    }
}