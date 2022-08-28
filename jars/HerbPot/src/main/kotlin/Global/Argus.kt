package Global

import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.Bukkit
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.PluginManager
import org.bukkit.scheduler.BukkitScheduler

object Argus{
    var pm: PluginManager = Bukkit.getPluginManager()
    var plugin: Plugin = pm.getPlugin("HerbPot")!!
    var sc: BukkitScheduler = Bukkit.getScheduler()

    fun wait(time:Int,delay:Int) {
        sc.scheduleSyncDelayedTask(plugin, Runnable {
            Bukkit.broadcast(TextComponent(time.toString()))
        },delay*20L)
    }
}
