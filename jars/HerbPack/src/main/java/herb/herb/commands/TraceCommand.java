package herb.herb.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;

public class TraceCommand implements CommandExecutor {
    public Inventory UI() {
        @NotNull Inventory inv = Bukkit.createInventory(null, 36, "tracer");
        inv.clear();
        Bukkit.getOnlinePlayers().forEach(p -> {
            ItemStack head = new ItemStack(Material.PLAYER_HEAD);
            SkullMeta headM = (SkullMeta) head.getItemMeta();
            headM.setOwningPlayer(p);
            headM.setPlayerProfile(p.getPlayerProfile());
            headM.setDisplayName(p.getClientBrandName());
            head.setItemMeta(headM);
            inv.setItem(inv.firstEmpty(),head);
        });
        return inv;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)) {return false;}
        Player p = (Player) sender;
        p.openInventory(UI());
        return true;
    }

}
