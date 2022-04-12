package herb.herb.commands;

import herb.herb.utilitys.GlobalArgus;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class BlockHealthCommand_ implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        GlobalArgus Gargs = new GlobalArgus();
        Bukkit.getOnlinePlayers().forEach(p -> {
            List Mvalue = (List) Arrays.stream(Material.values()).filter(material -> material.isBlock() && !material.isAir());
            Collections.shuffle(Mvalue);
            Gargs.UserConfig.setComments(p.getUniqueId()+"."+command.getName(),Mvalue.subList(0,20));
        });
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        return null;
    }
}
