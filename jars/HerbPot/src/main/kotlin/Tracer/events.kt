package Tracer

import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta

class events: Listener {
    @EventHandler
    fun clickinv(e: InventoryClickEvent){
        var selecteditem: ItemStack
        if (e.currentItem != null) {
            selecteditem = e.currentItem!!
        }else return
        if(selecteditem.type != Material.AIR){
            e.isCancelled = true
            var meta = selecteditem.itemMeta as SkullMeta
            e.whoClicked.teleport(meta.owningPlayer!!.player!!.location)
            InventoryClickEvent.getHandlerList().unregister(this)
        }
    }
}