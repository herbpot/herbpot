package herb.herb.commands

import herb.herb.Argus.GlobalArgus.Companion.wait
import org.bukkit.command.TabExecutor
import herb.herb.Argus.GlobalArgus
import herb.herb.Events.ManhuntEvent
import org.bukkit.entity.Player
import java.lang.Runnable
import net.kyori.adventure.title.TitlePart
import net.kyori.adventure.text.Component
import org.bukkit.*
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import java.lang.Exception
import java.util.function.Consumer
import kotlin.collections.ArrayList

class manhuntCommand() : TabExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        GlobalArgus.pm.registerEvents(ManhuntEvent(), (GlobalArgus.plugin)!!)
        var p:Player? = Bukkit.getPlayer(args[0])
        if (p == null){
            sender.sendMessage("존재하지 않는 플레이어입니다")
        }else{
            Bukkit.getOnlinePlayers().forEach { pl: Player ->
                pl.teleport(p.getLocation())
                pl.setGameMode(GameMode.CREATIVE)
            }
            val sc = Bukkit.getPluginManager().getPlugin("HerbPack")!!.server.scheduler
            sc.scheduleSyncDelayedTask((Bukkit.getPluginManager().getPlugin("HerbPack"))!!, Runnable {
                wait(3, 1)
                wait(2, 2)
                wait(1, 3)
                Bukkit.getOnlinePlayers().forEach { pl: Player -> pl.sendTitlePart(TitlePart.SUBTITLE, Component.text(ChatColor.YELLOW.toString() + "시작")) } }, 20L)
        }
        GlobalArgus.ManHunttarget = p
        GlobalArgus.Manhuntgamestart = true
        Bukkit.getOnlinePlayers().forEach { pl: Player ->
            pl.setGameMode(GameMode.SURVIVAL)
            GlobalArgus.Manhuntbar.addPlayer(pl)
        }
        GlobalArgus.PlayStartTime = System.currentTimeMillis()
        return true
    }

    override fun onTabComplete(sender: CommandSender, command: Command, alias: String, args: Array<String>): List<String>? {
        if ((command.name == "startmanhunt")) {
            if (args.size == 1) {
                val playerList: MutableList<String> = ArrayList<String>()
                Bukkit.getOnlinePlayers().forEach { p: Player -> playerList.add(p.getName()) }
                return playerList
            } else if (args.size == 2) {
                val typeList: MutableList<String> = ArrayList<String>()
                GlobalArgus.ServerConfig!!.getKeys(true)
                typeList.add("nomal")
                typeList.add("randomEffect")
                return typeList
            }
        }
        return null
    }
}