package tony.project.language.formatter;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.apache.xmlbeans.impl.tool.Extension.Param;

public class JSONHashMap<K, V> extends LinkedHashMap {

	private Class<V> clazz;

	@Override
	public String toString() {
		Set<Map.Entry<K, V>> keyset = this.entrySet();
		Iterator<Map.Entry<K, V>> iterator = keyset.iterator();

		if (!iterator.hasNext()) {
			return "{}";
		}

		StringBuffer buffer = new StringBuffer();
		buffer.append('{');
		for (;;) {

			Map.Entry<K, V> item = iterator.next();
			K key = item.getKey();
			V value = item.getValue();

			if (value instanceof String && ((String) value).trim().startsWith("{")) {

				buffer.append(key == this ? "(this Map)" : "\"" + key + "\"");
				buffer.append(": ");
				buffer.append(value == this ? "(this Map)" : value);

			} else {

				buffer.append(key == this ? "(this Map)" : "\"" + key + "\"");
				buffer.append(": ");
				buffer.append(value == this ? "(this Map)" : "\"" + value + "\"");

			}

			if (!iterator.hasNext()) {
				return buffer.append('}').toString();
			}
			buffer.append(", ");

		}

	}

}
