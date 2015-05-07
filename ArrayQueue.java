// Wenlu Cheng, CSE  373
// assignment #1, ID:1336340
// This file is an arrayQueue that can be used to store oracle's questions
// in the executor.java file

public class ArrayQueue {
	private String[] queueArray;
	private int size;
	private int front;
	private int back;
	
	public ArrayQueue(){
		queueArray = new String[100];
		size = 0;
		front = 0;
		back = -1;
	}
	
	public ArrayQueue(int startSize){
		queueArray = new String[startSize];
		size = 0;
		front = 0;
		back = -1;
	}
	
	/**
	 * @function returns the number of elements in the queue
	 * @return size
	 */
	public int getSize(){
		return size;
	}
	
	/**
	 * @function adds a string to the end of the queue
	 * @param toEnqueue: the input to be inserted
	 */
	public void enqueue(String toEnqueue){
		if(isFull())
			System.out.print("Queue is full");
		
		// if not full, keep enqueuing
		back = (back + 1) % (queueArray.length);
		queueArray[back] = toEnqueue;
		size++;
	}
	
	/**
	 * @function removes the string from the front of the queue
	 * @return the string from the front of the queue
	 */
	public String dequeue(){
		if(isEmpty())
			return null;
		
		// if not empty, keep dequeuing
		String toDequeue = queueArray[front];
		front = (front + 1) % queueArray.length;
		size--;
		return toDequeue;
	}
	
	/**
	 * 
	 * @return returns true if the queue is empty, false otherwise
	 */
	public boolean isEmpty(){
		return (size == 0);
	}

	/**
	 * 
	 * @return returns true if the queue is full, false otherwise
	 */
	public boolean isFull(){
		return (size == queueArray.length);
	}
	
}
