package uk.gaz492.simplenavigator.Hud;

import com.google.common.base.Strings;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import uk.gaz492.simplenavigator.SimpleNavigator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static net.minecraft.client.gui.DrawableHelper.fill;

public class NavigatorHud {

    private static final Random RAND = new Random();

    public static void render() {
        MinecraftClient client = MinecraftClient.getInstance();
        TextRenderer fontRenderer = client.textRenderer;
        BlockPos clientPos = client.player.getBlockPos();
        List<String> textList = new ArrayList<>();
        String horizontalFacing = client.player.getHorizontalFacing().toString();
        int textWidthMax = 0;

        if(!client.options.debugEnabled){
            textList.add(String.format(Formatting.GOLD + "X: %s | Y: %s | Z: %s", clientPos.getX(), clientPos.getY(), clientPos.getZ()));
            textList.add(String.format(Formatting.GOLD + "Direction: %s", horizontalFacing.substring(0, 1).toUpperCase() + horizontalFacing.substring(1)));
            if (canSlimeSpawnAt(clientPos.getX(), clientPos.getZ(), SimpleNavigator.seed) && SimpleNavigator.seed != 0) {
                textList.add(Formatting.DARK_GREEN + "Slime Chunk");
            }
        }

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

    public static boolean canSlimeSpawnAt(int posX, int posZ, long worldSeed) {
        return canSlimeSpawnInChunk(posX >> 4, posZ >> 4, worldSeed);
    }

    public static boolean canSlimeSpawnInChunk(int chunkX, int chunkZ, long worldSeed) {
        long slimeSeed = 987234911L;
        long rngSeed = worldSeed +
                (long) (chunkX * chunkX * 4987142) + (long) (chunkX * 5947611) +
                (long) (chunkZ * chunkZ) * 4392871L + (long) (chunkZ * 389711) ^ slimeSeed;

        RAND.setSeed(rngSeed);

        return RAND.nextInt(10) == 0;
    }
}
