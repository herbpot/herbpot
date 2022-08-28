package herb.herb.commands

import org.bukkit.command.TabExecutor
import org.bukkit.entity.Player
import org.bukkit.Bukkit
import org.bukkit.command.ConsoleCommandSender
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import java.lang.Exception
import java.util.ArrayList

class OnlyConsoleCommand constructor() : TabExecutor {
    public override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (sender is ConsoleCommandSender) {
            val tPl: Player?
            tPl = Bukkit.getPlayer(args.get(0))
            if (tPl == null){
                Bukkit.getLogger().warning("존재하지 않는 플레이어 입니다")
                return false

            }
            try {
                tPl.performCommand(args.get(1))
            } catch (e: Exception) {
                Bukkit.getLogger().warning(e.toString())
            }
            return true
        }
        return false
    }

    public override fun onTabComplete(sender: CommandSender, command: Command, alias: String, args: Array<String>): List<String>? {
        if ((command.getName() == "force")) {
            if (args.size == 1) {
                val playerList: MutableList<String> = ArrayList<String>()
                Bukkit.getOnlinePlayers().forEach({ p: Player -> playerList.add(p.getName()) })
                return playerList
            }
        }
        return null
    }
}