package uk.gaz492.simplenavigator.Hud;

import com.google.common.base.Strings;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.List;

import static net.minecraft.client.gui.DrawableHelper.fill;

public class NavigatorHud {

    public static void render() {
        MinecraftClient client = MinecraftClient.getInstance();
        TextRenderer fontRenderer = client.textRenderer;
        BlockPos clientPos = client.player.getBlockPos();
        List<String> textList = new ArrayList<>();
        String horizontalFacing = client.player.getHorizontalFacing().toString();
        int textWidthMax = 0;

        textList.add(String.format(Formatting.GOLD + "X: %s | Y: %s | Z: %s", clientPos.getX(), clientPos.getY(), clientPos.getZ()));
        textList.add(String.format(Formatting.GOLD + "Direction: %s", horizontalFacing.substring(0, 1).toUpperCase() + horizontalFacing.substring(1)));

        for (int i = 0; i < textList.size(); ++i) {
            String text = textList.get(i);
            if (!Strings.isNullOrEmpty(text)) {
                int textWidth = fontRenderer.getStringWidth(text);
                if (textWidthMax < textWidth) {
                    textWidthMax = textWidth;
                }
                if (i + 1 == textList.size()) {
//                    int boxPosLeft = client.window.getScaledWidth() / 2 - (textWidthMax / 2) - 5; // Center
                    int boxPosLeft = client.window.getScaledWidth() - (textWidthMax) - 15; // Right
                    int boxPosTop = 2;
                    int boxPosRight = boxPosLeft + textWidthMax + 10;
                    int boxPosBottom = 15 + 15 * i;

                    fill(boxPosLeft, boxPosTop, boxPosRight, boxPosBottom, client.options.getTextBackgroundColor(-2147483648));
                }
            }
        }

        for (int i = 0; i < textList.size(); ++i) {
            String text = textList.get(i);
            if (!Strings.isNullOrEmpty(text)) {
                int textWidth = fontRenderer.getStringWidth(text);
//                float textPosX = (float) client.window.getScaledWidth() / 2 - ((float) textWidth / 2); // Center
                float textPosX = (float) client.window.getScaledWidth() - 10 - ((float) textWidth); // Right
                float textPosY = 5 + 14 * i;
                fontRenderer.drawWithShadow(text, textPosX, textPosY, 0);
            }
        }
    }
}
