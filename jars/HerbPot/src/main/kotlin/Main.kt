package herbpot

import org.bukkit.plugin.java.JavaPlugin

class Main: JavaPlugin() {
    override fun onEnable() {
        logger.info("HerbPot is alive!")

        Global.Argus.pm.registerEvents(Global.events(),this)
        getCommand("diamining")!!.setExecutor(DiaMining.command())
        getCommand("diamining")!!.tabCompleter = DiaMining.command()
        getCommand("tracer")!!.setExecutor(Tracer.command())
    }

    override fun onDisable() {
        logger.info("HerbPot is sleep..")
    }
}