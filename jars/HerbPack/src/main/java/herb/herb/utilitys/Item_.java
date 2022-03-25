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

}
