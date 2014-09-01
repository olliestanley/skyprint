package pw.ollie.skyprint.util;

import pw.ollie.skyprint.SkyPrint;
import pw.ollie.skyprint.edit.Edit;
import pw.ollie.skyprint.exception.UnsupportedCharacterException;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Utilities {
    public static double toRealYaw(final float yaw) {
        double rot = (yaw - 90) % 360;
        return rot < 0 ? rot + 360 : rot;
    }

    @SuppressWarnings("deprecation")
    public static Material getMaterial(final String arg) {
        Material mat = Material.getMaterial(arg);
        if (mat == null) {
            mat = Material.valueOf(arg);
        }
        if (mat == null) {
            try {
                mat = Material.getMaterial(Integer.parseInt(arg));
            } catch (NumberFormatException ignore) {
            }
        }
        return mat;
    }

    public static Edit newEdit(final SkyPrint plugin, final Player ply, final Material mat, final Direction dir, final String words) throws UnsupportedCharacterException {
        return new Edit(plugin, ply.getUniqueId(), new LocationData(ply.getEyeLocation()), mat, dir, ply.getWorld().getName(), words.toCharArray());
    }

    private Utilities() {
        throw new UnsupportedOperationException();
    }
}
