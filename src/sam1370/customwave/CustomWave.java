package sam1370.customwave;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.projectkorra.projectkorra.ProjectKorra;
import com.projectkorra.projectkorra.ability.AddonAbility;
import com.projectkorra.projectkorra.ability.WaterAbility;

public class CustomWave extends WaterAbility implements AddonAbility {

    public CustomWave(Player player) {
	super(player);
    }

    @Override
    public long getCooldown() {
	return 0;
    }

    @Override
    public Location getLocation() {
	return null;
    }

    @Override
    public String getName() {
	return "CustomSurge";
    }

    @Override
    public boolean isHarmlessAbility() {
	return false;
    }

    @Override
    public boolean isSneakAbility() {
	return false;
    }

    @Override
    public void progress() {

    }

    @Override
    public String getAuthor() {
	return "Sam1370 and xNuminousx";
    }

    @Override
    public String getVersion() {
	return null;
    }

    @Override
    public void load() {
	// Register events for CustomSurgeListener
	ProjectKorra.plugin.getServer().getPluginManager().registerEvents(new CustomWaveListener(),
		ProjectKorra.plugin);
	// Register events for CustomGUIListener
	ProjectKorra.plugin.getServer().getPluginManager().registerEvents(new CustomGUIListener(), ProjectKorra.plugin);
	// Enable message
	ProjectKorra.log.info(getName() + " " + getVersion() + " by " + getAuthor() + " has been loaded and enabled!");
    }

    @Override
    public void stop() {

    }

}
