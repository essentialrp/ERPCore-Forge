package erp.forge.core.client.money;

import com.google.common.collect.Sets;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class MoneyHUDManager
{
    private Set<MoneyHUDEntry> entries;
    
    public MoneyHUDManager() {
        this.entries = Sets.newConcurrentHashSet();
    }
    
    public Set<MoneyHUDEntry> getEntries() {
        return new HashSet<MoneyHUDEntry>(this.entries);
    }
    
    public void remove(final MoneyHUDEntry entry) {
        this.entries.remove(entry);
    }
    
    public void add(final MoneyHUDEntry entry) {
        if (this.entries.size() >= 6) {
            this.entries.stream().max(Comparator.comparingInt(MoneyHUDEntry::getTimeLived)).ifPresent(value -> this.entries.remove(value));
        }
        this.entries.add(entry);
    }
}
