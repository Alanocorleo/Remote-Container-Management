package journeysManagement;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Record {
	
	private Map<String, Container> record;

	public Record() {
		this.record = new HashMap<String, Container>();
	}
	
	public Map<String, Container> get() {
		return record;
	}
	
    // Generic Map filter, with predicate
    public <K, V> Map<K, V> filter(Map<K, V> map, Predicate<V> predicate) {
        return map.entrySet()
                .stream()
                .filter(x -> predicate.test(x.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
	
}
