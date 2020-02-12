package uk.gaz492.simplenavigator;

import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.client.ClientTickCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.command.ServerCommandSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uk.gaz492.simplenavigator.Command.ClientCommandManager;
import uk.gaz492.simplenavigator.Command.PosCommand;
import uk.gaz492.simplenavigator.Command.SeedCommand;

public class SimpleNavigator implements ModInitializer {

    public static final Logger LOGGER = LogManager.getLogger("Simple Navigator");

    public static long seed = 0;
    public static boolean tapeMeasure = false;

    @Override
    public void onInitialize() {
        LOGGER.info("Simple Navigator Initializing");
    }

    public static void registerCommands(CommandDispatcher<ServerCommandSource> dispatcher){
        ClientCommandManager.clearClientSideCommands();
        LOGGER.info("Registering Client Commands");
        PosCommand.register(dispatcher);
        SeedCommand.register(dispatcher);
    }
}
