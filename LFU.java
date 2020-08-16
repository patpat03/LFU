class LFUCache {
    HashMap<Integer, Integer> values = new HashMap<Integer, Integer>();
    HashMap<Integer, Integer> count = new HashMap<Integer, Integer>();
    HashMap<Integer, LinkedHashSet<Integer>> hash = new HashMap<>();
    int min = -1;
    int cap = 0;
    public LFUCache(int capacity) {
        cap = capacity;
        hash.put(1, new LinkedHashSet<Integer>());
    }
    
    public int get(int key) {
       if(!values.containsKey(key))return -1;
        int c = count.get(key);
        count.put(key, c + 1);
        hash.get(c).remove(key);
        if(hash.get(c).size() ==0 && c == min)min++;
        hash.putIfAbsent(c +1, new LinkedHashSet<Integer>());
        hash.get(c + 1).add(key);  
        return values.get(key);
    }
    
    public void put(int key, int value) {
        if(cap <= 0)return;
        if(values.containsKey(key))
        {
            values.put(key, value);
            get(key);
            return;
        }
        if(values.size() >= cap)
        {
            int k = hash.get(min).iterator().next();
            values.remove(k);
            count.remove(k);
            hash.get(min).remove(k);
        }
        values.put(key, value);
        min = 1;
        hash.get(min).add(key);
        count.put(key, min);
    }
}
