package io.wdsj.liteip2region.util;

import com.github.houbb.heaven.util.lang.StringUtil;
import org.bukkit.entity.Player;
import org.lionsoul.ip2region.xdb.Searcher;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static io.wdsj.liteip2region.LiteIp2Region.getDataBase;

public class Utils {
    private Utils() {}

    public static List<String> strToStrList(String str) {
        List<String> list = new ArrayList<>();
        if (StringUtil.isEmpty(str)) {
            return list;
        }
        return Arrays.stream(str.split("\\|")).map(String::trim).collect(Collectors.toList());
    }

    public static String getIpRegion(String ip) {
        try {
            Searcher searcher = Searcher.newWithFileOnly(getDataBase().getPath());
            String search = searcher.search(ip);
            searcher.close();
            return search;
        } catch (Exception ignored) {
        }
        return "";
    }


    public static String getPlayerIp(Player player) {
        InetSocketAddress address = player.getAddress();
        if (address != null && address.getAddress() != null) return address.getAddress().getHostAddress();
        return "null";
    }


}