package io.wdsj.liteip2region.placeholder;

import com.github.houbb.heaven.util.lang.StringUtil;
import io.wdsj.liteip2region.LiteIp2Region;
import io.wdsj.liteip2region.setting.Settings;
import io.wdsj.liteip2region.util.CacheUtils;
import io.wdsj.liteip2region.util.Utils;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static io.wdsj.liteip2region.LiteIp2Region.settingsManager;

public class PlaceHolderPlugin extends PlaceholderExpansion {
    private final LiteIp2Region plugin;

    public PlaceHolderPlugin(LiteIp2Region plugin) {
        this.plugin = plugin;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "ip2region";
    }

    @Override
    public String onRequest(OfflinePlayer player, @NotNull String identifier) {
        if (player == null) {
            return null;
        }
        // %ip2region_ip%
        if ("ip".equals(identifier)) {
            if (player.getPlayer() == null) {
                return null;
            }
            return Utils.getPlayerIp(player.getPlayer()).equals("null") ? "未知" : Utils.getPlayerIp(player.getPlayer());
        }

        String region = CacheUtils.getIpRegionString(player.getUniqueId());
        if (StringUtil.isEmpty(region)) {
            return "未知";
        }
        List<String> list = Utils.strToStrList(region);
        String national = list.get(0);
        String provincial = list.get(2);
        String municipal = list.get(3);
        String serviceProvider = list.get(4);
        String district = "0";
        if (list.size() > 5) {
            district = list.get(5);
        }
        String unknown = settingsManager.getProperty(Settings.UNKNOWN_REGION);
        String local = settingsManager.getProperty(Settings.LOCAL_IP);

        // 判断是否开启显示
        boolean showEnable = CacheUtils.getShowIpStatus(player.getUniqueId());
        if (!showEnable) {
            return unknown;
        }
        // %ip2region_region%
        if ("region".equals(identifier)) {
            return region;
        }
        // %ip2region_national%
        if ("national".equals(identifier)) {
            return "0".equals(national) ? unknown : national;
        }
        boolean removeProvinceAndCity = settingsManager.getProperty(Settings.REMOVE_PROVINCE_CITY);
        // %ip2region_provincial%
        if ("provincial".equals(identifier)) {
            String provincialStr = "0".equals(provincial) ? unknown : provincial;
            if ("内网IP".equals(provincialStr)) {
                provincialStr = local;
            }
            return removeProvinceAndCity ? provincialStr.replace("省", "") : provincialStr;
        }
        // %ip2region_municipal%
        if ("municipal".equals(identifier)) {
            String municipalStr = "0".equals(municipal) ? unknown : municipal;
            if ("内网IP".equals(municipalStr)) {
                municipalStr = local;
            }
            return removeProvinceAndCity ? municipalStr.replace("市", "") : municipalStr;
        }
        // %ip2region_serviceProvider%
        if ("serviceProvider".equals(identifier)) {
            return "0".equals(serviceProvider) ? unknown : serviceProvider;
        }
        // %ip2region_district%
        if ("district".equals(identifier)) {
            return "0".equals(district) ? unknown : district;
        }
        return null;
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public @NotNull String getAuthor() {
        return plugin.getDescription().getAuthors().toString();
    }

    @Override
    public @NotNull String getVersion() {
        return plugin.getDescription().getVersion();
    }
}
