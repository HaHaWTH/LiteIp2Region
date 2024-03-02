package io.wdsj.liteip2region.command;

import io.wdsj.liteip2region.setting.Settings;
import io.wdsj.liteip2region.util.CacheUtils;
import io.wdsj.liteip2region.util.PersistDataUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static io.wdsj.liteip2region.LiteIp2Region.settingsManager;

public class ConstructCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("reload") && (sender.hasPermission("ip2region.reload") || sender instanceof ConsoleCommandSender)) {
                settingsManager.reload();
                sendMessage(sender, settingsManager.getProperty(Settings.RELOAD_MESSAGE));
                return true;
            }
            if (args[0].equalsIgnoreCase("reload")) {
                sendMessage(sender, settingsManager.getProperty(Settings.NO_PERMISSION));
                return true;
            }
            if (args[0].equalsIgnoreCase("show") && (sender.hasPermission("ip2region.show") || sender instanceof ConsoleCommandSender)) {
                if (!(sender instanceof Player)) {
                    sendMessage(sender, settingsManager.getProperty(Settings.FOR_PLAYER_ONLY));
                    return true;
                }
                Player player = (Player) sender;
                CacheUtils.addShowIpStatus(player.getUniqueId(), true);
                PersistDataUtils.savePlayerData(player, true);
                sendMessage(sender, settingsManager.getProperty(Settings.SHOW_MESSAGE));
                return true;
            }
            if (args[0].equalsIgnoreCase("show")) {
                sendMessage(sender, settingsManager.getProperty(Settings.NO_PERMISSION));
                return true;
            }
            if (args[0].equalsIgnoreCase("hide") && (sender.hasPermission("ip2region.hide") || sender instanceof ConsoleCommandSender)) {
                if (!(sender instanceof Player)) {
                    sendMessage(sender, settingsManager.getProperty(Settings.FOR_PLAYER_ONLY));
                    return true;
                }
                Player player = (Player) sender;
                CacheUtils.addShowIpStatus(player.getUniqueId(), false);
                PersistDataUtils.savePlayerData(player, false);
                sendMessage(sender, settingsManager.getProperty(Settings.HIDE_MESSAGE));
                return true;
            }
            if (args[0].equalsIgnoreCase("hide")) {
                sendMessage(sender, settingsManager.getProperty(Settings.NO_PERMISSION));
                return true;
            }
            if (args[0].equalsIgnoreCase("toggle") && (sender.hasPermission("ip2region.toggle") || sender instanceof ConsoleCommandSender)) {
                if (!(sender instanceof Player)) {
                    sendMessage(sender, settingsManager.getProperty(Settings.FOR_PLAYER_ONLY));
                    return true;
                }
                Player player = (Player) sender;
                boolean show = CacheUtils.getShowIpStatus(player.getUniqueId());
                if (show) {
                    CacheUtils.addShowIpStatus(player.getUniqueId(), false);
                    PersistDataUtils.savePlayerData(player, false);
                    sendMessage(sender, settingsManager.getProperty(Settings.HIDE_MESSAGE));
                } else {
                    CacheUtils.addShowIpStatus(player.getUniqueId(), true);
                    PersistDataUtils.savePlayerData(player, true);
                    sendMessage(sender, settingsManager.getProperty(Settings.SHOW_MESSAGE));
                }
                return true;
            }
            if (args[0].equalsIgnoreCase("toggle")) {
                sendMessage(sender, settingsManager.getProperty(Settings.NO_PERMISSION));
                return true;
            }
        }
        sendMessage(sender, settingsManager.getProperty(Settings.COMMAND_NOT_FOUND));
        return true;
    }

    private void sendMessage(CommandSender commandSender, String message) {
        commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }
}
