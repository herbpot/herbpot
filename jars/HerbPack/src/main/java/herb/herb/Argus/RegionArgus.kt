package herb.herb.Argus

import herb.herb.Argus.GlobalArgus
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.Bukkit
import org.bukkit.plugin.PluginManager
import org.bukkit.scheduler.BukkitScheduler
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.World
import java.util.UUID
import org.bukkit.entity.Player
import java.util.HashMap
import org.bukkit.event.HandlerList
import herb.herb.Events.ManhuntEvent
import org.bukkit.enchantments.Enchantment
import org.bukkit.enchantments.EnchantmentTarget
import java.util.Collections
import java.lang.Runnable
import net.kyori.adventure.title.TitlePart
import org.bukkit.Location
import org.bukkit.boss.BarColor
import org.bukkit.boss.BarStyle
import java.util.ArrayList

object RegionArgus {
    @JvmField
    var redTeam = ArrayList<Player>()
    @JvmField
    var blueTeam = ArrayList<Player>()
    @JvmField
    var redloc: Location? = null
    @JvmField
    var blueloc: Location? = null
    @JvmField
    var bar = Bukkit.createBossBar("defualt", BarColor.BLUE, BarStyle.SOLID)
    @JvmField
    var timer = 300
    @JvmField
    var borderSize = 0.0
    @JvmField
    var centerLoc: Location? = null
}