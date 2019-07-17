package erp.forge.core.player;

import erp.forge.core.ERPCoreForge;
import erp.forge.core.datamanager.PlayerData;
import erp.forge.core.datamanager.PlayerDatas;
import net.minecraft.entity.player.EntityPlayer;

import java.util.UUID;

public class ERPlayer {


    public static boolean accountExist(EntityPlayer player){
        PlayerData playerData = PlayerDatas.getPlayerData(player);
        if(playerData.getBoolean("exist") == false){
            ERPCoreForge.logger.info("Account does not exist");

            return false;
        } else {
            return true;
        }
    }

    public static void loadAccount(EntityPlayer player){
        if(!accountExist(player)){
            PlayerData playerData = PlayerDatas.getPlayerData(player);
            ERPCoreForge.logger.info("Load account");

            for(EnumProfile data : EnumProfile.values()){
                ERPCoreForge.logger.info("Key:"+data.getKey()+" DefaultValue:"+String.valueOf(data.getDefaultValue()));

                if (data.getType().equals(String.class)) {
                    playerData.setString(data.getKey(), (String)data.getDefaultValue());
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

    public static void clearAccount(EntityPlayer player){
        UUID playerUUID = player.getGameProfile().getId();
        PlayerDatas.PLAYERDATAS.remove(playerUUID);
    }


}
