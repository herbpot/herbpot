package herb.herb.Argus

import org.bukkit.Bukkit
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.Player
import org.bukkit.event.HandlerList
import herb.herb.Events.ManhuntEvent
import org.bukkit.Location
import org.bukkit.boss.BarColor
import org.bukkit.boss.BarStyle
import org.bukkit.boss.BossBar
import java.awt.Component

class GlobalArgus {

    companion object {
        @JvmField
        val plugin = Bukkit.getPluginManager().getPlugin("HerbPack")
        @JvmField
        val pm = plugin!!.server.pluginManager
        @JvmField
        val sc = plugin!!.server.scheduler
        @JvmField
        var ServerConfig: YamlConfiguration? = null
        var world = plugin!!.server.getWorld("World")
        @JvmField
        var loczero:Location = Location(world, (0).toDouble(), world!!.getHighestBlockYAt(0, 0).toDouble(), (0).toDouble())
        @JvmField
        var TracerName: Component = Component.text("추적기")
        @JvmField
        var ManHunttarget: Player? = null
        @JvmField
        var Manhuntgamestart = false
        @JvmField
        var Manhuntbar: BossBar = Bukkit.createBossBar("defualt",BarColor.BLUE,BarStyle.SOLID)
        @JvmField
        var PlayStartTime: Long = 0
        @JvmStatic
        fun resetManHuntArgs() {
            Manhuntgamestart = false
            ManHunttarget = null
            Manhuntbar = Bukkit.createBossBar("defualt",BarColor.BLUE,BarStyle.SOLID)
            HandlerList.unregisterAll(ManhuntEvent())
        }

        @JvmStatic
        fun wait(time: Int, delay: Int) {
            val sc = Bukkit.getPluginManager().getPlugin("HerbPack")!!.server.scheduler
            sc.scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("HerbPack")!!, {
                Bukkit.getOnlinePlayers().forEach { pl: Player ->
                    pl.sendTitle(time.toString(),"",0,0,0)
                    Bukkit.broadcast(time.toString(),"")
                }
            }, delay * 20L)
        }
    }
}