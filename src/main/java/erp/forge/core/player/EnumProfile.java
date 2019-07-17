package erp.forge.core.player;

import java.util.HashMap;
import java.util.Map;

public enum  EnumProfile {

    EXIST(0, "exist", "yes", String.class),
    FIRSTNAME(1, "firstname", "", String.class),
    LASTNAME(2, "lastname", "", String.class),
    AGE(3, "age", 0, Integer.class),
    JOB(4, "job", "", String.class),
    BANK(5, "bank", 0, Integer.class),
    MONEY(6, "money", 0, Integer.class);

    private int id;
    private String key;
    private Object value;
    private Class type;

    public static Map<String, EnumProfile> enumProfile = new HashMap<>();


    EnumProfile(int id, String key, Object defaultValue, Class type){
        this.id = id;
        this.key = key;
        this.value = defaultValue;
        this.type = type;
    }

    static {
        for(EnumProfile r : EnumProfile.values()){
            enumProfile.put(r.getKey(), r);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object defaultValue) {
        this.value = defaultValue;
    }

    public Class getType() {
        return type;
    }

    public void setType(Class type) {
        this.type = type;
    }

}
