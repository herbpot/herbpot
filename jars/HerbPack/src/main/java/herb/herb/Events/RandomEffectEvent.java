package herb.herb.Events;

import herb.herb.utilitys.GlobalArgus;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Map;

public class RandomEffectEvent implements Listener {
    @EventHandler
    public void damagedPlayer(EntityDamageEvent e) {
        if (new GlobalArgus().randomEffect) {
            if (e.getEntityType().equals(EntityType.PLAYER)) {
                Player p = (Player) e.getEntity();
                int randomNum = (int) Math.floor(Math.random() * 32);
                p.addPotionEffect(new PotionEffect((Map<String, Object>) PotionEffectType.getById(randomNum)));
            }
        }
    }
}
