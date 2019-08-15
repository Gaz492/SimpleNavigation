package uk.gaz492.simplenavigator.mixin;

import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.gaz492.simplenavigator.Hud.RenderHud;

@Mixin(InGameHud.class)
public class HudMixin {

    RenderHud hud = new RenderHud();

    @Inject(at = @At("RETURN"), method = "render")
    public void render(CallbackInfo info) {
        hud.draw();
    }
}
