package herb.herb.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static org.bukkit.Bukkit.getLogger;

public class tptoCommand implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof ConsoleCommandSender) {getLogger().warning("플레이어전용 명령어 입니다!");return false;}
        if (args.length == 0){sender.sendMessage("대상을 지정하세요"); return false;}
        if (args.length >= 2){sender.sendMessage("인수가 너무 많습니다"); return false;}
        Player p;
        Player player = (Player) sender;
        try {
            p = Bukkit.getPlayer(args[0]);
            p.teleport(player.getLocation());
        }catch (NullPointerException e) {
            if (args[0].equals("all")){
                Bukkit.getOnlinePlayers().forEach(pl -> pl.teleport(player.getLocation()));
                return true;
            }
            sender.sendMessage("존재하지 않는 플레이어 입니다");
            return false;
        }

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (command.getName().equals("tpto")){
            if (args.length == 1){
                List playerList = new ArrayList<>();
                Bukkit.getOnlinePlayers().forEach(p -> playerList.add(p.getName()));
                playerList.add("all");
                return playerList;
            }
        }
        return null;
    }
}
