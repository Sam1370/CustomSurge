package sam1370.customwave;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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

	// The HashMap that stores the player values
	static Map<Player, Object[]> dimensions = new HashMap<Player, Object[]>();

	// Default values for the HashMap
	static Object[] default_map = { 5, State.Water };
	// The wave item
	private ItemStack waveItem = createItem(Material.STAINED_GLASS, colorize("&fYour &bCustomWave"),
			colorize("&fSize: &b" + default_map[0] + "||&fState: &b" + default_map[1].toString()));
	// The GUI
	private Inventory gui = Bukkit.createInventory(null, 27, colorize("&aCustomPillars"));

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

		gui.setItem(10, waveItem);
		gui.setItem(12, null);
		gui.setItem(13, null);
		gui.setItem(14, null);
		gui.setItem(3, null);
		gui.setItem(21, null);
		gui.setItem(4, null);
		gui.setItem(22, null);
		gui.setItem(5, null);
		gui.setItem(23, null);

		Player p = e.getPlayer();
		p.openInventory(gui);
	}

	// Create a new up or down item //
	private ItemStack createUDItem(up_down ud, String name) {
		ItemStack i;
		if (ud.equals(up_down.UP)) {
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
