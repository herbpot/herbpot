package herb.herb.Argus

import herb.herb.Argus.GlobalArgus
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.Bukkit
import org.bukkit.plugin.PluginManager
import org.bukkit.scheduler.BukkitScheduler
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.World
import org.bukkit.entity.Player
import org.bukkit.event.HandlerList
import herb.herb.Events.ManhuntEvent
import org.bukkit.enchantments.Enchantment
import org.bukkit.enchantments.EnchantmentTarget
import java.lang.Runnable
import net.kyori.adventure.title.TitlePart
import org.bukkit.boss.BarColor
import org.bukkit.boss.BarStyle
import org.bukkit.entity.EntityType
import org.bukkit.event.block.Action
import java.util.*
import kotlin.collections.ArrayList

object InvLockMonArgus {
    var Monlist: ArrayList<EntityType>? = ArrayList<EntityType>()
    var Actionlist: List<*> = Arrays.asList(Action.RIGHT_CLICK_AIR, Action.LEFT_CLICK_AIR, Action.LEFT_CLICK_BLOCK, Action.RIGHT_CLICK_BLOCK)
}