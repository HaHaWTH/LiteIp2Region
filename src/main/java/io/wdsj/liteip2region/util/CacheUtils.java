package io.wdsj.liteip2region.util;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class CacheUtils {
    private CacheUtils() {}

    private static final ConcurrentHashMap<UUID, Boolean> cache = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<UUID, String> region = new ConcurrentHashMap<>();


    public static Boolean getShowIpStatus(UUID uuid) {
        return cache.getOrDefault(uuid, true);
    }

    public static String getIpRegionString(UUID uuid) {
        return region.get(uuid);
    }

    public static void addIpRegion(UUID uuid, String region) {
        CacheUtils.region.put(uuid, region);
    }

    public static void addShowIpStatus(UUID uuid, Boolean show) {
        CacheUtils.cache.put(uuid, show);
    }

    public static void removeIpRegion(UUID uuid) {
        CacheUtils.region.remove(uuid);
    }

    public static void removeShowIpStatus(UUID uuid) {
        CacheUtils.cache.remove(uuid);
    }
}
