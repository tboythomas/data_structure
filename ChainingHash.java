
public class ChainingHash {
		
		private static final int DEFAULT = 128;
		Node current;
		private static Node[] list;
		int i;
		
		private class Node {
			
			public String key;
				public int counter;
				public Node next;
				
				public Node(String key){
					this(key, 1 , null);
				}
				
				public Node(String key, int counter, Node next){
					this.key = key;
					this.counter = counter;
					this.next = next;
				}
		}

		public ChainingHash(){
			this(DEFAULT);
		}
		
		public ChainingHash(int startSize){
			//TODO Implement a constructor that instantializes the hash array to startSize.
			list = new Node[startSize];
			for(int i = 0; i < list.length; i++){
				list[i] = null;
			}
			current = null;
			i = 0;
		}
		
		
		/**
		 * This function allows rudimentary iteration through the ChainingHash.
		 * The ordering is not important so long as all added elements are returned only once.
		 * It should return null once it has gone through all elements
		 * @return Returns the next element of the hash table. Returns null if it is at its end.
		 */
		public String getNextKey(){
			if(current == null){
				while(list[i] == null){
					i++;
				}
				current = list[i];
			}
			String temp = current.key;
			current = current.next;
			return temp;
		}
		/**
		 * Adds the key to the hash table.
		 * If there is a collision, it should be dealt with by chaining the keys together.
		 * If the key is already in the hash table, it increments that key's counter.
		 * @param keyToAdd : the key which will be added to the hash table
		 */
		
		public void insert(String keyToAdd){
			int index = hash(keyToAdd);
			if(list[index] == null){
				Node temp = new Node(keyToAdd);
				list[index] = temp;
			}else{
				Node current = list[index];
				//check the head
				if(current.key.equals(keyToAdd))
					current.counter++;
				
				while(current.next != null){
					if(current.next.key.equals(keyToAdd)){
						current.counter++;
						break;
					}else{
						current = current.next;
					}
				}
				current.next = new Node(keyToAdd);
				}
		}
		
		/**
		 * Returns the number of times a key has been added to the hash table.
		 * @param keyToFind : The key being searched for
		 * @return returns the number of times that key has been added.
		 */
		public int findCount(String keyToFind){
			int index = hash(keyToFind);
			if(list[index] == null)
				return 0;
			Node current = list[index];
			while(current != null){
				if(current.key.equals(keyToFind)){
					return current.counter;
				}else{
				current = current.next;
				}
			}
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
