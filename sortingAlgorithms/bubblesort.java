public class BubbleSort{

	public void sort(int[] data) {
	
    //I'm setting the variable "newEndpoint" here
    //so that I can change its value later
    //to slightly optimize the sorting
    
    int newEndpoint = data.length - 1;
    
    //the trick is when you get to the end
    //of the data set and you don't need to swap,
    //(that is, there is already a sorted pair at the end)
    //you can decrease the number of times you have to
    //go through the data set
		
		for(int i = 0; i < data.length; i++){
			
			int lastSwap = 0;
			boolean isSorted = false;
			
			for(int j = 0; j < newEndpoint; j++){
				
					if(data[j] > data[j+1]){
						
						//swap
						int temp = data[j];
						data[j] = data[j+1];
						data[j+1] = temp;
						
						lastSwap = j; //record the index of the last swap
						isSorted = true;
						
					}
			}
				if(!isSorted){
					return; //if no swap occurred, don't update "count"
				}
				newEndpoint = lastSwap;
		}
		
		
	}

}
