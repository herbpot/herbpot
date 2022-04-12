package herb.herb.commands;

import herb.herb.utilitys.GlobalArgus;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.TitlePart;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class manhuntCommand implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        GlobalArgus Gargs = new GlobalArgus();
        @NotNull List<Player> p = new ArrayList<Player>();
        try {
            p.add(Bukkit.getPlayer(args[0]));
        }catch (Exception e){
            GlobalArgus.ServerConfig.getComments("teams."+args[0]).forEach(pl -> p.add(Bukkit.getPlayer(pl)));
        }
        try {
            Bukkit.getOnlinePlayers().forEach(pl -> {
                pl.teleport(p.get(0).getLocation());
                pl.setGameMode(GameMode.CREATIVE);
            });
            BukkitScheduler sc = Bukkit.getPluginManager().getPlugin("HerbPack").getServer().getScheduler();
            sc.scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("HerbPack"), new Runnable() {
                        @Override
                        public void run() {
                            GlobalArgus.wait(3,1);
                            GlobalArgus.wait(2,2);
                            GlobalArgus.wait(1,3);
                            Bukkit.getOnlinePlayers().forEach(pl -> {pl.sendTitlePart(TitlePart.SUBTITLE, Component.text(ChatColor.YELLOW+"시작"));});
                        }
                    },20L);

        } catch (Exception e) {}
        Gargs.ManHunttarget = p;
        Gargs.Manhuntgamestart = true;
        Bukkit.getOnlinePlayers().forEach(pl -> {
            pl.setGameMode(GameMode.SURVIVAL);
            Gargs.Manhuntbar.keySet().forEach(player -> Gargs.Manhuntbar.get(player).addPlayer(pl));
        });
        Gargs.PlayStartTime = System.currentTimeMillis();

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (command.getName().equals("startmanhunt")){
            if (args.length == 1){
                List playerList = new ArrayList<>();
                Bukkit.getOnlinePlayers().forEach(p -> playerList.add(p.getName()));
                return playerList;
            }else if (args.length == 2){
                List typeList = new ArrayList<>();
                GlobalArgus.ServerConfig.getKeys(true);
                typeList.add("nomal");
                typeList.add("randomEffect");
                return typeList;
            }
        }
        return null;
    }
}
