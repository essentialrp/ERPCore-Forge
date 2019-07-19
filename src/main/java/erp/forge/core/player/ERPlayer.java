package erp.forge.core.player;

import erp.forge.core.ERPCoreForge;
import erp.forge.core.network.message.MessagePlayerMoneyHUD;
import fr.nathanael2611.simpledatabasemanager.core.Database;
import fr.nathanael2611.simpledatabasemanager.core.Databases;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.FMLCommonHandler;

import java.util.UUID;


public class ERPlayer {

    private UUID uuid;
    private Database playerData;

    public ERPlayer(UUID uuid) {
        this.uuid = uuid;
        this.playerData = Databases.getPlayerData(getPlayer());
    }

    public ERPlayer(UUID uuid, Database database) {
        this.uuid = uuid;
        this.playerData = database;
    }

    public UUID getUUID() {
        return uuid;
    }

    public EntityPlayerMP getPlayer() {
        return FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerByUUID(this.uuid);
    }

    // USER MANAGMENT

    public String getFirstname(){
        return (String) this.get(EnumProfile.FIRSTNAME);
    }

    public void setFirstname(String firstname){
        set(EnumProfile.FIRSTNAME, firstname);
    }

    public String getLastname() {
        return (String) this.get(EnumProfile.LASTNAME);
    }

    public void setLastname(String lastname){
        set(EnumProfile.LASTNAME, lastname);
    }

    public int getAge(){
        return (int) this.get(EnumProfile.AGE);
    }

    public void setAge(int age){
        set(EnumProfile.AGE, age);
    }

    public void addAge(int age){
        this.add(EnumProfile.AGE, age);
    }

    public void removeAge(int age){
        this.remove(EnumProfile.AGE, age);
    }

    public String getJob(){
        return (String)this.get(EnumProfile.JOB);
    }

    public void setJob(String job){
        this.set(EnumProfile.JOB, job);
    }

    public int getMoney(){
        return (int)this.get(EnumProfile.MONEY);
    }

    public void setMoney(int money){
        this.set(EnumProfile.MONEY, money);
    }

    public void addMoney(int money){
        this.add(EnumProfile.MONEY, money);
        this.sendHUDMoneyEvent(false, money);
    }

    public void removeMoney(int money){
        this.remove(EnumProfile.MONEY, money);
        this.sendHUDMoneyEvent(true, money);
    }

    public int getBank(){
        return (int)this.get(EnumProfile.BANK);
    }

    public void setBank(int bank){
        this.set(EnumProfile.BANK, bank);
    }

    public void addBank(int bank){
        this.add(EnumProfile.BANK, bank);
        this.sendHUDMoneyEvent(false, bank);
    }

    public void removeBank(int bank){
        this.remove(EnumProfile.BANK
                , bank);
        this.sendHUDMoneyEvent(true, bank);
    }

    // GET | SET | ADD | REMOVE

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
        if (enumProfile.getValue() instanceof String && value instanceof String) {
            playerData.setString(enumProfile.getKey(), (String) value);
        } else if (enumProfile.getValue() instanceof Integer && value instanceof Integer) {
            playerData.setInteger(enumProfile.getKey(), (Integer) value);
        } else if (enumProfile.getValue() instanceof Double && value instanceof Double) {
            playerData.setDouble(enumProfile.getKey(), (Double) value);
        } else if (enumProfile.getValue() instanceof Float && value instanceof Float) {
            playerData.setFloat(enumProfile.getKey(), (Float) value);
        } else if (enumProfile.getValue() instanceof Boolean && value instanceof Boolean)
            playerData.setBoolean(enumProfile.getKey(), (Boolean) value);
    }

    public void add(EnumProfile enumProfile, Object value) {
        if (enumProfile.getValue() instanceof Integer && value instanceof Integer) {
            playerData.setInteger(enumProfile.getKey(), (int) get(enumProfile) + (int) value);
        } else if (enumProfile.getValue() instanceof Double && value instanceof Double) {
            playerData.setDouble(enumProfile.getKey(), (double) get(enumProfile) + (double) value);
        } else if (enumProfile.getValue() instanceof Float && value instanceof Float) {
            playerData.setFloat(enumProfile.getKey(), (float) get(enumProfile) + (float) value);
        }
    }

    public void remove(EnumProfile enumProfile, Object value) {
        if (enumProfile.getValue() instanceof Integer && value instanceof Integer) {
            if ((int) value <= (int) get(enumProfile)) {
                playerData.setInteger(enumProfile.getKey(), (int) get(enumProfile) - (int) value);
            }
        } else if (enumProfile.getValue() instanceof Double && value instanceof Double) {
            if ((double) value <= (double) get(enumProfile)) {
                playerData.setDouble(enumProfile.getKey(), (double) get(enumProfile) - (double) value);
            }
        } else if (enumProfile.getValue() instanceof Float && value instanceof Float) {
            if ((float) value <= (float) get(enumProfile)) {
                playerData.setFloat(enumProfile.getKey(), (float) get(enumProfile) - (float) value);
            }
        }
    }

    // UTILS
    public boolean accountExist() {
        if(this.get(EnumProfile.EXIST) instanceof String){
            if(((String) this.get(EnumProfile.EXIST)).equalsIgnoreCase("yes")){
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public void loadAccount() {
        if (!accountExist()) {
            ERPCoreForge.logger.info("Creating account for: " + getPlayer().getName());

            for (EnumProfile data : EnumProfile.values()) {
                this.set(data, data.getValue());
            }
        }
    }

    public Database getCustomUser() {
        return this.playerData;
    }

    public void sendHUDMoneyEvent(Boolean isWithdraw, int amount){
        if(isWithdraw) {
            ERPCoreForge.instance.getPacketChannel().sendTo(new MessagePlayerMoneyHUD("§c-"+amount+"$"), getPlayer());
        } else {
            ERPCoreForge.instance.getPacketChannel().sendTo(new MessagePlayerMoneyHUD("§a+"+amount+"$"), getPlayer());
        }
    }

}
