package io.wdsj.liteip2region.listener;

import io.wdsj.liteip2region.util.CacheUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class PlayerQuitListener implements Listener {
    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        CacheUtils.removeIpRegion(uuid);
        CacheUtils.removeShowIpStatus(uuid);
    }
    @EventHandler
    public void onKick(PlayerKickEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        CacheUtils.removeIpRegion(uuid);
        CacheUtils.removeShowIpStatus(uuid);
    }
}
