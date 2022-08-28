package DiaMining

import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.boss.BarColor
import org.bukkit.boss.BarStyle
import org.bukkit.boss.BossBar
import org.bukkit.entity.Player

object Argus {
    var timebar: BossBar = Bukkit.createBossBar("남은 시간 : ", BarColor.BLUE, BarStyle.SOLID)
    var timer = 0
    var StartLocation: Location? = null
    var progresssize: Double = 0.0
    var diaplayers: ArrayList<Player> = ArrayList<Player>()
}