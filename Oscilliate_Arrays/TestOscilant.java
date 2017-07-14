import java.util.Arrays;

//package container
public class TestOscilant {

	//method
	public static boolean isOscilant(int[] arr) {

		  if (arr.length < 2) 
		    return true;
				
		  if (arr[0] == arr[1])
		    return false;

		  for (int i = 1; i < arr.length - 1; i++) 
		    if (!((arr[i - 1] < arr[i] && arr[i] > arr[i + 1]) 
		        || (arr[i - 1] > arr[i] && arr[i] < arr[i + 1]))) 
		      return false;

		  return true;
		}   
	
	//main
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		   System.out.println("Check arrays for oscilant: ");

		      int array1[] = new int[]{1, 2, 5, 8};

		      int array2[] = new int[]{2, 3, 2};

		      int[] array3 = new int[]{2, 3, -2, 7, 0, 1};

		      int[] array4 = new int[]{1, 2, 1, 1, 8};      

		      System.out.println(Arrays.toString(array1) + " is Oscilant: " + isOscilant(array1));

		      System.out.println(Arrays.toString(array2) + " is Oscilant: " + isOscilant(array2));

		      System.out.println(Arrays.toString(array3) + " is Oscilant: " + isOscilant(array3));

		      System.out.println(Arrays.toString(array4) + " is Oscilant: " + isOscilant(array4));
		
	}

}


