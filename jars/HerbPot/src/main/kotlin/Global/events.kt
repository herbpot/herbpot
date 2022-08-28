package Global

import org.bukkit.ChatColor
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

class events:Listener {
    @EventHandler
    fun join(e: PlayerJoinEvent){
        e.joinMessage = e.player.displayName + ChatColor.AQUA +"님 입장!"
    }

    @EventHandler
    fun leave(e: PlayerQuitEvent){
        e.quitMessage = e.player.displayName + ChatColor.DARK_AQUA + "님 퇴장!"
    }

    @EventHandler
    fun e(){

    }

}