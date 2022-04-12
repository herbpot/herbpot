package herb.herb.commands;

import herb.herb.utilitys.GlobalArgus;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.bukkit.Bukkit.getLogger;

public class TraceCommand implements TabExecutor {
    public Inventory UI(int i) {
        @NotNull Inventory inv = Bukkit.createInventory(null, 36, new GlobalArgus().TracerName);
        inv.clear();
        int i_ = (i - 1) * 36;
        Bukkit.getOnlinePlayers().forEach(p -> {
            if (i_ == 0) {
                for(int o=36;o != 0;o--){
                    ItemStack head = new ItemStack(Material.PLAYER_HEAD);
                    SkullMeta headM = (SkullMeta) head.getItemMeta();
                    headM.setOwningPlayer(p);
                    headM.setPlayerProfile(p.getPlayerProfile());
                    headM.setDisplayName(p.getClientBrandName());
                    head.setItemMeta(headM);
                    inv.setItem(inv.firstEmpty(), head);
                }
            }
        });
        return inv;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if (args[0].equals(null)) {
                p.openInventory(UI(1));
            }else {
                p.openInventory(UI(Integer.parseInt(args[0])));
            }
            return true;
        }else {
            getLogger().warning("플레이어 전용 명령어 입니다!");
            return false;
        }
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (command.getName().equals("tracer")) {
            if (args.length == 1) {
                List numList = new ArrayList();
                for (int i=1; i>=Bukkit.getOnlinePlayers().size() / 36; i++){
                    numList.add(i);
                }
                return numList;
            }
        }
        return null;
    }
}

