package io.wdsj.liteip2region.setting;

import ch.jalu.configme.Comment;
import ch.jalu.configme.SettingsHolder;
import ch.jalu.configme.properties.Property;

import static ch.jalu.configme.properties.PropertyInitializer.newProperty;

public class Settings implements SettingsHolder {
    @Comment("是否移除省/市(例: 江苏省南京市->江苏南京")
    public static final Property<Boolean> REMOVE_PROVINCE_CITY = newProperty("Settings.removeProvinceAndCity", false);
    @Comment("如果一个IP未找到归属地,应显示为什么")
    public static final Property<String> UNKNOWN_REGION = newProperty("Settings.unknownRegion", "未知");
    @Comment("如果IP为本地IP.应显示为什么")
    public static final Property<String> LOCAL_IP = newProperty("Settings.localIp", "内网IP");
    @Comment("是否启用缓存(有效提升获取速度)")
    public static final Property<Boolean> ENABLE_CACHE = newProperty("Settings.enableCache", true);

    @Comment("没有使用权限")
    public static final Property<String> NO_PERMISSION = newProperty("Messages.noPermission", "&4你没有权限执行该命令");
    @Comment("仅供玩家使用")
    public static final Property<String> FOR_PLAYER_ONLY = newProperty("Messages.forPlayerOnly", "&4只能由玩家使用该命令");
    @Comment("重载消息")
    public static final Property<String> RELOAD_MESSAGE = newProperty("Messages.reloadMessage", "&a重载成功");
    @Comment("显示消息")
    public static final Property<String> SHOW_MESSAGE = newProperty("Messages.showMessage", "&8[&a✔&8] &a显示成功");
    @Comment("隐藏消息")
    public static final Property<String> HIDE_MESSAGE = newProperty("Messages.hideMessage", "&8[&a✔&8] &a隐藏成功");
    @Comment("找不到命令")
    public static final Property<String> COMMAND_NOT_FOUND = newProperty("Messages.commandNotFound", "&4找不到命令");

    private Settings() {
    }
}
