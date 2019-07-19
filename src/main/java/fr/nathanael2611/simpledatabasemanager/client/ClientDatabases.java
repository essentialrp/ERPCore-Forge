package fr.nathanael2611.simpledatabasemanager.client;

import fr.nathanael2611.simpledatabasemanager.core.Database;
import fr.nathanael2611.simpledatabasemanager.core.DatabaseReadOnly;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.HashMap;

/**
 * This class will contain stuff to get the read-only databases
 * that the server share with the client
 *
 * @author Nathanael2611
 */
@SideOnly(Side.CLIENT)
public class ClientDatabases {

    private static DatabaseReadOnly personalPlayerData = new DatabaseReadOnly();

    /**
     * Just get the client player-data
     */
    public static Database getPersonalPlayerData() {
        return personalPlayerData;
    }

    public static void updatePersonalPlayerData(DatabaseReadOnly database){
        personalPlayerData = database;
    }

    /**
     * The databases hashmap
     */
    public static final HashMap<String, DatabaseReadOnly> CLIENT_DATABASES = new HashMap<>();

    /**
     * Used for update a client db
     */
    public static void updateClientDB(DatabaseReadOnly db){
        CLIENT_DATABASES.put(db.getId(), db);
    }

    /**
     * Used for get a read-only db
     */
    public static DatabaseReadOnly getDatabase(String dbName){
        if(!CLIENT_DATABASES.containsKey(dbName)){
            CLIENT_DATABASES.put(dbName, new Database(dbName).toReadOnly());
        }
        return CLIENT_DATABASES.get(dbName);
    }

}
