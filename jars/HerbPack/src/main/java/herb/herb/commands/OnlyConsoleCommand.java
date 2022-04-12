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

public class OnlyConsoleCommand implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof ConsoleCommandSender){
            Player tPl;
            try {
                tPl = Bukkit.getPlayer(args[0]);
            }catch (Exception e){
                getLogger().warning("존재하지 않는 플레이어 입니다");
                return false;
            }
            try {
                tPl.performCommand(args[1]);
            }catch (Exception e){
                getLogger().warning(String.valueOf(e));
            }

            return true;
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (command.getName().equals("force")){
            if (args.length == 1){
                List playerList = new ArrayList<>();
                Bukkit.getOnlinePlayers().forEach(p -> playerList.add(p.getName()));
                return playerList;
            }
        }
        return null;
    }
}
