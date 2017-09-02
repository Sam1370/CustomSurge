package sam1370.customsurge;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.projectkorra.projectkorra.ability.AddonAbility;
import com.projectkorra.projectkorra.ability.WaterAbility;

public class CustomSurgeListener extends WaterAbility implements AddonAbility {

    public CustomSurgeListener(Player player) {
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
	return null;
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
	return null;
    }

    @Override
    public String getVersion() {
	return null;
    }

    @Override
    public void load() {
	
    }

    @Override
    public void stop() {
	
    }

}
