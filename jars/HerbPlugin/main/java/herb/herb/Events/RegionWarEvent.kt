package herb.herb.Events

import org.bukkit.entity.Player
import herb.herb.Argus.GlobalArgus
import org.bukkit.event.world.PortalCreateEvent
import org.bukkit.entity.EntityType
import org.bukkit.event.entity.PlayerDeathEvent
import net.kyori.adventure.title.TitlePart
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.entity.Firework
import org.bukkit.inventory.meta.FireworkMeta
import org.bukkit.event.server.ServerCommandEvent
import herb.herb.Argus.RegionArgus
import org.bukkit.event.HandlerList
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.metadata.FixedMetadataValue
import org.bukkit.event.enchantment.EnchantItemEvent
import org.bukkit.enchantments.Enchantment
import io.papermc.paper.event.player.AsyncChatEvent
import io.papermc.paper.chat.ChatRenderer
import org.bukkit.inventory.meta.ItemMeta
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.event.player.PlayerInteractEvent
import net.kyori.adventure.text.Component
import org.bukkit.*
import org.bukkit.block.Block
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.util.Vector
import java.lang.Exception
import java.util.function.Consumer

class RegionWarEvent constructor() : Listener {
    private fun printWinTeam(team: String, b: Block, color: Color) {
        Bukkit.getOnlinePlayers().forEach{
            it.sendTitlePart(TitlePart.SUBTITLE, Component.text(team + "팀 승리!"))
            it.sendMessage(team + "팀 승리!")
            it.teleport(Location(it.world, (0).toDouble(), (it.world.getHighestBlockYAt(0, 0) + 2).toDouble(), (0).toDouble()))
        }
        val firework: Firework = b.chunk.world.spawnEntity(GlobalArgus.loczero.add(Vector(0, 1, 0)), EntityType.FIREWORK) as Firework
        val fmeta: FireworkMeta = firework.fireworkMeta
        fmeta.power = 127
        fmeta.addEffect(FireworkEffect.builder().withColor(Color.WHITE).withFade(color).withFade().flicker(true).build())
        firework.fireworkMeta = fmeta
        (b.chunk.world.spawnEntity(GlobalArgus.loczero.add(Vector(0, 1, 0)), EntityType.FIREWORK) as Firework).fireworkMeta = fmeta
        GlobalArgus.pm.callEvent(ServerCommandEvent(Bukkit.getConsoleSender(), String.format("setblock %d %d %d minecraft:air", RegionArgus.redloc!!.blockX, RegionArgus.redloc!!.blockY, RegionArgus.redloc!!.blockZ)))
        GlobalArgus.pm.callEvent(ServerCommandEvent(Bukkit.getConsoleSender(), String.format("setblock %d %d %d minecraft:air", RegionArgus.blueloc!!.blockX, RegionArgus.blueloc!!.blockY, RegionArgus.blueloc!!.blockZ)))
        b.world.worldBorder.size = RegionArgus.borderSize
        HandlerList.unregisterAll(RegionWarEvent())
    }

    @EventHandler
    fun BreakSign(e: BlockBreakEvent) {
        val b: Block = e.block
        if (b.type == Material.BEACON) {
            if (RegionArgus.bar.isVisible) {
                e.player.sendMessage("준비시간에는 코어를 부술 수 없습니다.")
                e.isCancelled = true
                return
            }
            when (b.getMetadata("team")[0].value().toString()) {
                "blue" -> if (RegionArgus.blueTeam.contains(e.player)) {
                    e.isCancelled = true
                } else {
                    printWinTeam("red", b, Color.RED)
                }
                "red" -> if (RegionArgus.redTeam.contains(e.player)) {
                    e.isCancelled = true
                } else {
                    printWinTeam("blue", b, Color.BLUE)
                }
                else -> {
                }
            }
        } else {
            try {
                if (!(b.getMetadata("isreplaced")[0].value().toString() == "true")) {
                    e.isDropItems = false
                    b.drops.forEach(Consumer { item: ItemStack ->
                        item.amount = item.amount * 2
                        e.player.world.dropItem(b.location, item)
                    })
                }
            } catch (er: Exception) {
                e.isDropItems = false
                b.drops.forEach(Consumer { item: ItemStack ->
                    item.amount = item.amount * 2
                    e.player.world.dropItem(b.location, item)
                })
            }
        }
    }

