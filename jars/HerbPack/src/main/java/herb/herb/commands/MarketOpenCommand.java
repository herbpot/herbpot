package herb.herb.commands;

import herb.herb.utilitys.Item_;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static org.bukkit.Bukkit.getLogger;

public class marketCommand implements CommandExecutor {
    Item_ Item = new Item_();
    public Inventory inv() {
        Inventory i = Bukkit.createInventory(null,36);
        for (int l = 0; l < 2;l++) {
            i.setItem(1, Item.dumy());
        }i.setItem();
        return i;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player){
            Player p = (Player) sender;
            p.openInventory(inv());
        }else{
            getLogger().warning("사용자만 이용가능한 명령어 입니다");
            return false;
        }
        return true;
    }
}
