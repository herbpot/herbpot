package herb.herb.Events;

import herb.herb.utilitys.GlobalArgus;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class MarketEvent implements Listener {
    private void sell(ItemStack item, Player p,int count){
        for (int i = 0; i<count; i++) {
            try{
                p.getInventory().removeItem(new ItemStack(item.getType()));
            }catch (Exception e){
                p.sendMessage(ChatColor.RED+"해당 아이템을 가지고 있지 않습니다");
            }
            GlobalArgus.UserConfig.set(p.getUniqueId() + ".money",(Integer) GlobalArgus.UserConfig.get(p.getUniqueId()+".money") + Integer.parseInt(GlobalArgus.MarketConfig.getItemStack(item.getItemMeta().getDisplayName()).getItemMeta().getLore().get(3)));
        }
    }
    private void buy(ItemStack item, Player p,int count){for (int i = 0; i<count; i++){
            try {
                int resultMoney = Integer.parseInt(String.valueOf(GlobalArgus.UserConfig.get(p.getUniqueId() + ".money"))) - Integer.parseInt(GlobalArgus.MarketConfig.getItemStack(item.getItemMeta().getDisplayName()).getItemMeta().getLore().get(3));
                if (resultMoney >= 0) {
                    GlobalArgus.UserConfig.set(p.getUniqueId() + ".money", Integer.parseInt((String)  GlobalArgus.UserConfig.get(p.getUniqueId() + ".money")) - resultMoney);
                    p.sendMessage(String.valueOf(resultMoney));
                    p.sendMessage((Component) GlobalArgus.UserConfig.get(p.getUniqueId()+".money"));
                    p.getInventory().addItem(new ItemStack(item.getType()));
                } else {
                    p.sendMessage(ChatColor.RED + "돈이 부족합니다");
                }
            }catch (NullPointerException e){
                GlobalArgus.UserConfig.set(p.getUniqueId()+ ".money",0);
            }
        }
    }

    @EventHandler
    public void click(InventoryClickEvent e){
        if (e.getView().getTitle().equals(GlobalArgus.MarketName)){
            e.setCancelled(true);
            if (e.getCurrentItem() == null)return;
            if (e.getCurrentItem().getType() == Material.AIR)return;

            if (e.getClick().isLeftClick()){
                if (e.getClick().isShiftClick()){
                    sell(e.getCurrentItem(), (Player) e.getWhoClicked(),32);
                }else {
                    sell(e.getCurrentItem(), (Player) e.getWhoClicked(), 1);
                }
            }else if (e.getClick().isRightClick()){
                if (e.getClick().isShiftClick()){
                    buy(e.getCurrentItem(), (Player) e.getWhoClicked(),32);
                }else {
                    buy(e.getCurrentItem(), (Player) e.getWhoClicked(), 1);
                }
            }
        }
    }
}
