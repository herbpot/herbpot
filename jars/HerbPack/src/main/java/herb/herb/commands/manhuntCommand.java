package herb.herb.commands;

import herb.herb.utilitys.GlobalArgus;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.ArrayList;
import java.util.List;

public class manhuntCommand implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        GlobalArgus Gargs = new GlobalArgus();
        Player p = Bukkit.getPlayer(args[0]);
        try {
            Bukkit.getOnlinePlayers().forEach(pl -> {
                pl.teleport(p.getLocation());
                pl.setGameMode(GameMode.CREATIVE);
                pl.sendTitle("3"," ");
            });
            Thread.sleep(1000);
            Bukkit.getOnlinePlayers().forEach(pl -> {pl.sendTitle("2"," ");});
            Thread.sleep(1000);
            Bukkit.getOnlinePlayers().forEach(pl -> {pl.sendTitle("1"," ");});
            Thread.sleep(1000);
            Bukkit.getOnlinePlayers().forEach(pl -> {pl.sendTitle(ChatColor.YELLOW+"시작"," ");});
        } catch (InterruptedException e) {

        }
        Gargs.ManHunttarget = p;
        Gargs.Manhuntgamestart = true;
        Bukkit.getOnlinePlayers().forEach(pl -> {
            pl.setGameMode(GameMode.SURVIVAL);
            Gargs.Manhuntbar.addPlayer(pl);
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
                typeList.add("nomal");
                typeList.add("randomEffect");
                return typeList;
            }
        }
        return null;
    }
}
