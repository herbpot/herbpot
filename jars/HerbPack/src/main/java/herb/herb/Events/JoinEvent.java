package herb.herb.Events;

import herb.herb.utilitys.GlobalArgus;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.TitlePart;
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
        GlobalArgus.UserMap.put(p.getUniqueId(), e.getPlayer());

        if (p.isOp()){
            p.setDisplayName(ChatColor.AQUA+"[관리자]"+ChatColor.RESET+ChatColor.BOLD+p.getName());
            String inner = ChatColor.LIGHT_PURPLE + p.getDisplayName() + ChatColor.AQUA + "님 입장!";
            Bukkit.getOnlinePlayers().forEach(pl -> {
                pl.sendTitlePart(TitlePart.SUBTITLE,Component.text(inner));
            });
        }else {
            String inner = ChatColor.RED + p.getDisplayName() + ChatColor.YELLOW + "님이 입장했습니다!";
            Bukkit.broadcast(Component.text(inner));
        }
        int count = 0;
        try{
            count = (int) GlobalArgus.UserConfig.get(p.getUniqueId() + ".ConnectCount") + 1;
        }catch (Exception er){
            er.printStackTrace();
        }
        GlobalArgus.UserConfig.set(p.getUniqueId()+".ConnectCount",count);
        if (!(GlobalArgus.UserConfig.isSet(p.getUniqueId() + "money"))){
            GlobalArgus.UserConfig.set(p.getUniqueId()+"money",0);
        }

    }
}
