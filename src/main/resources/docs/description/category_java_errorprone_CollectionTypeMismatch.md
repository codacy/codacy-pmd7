Since: PMD 7.17.0

Detects method calls on collections where the passed object cannot possibly be in the collection
due to type mismatch. This helps catch potential programming errors where incompatible types
are used with collection methods like contains(), remove(), indexOf(), etc.

Methods checked include:
- Collection: contains(), remove(), removeAll(), retainAll(), containsAll()
- List: indexOf(), lastIndexOf()
- Map: containsKey(), containsValue(), get(), getOrDefault(), remove()
- Deque: removeFirstOccurrence(), removeLastOccurrence()
- Hashtable: contains() (legacy method that checks values)
- ConcurrentHashMap: contains() (legacy method that checks values)

Example(s):
```
List<Integer> numbers = Arrays.asList(1, 2, 3);
numbers.remove("string"); // violation: String cannot be in Integer list

Map<String, Integer> map = new HashMap<>();
map.get(42); // violation: Integer key cannot be in String-keyed map

Set<String> names = new HashSet<>(); 
names.contains(123); // violation: Integer cannot be in String set
```
