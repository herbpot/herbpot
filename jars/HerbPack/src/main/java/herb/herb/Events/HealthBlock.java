package herb.herb.Events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HealthBlock implements Listener {
    Plugin plugin;
    @EventHandler
    public void hitEvent(EntityDamageByEntityEvent e){
        if (e.getDamager() instanceof Player){
            Player p = (Player) e.getDamager();
            if ((boolean) p.getMetadata("disattack").get(0).value()){
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void BreakBlock(BlockBreakEvent e){
        Player p = e.getPlayer();
        Map<Material,String> targets = (Map<Material, String>) p.getMetadata("TargetBlocks").get(0).value();
        try{
            p.removeMetadata(targets.get(e.getBlock().getType()),plugin);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
