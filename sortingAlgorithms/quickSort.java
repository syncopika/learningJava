import java.util.*;

public class QuickSort implements SortingAlgorithm{
//helpful reference: https://www.cs.duke.edu/courses/fall05/cps130/lectures/skiena.lectures/lecture5.pdf
	
		@Override
		public String getName() {
			return "Quick Sort";
		}

		@Override
		public void sort(int[] data) {
			quicksortHelper(data, 0, data.length);
		}
		
		//private helper function 
		private static void quicksortHelper(int[] data, int start, int end){
			
			if(start >= end || end <= start){
				return;
			}
			
			//pick a pivot element
			//(the leftmost element)
			int pivot = data[start];
			
			//partitioning step
			
			//record the pivot's index
			int pivotIndex = start;
			
			//traverse through data set depending on what the value of "start" and "end" is
			//notice we start on "start + 1", so that we ignore the pivot,
			//which we picked as the leftmost element
			for(int i = start + 1; i < end; i++){
				//then if an element is less than the pivot
				if(data[i] < pivot){
					//increment pivotIndex so that the pivot will be placed further in the array,
					//where it should belong
					pivotIndex++;
					//then we swap the current element (data[i]) with the element at pivotIndex.
					//sometimes this will be the same element, but also think of instances
					//where perhaps data[i] is LARGER than the pivot. nothing will happen during
					//that visit, but for the next data[i], if it's SMALLER than pivot,
					//then the pivotIndex would be referring to the previous element larger than
					//pivot. then we swap that larger element with the current element, so that
					//the larger element "floats" toward the larger end of the array. 
					int temp = data[pivotIndex];
					data[pivotIndex] = data[i];
					data[i] = temp;
				}
			}
			//finally when the forloop is done, we just need to swap the pivot (data[start])
			//and whatever is at pivotIndex now. that way all elements before the pivot
			//should be smaller and everything above larger
			int temp = data[pivotIndex];
			data[pivotIndex] = data[start];
			data[start] = temp;
			
			//don't touch the pivotIndex again- therefore, pivotIndex +/- 1 
			quicksortHelper(data, start, pivotIndex - 1); 
			quicksortHelper(data, pivotIndex + 1, end);
		}
}
