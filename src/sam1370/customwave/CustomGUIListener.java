package sam1370.customwave;

import java.util.ArrayList;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAnimationEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.projectkorra.projectkorra.BendingPlayer;
import com.projectkorra.projectkorra.ability.CoreAbility;

public class CustomGUIListener implements Listener {
    /* Things that need to be declared */
    
    private ItemStack pillarItem = createItem(Material.STAINED_GLASS, colorize("&fYour &aCustomPillar"),
	    colorize("&fX: &a1||&fY: &a7||&fZ: &a1"), null);
    
    private Inventory gui = Bukkit.createInventory(null, 27, colorize("&aCustomPillars"));
    private String changeX = colorize("&fChange the X of your CustomPillar");
    private String changeY = changeX.replace("X", "Y");
    private String changeZ = changeX.replace("X", "Z");

    private String xUp = colorize("&fAdd 1 to the X of your CustomPillar");
    private String xDown = xUp.replace("Add 1 to", "Remove 1 from");

    private String yUp = xUp.replace("X", "Y");
    private String yDown = xDown.replace("X", "Y");

    private String zUp = xUp.replace("X", "Z");
    private String zDown = xDown.replace("X", "Z");

    
    /*----------------------------------*/
    
    
    // Called when the player swings their hand
    @EventHandler
    public void onSwing(PlayerAnimationEvent e) {
	if (e.isCancelled()) {
	    return;
	}
	BendingPlayer bPlayer = BendingPlayer.getBendingPlayer(e.getPlayer());
	if ((bPlayer != null) && (bPlayer.canBend(CoreAbility.getAbility("CustomWave")))
		&& (CoreAbility.getAbility(e.getPlayer(), CustomWave.class) == null)) {
	    openGui(e);
	}
    }
    
    /* Essential methods */
    
    // Opens the GUI
    private void openGui(PlayerAnimationEvent e) {
	ItemStack xItem = createItem(Material.GRASS, colorize("&aX"), changeX);
	ItemStack yItem = createItem(Material.GRASS, colorize("&aY"), changeY);
	ItemStack zItem = createItem(Material.GRASS, colorize("&aZ"), changeZ);

	ItemStack xUpItem = createUDItem(UD.UP, xUp);
	ItemStack xDownItem = createUDItem(UD.DOWN, xDown);

	ItemStack yUpItem = createUDItem(UD.UP, yUp);
	ItemStack yDownItem = createUDItem(UD.DOWN, yDown);

	ItemStack zUpItem = createUDItem(UD.UP, zUp);
	ItemStack zDownItem = createUDItem(UD.DOWN, zDown);

	gui.setItem(10, pillarItem);
	gui.setItem(12, xItem);
	gui.setItem(13, yItem);
	gui.setItem(14, zItem);
	gui.setItem(3, xUpItem);
	gui.setItem(21, xDownItem);
	gui.setItem(4, yUpItem);
	gui.setItem(22, yDownItem);
	gui.setItem(5, zUpItem);
	gui.setItem(23, zDownItem);

	Player p = e.getPlayer();
	p.openInventory(gui);
    }
    

    // Create a new up or down item //
    private ItemStack createUDItem(UD ud, String name) {
	ItemStack i;
	if (ud.equals(UD.UP)) {
	    i = createItem(Material.STAINED_GLASS_PANE, name, 5);
	} else {
	    i = createItem(Material.STAINED_GLASS_PANE, name, 14);
	}
	return i;
    }

    // These methods are used for creating items //
    private ItemStack createItem(Material material, String name, String lore) {
	return createItem(material, name, lore, null);
    }

    private ItemStack createItem(Material material, String name, Integer damage) {
	return createItem(material, name, null, damage);
    }

    private ItemStack createItem(Material material, String name, String lore, Integer damage) {
	ItemStack i = new ItemStack(material);
	if (damage != null)
	    i.setDurability((short) (int) damage);
	ItemMeta iMeta = i.getItemMeta();
	iMeta.setDisplayName(name);
	ArrayList<String> iLore = new ArrayList<String>();
	if (lore != null) {
	    if (lore.contains("||")) {
		String[] lParts = lore.split(Pattern.quote("||"));
		for (String lp : lParts) {
		    iLore.add(lp);
		}
	    } else {
		iLore.add(lore);
	    }
	}
	iMeta.setLore(iLore);
	i.setItemMeta(iMeta);
	return i;
    }

    // Quickly 'colorize' text //
    private String colorize(String toBeColored) {
	return ChatColor.translateAlternateColorCodes('&', toBeColored);
    }
    
    /*--------------------------------*/
    
}
