package erp.forge.core.player;

import fr.nathanael2611.simpledatabasemanager.client.ClientDatabases;
import fr.nathanael2611.simpledatabasemanager.core.Database;
import fr.nathanael2611.simpledatabasemanager.core.DatabaseReadOnly;
import net.minecraft.entity.player.EntityPlayerMP;

import java.util.UUID;


public class ERPlayerClient extends ERPlayer {

    private UUID uuid;
    private Database playerData;

    public ERPlayerClient(UUID uuid) {
        super(uuid, ClientDatabases.getPersonalPlayerData());
    }


    @Override
    public UUID getUUID() {
        return super.getUUID();
    }

    @Override
    public EntityPlayerMP getPlayer() {
        printNoPermMessage();
        return null;
    }

    public void printNoPermMessage() {
        System.err.println("Cannot write read-only databases");
    }


    // USER MANAGMENT
    @Override
    public String getFirstname() {
        return (String) super.getFirstname();
    }

    @Override
    public void setFirstname(String firstname) {
        printNoPermMessage();
    }

    @Override
    public String getLastname() {
        return (String) super.getLastname();
    }

    @Override
    public void setLastname(String lastname) {
        printNoPermMessage();
    }

    @Override
    public int getAge() {
        return (int) super.getAge();
    }

    @Override
    public void setAge(int age) {
        printNoPermMessage();
    }

    @Override
    public void addAge(int age) {
        printNoPermMessage();
    }

    @Override
    public void removeAge(int age) {
        printNoPermMessage();
    }

    @Override
    public String getJob() {
        return (String) super.getJob();
    }

    @Override
    public void setJob(String job) {
        printNoPermMessage();
    }

    @Override
    public int getMoney() {
        return (int) super.getMoney();
    }

    @Override
    public void setMoney(int money) {
        printNoPermMessage();
    }

    @Override
    public void addMoney(int money) {
        printNoPermMessage();
    }

    @Override
    public void removeMoney(int money) {
        printNoPermMessage();
    }

    @Override
    public int getBank() {
        return (int) super.getBank();
    }

    @Override
    public void setBank(int bank) {
        printNoPermMessage();
    }

    @Override
    public void addBank(int bank) {
        printNoPermMessage();
    }

    @Override
    public void removeBank(int money) {
        printNoPermMessage();
    }

    // GET | SET | ADD | REMOVE
    @Override
    public Object get(EnumProfile enumProfile) {
        return super.get(enumProfile);
    }

    @Override
    public void set(EnumProfile enumProfile, Object value) {
        printNoPermMessage();
    }

    @Override
    public void add(EnumProfile enumProfile, Object value) {
        printNoPermMessage();
    }

    @Override
    public void remove(EnumProfile enumProfile, Object value) {
        printNoPermMessage();
    }

    // UTILS
    @Override
    public boolean accountExist() {
        if (super.get(EnumProfile.EXIST) instanceof String) {
            if (((String) super.get(EnumProfile.EXIST)).equalsIgnoreCase("yes")) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    @Override
    public DatabaseReadOnly getCustomUser() {
        return (DatabaseReadOnly) this.playerData;
    }

    @Override
    public void sendHUDMoneyEvent(Boolean isWithdraw, int amount) {
        printNoPermMessage();
    }

}