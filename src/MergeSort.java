public class MergeSort {
	
	public MergeSort() {
		
	}
	
	@SuppressWarnings("rawtypes") 
    public static Object[] mergeSort(Object[] list) 
    {
        //If list is empty; no need to do anything
        if (list.length <= 1) {
            return list;
        }
         
        //Split the array in half in two parts
        Object[] first = new Object[list.length / 2];
        Object[] second = new Object[list.length - first.length];
        System.arraycopy(list, 0, first, 0, first.length);
        System.arraycopy(list, first.length, second, 0, second.length);
         
        //Sort each half recursively
        mergeSort(first);
        mergeSort(second);
         
        //Merge both halves together, overwriting to original array
        merge(first, second, list);
        return list;
    }
     
    @SuppressWarnings({ "rawtypes", "unchecked" }) 
    private static void merge(Object[] first, Object[] second, Object[] list) 
    {
        //Index Position in first array - starting with first element
        int iFirst = 0;
         
        //Index Position in second array - starting with first element
        int iSecond = 0;
         
        //Index Position in merged array - starting with first position
        int iMerged = 0;
         
        //Compare elements at iFirst and iSecond, 
        //and move smaller element at iMerged
        while (iFirst < first.length && iSecond < second.length) 
        {
            if (((Node)first[iFirst]).getX() < ((Node)second[iSecond]).getX()) 
            {
                list[iMerged] = first[iFirst];
                iFirst++;
            } 
            else
            {
                list[iMerged] = second[iSecond];
                iSecond++;
            }
            iMerged++;
        }
        //copy remaining elements from both halves - each half will have already sorted elements
        System.arraycopy(first, iFirst, list, iMerged, first.length - iFirst);
        System.arraycopy(second, iSecond, list, iMerged, second.length - iSecond);
    }

}
