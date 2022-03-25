package herb.herb;

import herb.herb.Events.ManhuntEvent;
import herb.herb.commands.RanMonsterCommand;
import herb.herb.commands.TraceCommand;
import herb.herb.commands.manhuntCommand;
import herb.herb.commands.tptoCommand;
import herb.herb.utilitys.con;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {


    @Override
    public void onEnable() {
        getLogger().info(
                "====================\n" +
                        "The Tracer is alive\n" +
                        "====================\n\n\n");
        String WarningMessage = ChatColor.RED + "Warning: /startRanmon 명령어는 플레이어가 위치한 청크내부의 엔티티들을 전부 죽입니다 ";
        getLogger().info(ChatColor.BOLD + WarningMessage + "\n\n\n\n");
        new con().send("online","nelpi.duckdns.org");

        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new ManhuntEvent(),this);
        getCommand("trace").setExecutor(new TraceCommand());
        getCommand("tpto").setExecutor(new tptoCommand());
        getCommand("tpto").setTabCompleter(new tptoCommand());
        getCommand("startmanhunt").setExecutor(new manhuntCommand());
        getCommand("startmanhunt").setTabCompleter(new manhuntCommand());
        getCommand("startRanmon").setExecutor(new RanMonsterCommand());
        getCommand("startRanmon").setTabCompleter(new RanMonsterCommand());

    }

    @Override
    public void onDisable() {
        getLogger().info(
                "====================\n" +
                        "The Tracer is down\n" +
                        "====================\n\n\n");
    }
}
