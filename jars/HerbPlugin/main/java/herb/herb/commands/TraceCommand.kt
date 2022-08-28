package herb.herb.commands

import org.bukkit.command.TabExecutor
import herb.herb.Argus.GlobalArgus
import org.bukkit.entity.Player
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.meta.SkullMeta
import herb.herb.Events.TraceEvent
import net.kyori.adventure.text.Component
import org.bukkit.command.Command
import org.bukkit.command.CommandSender

class TraceCommand constructor() : TabExecutor {
    fun UI(): Inventory {
        val pls: Collection<Player> = Bukkit.getOnlinePlayers()
        val inv: Inventory = Bukkit.createInventory(null, pls.size, GlobalArgus.TracerName)
        inv.clear()
        pls.forEach({ p: Player ->
            val head: ItemStack = ItemStack(Material.PLAYER_HEAD)
            val headM: SkullMeta = head.getItemMeta() as SkullMeta
            headM.setOwningPlayer(p)
            headM.setPlayerProfile(p.getPlayerProfile())
            headM.displayName(Component.text((p.getClientBrandName())!!))
            head.setItemMeta(headM)
            inv.setItem(inv.firstEmpty(), head)
        })
        return inv
    }

    public override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        GlobalArgus.pm.registerEvents(TraceEvent(), (GlobalArgus.plugin)!!)
        if (sender is Player) {
            sender.openInventory(UI())
            GlobalArgus.pm.registerEvents(TraceEvent(), GlobalArgus.plugin)
            return true
        } else {
            Bukkit.getLogger().warning("플레이어 전용 명령어 입니다!")
            return false
        }
    }

    public override fun onTabComplete(sender: CommandSender, command: Command, alias: String, args: Array<String>): List<String>? {
        return null
    }
}