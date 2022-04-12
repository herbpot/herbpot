package herb.herb.Events;

import herb.herb.utilitys.GlobalArgus;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.TitlePart;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import java.util.Collections;

public class RanMonsterEvent_ implements Listener {
    @EventHandler
    public void killMonster(EntityDamageByEntityEvent e){
        if(!(new GlobalArgus().RanMonsterStart)){return;}
        if(e.getCause().equals(EntityDamageEvent.DamageCause.FALL)){return;}
        Player p = (Player) e.getDamager();
        if (p.getChunk().getEntities().length == 0){
            EntityType[] EntityTypes = EntityType.values();
            Collections.shuffle(Collections.singletonList(EntityTypes));
            p.getWorld().spawnEntity(p.getLocation(),EntityTypes[0]);
        }
    }

    @EventHandler
    public void PlayerDead(PlayerDeathEvent e){
        if(!(new GlobalArgus().RanMonsterStart)){return;}
        GlobalArgus Gargs = new GlobalArgus();
        Player p = e.getPlayer();
            if (Gargs.RanMonPlayers.contains(p)){
                Gargs.RanMonPlayers.forEach(pl -> {
                    pl.sendTitlePart(TitlePart.SUBTITLE, Component.text(ChatColor.LIGHT_PURPLE +String.valueOf(p.getDisplayName())+ChatColor.RESET+ChatColor.YELLOW+" 탈락!"));
                });
            }
        Gargs.RanMonPlayers.remove(p);
        if (Gargs.RanMonPlayers.size() == 1){
            Gargs.resetRanMonArgs();
            p.playSound(p.getLocation(),Sound.ENTITY_CREEPER_DEATH,SoundCategory.PLAYERS,(float) 1,(float) 0.8);
            Gargs.RanMonPlayers.get(0).spawnParticle(Particle.FIREWORKS_SPARK,Gargs.RanMonPlayers.get(0).getLocation(), 10);
            Bukkit.broadcast(Component.text(Gargs.RanMonPlayers.get(0) + "의 승리!"));
        }
    }
}
