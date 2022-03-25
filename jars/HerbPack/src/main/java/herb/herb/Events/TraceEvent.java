package herb.herb.Events;

import herb.herb.commands.TraceCommand;
import herb.herb.utilitys.GlobalArgus;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.meta.SkullMeta;

public class TraceEvent implements Listener {
    @EventHandler
    public void Clicktracer(InventoryClickEvent e) {
        if (e.getInventory() instanceof TraceCommand) {
            if(e.getCurrentItem() == null){return;}
            SkullMeta meta = (SkullMeta) e.getCurrentItem().getItemMeta();
            Player p = (Player) meta.getOwningPlayer();
            Player player = (Player) e.getWhoClicked();
            player.teleport(p.getLocation());
            e.setCancelled(true);
            player.sendMessage(p.getName() + "님에게 이동합니다!");
        }
        if (e.getInventory().getType().equals(InventoryType.PLAYER)){
            Player p = (Player) e.getWhoClicked();;
            if (!(p.getUniqueId().equals(new GlobalArgus().ManHunttarget))){
                if(e.getSlot() == 8){
                    p.sendMessage("cancel");
                    e.setCancelled(true);
                }
            }
        }
    }
}
