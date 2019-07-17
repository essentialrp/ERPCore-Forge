package erp.forge.core.datamanager;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.HashMap;

public class Database implements INBTSerializable<NBTTagCompound> {

    public static final String[] COMMAND_SET_ACTIONS = new String[]{
            "setString", "setDouble", "setBoolean", "setInteger", "setFloat"
    };
    public static final String[] COMMAND_GET_ACTIONS = new String[]{
            "getString", "getDouble", "getBoolean", "getInteger", "getFloat"
    };
    public static final String[] COMMAND_ALL_ACTIONS = new String[]{
            "setString", "setDouble", "setBoolean", "setInteger", "setFloat",
            "getString", "getDouble", "getBoolean", "getInteger", "getFloat"
    };

    private final HashMap<String, String>  STRINGS  = new HashMap<>();
    private final HashMap<String, Integer> INTEGERS = new HashMap<>();
    private final HashMap<String, Double>  DOUBLES  = new HashMap<>();
    private final HashMap<String, Float>   FLOATS   = new HashMap<>();
    private final HashMap<String, Boolean> BOOLEANS = new HashMap<>();

    private String dbName;

    public Database(){}

    public Database(String dbName){
        this.dbName = dbName;
    }

    /**
     *  String stuff
     */
    public String getString(String key){
        if(STRINGS.containsKey(key)){
            for(String str : STRINGS.values()){
                System.out.println(str);
            }
            return STRINGS.get(key);
        }
        return "<undefined string>";
    }
    public void setString(String key, String value){
        STRINGS.put(key, value);
        Databases.save();
    }


    /**
     *  Integer stuff
     */
    public int getInteger(String key){
        if(INTEGERS.containsKey(key)){
            return INTEGERS.get(key);
        }
        return 0;
    }
    public void setInteger(String key, int value){
        INTEGERS.put(key, value);
        Databases.save();
    }

    /**
     *  Double stuff
     */
    public double getDouble(String key){
        if(DOUBLES.containsKey(key)){
            return DOUBLES.get(key);
        }
        return 0;
    }
    public void setDouble(String key, double value){
        DOUBLES.put(key, value);
        Databases.save();
    }

    /**
     *  Float stuff
     */
    public float getFloat(String key){
        if(FLOATS.containsKey(key)){
            return FLOATS.get(key);
        }
        return 0f;
    }
    public void setFloat(String key, float value){
        FLOATS.put(key, value);
        Databases.save();
    }

    /**
     *  Boolean stuff
     */
    public boolean getBoolean(String key){
        if(BOOLEANS.containsKey(key)){
            return BOOLEANS.get(key);
        }
        return false;
    }
    public void setBoolean(String key, boolean value){
        BOOLEANS.put(key, value);
        Databases.save();
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound compound = new NBTTagCompound();

        /**
         * Saving the strings
         */
        NBTTagList stringList = new NBTTagList();
        for(String str : STRINGS.keySet()){
            stringList.appendTag(
                    new SavedData(
                            str,
                            STRINGS.get(str)
                    ).serializeNBT()
            );
        }
        compound.setTag("strings", stringList);

        /**
         * Saving the integers
         */
        NBTTagList integerList = new NBTTagList();
        for(String str : INTEGERS.keySet()){
            integerList.appendTag(new SavedData(str, INTEGERS.get(str)).serializeNBT());
        }
        compound.setTag("integers", integerList);

        /**
         * Saving the doubles
         */
        NBTTagList doubleList = new NBTTagList();
        for(String str : DOUBLES.keySet()){
            doubleList.appendTag(
                    new SavedData(
                            str,
                            DOUBLES.get(str)
                    ).serializeNBT()
            );
        }
        compound.setTag("doubles", doubleList);

        /**
         * Saving the floats
         */
        NBTTagList floatList = new NBTTagList();
        for(String str : FLOATS.keySet()){
            floatList.appendTag(
                    new SavedData(
                            str,
                            FLOATS.get(str)
                    ).serializeNBT()
            );
        }
        compound.setTag("floats", floatList);

        /**
         * Saving the database-name
         */
        compound.setString("dbname", dbName);

        return compound;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {

        /**
         * Read the strings
         */
        NBTTagList stringList = nbt.getTagList("strings", Constants.NBT.TAG_COMPOUND);
        for(NBTBase compound : stringList){
            SavedData string = new SavedData(String.class);
            string.deserializeNBT((NBTTagCompound) compound);
            STRINGS.put(string.key, (String)string.value);
        }

        /**
         * Read the integers
         */
        NBTTagList integerList = nbt.getTagList("integers", Constants.NBT.TAG_COMPOUND);
        for(NBTBase compound : integerList){
            SavedData integer = new SavedData(Integer.class);
            integer.deserializeNBT((NBTTagCompound) compound);
            INTEGERS.put(
                    integer.key, (Integer)integer.value
            );
        }

        /**
         * Read the doubles
         */
        NBTTagList doubleList = nbt.getTagList("doubles", Constants.NBT.TAG_COMPOUND);
        for(NBTBase compound : doubleList){
            SavedData doubleValue = new SavedData(Double.class);
            doubleValue.deserializeNBT((NBTTagCompound) compound);
            DOUBLES.put(
                    doubleValue.key, (Double)doubleValue.value
            );
        }

        /**
         * Read the floats
         */
        NBTTagList floatList = nbt.getTagList("floats", Constants.NBT.TAG_COMPOUND);
        for(NBTBase compound : floatList){
            SavedData floatValue = new SavedData(Float.class);
            floatValue.deserializeNBT((NBTTagCompound) compound);
            FLOATS.put(
                    floatValue.key, (Float)floatValue.value
            );
        }

        /**
         * Read the player-uuid
         */
        this.dbName = nbt.getString("dbname");

    }

    public String getDbName() {
        return dbName;
    }

    public String[] getAllStringEntry(){
        String[] strings = new String[STRINGS.size()];
        int i = 0;
        for(String str : STRINGS.keySet()) {
            strings[i] = str;
            i++;
        }
        return strings;
    }

    public String[] getAllIntegerEntry(){
        String[] strings = new String[INTEGERS.size()];
        int i = 0;
        for(String str : INTEGERS.keySet()) {
            strings[i] = str;
            i++;
        }
        return strings;
    }

    public String[] getAllDoubleEntry(){
        String[] strings = new String[DOUBLES.size()];
        int i = 0;
        for(String str : DOUBLES.keySet()) {
            strings[i] = str;
            i++;
        }
        return strings;
    }

    public String[] getAllFloatEntry(){
        String[] strings = new String[FLOATS.size()];
        int i = 0;
        for(String str : FLOATS.keySet()) {
            strings[i] = str;
            i++;
        }
        return strings;
    }

    public String[] getAllBooleanEntry(){
        String[] strings = new String[BOOLEANS.size()];
        int i = 0;
        for(String str : BOOLEANS.keySet()) {
            strings[i] = str;
            i++;
        }
        return strings;
    }
}
