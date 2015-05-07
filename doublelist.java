public class doublelist {
	
	@SuppressWarnings("hiding")
	private static class node<Object>{
		public node<Object> prev;
		public node<Object> next;
		public Object data;
		
		public node(Object data, node<Object>p, node<Object> n){
			this.data = data;
			this.prev = p;
			this.next = n;
		}
	}
	
	private int count;
	private node<Object> front;
	private node<Object> end;

	public doublelist(){
		count = 0;
		front = new node<Object> (null,null,null);
		end = new node<Object>(null,front,null);
		front.next = end;
	}
	
	
	public void add(Object temp){
		add(temp,size());
		count++;
	}
	
	//index在哪就在那add 在index的地方断开连接 重新连上add的node
	public void add(Object temp, int index){
		addbefore(getnode(index),temp);
		count++;
	}
	
	// find what node is at given index and then return the node,
	// and then add the new node before the returned node
	private void addbefore(node<Object> old, Object x ){
		node<Object> newnode = new node<Object>(x,old.prev,old);
		newnode.prev.next = newnode;
		old.prev = newnode;
	}
	
	private node<Object> getnode(int index){
		return getnode(index, 0, size() - 1);
	}
	
	
	//get the node and return
	private node<Object> getnode(int index, int lower, int upper){
		node<Object> temp;
		if(index < lower || index > upper){
			throw new IndexOutOfBoundsException();
		}
		
		if(index < size() / 2){
			temp = front.next;
			for(int i = 0; i < index; i ++){
				temp = temp.next;
			}
		}else{
			temp = end.prev;
			for(int i = upper; i > index; i--){
				temp = temp.prev;
			}
		}
		return temp;
	}
	
	public Object set(Object temp, int index){
		Object old = getnode(index).data;
		getnode(index).data = temp;
		return old;
	}
	
	public Object get(int index){
		return getnode(index).data;
	}
	
	public Object remove(int index){
		node<Object> old = getnode(index);
		old.prev.next = old.next;
		old.next.prev = old.prev;
		count--;
		return old.data;
	}
	
	public int size(){
		return count;
	}
	
}
