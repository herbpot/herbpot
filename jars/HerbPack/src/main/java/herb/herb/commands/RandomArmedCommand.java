package herb.herb.commands;

import herb.herb.utilitys.GlobalArgus;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RandomArmedCommand implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player targetplayer = null;
        try {
            targetplayer = Bukkit.getPlayer(args[0]);
        }catch (Exception e){

            sender.sendMessage("존재하지 않는 플레이어입니다");
            return false;
        }
        Player p = (Player) sender;
        PlayerInventory inv = p.getInventory();
        PlayerInventory Tinv = targetplayer.getInventory();

        //갑옷, 칼, 도끼, 방패, 낚시대, 황금사과
        List<Material> helmet = (List<Material>) Arrays.stream(Material.values()).filter(material -> material.name().contains("HELMAT") && !material.isLegacy());
        List<Material> chestplate = (List<Material>) Arrays.stream(Material.values()).filter(material -> material.name().contains("CHESTPLATE") && !material.isLegacy());
        List<Material> leggings = (List<Material>) Arrays.stream(Material.values()).filter(material -> material.name().contains("LEGGINGS") && !material.isLegacy());
        List<Material> boots = (List<Material>) Arrays.stream(Material.values()).filter(material -> material.name().contains("BOOTS") && !material.isLegacy());
        List<Material> swords = (List<Material>) Arrays.stream(Material.values()).filter(material -> material.name().contains("SWORD") && !material.isLegacy());
        List<Material> axes = (List<Material>) Arrays.stream(Material.values()).filter(material -> !material.name().contains("PICKAXE") && material.name().contains("AXE") && !material.isLegacy());

        Collections.shuffle(helmet);
        Collections.shuffle(chestplate);
        Collections.shuffle(leggings);
        Collections.shuffle(boots);
        Collections.shuffle(swords);
        Collections.shuffle(axes);

        inv.setHelmet(GlobalArgus.ArmorMeta(new ItemStack(helmet.get(0))));
        inv.setChestplate(GlobalArgus.ArmorMeta(new ItemStack(chestplate.get(0))));
        inv.setLeggings(GlobalArgus.ArmorMeta(new ItemStack(leggings.get(0))));
        inv.setBoots(GlobalArgus.ArmorMeta(new ItemStack(boots.get(0))));

        Tinv.setHelmet(GlobalArgus.ArmorMeta(new ItemStack(helmet.get(0))));
        Tinv.setChestplate(GlobalArgus.ArmorMeta(new ItemStack(chestplate.get(0))));
        Tinv.setLeggings(GlobalArgus.ArmorMeta(new ItemStack(leggings.get(0))));
        Tinv.setBoots(GlobalArgus.ArmorMeta(new ItemStack(boots.get(0))));

        inv.addItem(GlobalArgus.SwordMeta(new ItemStack(swords.get(0))));
        inv.addItem(new ItemStack(Material.SHIELD));
        ItemStack Gap = new ItemStack(Material.GOLDEN_APPLE);
        Gap.setAmount((int) Math.floor(Math.random()*10));
        inv.addItem(Gap);
        inv.addItem(new ItemStack(Material.FISHING_ROD));

        Tinv.addItem(GlobalArgus.SwordMeta(new ItemStack(swords.get(0))));
        Gap.setAmount((int) Math.floor(Math.random()*10));
        Tinv.addItem(new ItemStack(Material.SHIELD));
        Tinv.addItem(Gap);
        Tinv.addItem(new ItemStack(Material.FISHING_ROD));



        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (command.getName().equals("startrandomarmed")){
            if (args.length == 1){
                List playerList = new ArrayList<>();
                Bukkit.getOnlinePlayers().forEach(p -> playerList.add(p.getName()));
                playerList.add("all");
                return playerList;
            }
        }
        return null;
    }
}
