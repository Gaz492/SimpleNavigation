package uk.gaz492.simplenavigator.Command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.client.MinecraftClient;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.util.math.BlockPos;

import static uk.gaz492.simplenavigator.Command.ClientCommandManager.*;
import static net.minecraft.server.command.CommandManager.*;

public class PosCommand {

    public static void register(CommandDispatcher<ServerCommandSource> commandDispatcher) {
        addClientSideCommand("pos");
        commandDispatcher.register(literal("pos").executes(c -> {
            MinecraftClient client = MinecraftClient.getInstance();

            BlockPos clientPos = client.player.getBlockPos();
            String text = String.format("X: %s | Y: %s | Z: %s", clientPos.getX(), clientPos.getY(), clientPos.getZ());
            client.player.sendChatMessage(text);
            return Command.SINGLE_SUCCESS;
        }));
    }
}
