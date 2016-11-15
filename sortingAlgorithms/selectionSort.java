//pick one element (i.e. the first one) in the data set
//look past the first element and see if there's a smaller one
//if there is, record the index of that element
//(need to go through the end of the data set through to make sure the smallest one is recorded)
//then swap with the element you picked at the beginning. 
//repeat the process with the next element.

public class SelectionSort{

	public void sort(int[] data) {
		
		for(int i = 0; i < data.length - 1; i++){
			int minIndex = i;
			for(int j = i+1; j < data.length; j++){
				if(data[j] < data[minIndex]){
					minIndex = j;
				}
			}
			
			//do the swap
			int temp = data[i];
			data[i] = data[minIndex];
			data[minIndex] = temp;
		}
	}

}
