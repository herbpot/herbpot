package herb.herb.commands

import herb.herb.Argus.DiamondMiningArgus
import herb.herb.Argus.GlobalArgus
import org.bukkit.*
import org.bukkit.boss.BarColor
import org.bukkit.boss.BarStyle
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import org.bukkit.entity.Firework
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class diamondminingCommand: TabExecutor {

    fun CountDia(){
        val HasDia:HashMap<Player,Boolean> = hashMapOf()
        Bukkit.getOnlinePlayers().forEach {
            if (HasDia[it] == true){
                val inv = it.inventory
                inv.removeItem(ItemStack(Material.DIAMOND))
                if (inv.getItem(inv.indexOf(ItemStack(Material.DIAMOND)))?.amount == 0){
                    val en = it.world.spawn(it.location,Firework::class.java)
                    en.fireworkMeta.addEffect(FireworkEffect.builder()
                            .with(FireworkEffect.Type.BALL)
                            .flicker(true)
                            .withColor(Color.AQUA)
                            .withFade(Color.BLUE)
                            .build()
                    )
                    HasDia.set(it,false)
                }
                HasDia.set(it,true)
            }

        }
    }

    fun progress() {
        GlobalArgus.sc.scheduleSyncDelayedTask(GlobalArgus.plugin!!, Runnable { // bossbar progress
            while (DiamondMiningArgus.timebar!!.progress != 0.0) {
                Thread.sleep((DiamondMiningArgus.timer / 100).toLong())
                DiamondMiningArgus.timebar!!.progress -= 0.01
            }
            DiamondMiningArgus.timebar!!.isVisible = false
            progress()
        })

    }

    fun timer() {
        GlobalArgus.sc.scheduleSyncDelayedTask(GlobalArgus.plugin!!, Runnable{ // timer
            while (DiamondMiningArgus.timer != 0) {
                Thread.sleep(1000)
                DiamondMiningArgus.timer -= 1
                DiamondMiningArgus.timebar!!.setTitle("남은 시간 : " + DiamondMiningArgus.timer / 60 + " : " + DiamondMiningArgus.timer % 60)

            }
            Bukkit.getOnlinePlayers().forEach {
                it.teleport(DiamondMiningArgus.StartLocation!!)
            }
            CountDia()
        })
        timer()
    }

    override fun onTabComplete(sender: CommandSender, command: Command, alias: String, args: Array<out String>): MutableList<String>? {
        val list:MutableList<String> = ArrayList()
        list.add("600")
        list.add("1800")
        list.add("3600")
        return list
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (!sender.isOp) {
            sender.sendMessage("you are not op")
            return false
        }
        if (args.size == 0) {
            sender.sendMessage("this command need args Ex)startminingdia 3600")
            return false
        }
        var time:Int
        try {
            time = args[0].toInt()
        }catch (e:Exception){
            sender.sendMessage("arg must be number")
            return false
        }

        DiamondMiningArgus.timer = time
        DiamondMiningArgus.StartLocation = (sender as Player).location
        DiamondMiningArgus.timebar = Bukkit.createBossBar("남은 시간 : ",BarColor.BLUE,BarStyle.SOLID)
        DiamondMiningArgus.timebar!!.isVisible = true

        GlobalArgus.wait(3,1)
        GlobalArgus.wait(2,2)
        GlobalArgus.wait(1,3)

        GlobalArgus.sc.scheduleSyncDelayedTask(GlobalArgus.plugin!!,Runnable {
            Bukkit.broadcast(net.kyori.adventure.text.Component.text("시작!"))
            timer()
            progress()
        },80L)

        return true
    }
}