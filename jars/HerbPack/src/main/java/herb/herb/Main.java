package herb.herb;

import herb.herb.Events.*;
import herb.herb.commands.*;
import herb.herb.utilitys.GlobalArgus;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        GlobalArgus.MarketConfig = YamlConfiguration.loadConfiguration(new File(getDataFolder(),"Market.yml"));
        GlobalArgus.UserConfig = YamlConfiguration.loadConfiguration(new File(getDataFolder(),"User.yml"));
        GlobalArgus.ServerConfig = YamlConfiguration.loadConfiguration(new File(getDataFolder(),"Server.yml"));

        PluginManager pm = getServer().getPluginManager();

        getCommand("tpto").setExecutor(new tptoCommand());
        getCommand("tpto").setTabCompleter(new tptoCommand());

        getCommand("force").setExecutor(new OnlyConsoleCommand());
        getCommand("force").setTabCompleter(new OnlyConsoleCommand());

        pm.registerEvents(new JoinEvent(),this);

        pm.registerEvents(new RandomEffectEvent(),this);

        pm.registerEvents(new TraceEvent(),this);
        getCommand("trace").setExecutor(new TraceCommand());

        getCommand("startmanhunt").setExecutor(new manhuntCommand());
        getCommand("startmanhunt").setTabCompleter(new manhuntCommand());
        pm.registerEvents(new ManhuntEvent(),this);

        getCommand("market").setExecutor(new MarketOpenCommand());
        getCommand("marketsetitem").setExecutor(new MarketSetCommand());
        getCommand("marketsetitem").setTabCompleter(new MarketSetCommand());
        pm.registerEvents(new MarketEvent(),this);

        getCommand("setuserconfig").setExecutor(new SetUserConfigCommand());
        getCommand("setuserconfig").setTabCompleter(new SetUserConfigCommand());

        getCommand("showuserconfig").setExecutor(new ShowUserConfigCommand());
        getCommand("showuserconfig").setTabCompleter(new ShowUserConfigCommand());

        getCommand("HBlock").setExecutor(new HealthBlockCommand());
        pm.registerEvents(new HealthBlock(),this);

        getLogger().info("====================");
        getLogger().info("The "+ ChatColor.AQUA +"Herb"+ChatColor.RESET+"is"+ ChatColor.BLUE +"alive"+ChatColor.RESET);
        getLogger().info("====================\n\n\n");
//        String WarningMessage = ChatColor.RED + "Warning: /startRanmon 명령어는 플레이어가 위치한 청크내부의 엔티티들을 전부 죽입니다 ";
//        getLogger().warning(ChatColor.BOLD + WarningMessage + "\n\n\n\n");
    }

    @Override
    public void onDisable() {
        GlobalArgus Gargs = new GlobalArgus();
        try {
            Gargs.MarketConfig.save(new File(getDataFolder(),"Market.yml"));
            Gargs.UserConfig.save(new File(getDataFolder(),"User.yml"));
            Gargs.ServerConfig.save(new File(getDataFolder(),"Server.yml"));
            getLogger().info("====================");
            getLogger().info("The "+ ChatColor.AQUA +"Herb"+ChatColor.RESET+"is"+ ChatColor.RED +"dead"+ChatColor.RESET);
            getLogger().info("====================\n\n\n");

        } catch (IOException e) {
            e.printStackTrace();
            getLogger().warning("플러그인의 정보가 저장되지 않았습니다");
            getLogger().info("====================");
            getLogger().info("The "+ ChatColor.AQUA +"Herb"+ChatColor.RESET+"is"+ ChatColor.RED +"dead"+ChatColor.RESET);
            getLogger().info("====================\n\n\n");

        }
    }
}
