package uk.gaz492.simplenavigator;

import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.api.ModInitializer;
import net.minecraft.server.command.ServerCommandSource;
import uk.gaz492.simplenavigator.Command.ClientCommandManager;
import uk.gaz492.simplenavigator.Command.PosCommand;
import uk.gaz492.simplenavigator.Command.SeedCommand;

public class SimpleNavigator implements ModInitializer {
    public static long seed = 0;
    @Override
    public void onInitialize() {
    }

    public static void registerCommands(CommandDispatcher<ServerCommandSource> dispatcher){
        ClientCommandManager.clearClientSideCommands();

        PosCommand.register(dispatcher);
        SeedCommand.register(dispatcher);
    }
}
