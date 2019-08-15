package uk.gaz492.simplenavigator.mixin;

import com.mojang.brigadier.StringReader;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.gaz492.simplenavigator.Command.ClientCommandManager;

@Mixin(Screen.class)
public class ScreenMixin {
    @Inject(method = "sendMessage(Ljava/lang/String;Z)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;sendChatMessage(Ljava/lang/String;)V"), cancellable = true)
    public void onSendMessage(String message, boolean addToHistory, CallbackInfo ci) {
        if (message.startsWith("/")) {
            StringReader reader = new StringReader(message);
            reader.skip();
            int cursor = reader.getCursor();
            String commandName = reader.canRead() ? reader.readUnquotedString() : "";
            reader.setCursor(cursor);
            if (ClientCommandManager.isClientSideCommand(commandName)) {
                ClientCommandManager.executeCommand(reader, message);
                ci.cancel();
            }
        }
    }
}
