package herb.herb.commands

import org.bukkit.command.TabExecutor
import org.bukkit.entity.Player
import org.bukkit.Bukkit
import org.bukkit.command.ConsoleCommandSender
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import java.util.ArrayList

class tptoCommand constructor() : TabExecutor {
    public override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (sender is ConsoleCommandSender) {
            Bukkit.getLogger().warning("플레이어전용 명령어 입니다!")
            return false
        }
        if (args.size == 0) {
            sender.sendMessage("대상을 지정하세요")
            return false
        }
        if (args.size >= 2) {
            sender.sendMessage("인수가 너무 많습니다")
            return false
        }
        val p: Player?
        val player: Player = sender as Player
        p = Bukkit.getPlayer(args.get(0))
        if (p == null) {
            if ((args.get(0) == "all")) {
                Bukkit.getOnlinePlayers().forEach({ pl: Player -> pl.teleport(player.getLocation()) })
                return true
            }
            sender.sendMessage("존재하지 않는 플레이어 입니다")
            return false
        }
        p.teleport(player.getLocation())
        return true
    }

    public override fun onTabComplete(sender: CommandSender, command: Command, alias: String, args: Array<String>): List<String>? {
        if ((command.getName() == "tpto")) {
            if (args.size == 1) {
                val playerList: MutableList<String> = ArrayList<String>()
                Bukkit.getOnlinePlayers().forEach({ p: Player -> playerList.add(p.getName()) })
                playerList.add("all")
                return playerList
            }
        }
        return null
    }
}