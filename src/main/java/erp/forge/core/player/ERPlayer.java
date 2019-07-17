package erp.forge.core.player;

import erp.forge.core.ERPCoreForge;
import fr.nathanael2611.simpledatabasemanager.core.Database;
import fr.nathanael2611.simpledatabasemanager.core.Databases;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.FMLCommonHandler;

import java.util.UUID;

public class ERPlayer {

    private UUID uuid;
    private Database playerData;

    public ERPlayer(UUID uuid) {
        this.uuid = uuid;
        this.playerData = Databases.getPlayerData(getPlayer());
    }

    public UUID getUUID() {
        return uuid;
    }

    public EntityPlayer getPlayer() {
        return FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerByUUID(this.uuid);
    }


    public Object get(EnumProfile enumProfile) {
        if (enumProfile.getType().equals(String.class)) {
            return playerData.getString(enumProfile.getKey());
        } else if (enumProfile.getType().equals(Integer.class)) {
            return playerData.getInteger(enumProfile.getKey());
        } else if (enumProfile.getType().equals(Double.class)) {
            return playerData.getDouble(enumProfile.getKey());
        } else if (enumProfile.getType().equals(Float.class)) {
            return playerData.getFloat(enumProfile.getKey());
        } else if (enumProfile.getType().equals(Boolean.class)) {
            return playerData.getBoolean(enumProfile.getKey());
        }
        return null;
    }

    public void set(EnumProfile enumProfile, Object value) {
        ERPCoreForge.logger.info("Trying to set:" + enumProfile.getKey() + " to " + value);
        if (enumProfile.getDefaultValue() instanceof String && value instanceof String) {
            playerData.setString(enumProfile.getKey(), (String) value);
        } else if (enumProfile.getDefaultValue() instanceof Integer && value instanceof Integer) {
            playerData.setInteger(enumProfile.getKey(), (Integer) value);
        } else if (enumProfile.getDefaultValue() instanceof Double && value instanceof Double) {
            playerData.setDouble(enumProfile.getKey(), (Double) value);
        } else if (enumProfile.getDefaultValue() instanceof Float && value instanceof Float) {
            playerData.setFloat(enumProfile.getKey(), (Float) value);
        } else if (enumProfile.getDefaultValue() instanceof Boolean && value instanceof Boolean)
            playerData.setBoolean(enumProfile.getKey(), (Boolean) value);
    }

    public void add(EnumProfile enumProfile, Object value) {
        if (enumProfile.getDefaultValue() instanceof Integer && value instanceof Integer) {
            playerData.setInteger(enumProfile.getKey(), (int) get(enumProfile) + (int) value);
        } else if (enumProfile.getDefaultValue() instanceof Double && value instanceof Double) {
            playerData.setDouble(enumProfile.getKey(), (double) get(enumProfile) + (double) value);
        } else if (enumProfile.getDefaultValue() instanceof Float && value instanceof Float) {
            playerData.setFloat(enumProfile.getKey(), (float) get(enumProfile) + (float) value);
        }
    }

    public void remove(EnumProfile enumProfile, Object value) {
        if (enumProfile.getDefaultValue() instanceof Integer && value instanceof Integer) {
            if ((int) value <= (int) get(enumProfile)) {
                playerData.setInteger(enumProfile.getKey(), (int) get(enumProfile) - (int) value);
            }
        } else if (enumProfile.getDefaultValue() instanceof Double && value instanceof Double) {
            if ((double) value <= (double) get(enumProfile)) {
                playerData.setDouble(enumProfile.getKey(), (double) get(enumProfile) - (double) value);
            }
        } else if (enumProfile.getDefaultValue() instanceof Float && value instanceof Float) {
            if ((float) value <= (float) get(enumProfile)) {
                playerData.setFloat(enumProfile.getKey(), (float) get(enumProfile) - (float) value);
            }
        }
    }


    public boolean accountExist() {
        if (((String)this.get(EnumProfile.EXIST)).equalsIgnoreCase("yes")) {
            return true;
        } else {
            return false;
        }
    }

    public void loadAccount() {
        if (accountExist() == false) {
            ERPCoreForge.logger.info("Creating account for: " + getPlayer().getName());

            for (EnumProfile data : EnumProfile.values()) {
                this.set(data, data.getDefaultValue());
            }
        }
    }


    public Database getCustomUser() {
        return this.playerData;
    }

}
