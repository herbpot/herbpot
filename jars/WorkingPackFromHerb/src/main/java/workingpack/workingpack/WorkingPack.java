package workingpack.workingpack;

import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import workingpack.workingpack.commands.SettingCommand;
import workingpack.workingpack.etc.Argus;
import workingpack.workingpack.etc.FtpServer;

import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public final class WorkingPack extends JavaPlugin {

    org.apache.ftpserver.FtpServer ftpserver;
    @Override
    public void onEnable() {
        getYml();

        Argus.plugin = this;
        try {
            Argus.ip = InetAddress.getLocalHost().getHostAddress();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        Argus.ftp.set("directory",getDataFolder().getParent());
        getLogger().info(Argus.ip);
        getLogger().info(String.valueOf(Argus.ftp.getKeys(false)));
        getLogger().info(String.valueOf(Argus.User.getKeys(false)));
        ftpserver = new FtpServer().main();
        getLogger().info("ftp server is online");
        getLogger().info("ip : " + Argus.ip + "port : " + Argus.ftp.get("port"));
        addCommand();
        getLogger().info("commands are included");
        addevent();
    }

    @Override
    public void onDisable() {
        ftpserver.stop();
        setYml();
    }

    private void getYml() {
        Argus.ftp = YamlConfiguration.loadConfiguration(new File(getDataFolder(),"ftp.yml"));
        Argus.User = YamlConfiguration.loadConfiguration(new File(getDataFolder(), "User.yml"));
    }
    private void setYml() {
        try {
            Argus.ftp.save(new File(getDataFolder(),"ftp.yml"));
            Argus.User.save(new File(getDataFolder(), "User.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void addevent() {
        PluginManager pm =getServer().getPluginManager();
//        pm.registerEvents(this,);
    }
    private void addCommand() {
        setTabCommand("setting",new SettingCommand());
    }
    private void setTabCommand(String name, @Nullable TabExecutor executer) {
        getCommand(name).setExecutor(executer);
        getCommand(name).setTabCompleter(executer);

    }
}
