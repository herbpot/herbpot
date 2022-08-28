package herb.herb.Events

import herb.herb.Argus.GlobalArgus.Companion.resetManHuntArgs
import org.bukkit.entity.Player
import net.kyori.adventure.text.format.TextColor
import org.bukkit.event.player.PlayerChangedWorldEvent
import herb.herb.Argus.GlobalArgus
import org.bukkit.boss.BarColor
import org.bukkit.event.world.PortalCreateEvent
import org.bukkit.entity.EntityType
import java.lang.Runnable
import org.bukkit.event.entity.PlayerDeathEvent
import net.kyori.adventure.title.TitlePart
import org.bukkit.event.player.PlayerMoveEvent
import net.kyori.adventure.text.Component
import org.bukkit.*
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import java.lang.Exception

class ManhuntEvent() : Listener {
    @EventHandler
    fun worp(e: PlayerChangedWorldEvent) {
        if (GlobalArgus.Manhuntgamestart) {
            val p = e.player
            if (GlobalArgus.ManHunttarget == p) {
                val pw = p.world.environment
                if ((pw == World.Environment.NETHER)) {
                    GlobalArgus.Manhuntbar.color = BarColor.RED
                } else if ((pw == World.Environment.NORMAL)) {
                    GlobalArgus.Manhuntbar.color = BarColor.BLUE
                } else if ((pw == World.Environment.THE_END)) {
                    GlobalArgus.Manhuntbar.color = BarColor.PURPLE
                }
            }
        }
    }

    @EventHandler
    fun portalCreate(e: PortalCreateEvent) {
        val entity = e.entity
        if (GlobalArgus.Manhuntgamestart) {
            if ((entity == null)) {
                return
            }
            if ((entity.type == EntityType.ENDER_DRAGON)) {
                GlobalArgus.Manhuntbar.setTitle("Game Set! : " + (System.currentTimeMillis() - GlobalArgus.PlayStartTime).toString())
                GlobalArgus.Manhuntbar.setColor(BarColor.WHITE)
                GlobalArgus.Manhuntgamestart = false
                GlobalArgus.ManHunttarget = null
                Bukkit.getOnlinePlayers().forEach { pl: Player ->
                    if ((pl == GlobalArgus.ManHunttarget)) {
                        pl.sendTitlePart(TitlePart.SUBTITLE, Component.text("승리", TextColor.color(0,0,255)))
                    } else {
                        pl.sendTitlePart(TitlePart.SUBTITLE, Component.text("패배", TextColor.color(255,0,0)))
                    }
                }
                try {
                    val sc = Bukkit.getPluginManager().getPlugin("HerbPack")!!.server.scheduler
                    sc.scheduleSyncDelayedTask((Bukkit.getPluginManager().getPlugin("HerbPack"))!!, Runnable { GlobalArgus.Manhuntbar.removeAll() }, 3 * 20L)
                } catch (ex: Exception) {
                    GlobalArgus.Manhuntbar.removeAll()
                    ex.printStackTrace()
                }
            }
        }
    }

    @EventHandler
    fun killedPlayer(e: PlayerDeathEvent) {
        if (GlobalArgus.Manhuntgamestart) {
            if (GlobalArgus.ManHunttarget == e.player) {
                resetManHuntArgs()
                Bukkit.getOnlinePlayers().forEach { pl: Player ->
                    if ((pl == GlobalArgus.ManHunttarget)) {
                        pl.sendTitlePart(TitlePart.SUBTITLE, Component.text(ChatColor.RED.toString() + "패배"))
                    } else {
                        pl.sendTitlePart(TitlePart.SUBTITLE, Component.text(ChatColor.BLUE.toString() + "승리"))
                    }
                }
                try {
                    val sc = Bukkit.getPluginManager().getPlugin("HerbPack")!!.server.scheduler
                    sc.scheduleSyncDelayedTask((GlobalArgus.plugin)!!, object : Runnable {
                        override fun run() {
                            GlobalArgus.Manhuntbar.removeAll()
                        }
                    }, 3 * 20L)
                } catch (ex: Exception) {
                    GlobalArgus.Manhuntbar.removeAll()
                    //ex.printStackTrace();
                }
            }
        }
    }

    @EventHandler
    fun move(e: PlayerMoveEvent) {
        val p = e.player
        if (GlobalArgus.Manhuntgamestart) {
            if (p.inventory.contains(Material.ENDER_EYE, 3)) {
                p.teleport(Location(Bukkit.getServer().getWorld("THE_END"), (0).toDouble(), (70).toDouble(), (0).toDouble()))
                p.inventory.remove(Material.ENDER_EYE)
                p.inventory.remove(Material.ENDER_EYE)
                p.inventory.remove(Material.ENDER_EYE)
            }
            if (GlobalArgus.ManHunttarget == e.player) {
                val loc = e.to
                var msg = ChatColor.WHITE.toString() + "X : "
                msg += loc.blockX
                msg += ChatColor.WHITE.toString() + " / Z : "
                msg += loc.blockZ
                GlobalArgus.Manhuntbar.setTitle(ChatColor.BOLD.toString() + msg)
                GlobalArgus.Manhuntbar.isVisible = true
            }
        }
    }
}