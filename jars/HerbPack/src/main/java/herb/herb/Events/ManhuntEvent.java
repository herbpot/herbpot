package herb.herb.Events;

import herb.herb.utilitys.GlobalArgus;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.TitlePart;
import org.bukkit.*;
import org.bukkit.boss.BarColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.world.PortalCreateEvent;
import org.bukkit.scheduler.BukkitScheduler;
import org.jetbrains.annotations.NotNull;

import static org.bukkit.Bukkit.getServer;


public class ManhuntEvent implements Listener {

    @EventHandler
    public void worp(PlayerChangedWorldEvent e){
        GlobalArgus Gargs = new GlobalArgus();
        if (Gargs.Manhuntgamestart){
            @NotNull Player p = e.getPlayer();
            if (Gargs.ManHunttarget.contains(p)){
                @NotNull World.Environment pw = p.getWorld().getEnvironment();
                if(pw.equals(World.Environment.NETHER)){
                    Gargs.Manhuntbar.get(p).setColor(BarColor.RED);
                } else if(pw.equals(World.Environment.NORMAL)){
                    Gargs.Manhuntbar.get(p).setColor(BarColor.BLUE);
                } else if(pw.equals(World.Environment.THE_END)){
                    Gargs.Manhuntbar.get(p).setColor(BarColor.PURPLE);
                }
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
                Gargs.Manhuntbar.keySet().forEach(k -> Gargs.Manhuntbar.get(k).setTitle("Game Set! : "+String.valueOf(System.currentTimeMillis() - Gargs.PlayStartTime)));
                Gargs.Manhuntbar.keySet().forEach(k -> Gargs.Manhuntbar.get(k).setColor(BarColor.WHITE));
                Gargs.Manhuntgamestart = false;
                Gargs.ManHunttarget = null;
                Bukkit.getOnlinePlayers().forEach(pl -> {
                    if(pl.equals(Gargs.ManHunttarget)){
                        pl.sendTitle(ChatColor.BLUE+"승리"," ");
                    }else{
                        pl.sendTitle(ChatColor.RED+"패배"," ");
                    }
                });
                try {
                    BukkitScheduler sc = Bukkit.getPluginManager().getPlugin("HerbPack").getServer().getScheduler();
                    sc.scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("HerbPack"), new Runnable() {
                        @Override
                        public void run() {
                            Gargs.Manhuntbar.keySet().forEach(k -> Gargs.Manhuntbar.get(k).removeAll());
                        }
                    },3*20L);
                } catch (Exception ex) {
                    Gargs.Manhuntbar.keySet().forEach(k -> Gargs.Manhuntbar.get(k).removeAll());
                    ex.printStackTrace();
                }

            }
        }
    }

    @EventHandler
    public void killedPlayer(PlayerDeathEvent e){
        GlobalArgus Gargs = new GlobalArgus();
        if (Gargs.Manhuntgamestart){
            if(Gargs.ManHunttarget.contains(e.getPlayer())){
                Gargs.ManHunttarget.remove(e.getPlayer());
                if (Gargs.ManHunttarget.size() != 0) return;
                Gargs.resetManHuntArgs();
                Bukkit.getOnlinePlayers().forEach(pl -> {
                    if(pl.equals(Gargs.ManHunttarget)){
                        pl.sendTitlePart(TitlePart.SUBTITLE, Component.text(ChatColor.RED+"패배"));
                    }else{
                        pl.sendTitlePart(TitlePart.SUBTITLE,Component.text(ChatColor.BLUE+"승리"));
                    }
                });
                try {

                    BukkitScheduler sc = Bukkit.getPluginManager().getPlugin("HerbPack").getServer().getScheduler();
                    sc.scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("HerbPack"), new Runnable() {
                        @Override
                        public void run() {
                            Gargs.Manhuntbar.keySet().forEach(k -> Gargs.Manhuntbar.get(k).removeAll());
                        }
                    },3*20L);
                } catch (Exception ex) {
                    Gargs.Manhuntbar.keySet().forEach(k -> Gargs.Manhuntbar.get(k).removeAll());
                    //ex.printStackTrace();
                }
            }
        }
    }


    @EventHandler
    public void move(PlayerMoveEvent e){
        GlobalArgus Gargs =  new GlobalArgus();
        Player p = e.getPlayer();
        if(Gargs.Manhuntgamestart) {
            if(p.getInventory().contains(Material.ENDER_EYE,3)){
                p.teleport(new Location(getServer().getWorld("THE_END"),0,70,0));
                p.getInventory().remove(Material.ENDER_EYE);
                p.getInventory().remove(Material.ENDER_EYE);
                p.getInventory().remove(Material.ENDER_EYE);
            }
            if (Gargs.ManHunttarget.contains(e.getPlayer())) {
                @NotNull Location loc = e.getTo();
                String msg = ChatColor.WHITE + "X : ";
                msg += loc.getBlockX();
                msg += ChatColor.WHITE + " / Z : ";
                msg += loc.getBlockZ();
                Gargs.Manhuntbar.get(p).setTitle(ChatColor.BOLD + msg);
                Gargs.Manhuntbar.get(p).setVisible(true);
            }
        }
    }



}
