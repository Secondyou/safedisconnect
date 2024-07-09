package net.secondyou.safedisconnect.mixin;

import net.minecraft.client.gui.DrawContext;
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

    @Inject(method = "initWidgets", at = @At("TAIL"))
    private void injectCustomDisconnectButton(CallbackInfo ci) {
        this.children().removeIf(child -> child instanceof ButtonWidget &&
                ((ButtonWidget) child).getMessage().getString().equals("Disconnect"));

        this.addDrawableChild(ButtonWidget.builder(Text.literal("Safe Disconnect"), (button) ->
                        this.client.setScreen(new ConfirmDisconnectScreen(this, (b) -> this.client.world.disconnect()) {
                            @Override
                            public void renderBackground(DrawContext context) {

                            }
                        }))
                .dimensions(this.width / 2 - 102, this.height / 4 + 120 + -16, 204, 20)
                .build());
    }
}