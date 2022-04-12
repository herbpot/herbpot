package herb.herb.commands;

import herb.herb.utilitys.GlobalArgus;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SetUserConfigCommand implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender.isOp())) return false;
        try {
            Player targetP;
            try {
                targetP = Bukkit.getPlayer(args[0]);
            } catch (Exception e) {
                sender.sendMessage(ChatColor.RED + "대상 플레이어가 존재하지 않습니다");
                return false;
            }
            GlobalArgus.UserConfig.set(targetP.getUniqueId() + args[1], args[2]);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            sender.sendMessage("플레이어 설정에 해당 경로가 존재하지 않습니다");
            return false;
        }
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (command.getName().equals("setuserconfig")){
            if (args.length == 1){
                ArrayList playerlist = new ArrayList<>();
                Bukkit.getOnlinePlayers().forEach(p -> playerlist.add(p.getName()));
                return playerlist;
            }
        }
        return null;
    }
}
