package uk.gaz492.simplenavigator.Command;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.server.command.ServerCommandSource;

import java.util.Collection;
import java.util.stream.Collectors;

public class FakeCommandSource extends ServerCommandSource {
    public FakeCommandSource(ClientPlayerEntity player) {
        super(player, player.getPosVector(), player.getRotationClient(), null, 0, player.getEntityName(), player.getName(), null, player);
    }

    @Override
    public Collection<String> getPlayerNames() {
        return MinecraftClient.getInstance().getNetworkHandler().getPlayerList()
                .stream().map(e -> e.getProfile().getName()).collect(Collectors.toList());
    }
}
