package herb.herb.Events;

import herb.herb.utilitys.GlobalArgus;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {
    @EventHandler
    public void join(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        new GlobalArgus().UserMap.put(p.getUniqueId(), e.getPlayer());

        if (p.isOp()){
            String inner = ChatColor.LIGHT_PURPLE + p.getCustomName() + ChatColor.AQUA + "님 입장!";
            Bukkit.getOnlinePlayers().forEach(pl -> {
                pl.sendTitle(inner," ");
            });
        }else {
            String inner = ChatColor.RED + p.getCustomName() + ChatColor.YELLOW + "님이 입장했습니다!";
            Bukkit.broadcast(Component.text(inner));
        }
    }
}
