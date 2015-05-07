

public class QPHash {
	
	private static final int DEFAULT = 10000;
	private HashEntry[] list;
	private int current;
	
	private class HashEntry{
		public String key;
		public int counter;
		public boolean active;
		
		public HashEntry(String key){
			this(key, 1, true);
		}
		
		public HashEntry(String key, int counter, boolean active){
			this.key = key;
			this.counter = counter;
			this.active = active;
		}
	}
		public QPHash(){
			this(DEFAULT);
		}
		
		public QPHash(int startSize){
			list = new HashEntry[startSize];
			current = 0;
		}

		/**
		 * This function allows rudimentary iteration through the QPHash.
		 * The ordering is not important so long as all added elements are returned only once.
		 * It should return null once it has gone through all elements
		 * @return Returns the next element of the hash table. Returns null if it is at its end.
		 */
		public String getNextKey(){
			if(current == list.length){
				return null;
			}
			while(!list[current].active){
				current++;
			}
			String temp = list[current].key;
			current++;
			return temp;
		}
		/**
		 * Adds the key to the hash table.
		 * If there is a collision, a new location should be found using quadratic probing.
		 * If the key is already in the hash table, it increments that key's counter.
		 * @param keyToAdd : the key which will be added to the hash table
		 */
		public void insert(String keyToAdd){
			int index = find(keyToAdd);
			//if the string is same, increase the counter
			if(list[index].active){
				list[index].counter++;
			}
			//else set up a new hashentry and add it into the list
			else{
				list[index] = new HashEntry(keyToAdd);
			}
			
			
		}
		
		private int find(String key){
			int offset = 1;
			int index = hash(key);
			while(list[index] != null && !list[index].equals(key)){
				index += offset;
				offset+= 2;
				if(index > list.length){
					index -= list.length;
				}	
			}
			return index;
		}
		/**
		 * Returns the number of times a key has been added to the hash table.
		 * @param keyToFind : The key being searched for
		 * @return returns the number of times that key has been added.
		 */
		public int findCount(String keyToFind){
			for(int i = 0; i < list.length;i ++){
				//find the key in the list 
				if(list[i].active && list[i].key.equals(keyToFind)){
					return list[i].counter;
				}
			}
			//go through the whole list and didnt find it, return 0
			return 0;
		}

		private int hash(String keyToHash){
			 int hashVal = 0;
			 for(int i = 0;i < keyToHash.length(); i++){
				 hashVal = 37 * hashVal + keyToHash.charAt( i );
			 }
			  hashVal %= list.length;
			  if( hashVal < 0 )
			      hashVal += list.length;
			  return hashVal;
		}	
}
