package io.wdsj.liteip2region.listener;

import io.wdsj.liteip2region.LiteIp2Region;
import io.wdsj.liteip2region.util.CacheUtils;
import io.wdsj.liteip2region.util.IpUtils;
import io.wdsj.liteip2region.util.PersistDataUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        LiteIp2Region.getScheduler().runTaskAsynchronously(() -> {
            if (PersistDataUtils.getPlayerShowValue(player)) {
                CacheUtils.addShowIpStatus(player.getUniqueId(), true);
                CacheUtils.addIpRegion(player.getUniqueId(), IpUtils.getPlayerRegion(player));
            } else {
                CacheUtils.addShowIpStatus(player.getUniqueId(), false);
                CacheUtils.addIpRegion(player.getUniqueId(), "");
            }
        });
    }
}
