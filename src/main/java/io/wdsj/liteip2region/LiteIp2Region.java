package io.wdsj.liteip2region;

import ch.jalu.configme.SettingsManager;
import ch.jalu.configme.SettingsManagerBuilder;
import com.github.Anon8281.universalScheduler.UniversalScheduler;
import com.github.Anon8281.universalScheduler.scheduling.schedulers.TaskScheduler;
import com.github.houbb.heaven.util.io.FileUtil;
import io.wdsj.liteip2region.command.ConstructCommand;
import io.wdsj.liteip2region.command.ConstructTabCompleter;
import io.wdsj.liteip2region.listener.PlayerJoinListener;
import io.wdsj.liteip2region.listener.PlayerQuitListener;
import io.wdsj.liteip2region.placeholder.PlaceHolderPlugin;
import io.wdsj.liteip2region.setting.Settings;
import org.bstats.bukkit.Metrics;
import org.bukkit.ChatColor;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Objects;

public final class LiteIp2Region extends JavaPlugin {
    private static LiteIp2Region instance;
    private static TaskScheduler scheduler;
    private final File CONFIG_FILE = new File(getDataFolder(), "config.yml");
    private static File DB;
    public static SettingsManager settingsManager;
    public static LiteIp2Region getInstance() {
        return instance;
    }
    public static TaskScheduler getScheduler() {
        return scheduler;
    }
    public static File getDataBase() {
        return DB;
    }


    @Override
    public void onEnable() {
        instance = this;
        scheduler = UniversalScheduler.getScheduler(this);
        DB = new File(getDataFolder(), "ip2region.xdb");
        settingsManager = SettingsManagerBuilder
                .withYamlFile(CONFIG_FILE)
                .configurationData(Settings.class)
                .useDefaultMigrationService()
                .create();
        if (FileUtil.notExists(DB)) {
            saveResource("ip2region.xdb", false);
        }
        Metrics metrics = new Metrics(this, 21193);
        new PlaceHolderPlugin(this).register();
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(), this);
        Objects.requireNonNull(getCommand("ip2region")).setExecutor(new ConstructCommand());
        Objects.requireNonNull(getCommand("ip2region")).setTabCompleter(new ConstructTabCompleter());
        getLogger().info(ChatColor.GREEN + "LiteIp2Region 已启用");
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll(this);
        new PlaceHolderPlugin(this).unregister();
        Objects.requireNonNull(getCommand("ip2region")).setExecutor(null);
        Objects.requireNonNull(getCommand("ip2region")).setTabCompleter(null);
    }
}
