// Wenlu Cheng, CSE  373
// assignment #1, ID:1336340
// This file is assigning questions to oracles in random order and print out their
// answers

public class Executor {

	public static void main(String[] args) {
		Utility.init(); // initializes file readers
		String[] questions = Utility.readQuestions(); //reads question.txt file into questions array
		String[] answers = Utility.readAnswers(); // reads answers.txt file into answers array
		
		int numOracles = answers.length; //finds the number of oracles

		// 1. Initialize one ArrayQueue per oracle
		ArrayQueue[] oracles = new ArrayQueue[numOracles];
		for(int i = 0; i < numOracles; i++){
			oracles[i] = new ArrayQueue();
		}
		
		// 2. Put the questions into the queues,assigning each one 
		//to the queue of the oracle in random order
		for(int i= 0; i < questions.length; i++){
			String temp = questions[i];
			int number = Utility.random(numOracles);
			oracles[number].enqueue(temp);
		}
		
		// 3. Loop through the oracles, having each one remove a question from 
		//its queue (if empty do nothing) and answer it with its unique 
		//answer repeatedly until all queues are empty
		
		//find the longest element in the array(that is how many time
		// it has to loop for every arrayQueue in the element)
		int max = 0;
		for(int i = 0; i < oracles.length; i++ ){
			max = Math.max(oracles[i].getSize(), max);
		}
		
		
		// loop max time to print every question inside the arrayQueues
		// if it is empty just skip it.
		for(int i = 0; i < questions.length * max; i++){
			if(!oracles[i % numOracles].isEmpty()){
				String tempQuestion = oracles[i % numOracles].dequeue();		
				String tempAnswer = answers[i % numOracles];
				System.out.println(tempQuestion + ": " + tempAnswer);
			}
		}
	}
}