    @EventHandler
    fun NoWorldChange(e: PortalCreateEvent) {
        e.isCancelled = true
    }

    @EventHandler
    fun setBlock(e: BlockPlaceEvent) {
        e.block.setMetadata("isreplaced", FixedMetadataValue((GlobalArgus.plugin)!!, "true"))
    }

    @EventHandler
    fun enchant(e: EnchantItemEvent) {
        if (!e.item.type.toString().contains("_")) {
            return
        }
        val item: ItemStack = e.item
        var enchant: Enchantment? = null
        when (item.type.toString().split("_").toTypedArray().get(1)) {
            "SWORD" -> enchant = Enchantment.DAMAGE_ALL
            "HELMET", "PLATE", "LEGGINGS", "BOOTS" -> enchant = Enchantment.PROTECTION_ENVIRONMENTAL
            else -> {
            }
        }
        item.addEnchantment((enchant)!!, 2)
    }

    @EventHandler
    fun walk(e: PlayerMoveEvent) {
        if (e.to.distance((RegionArgus.redloc)!!) <= 10) {
            e.player.sendActionBar(Component.text("RED REGION"))
        } else if (e.to.distance((RegionArgus.blueloc)!!) <= 10) {
            e.player.sendActionBar(Component.text("BLUE REGION"))
        }
    }

    @EventHandler
    fun chat(e: AsyncChatEvent) {
        val ren: ChatRenderer = e.renderer()
        val p: Player = e.player
        if (RegionArgus.redTeam.contains(p)) {
            RegionArgus.redTeam.forEach(Consumer { pl: Player? -> ren.render(p, Component.text("[" + ChatColor.RED + "RED" + ChatColor.RESET + "]").append(p.displayName()), e.message(), (pl)!!) })
        } else {
            RegionArgus.blueTeam.forEach(Consumer { pl: Player? -> ren.render(p, Component.text("[" + ChatColor.BLUE + "BLUE" + ChatColor.RESET + "]").append(p.displayName()), e.message(), (pl)!!) })
        }
    }

    @EventHandler
    fun playerKilled(e: PlayerDeathEvent) {
        val w: World = e.player.world
        val p: Player = e.player
        e.drops.forEach(Consumer { item: ItemStack? -> w.dropItem(p.getLocation(), (item)!!) })
        val i: ItemStack = ItemStack(Material.NETHER_STAR)
        val im: ItemMeta = i.itemMeta
        im.displayName(Component.text("좌표 탐색기").decorate(TextDecoration.BOLD))
        im.addEnchant(Enchantment.LUCK, 10, true)
        i.itemMeta = im
        w.dropItem(p.location, i)
    }

    @EventHandler
    fun interaction(e: PlayerInteractEvent) {
        if ((e.item == null)) {
            return
        }
        if (e.item!!.itemMeta.hasEnchant(Enchantment.LUCK) && (e.item!!.type == Material.NETHER_STAR) && (e.action == Action.RIGHT_CLICK_AIR)) {
            if (RegionArgus.redTeam.contains(e.player)) {
                if (Math.round(Math.random()) == 0L) {
                    e.player.sendMessage("BLUE 팀 코어의 x좌표 : " + RegionArgus.blueloc!!.blockX)
                } else {
                    e.player.sendMessage("BLUE 팀 코어의 Z좌표 : " + RegionArgus.blueloc!!.blockZ)
                }
            } else if (RegionArgus.blueTeam.contains(e.player)) {
                if (Math.round(Math.random()) == 0L) {
                    e.player.sendMessage("RED 팀 코어의 x좌표 : " + RegionArgus.redloc!!.blockX)
                } else {
                    e.player.sendMessage("red 팀 코어의 Z좌표 : " + RegionArgus.redloc!!.blockZ)
                }
            }
            e.item!!.amount = 0
        }
    }
}