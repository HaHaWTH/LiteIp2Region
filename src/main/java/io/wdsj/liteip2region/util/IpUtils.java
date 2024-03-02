package io.wdsj.liteip2region.util;

import com.github.houbb.heaven.util.lang.StringUtil;
import org.bukkit.entity.Player;

import static io.wdsj.liteip2region.util.Utils.getPlayerIp;
public class IpUtils {
    public static String getPlayerRegion(Player player) {
        String ip = getPlayerIp(player);
        return Utils.getIpRegion(ip);
    }

    public static String getStr(String str) {
        return StringUtil.isNotEmpty(str) ? str : "0";
    }

}
