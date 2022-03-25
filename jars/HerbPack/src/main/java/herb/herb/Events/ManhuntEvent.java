package herb.herb.Events;

import herb.herb.utilitys.GlobalArgus;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.boss.BarColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.world.PortalCreateEvent;
import org.jetbrains.annotations.NotNull;


public class ManhuntEvent implements Listener {

    @EventHandler
    public void worp(PlayerChangedWorldEvent e){
        GlobalArgus Gargs = new GlobalArgus();
        if (Gargs.Manhuntgamestart)
        if (e.getPlayer().equals(Gargs.ManHunttarget)){
            @NotNull World pw = e.getFrom();
            if(pw.equals(World.Environment.NETHER)){
                Gargs.Manhuntbar.setColor(BarColor.RED);
            } else if(pw.equals(World.Environment.NORMAL)){
                Gargs.Manhuntbar.setColor(BarColor.BLUE);
            } else if(pw.equals(World.Environment.THE_END)){
                Gargs.Manhuntbar.setColor(BarColor.PURPLE);
            }
        }
    }

    @EventHandler
    public void portalCreate(PortalCreateEvent e){
        Entity entity = e.getEntity();
        GlobalArgus Gargs = new GlobalArgus();
        if(Gargs.Manhuntgamestart){
            if (entity.equals(null)){return;}
            if(entity.getType().equals(EntityType.ENDER_DRAGON)){
                Gargs.Manhuntbar.setTitle("Game Set! : "+String.valueOf(System.currentTimeMillis() - Gargs.PlayStartTime));
                Gargs.Manhuntbar.setColor(BarColor.WHITE);
                Gargs.Manhuntgamestart = false;
                Gargs.ManHunttarget = null;
                Bukkit.getOnlinePlayers().forEach(pl -> {
                    if(pl.equals(Gargs.ManHunttarget)){
                        pl.sendTitle(ChatColor.BLUE+"승리"," ");
                    }else{
                        pl.sendTitle(ChatColor.RED+"패배"," ");
                    }
                });

            }
        }
    }

    @EventHandler
    public void killedPlayer(PlayerDeathEvent e){
        GlobalArgus Gargs = new GlobalArgus();
        if (Gargs.Manhuntgamestart){
            if(e.getPlayer().equals(Gargs.ManHunttarget)){
                Gargs.Manhuntbar.setTitle("Game Set!");
                Gargs.Manhuntbar.setColor(BarColor.WHITE);
                Gargs.Manhuntgamestart = false;
                Gargs.ManHunttarget = null;
                Bukkit.getOnlinePlayers().forEach(pl -> {
                    if(pl.equals(Gargs.ManHunttarget)){
                        pl.sendTitle(ChatColor.RED+"패배"," ");
                    }else{
                    pl.sendTitle(ChatColor.BLUE+"승리"," ");
                    }
                });
            }
        }
    }

    @EventHandler
    public void move(PlayerMoveEvent e){
        GlobalArgus Gargs =  new GlobalArgus();
        if(Gargs.Manhuntgamestart) {
            if (e.getPlayer().equals(Gargs.ManHunttarget)) {
                String msg = ChatColor.AQUA + "X : ";
                msg += e.getTo().getBlockX();
                msg += ChatColor.AQUA + " / Z : ";
                msg += e.getTo().getBlockZ();
                Gargs.Manhuntbar.setTitle(ChatColor.BOLD + msg);
                Gargs.Manhuntbar.setVisible(true);
            }
        }
    }



}
