package herb.herb.Events

import herb.herb.Argus.InvLockMonArgus
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent

class InvLockMon: Listener {
    @EventHandler
    fun interaction(e:PlayerInteractEvent){
        if (e.item?.type == Material.BARRIER && InvLockMonArgus.Actionlist.contains(e.action)) e.isCancelled=true
    }

}