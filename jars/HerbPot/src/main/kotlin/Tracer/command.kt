package Tracer

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta

class command: CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (!sender.isOp) {sender.sendMessage("you are not op");return false}
        var players = Bukkit.getOnlinePlayers()
        var size = if (players.size <= 9) 9 else players.size
        var tinv: Inventory = Bukkit.createInventory(null,size,"tracer")
        players.forEach {
            val i = ItemStack(Material.PLAYER_HEAD)
            val meta = i.itemMeta as SkullMeta
            meta.owningPlayer = it
            meta.playerProfile = it.playerProfile
            meta.setDisplayName(it.name)
            meta.lore = listOf("대상에게 이동합니다")
            i.itemMeta = meta
            tinv.addItem(i)
        }
        val p: Player = sender as Player
        p.openInventory(tinv)
        Global.Argus.pm.registerEvents(events(),Global.Argus.plugin)
        return true
    }
}