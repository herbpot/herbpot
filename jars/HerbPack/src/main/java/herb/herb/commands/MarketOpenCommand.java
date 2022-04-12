package herb.herb.commands;

import herb.herb.utilitys.GlobalArgus;
import herb.herb.utilitys.Item_;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import static org.bukkit.Bukkit.getLogger;

public class MarketOpenCommand implements CommandExecutor {
    Item_ Item = new Item_();
    public Inventory inv() {
        Inventory i = Bukkit.createInventory(null,36,GlobalArgus.MarketName);
        for (int l = 0; l <= 8;l++) {
            i.setItem(l, Item.dumy());
        }
        for (String key : GlobalArgus.MarketConfig.getKeys(true)){
            try {
                i.addItem( GlobalArgus.MarketConfig.getItemStack(key));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
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
