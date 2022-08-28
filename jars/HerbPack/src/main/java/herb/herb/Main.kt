package herb.herb

import org.bukkit.plugin.java.JavaPlugin
import herb.herb.Argus.GlobalArgus
import herb.herb.Events.JoinEvent
import herb.herb.commands.tptoCommand
import herb.herb.commands.OnlyConsoleCommand
import herb.herb.commands.TraceCommand
import herb.herb.commands.manhuntCommand
import herb.herb.commands.RegionWarCommand
import herb.herb.commands.diamondminingCommand
import org.bukkit.ChatColor
import java.io.IOException

class Main : JavaPlugin() {
    override fun onEnable() {
        GlobalArgus.pm.registerEvents(JoinEvent(), this)
        getCommand("tpto")!!.setExecutor(tptoCommand())
        getCommand("tpto")!!.tabCompleter = tptoCommand() //tpto
        getCommand("force")!!.setExecutor(OnlyConsoleCommand())
        getCommand("force")!!.tabCompleter = OnlyConsoleCommand() //force
        getCommand("trace")!!.setExecutor(TraceCommand()) //trace
        getCommand("manhunt")!!.setExecutor(manhuntCommand())
        getCommand("manhunt")!!.tabCompleter = manhuntCommand() //manhunt
        getCommand("regionwar")!!.setExecutor(RegionWarCommand())
        getCommand("regionwar")!!.tabCompleter = RegionWarCommand() //regionwar
        getCommand("diamining")!!.setExecutor(diamondminingCommand())
        getCommand("diamining")!!.tabCompleter = diamondminingCommand()

        /*
        getCommand("setuserconfig").setExecutor(new SetUserConfigCommand());
        getCommand("setuserconfig").setTabCompleter(new SetUserConfigCommand());

        getCommand("showuserconfig").setExecutor(new ShowUserConfigCommand());
        getCommand("showuserconfig").setTabCompleter(new ShowUserConfigCommand());
         */logger.info("====================")
        logger.info("The " + ChatColor.AQUA + "Herb" + ChatColor.RESET + "is" + ChatColor.BLUE + "alive" + ChatColor.RESET)
        logger.info("====================\n\n\n")
        //        String WarningMessage = ChatColor.RED + "Warning: /startRanmon 명령어는 플레이어가 위치한 청크내부의 엔티티들을 전부 죽입니다 ";
//        getLogger().warning(ChatColor.BOLD + WarningMessage + "\n\n\n\n");
    }

    override fun onDisable() {
        try {
            logger.info("====================")
            logger.info("The " + ChatColor.AQUA + "Herb" + ChatColor.RESET + "is" + ChatColor.RED + "dead" + ChatColor.RESET)
            logger.info("====================\n\n\n")
        } catch (e: IOException) {
            e.printStackTrace()
            logger.warning("플러그인의 정보가 저장되지 않았습니다")
            logger.info("====================")
            logger.info("The " + ChatColor.AQUA + "Herb" + ChatColor.RESET + "is" + ChatColor.RED + "dead" + ChatColor.RESET)
            logger.info("====================\n\n\n")
        }
    }
}