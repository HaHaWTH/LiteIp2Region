package io.wdsj.liteip2region.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConstructTabCompleter implements TabCompleter {
    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        Player player = (Player) sender;
        if (args.length == 1) {
            List<String> tabComplete = new ArrayList<>();
            if (player.hasPermission("ip2region.reload") && args[0].startsWith("r")) {
                tabComplete.add("reload");
            } else if (player.hasPermission("ip2region.show") && args[0].startsWith("s")) {
                tabComplete.add("show");
            } else if (player.hasPermission("ip2region.toggle") && args[0].startsWith("t")) {
                tabComplete.add("toggle");
            } else if (player.hasPermission("ip2region.hide") && args[0].startsWith("h")) {
                tabComplete.add("hide");
            } else if (player.hasPermission("ip2region.reload") ||
                    player.hasPermission("ip2region.show") || player.hasPermission("ip2region.toggle") || player.hasPermission("ip2region.hide")) {
                tabComplete.add("hide");
                tabComplete.add("reload");
                tabComplete.add("show");
                tabComplete.add("toggle");
            }
            return tabComplete;
        }
        return Collections.emptyList(); // Must return empty list, if null paper will supply player names
    }
}
