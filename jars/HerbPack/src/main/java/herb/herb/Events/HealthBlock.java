package herb.herb.Events;

import herb.herb.utilitys.GlobalArgus;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;


public class HealthBlock implements Listener {
    @EventHandler
    public void BreakBlock(BlockBreakEvent e){
        Player p = e.getPlayer();
        if (GlobalArgus.HealthBlock) {
            if (GlobalArgus.UserConfig.getComments(p.getUniqueId() + "." + GlobalArgus.HealthBlockName).contains(e.getBlock().getType())) {
                p.setHealth(p.getHealth() + 1);
            }
        }
    }
}
