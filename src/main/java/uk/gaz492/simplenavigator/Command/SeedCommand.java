package uk.gaz492.simplenavigator.Command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.client.MinecraftClient;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import uk.gaz492.simplenavigator.SimpleNavigator;

import static com.mojang.brigadier.arguments.StringArgumentType.*;
import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;
import static uk.gaz492.simplenavigator.Command.ClientCommandManager.addClientSideCommand;

public class SeedCommand {

    public static void register(CommandDispatcher<ServerCommandSource> commandDispatcher) {
        addClientSideCommand("setSeed");
        commandDispatcher.register(literal("setSeed")
                .then(argument("seed", greedyString())
                        .executes(ctx -> {
                            MinecraftClient client = MinecraftClient.getInstance();
                            try{
                                long seed = Long.parseLong(getString(ctx, "seed"));
                                ctx.getSource().sendFeedback(new LiteralText(String.format("Set Seed To: %s%s%s", Formatting.GREEN, Formatting.BOLD, seed)), false);
                                SimpleNavigator.seed = seed;
                            }catch (NumberFormatException e){
                                int seed = getString(ctx, "seed").hashCode();
                                SimpleNavigator.seed = seed;
                                ctx.getSource().sendFeedback(new LiteralText(String.format("Set Seed To: %s%s%s", Formatting.GREEN, Formatting.BOLD, seed)), false);
                            }

                            return Command.SINGLE_SUCCESS;
                        })));
//        .executes(ctx -> (int) (SimpleNavigator.seed = Long.parseLong(getString(ctx, "seed"))))));
    }
}
