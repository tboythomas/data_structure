
public class MyarrayList {		   
	
	//initial size
	private static final int DEFAULT = 10;
	   
	private int count;
	private Object[] arrayList;
	
	public MyarrayList(){
		count = 0;
		arrayList = new Object[DEFAULT];
	}
	
	public void add(Object temp){
		if(!addble()){
			ensureCapacity(count * 2+ 1);
		}
		arrayList[count] = temp;
		count++;
	}
	
	public void add(Object temp, int index){
      if(index > count || index < 0){
			throw new ArrayIndexOutOfBoundsException( );
		}
		if(arrayList.length == count){ //if it is full
			ensureCapacity(count+ 1);
		}
		for(int i = count; i > index; i--){
			arrayList[i] = arrayList[i - 1];
		}
		arrayList[index] = temp;
		count++;
	}
	
	public Object get(int index){
		if(index > count || index < 0){
			throw new ArrayIndexOutOfBoundsException( );
		}
		return arrayList[index];
	}
	
	public Object set(Object temp, int index){
		if(index > count || index < 0){
			throw new ArrayIndexOutOfBoundsException( );
		}
		
		Object old = arrayList[index];
		arrayList[index] = temp;
		return old;
	}
	
	public Object remove(int index){
		if(index > count || index < 0){
			throw new ArrayIndexOutOfBoundsException( );
		}
		
		Object old = arrayList[index];
		for(int i = index; i < count - 1; i++){
			arrayList[i] = arrayList[i + 1];
		}
		count--;
		return old;
	}
	
	public  int size(){
		return count;
	}
	
	public boolean isEmpty(){
		return count == 0;
	}
	
	//check if there is enough space to add
	public boolean addble(){
		return (count < DEFAULT);
	}
	
	public void ensureCapacity(int newCapacity){
		if(newCapacity < count){
			return ;
		}
		Object[] old = arrayList;
		arrayList = new Object[newCapacity];
		for(int i = 0; i < old.length; i++){
			arrayList[i] = old[i];
		}
	}
	
	public String toString(){
		String result = "";
		for(int i = 0; i < count;i++){
			result += arrayList[i] + " ";
		}
		return result;
	}
}
