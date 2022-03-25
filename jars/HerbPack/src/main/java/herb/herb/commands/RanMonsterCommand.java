package herb.herb.commands;

import herb.herb.utilitys.GlobalArgus;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RanMonsterCommand implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        GlobalArgus Gargs = new GlobalArgus();
        Player p = (Player) sender;
        Gargs.RanMonsterStart = true;
        Gargs.RanMonChunk = p.getChunk();
        if (args.length != 1) {
            for (String i : args) {
                try {
                    Gargs.RanMonPlayers.add(Bukkit.getPlayer(i));
                } catch (Exception e) {
                    p.sendMessage(ChatColor.RED + "존재하지 않는 플레이어가 있습니다!");
                    return false;
                }
            }
        } else {
            if (args[0].equals("all")){
                Bukkit.getOnlinePlayers().forEach(pl -> Gargs.RanMonPlayers.add(pl));
            }else {
                p.sendMessage(ChatColor.RED + "존재하지 않는 설정입니다");
                return false;
            }
        }
        p.getWorld().setSpawnFlags(false,false);

        List entitys = Arrays.asList(Gargs.RanMonChunk.getEntities());
        entitys.forEach(entity -> {
            Entity en = (Entity) entity;
            en.remove();
        });
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (args.length == 1){
            List playerList = new ArrayList<>();
            Bukkit.getOnlinePlayers().forEach(p -> playerList.add(p.getName()));
            playerList.add("all");
            return playerList;
        }else if (args.length > 1){
            List playerList = new ArrayList<>();
            Bukkit.getOnlinePlayers().forEach(p -> playerList.add(p.getName()));
            return playerList;
        }
        return null;
    }
}
