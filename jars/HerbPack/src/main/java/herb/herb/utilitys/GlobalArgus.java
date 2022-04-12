package herb.herb.utilitys;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.TitlePart;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitScheduler;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class GlobalArgus {
    public static YamlConfiguration ServerConfig;

    public static YamlConfiguration UserConfig;
    public static Map<UUID, Player> UserMap = new HashMap<UUID, Player>();

    public static String TracerName = "추적기";
    public static String MarketName = "상점";
    public static String HealthBlockName = "HealthBlock";

    public static @NotNull List<Player> ManHunttarget;
    public static boolean Manhuntgamestart = false;
    public static Map<Player,BossBar> Manhuntbar = new HashMap<>();
    public static long PlayStartTime;

    public void resetManHuntArgs(){
        Manhuntgamestart = false;
        ManHunttarget = new ArrayList<>();
        Manhuntbar = new HashMap<>();
    }


    public static boolean randomEffect = false;

    public static boolean HealthBlock = false;

    public static boolean RanMonsterStart = false;
    public static List<Player> RanMonPlayers = new ArrayList();
    public static Chunk RanMonChunk;
    public void resetRanMonArgs() {
        RanMonChunk = null;
        RanMonPlayers = new ArrayList();
        RanMonsterStart = false;
    }

    public static YamlConfiguration MarketConfig;
    public ItemStack MarketMeta(ItemStack item,int cost){
        ItemMeta targetM = item.getItemMeta();
        targetM.setLore(Arrays.asList("우클릭 구매 / 좌클릭 판매","shift 추가시 한번에 64개씩 판매","가격 -",String.valueOf(cost)));
        item.setItemMeta(targetM);
        return item;
    }

    public static ItemStack ArmorMeta(ItemStack item){
        ItemMeta targetM = item.getItemMeta();
        List<Enchantment> ArmorEnchants = (List) Arrays.stream(Enchantment.values()).filter(enchantment -> enchantment.getItemTarget().equals(EnchantmentTarget.ARMOR));
        Collections.shuffle(ArmorEnchants);
        for (int i=0; i<(int) Math.floor(Math.random()*3); i++) {
            targetM.addEnchant(ArmorEnchants.get(0), (int) Math.floor(Math.random() * 5), true);
        }
        item.setItemMeta(targetM);
        return item;
    }

    public static ItemStack SwordMeta(ItemStack item){
        ItemMeta targetM = item.getItemMeta();
        List<Enchantment> ArmorEnchants = (List) Arrays.stream(Enchantment.values()).filter(enchantment -> enchantment.getItemTarget().equals(EnchantmentTarget.WEAPON));
        Collections.shuffle(ArmorEnchants);
        for (int i=0; i<(int) Math.floor(Math.random()*3); i++) {
            targetM.addEnchant(ArmorEnchants.get(0), (int) Math.floor(Math.random() * 5), true);
        }
        item.setItemMeta(targetM);
        return item;
    }

    public static void wait(int time, int delay){

        BukkitScheduler sc = Bukkit.getPluginManager().getPlugin("HerbPack").getServer().getScheduler();
        sc.scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("HerbPack"), new Runnable() {
            @Override
            public void run() {
                Bukkit.getOnlinePlayers().forEach(pl -> {
                    pl.sendTitlePart(TitlePart.SUBTITLE, Component.text(time));
                });
            }
        },delay*20L);
    }
}
