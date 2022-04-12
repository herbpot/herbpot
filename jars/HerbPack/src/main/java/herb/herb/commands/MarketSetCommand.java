package herb.herb.commands;

import herb.herb.utilitys.GlobalArgus;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

import static org.bukkit.Bukkit.getLogger;


public class MarketSetCommand implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender.isOp())){
            sender.sendMessage(ChatColor.RED + "해당 명령어는 op만 사용가능합니다");
            return false;
        }
        Player p = (Player) sender;
        ItemStack target;
        int cost;
        try {
            if( args[0].equals("inhand")){
                target = p.getInventory().getItemInMainHand();
            }else {
                target = new ItemStack(Material.getMaterial(args[0]));
            }
        }catch (Exception e){
            sender.sendMessage(ChatColor.RED + "1번째 인수는 inhand 또는 material 종류만 가능합니다");
            return false;
        }
        try {
            cost = Integer.parseInt(args[1]);
        }catch (Exception e){
            sender.sendMessage(ChatColor.RED + "2번째 인수는 숫자만 가능합니다");
            return false;
        }

        GlobalArgus Gargs = new GlobalArgus();
        target = Gargs.MarketMeta(target,cost);
        try {
            Gargs.MarketConfig.set(target.getItemMeta().getDisplayName(), target);
        }catch (Exception e){
            getLogger().warning("error");
            ItemMeta Meta = target.getItemMeta();
            Meta.setDisplayName(target.getType().name());
            target.setItemMeta(Meta);
            Gargs.MarketConfig.set(target.getItemMeta().getDisplayName(), target);
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (command.getName().equals("marketsetitem")){
            if (args.length == 1){
                return Arrays.asList("inhand");
            }else if (args.length == 2){
                return Arrays.asList("1000","2000","3000","4000","5000","6000");
            }
        }
        return null;
    }
}
