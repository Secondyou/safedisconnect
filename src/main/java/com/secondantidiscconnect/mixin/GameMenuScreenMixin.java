package com.secondantidiscconnect.mixin.client;

import com.secondantidiscconnect.ConfirmDisconnectScreen;
import com.secondantidiscconnect.SecondyouNoMissedDisconnect;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameMenuScreen.class)
public class GameMenuScreenMixin {

    @Inject(method = "initWidgets", at = @At("TAIL"))
    private void addSafeDisconnectButton(CallbackInfo ci) {
        if (!SecondyouNoMissedDisconnect.CONFIG.enableSafeDisconnect) return;

        GameMenuScreen screen = (GameMenuScreen)(Object)this;
        
        // Remove the original disconnect button
        screen.buttons.removeIf(button -> button.getMessage().getString().equals("Disconnect"));

        // Add our custom disconnect button
        screen.addDrawableChild(ButtonWidget.builder(Text.of(SecondyouNoMissedDisconnect.CONFIG.safeDisconnectButtonText), button -> 
            screen.client.setScreen(new ConfirmDisconnectScreen(screen)))
            .dimensions(screen.width / 2 - 100, screen.height / 4 + 120 + -16, 200, 20)
            .build()
        );
    }
}
