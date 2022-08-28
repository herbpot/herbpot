package herb.herb.commands

import herb.herb.Argus.GlobalArgus
import herb.herb.Argus.InvLockMonArgus
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import kotlin.random.Random

class InvLockMon: TabExecutor {
    override fun onTabComplete(sender: CommandSender, command: Command, alias: String, args: Array<out String>): MutableList<String>? {
        val list:MutableList<String> = listOf<String>("sda","dsad") as MutableList<String>
        return list
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {

//        GlobalArgus.pm.registerEvents()

        if (!sender.isOp) return false
        Bukkit.getOnlinePlayers().forEach{
            val inv: Inventory = it.inventory
            inv.forEach{
                if (inv.indexOf(it) != 0) inv.setItem(inv.indexOf(it) , ItemStack(Material.BARRIER))
            }
        }
        repeat((sender as Player).inventory.size-1) {
            InvLockMonArgus.Monlist?.add(EntityType.values()[Random.nextInt(0,EntityType.values().size-1)])
        }
        return true
    }
}