package net.secondyou.safedisconnect.mixin;

import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ButtonWidget.PressAction;
import net.minecraft.text.Text;
import net.minecraft.client.MinecraftClient;
import net.secondyou.safedisconnect.ConfirmDisconnectScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(GameMenuScreen.class)
public class GameMenuScreenMixin {

    @ModifyArg(
            method = "initWidgets()V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/widget/ButtonWidget;builder(Lnet/minecraft/text/Text;Lnet/minecraft/client/gui/widget/ButtonWidget$PressAction;)Lnet/minecraft/client/gui/widget/ButtonWidget$Builder;"
            ),
            index = 1
    )
    private PressAction modifyDisconnectButton(PressAction original) {
        return button -> {
            Text disconnectText = Text.translatable("menu.disconnect");
            if (button.getMessage().getString().equals(disconnectText.getString())) {
                GameMenuScreen thisScreen = (GameMenuScreen) (Object) this;
                MinecraftClient client = MinecraftClient.getInstance();
                client.setScreen(new ConfirmDisconnectScreen(thisScreen, () -> {
                    if (client.world != null) {
                        client.world.disconnect();
                    }
                }));
            } else {
                original.onPress(button);
            }
        };
    }
}
