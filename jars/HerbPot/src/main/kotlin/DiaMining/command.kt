package DiaMining

import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.*
import org.bukkit.boss.BarColor
import org.bukkit.boss.BarStyle
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import org.bukkit.entity.Firework
import org.bukkit.entity.Player
import java.lang.NumberFormatException

class command: TabExecutor {

    private fun CountDia(){
        var players: List<Player> = Argus.diaplayers
        Global.Argus.sc.scheduleSyncDelayedTask(Global.Argus.plugin, object : Runnable {
            override fun run() {
                Bukkit.getLogger().info(players.size.toString())
                Bukkit.getLogger().info("counting dia")
                if (players.size == 1){
                    Bukkit.getLogger().info("counting end")
                    players.forEach { winner: Player ->
                        Bukkit.broadcast(TextComponent(winner.displayName + " 승리!"))
                        val en = winner.world.spawn(winner.location, Firework::class.java)
                        val meta = en.fireworkMeta
                        meta.addEffect(
                            FireworkEffect.builder()
                                .with(FireworkEffect.Type.BALL_LARGE)
                                .flicker(true)
                                .withColor(Color.YELLOW)
                                .withFade(Color.SILVER)
                                .build()
                        )
                        en.fireworkMeta = meta
                        Argus.timebar.isVisible = false
                        return
                    }
                }else {
                    players.forEach {
                        var inv = it.inventory
                        Bukkit.getLogger().info("$it ${inv.first(Material.DIAMOND)}")
                        if (inv.first(Material.DIAMOND) == -1) {
                            players.drop(players.indexOf(it))
                        } else {
                            val en = it.world.spawn(it.location.add(0.0, -0.1, 0.0), Firework::class.java)
                            val meta = en.fireworkMeta
                            meta.addEffect(
                                FireworkEffect.builder()
                                    .with(FireworkEffect.Type.BALL)
                                    .flicker(true)
                                    .withColor(Color.AQUA)
                                    .withFade(Color.BLUE)
                                    .build()
                            )
                            meta.power = 0
                            en.fireworkMeta = meta
                        }
                    }
                }
                CountDia()
            }

        },40L)
    }

    private fun progress() {
        Global.Argus.sc.scheduleSyncDelayedTask(Global.Argus.plugin, object : Runnable { // bossbar progress
            public override fun run() {
                Argus.timebar.progress = Argus.timebar.progress - Argus.progresssize
                if (Argus.timebar.progress <= 0.0) {
                    Bukkit.getLogger().info("progress over")
                    Argus.timebar.color = BarColor.WHITE
                    return
                }
                progress()
            }
        },20L)
    }

    private fun timer() {
        Global.Argus.sc.scheduleSyncDelayedTask(Global.Argus.plugin, object : Runnable{ // timer
            override fun run() {
                if (Argus.timer != 0) {
                    Argus.timer -= 1
                    Argus.timebar.setTitle("남은 시간 : " + Argus.timer / 60 + " : " + Argus.timer % 60)
                    Bukkit.getLogger().info(Argus.timer.toString())
                    timer()
                }else {
                    Bukkit.getLogger().info("time over")
                    Argus.timebar.setTitle("종료")
                    Argus.diaplayers.forEach {
                        it.teleport(Argus.StartLocation!!)
                    }
                    CountDia()
                    return
                }
            }
        },20L)
    }

    override fun onTabComplete(sender: CommandSender, command: Command, alias: String, args: Array<out String>): MutableList<String>? {
        val list:MutableList<String> = ArrayList()
        if (command.name == "diamining"){
            return when(args.size) {
                1 -> {
                    list.add("600")
                    list.add("1800")
                    list.add("3600")
                    list
                }
                else -> {
                    Bukkit.getOnlinePlayers().forEach {
                        list.add(it.name)
                    }
                    list
                }
            }
        }else {
            return null
        }
        return null
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (!sender.isOp) {
            sender.sendMessage("you are not op")
            return false
        }
        if (args.isEmpty()) {
            sender.sendMessage("this command need args Ex)${command.name} 3600")
            return false
        }
        var time:Int
        try {
            time = args[0].toInt()
        }catch (e:Exception){
            sender.sendMessage("arg must be number")
            return false
        }
        val givenplayersString = args.clone().asList()

        givenplayersString.forEach {
            try {
                it.toInt()
            }catch (e:NumberFormatException) {
                val p = Bukkit.getPlayer(it)
                if (p != null) {
                    sender.sendMessage("${p.name}가 게임에 추가되었습니다")
                    Argus.diaplayers.add(p)
                    Argus.timebar.addPlayer(p)
                } else {
                    sender.sendMessage("$it 는 존재하지 않는 플레이어입니다")
                }
            }
        }

        Argus.timer = time
        Argus.progresssize = (1 / time).toDouble()
        Argus.StartLocation = (sender as Player).location
        Argus.timebar.progress = 1.0
        Argus.timebar.isVisible = true
        Argus.StartLocation!!.world.setGameRule(GameRule.KEEP_INVENTORY,true)

        Bukkit.broadcastMessage("솔연님 1100팔 축하드립니다!")
        Global.Argus.wait(3,1)
        Global.Argus.wait(2,2)
        Global.Argus.wait(1,3)

        Global.Argus.sc.scheduleSyncDelayedTask(Global.Argus.plugin,Runnable {
            Bukkit.broadcast(TextComponent("시작!"))
            timer()
            progress()
        },80L)

        return true
    }
}