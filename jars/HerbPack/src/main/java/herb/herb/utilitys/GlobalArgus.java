package herb.herb.utilitys;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class GlobalArgus {
    public static Map<UUID, Player> UserMap = new HashMap<UUID, Player>();

    public static Player ManHunttarget;
    public static boolean Manhuntgamestart = false;
    public static BossBar Manhuntbar = Bukkit.getServer().createBossBar("", BarColor.BLUE, BarStyle.SEGMENTED_10);
    public static long PlayStartTime;
    public void resetManHuntArgs(){
        Manhuntgamestart = false;
        ManHunttarget = null;
        Manhuntbar = Bukkit.getServer().createBossBar("", BarColor.BLUE, BarStyle.SEGMENTED_10);
    }

    public static boolean randomEffect = false;

    public static boolean RanMonsterStart = false;
    public static List<Player> RanMonPlayers;
    public static Chunk RanMonChunk;
    public void resetRanMonArgs() {
        RanMonChunk = null;
        RanMonPlayers = null;
        RanMonsterStart = false;
    }
//    public static Map<Integer,String> Events;

}
