public class MergeSort{

	public void sort(int[] data) {

		// base case
		if (data.length == 1) {
			return;
		}

		// get left and right halves of passed array
		int[] left = new int[data.length / 2];

		// fill up left array
		for (int i = 0; i < data.length / 2; i++) {
			left[i] = data[i];
		}

		int[] right = new int[data.length - (data.length / 2)];

		// fill up right array
		int counter = 0;
		for (int j = data.length / 2; j < data.length; j++) {
			right[counter++] = data[j];
		}

		// left and right halves should be obtained now
		sort(left);
		sort(right);

		// make sure "data" is updated to be the sorted set
		merge(left, right, data);

	}

	// helper merge method
	private void merge(int[] a, int[] b, int[] originalData) {

		int[] newArray = new int[a.length + b.length];

		// marker for each array set to be merged
		int aMarker = 0;
		int bMarker = 0;
		int newArrMarker = 0;
		
		//we can modify the original array! just keep a marker for the original array
		//this marker only really matters for the very last merge step that occurs
		//because by then, all the values should be sorted. whatever happened to
		//the original set in the process doesn't matter because the merged values are in a 
		//separate array
		int originalArrayMarker = 0;

		while (bMarker < b.length && aMarker < a.length) {
			if (b[bMarker] < a[aMarker]) {
				newArray[newArrMarker] = b[bMarker];
				originalData[originalArrayMarker++] = b[bMarker];
				bMarker++;
			} else {
				newArray[newArrMarker] = a[aMarker];
				originalData[originalArrayMarker++] = a[aMarker];
				aMarker++;
			}
			newArrMarker++;
		}

		// if arrays are not same length, start
		// wherever bMarker or aMarker left off, whichever is larger
		if (bMarker < b.length) {
			for (int i = bMarker; i < b.length; i++) {
				newArray[newArrMarker] = b[i];
				originalData[originalArrayMarker++] = b[i];
				newArrMarker++;
			}
		} else {
			for (int j = aMarker; j < a.length; j++) {
				newArray[newArrMarker] = a[j];
				originalData[originalArrayMarker++] = a[j];
				newArrMarker++;
			}
		}
	}

}
