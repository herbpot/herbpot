package herb.herb.Events

import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.Bukkit
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.Component
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class JoinEvent : Listener {
    @EventHandler
    fun join(e: PlayerJoinEvent) {
        val p = e.player
        Bukkit.broadcast(p.displayName().color(TextColor.color(153, 255, 255)).append(Component.text("님이 입장했습니다!").color(TextColor.color(102, 41, 255))))
    }
}