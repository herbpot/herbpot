package herb.herb.utilitys;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CompassMeta;
import org.bukkit.inventory.meta.ItemMeta;

public class Item_ {
    public ItemStack compass(Location target){
        ItemStack item = new ItemStack(Material.COMPASS);
        CompassMeta itemMeta = (CompassMeta) item.getItemMeta();
        itemMeta.setLodestone(target);
        item.setItemMeta(itemMeta);
        return item;
    }
    public ItemStack dumy(){
        ItemStack item = new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE);
        ItemMeta itemM = item.getItemMeta();
        itemM.setLocalizedName("dumy");
        itemM.setDisplayName(" ");
        item.setItemMeta(itemM);
        return item;
    }

//    public ItemStack arrow(int direction){
//
//        ItemStack item = new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE);
//        ItemMeta itemM = item.getItemMeta();
//        itemM.setLocalizedName("dumy");
//        itemM.setDisplayName(" ");
//        item.setItemMeta(itemM);
//        return item;
//    }

}
