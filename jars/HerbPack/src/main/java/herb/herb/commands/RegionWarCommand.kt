package herb.herb.commands

import org.bukkit.command.TabExecutor
import herb.herb.Argus.GlobalArgus
import org.bukkit.entity.Player
import java.lang.Runnable
import herb.herb.Argus.RegionArgus
import herb.herb.Events.RegionWarEvent
import org.bukkit.metadata.FixedMetadataValue
import net.kyori.adventure.text.Component
import org.bukkit.*
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import java.util.ArrayList

class RegionWarCommand constructor() : TabExecutor {
    val beconLocation: Int
        get() {
            return Math.floor(Math.random() * 50).toInt() + 150
        }

    private fun timer() {
        GlobalArgus.sc.scheduleSyncDelayedTask((GlobalArgus.plugin)!!, object : Runnable {
            public override fun run() {
                RegionArgus.timer -= 1
                if (RegionArgus.timer == 0) {
                    RegionArgus.bar.setVisible(false)
                    return
                }
                RegionArgus.bar.setTitle("준비시간 : " + (RegionArgus.timer / 60) + " : " + (RegionArgus.timer % 60))
                timer()
            }
        }, 20L)
    }

    private fun progressbar() {
        GlobalArgus.sc.scheduleSyncDelayedTask((GlobalArgus.plugin)!!, object : Runnable {
            public override fun run() {
                RegionArgus.timer -= 1
                if (RegionArgus.timer == 0) {
                    return
                }
                val now: Double = RegionArgus.bar.getProgress() - 0.01
                if (now == 0.0) {
                    RegionArgus.bar.setVisible(false)
                    return
                }
                RegionArgus.bar.setProgress(now)
                progressbar()
            }
        }, 60L)
    }

    public override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        GlobalArgus.pm.registerEvents(RegionWarEvent(), (GlobalArgus.plugin)!!)
        if (sender.isOp()) {
            RegionArgus.blueTeam = ArrayList()
            RegionArgus.redTeam = ArrayList()
            RegionArgus.timer = 300
            RegionArgus.bar.setProgress(1.0)
            val p: Player = sender as Player
            RegionArgus.centerLoc = p.getLocation()
            RegionArgus.centerLoc!!.setY(0.0)
            val w: World = p.getWorld()
            RegionArgus.redloc = Location(w, beconLocation.toDouble(), (w.getHighestBlockYAt(RegionArgus.centerLoc!!.getBlockX(), RegionArgus.centerLoc!!.getBlockZ()) + 2).toDouble(), beconLocation.toDouble())
            w.setBlockData(RegionArgus.redloc!!, Material.BEACON.createBlockData())
            w.getBlockAt(RegionArgus.redloc!!).setMetadata("team", FixedMetadataValue(GlobalArgus.plugin, "red"))
            RegionArgus.blueloc = Location(w, (-beconLocation).toDouble(), (w.getHighestBlockYAt(RegionArgus.centerLoc!!.getBlockX(), RegionArgus.centerLoc!!.getBlockZ()) + 2).toDouble(), (-beconLocation).toDouble())
            w.setBlockData(RegionArgus.centerLoc!!.add(RegionArgus.blueloc!!), Material.BEACON.createBlockData())
            w.getBlockAt(RegionArgus.blueloc!!).setMetadata("team", FixedMetadataValue(GlobalArgus.plugin, "blue"))
            val plL: Collection<Player> = Bukkit.getOnlinePlayers()
            plL.forEach({ pl: Player ->
                if (RegionArgus.redTeam.size < plL.size / 2 && Math.round(Math.random()) == 0L) {
                    RegionArgus.redTeam.add(pl)
                    pl.sendMessage("you are red team")
                } else {
                    RegionArgus.blueTeam.add(pl)
                    pl.sendMessage("you are blue team")
                }
                pl.teleport(RegionArgus.centerLoc!!.add(0.0, (w.getHighestBlockYAt(RegionArgus.centerLoc!!.getBlockX(), RegionArgus.centerLoc!!.getBlockZ()) + 2).toDouble(), 0.0))
            })
            w.getWorldBorder().setCenter(RegionArgus.centerLoc!!)
            RegionArgus.borderSize = w.getWorldBorder().getSize()
            w.getWorldBorder().setSize(400.0)
            GlobalArgus.plugin.getServer().broadcast(Component.text(ChatColor.BOLD.toString() + "팀 간의 체팅이 분리됩니다"))
            GlobalArgus.sc.scheduleSyncDelayedTask((GlobalArgus.plugin), object : Runnable {
                public override fun run() {
                    RegionArgus.bar.setTitle("준비시간 : ")
                    RegionArgus.bar.setProgress(1.0)
                    Bukkit.getOnlinePlayers().forEach({ pl: Player? -> RegionArgus.bar.addPlayer((pl)!!) })
                    RegionArgus.bar.setVisible(true)
                    timer()
                    progressbar()
                }
            })
            return true
        } else {
            return false
        }
    }

    public override fun onTabComplete(sender: CommandSender, command: Command, alias: String, args: Array<String>): List<String>? {
        return null
    }
}