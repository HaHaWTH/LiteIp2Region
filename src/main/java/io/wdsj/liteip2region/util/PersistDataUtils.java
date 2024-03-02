package io.wdsj.liteip2region.util;

import com.github.houbb.heaven.util.io.FileUtil;
import io.wdsj.liteip2region.LiteIp2Region;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class PersistDataUtils {
    private PersistDataUtils(){}

    private static final File dataFolder = new File(LiteIp2Region.getInstance().getDataFolder(), "player");

    public static void savePlayerData(Player player, boolean show) {
        File playerFile = new File(dataFolder, player.getUniqueId() + ".yml");
        if (!playerFile.exists()) {
            FileUtil.createFile(playerFile.getPath());
        }
        FileConfiguration playerData = YamlConfiguration.loadConfiguration(playerFile);
        playerData.set("show", show);
        try {
            playerData.save(playerFile);
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception
        }
    }

    public static boolean getPlayerShowValue(Player player) {
        File playerFile = new File(dataFolder, player.getUniqueId() + ".yml");
        if (!playerFile.exists()) {
            savePlayerData(player, true);
            return true;
        }
        FileConfiguration playerData = YamlConfiguration.loadConfiguration(playerFile);
        return playerData.getBoolean("show", true);
    }
}
