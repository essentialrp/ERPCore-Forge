package erp.forge.core.client.money;

public class MoneyHUDEntry
{
    private String text;
    private int timeLived;
    
    public MoneyHUDEntry(final String text) {
        this.text = text;
    }
    
    public int incrementTimeLived() {
        return this.timeLived++;
    }
    
    public int getTimeLived() {
        return this.timeLived;
    }
    
    public String getText() {
        return this.text;
    }
}
