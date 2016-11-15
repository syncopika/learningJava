public class BubbleSort{
	
	public void sort(int[] data) {
		
	//I'm setting the variable "newEndpoint" here
    	//so that I can change its value later
    	//to slightly optimize the sorting
  
  		int newEndpoint = data.length - 1;
    
   	//the trick is if during an iteration no swap is needed, 
    	//the sorting is done and the program can terminate.
		
    	//additionally, you can keep track of where the index was of
	//the last swap, and update newEndpoint
		
		for(int i = 0; i < data.length; i++){
			
			int lastSwap = 0;
			boolean isSorted = true;
			
			for(int j = 0; j < newEndpoint; j++){
				if(data[j] > data[j+1]){
					//swap
					int temp = data[j];
					data[j] = data[j+1];
					data[j+1] = temp;
						
					lastSwap = j; //record the index of the last swap
					isSorted = false;					
				}
			}
			if(isSorted){
				break; //done sorting!
			}
			newEndpoint = lastSwap;
		}
	}
}
