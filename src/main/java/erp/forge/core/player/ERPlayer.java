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

    public ERPlayer(UUID uuid){
        this.uuid = uuid;
        this.playerData = Databases.getPlayerData(getPlayer());
    }

    public UUID getUUID(){
        return uuid;
    }

    public EntityPlayer getPlayer(){
        return FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerByUUID(this.uuid);
    }




    public Object get(EnumProfile enumProfile) {
        if (enumProfile.getType().equals(String.class)) {
            return playerData.getString(enumProfile.getKey());
        }
        else if (enumProfile.getType().equals(Integer.class)) {
            return playerData.getInteger(enumProfile.getKey());
        }
        else if (enumProfile.getType().equals(Double.class)) {
            return playerData.getDouble(enumProfile.getKey());
        }
        else if (enumProfile.getType().equals(Float.class)) {
            return playerData.getFloat(enumProfile.getKey());
        }
        else if (enumProfile.getType().equals(Boolean.class)) {
            return playerData.getBoolean(enumProfile.getKey());
        }
        return null;
    }

    public void set(EnumProfile enumProfile, Object value) {
        if (enumProfile.getType().equals(String.class) && value.equals(String.class)) {
            playerData.setString(enumProfile.getKey(), (String)value);
        }
        else if (enumProfile.getType().equals(Integer.class) && value.equals(Integer.class)) {
            playerData.setInteger(enumProfile.getKey(), (Integer) value);
        }
        else if (enumProfile.getType().equals(Double.class) && value.equals(Double.class)) {
            playerData.setDouble(enumProfile.getKey(), (Double) value);
        }
        else if (enumProfile.getType().equals(Float.class) && value.equals(Float.class)) {
            playerData.setFloat(enumProfile.getKey(), (Float) value);
        }
        else if (enumProfile.getType().equals(Boolean.class) && value.equals(Boolean.class)) {
            playerData.setBoolean(enumProfile.getKey(), (Boolean) value);
        }
    }

    public void add(EnumProfile enumProfile, Object value){
        if (enumProfile.getType().equals(Integer.class) && value.equals(Integer.class)) {
            playerData.setInteger(enumProfile.getKey(), (int)get(enumProfile)+(int)value);
        }
        else if (enumProfile.getType().equals(Double.class) && value.equals(Double.class)) {
            playerData.setDouble(enumProfile.getKey(), (double)get(enumProfile)+(double)value);
        }
        else if (enumProfile.getType().equals(Float.class) && value.equals(Float.class)) {
            playerData.setFloat(enumProfile.getKey(), (float)get(enumProfile)+(float)value);
        }
    }

    public void remove(EnumProfile enumProfile, Object value){
        if (enumProfile.getType().equals(Integer.class) && value.equals(Integer.class)) {
            if((int)value <= (int)get(enumProfile)) {
                playerData.setInteger(enumProfile.getKey(), (int) get(enumProfile) - (int) value);
            }
        }
        else if (enumProfile.getType().equals(Double.class) && value.equals(Double.class)) {
            if((double)value <= (double)get(enumProfile)) {
                playerData.setDouble(enumProfile.getKey(), (double) get(enumProfile) - (double) value);
            }
        }
        else if (enumProfile.getType().equals(Float.class) && value.equals(Float.class)) {
            if((float)value <= (float)get(enumProfile)) {
                playerData.setFloat(enumProfile.getKey(), (float) get(enumProfile) - (float) value);
            }
        }
    }




    public boolean accountExist() {
        if (playerData.getString("firstname").equalsIgnoreCase("")) {
            return true;
        } else {
            return false;
        }
    }

    public void loadAccount() {
        if (accountExist() == false) {
            ERPCoreForge.logger.info("Creating account for: " + getPlayer().getName());

            for (EnumProfile data : EnumProfile.values()) {

                if (data.getType().equals(String.class)) {
                    playerData.setString(data.getKey(), (String) data.getDefaultValue());
                }
                if (data.getType().equals(Integer.class)) {
                    playerData.setInteger(data.getKey(), (Integer) data.getDefaultValue());
                }
                if (data.getType().equals(Double.class)) {
                    playerData.setDouble(data.getKey(), (Double) data.getDefaultValue());
                }
                if (data.getType().equals(Float.class)) {
                    playerData.setFloat(data.getKey(), (Float) data.getDefaultValue());
                }
                if (data.getType().equals(Boolean.class)) {
                    playerData.setBoolean(data.getKey(), (Boolean) data.getDefaultValue());
                }
            }
        }
    }


    public static Database getCustomUser(EntityPlayer entityPlayer) {
        return Databases.getPlayerData(entityPlayer);
    }

}
