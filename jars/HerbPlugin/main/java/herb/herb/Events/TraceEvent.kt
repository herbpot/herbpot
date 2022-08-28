package herb.herb.Events

import org.bukkit.entity.Player
import herb.herb.Argus.GlobalArgus
import org.bukkit.Material
import org.bukkit.event.HandlerList
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.meta.SkullMeta
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class TraceEvent constructor() : Listener {
    @EventHandler
    fun Clicktracer(e: InventoryClickEvent) {
        if ((e.getInventory().getType() == InventoryType.PLAYER)) {
            val p: Player = e.getWhoClicked() as Player
            if (!(p == GlobalArgus.ManHunttarget)) {
                if (e.getSlot() == 8) {
                    p.sendMessage("cancel")
                    e.setCancelled(true)
                }
            }
        } else if ((e.getCurrentItem()!!.getType() == Material.PLAYER_HEAD)) {
            if (e.getCurrentItem() == null) {
                return
            }
            val meta: SkullMeta = e.getCurrentItem()!!.getItemMeta() as SkullMeta
            val p: Player? = meta.getOwningPlayer() as Player?
            val player: Player = e.getWhoClicked() as Player
            player.teleport(p!!.getLocation())
            e.setCancelled(true)
            player.sendMessage(p.getName() + "님에게 이동합니다!")
            HandlerList.unregisterAll(TraceEvent())
        }
    }
}