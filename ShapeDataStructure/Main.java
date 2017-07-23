package package1;

import java.io.BufferedReader; //Reads input Stream into a string to splice
import java.io.FileReader; //Seeks path and file - reads characters =/= inputstream for bytes
import java.io.IOException; //Exception handling if unable to read file

//import java.util.ArrayList; //unused datastructure
//import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		
		System.out.println("Assignment 1 - Shape Perimeter Calculator Version 2");
		
		//Legacy Unused Datastructure
		//ArrayList < ArrayList<Shape> > listOfLists = new ArrayList < ArrayList<Shape> >(); //Not familiar with get/set methods and general syntax 
		//listOfLists.add(new ArrayList<Shape>()); //forced to use dumb 2dArray due to time constraint 
		//listOfLists.get(0).get(0); //??
		//decided on 2d Array instead|EDIT - made resizing complicated, went with arrays- poor readability but it works
		//Shape[][] twodArray = new Shape [5][10];
		
		//Simple arrays for each individual shape type
		Circle circleArray[] = new Circle[100];
		Square squareArray[]= new Square[100];
		Rectangle rectangleArray[]= new Rectangle[100];
		Parallelogram parallelogramArray[]= new Parallelogram[100];
		Triangle triangleArray[]= new Triangle[100];
		
		/* LEGACY:: Used for initial testing of Shape methods
		Circle Circle1 = new Circle(10);
		Circle Circle2 = new Circle(20);
		System.out.println("Circle1: " + Circle1); //toString works
		System.out.println("hashCode() for Circle1: " + Circle1.hashCode()); //hashCode works
		System.out.println("Result of equals: " + Circle1.equals(Circle2) ); // equals method works */
		  
        System.out.println("Task 1: Read Shape.txt and store shapes in datastructure of your choice...");
        System.out.println("Task 1: Print out the shapes and their perimeters...");
		buildShapesFromFile("Shapes.txt", circleArray , squareArray , rectangleArray , parallelogramArray , triangleArray);
		//Reads file Shapes.txt format: name,x,y,z. empty line represents end of file
		//Example Line: Triangle,1.0,2.0,3.0; Circle,5.0; Rectangle,4.4,5.5; "";
		//If line !valid or arguments supplied dont match required dimensions for that shape - throw exception
		//Stores all successful shapes in a datastructure of your choice
		
		//delete triangle with smallest perimeter 
		//[ INSTRUCTIONS SAY SINGULAR TRIANGLE , OTHERWISE RUN FUNCTION AGAIN TO REMOVE THE >>SECOND<< 12.0 TRIANGLE ]
		
        System.out.println("Task 2: Delete from Shape the [Triangle] with the smallest Perimeter...");
		triangleArray = delSmallest(triangleArray);
		
		System.out.println("Task 2: Delete from Shape the [Circle] with the largest Perimeter...");
		circleArray = delLargest(circleArray);
		
		System.out.println("NOTE, Had to create delLargest / delSmallest arguments specific to shape,\n"
				+ "Bubble sort clashed with previous Shape[] array , changed all Shape[]'s to corresponding Shapes");
		
		System.out.println("Task 3: Calculate total perimeter of all Parallelograms...");
		System.out.println("The total perimeter of all Parallelograms is: " + calcAll(parallelogramArray) );
		
		System.out.println("Task 3: Calculate total perimeter of all Triangles...");
		System.out.println("The total perimeter of all Triangles is: " + calcAll(triangleArray) );
		
		System.out.println("Task 4: Print all shapes alphabetically, then sorted from smallest to largest...");
		
		//i am highly aware at how inefficient this is, i think im so far down the hole i just cant stop digging - 4am thursday
		//note to self - study some proper java data structures - work around for a work around for a work around...
		
		//bubblesort each array and then print them - had to create shape specific methods
		//could have prevented this with a generic shape method but was unable to create a temporary object of type "T" 
		bubbleSortC(circleArray);
		bubbleSortP(parallelogramArray);
		bubbleSortR(rectangleArray);
		bubbleSortS(squareArray);
		bubbleSortT(triangleArray);
		
		printAll(circleArray);
		printAll(parallelogramArray);
		printAll(rectangleArray);	
		printAll(squareArray);	
		printAll(triangleArray);	
				
	}
	
	//Im sorry i had to do this, i should have asked for suggestions on a better dataStructure for the Shapes, ArrayList? 
	//array = ArrayUtils.removeElement(array, element) NOT WORKING UTIL :( have to make my own...
	//Static methods in java belong to the class (not an instance of it). They use no instance variables to operate
	public static Triangle[] delSmallest(Triangle[] sa){
		double smallest = sa[0].calcPeri();//set first shape to smallest
		int currentIndex=0; //tracks current traversing index 
		int smallestIndex=0;//holds index of last known smallest shape
		for(Shape sh : sa) {
			if(sh==null){break;}//null array index exception workaround
			if (sh.calcPeri() < smallest){
				System.out.println(sh + " Is smaller than " + smallest);
				smallest = sh.calcPeri();//if another shape is smaller, replace it as smallest
				smallestIndex=currentIndex;//confirm the new current smallest index
				currentIndex++;//increment index counter for future catches
				
			}
			else{
			currentIndex++;//if this shape is not larger, increment index counter
			}
		}
		
		System.out.println(sa[smallestIndex] + " Is the smallest element in the array");
		Triangle newArrayValues[] = new Triangle[99]; //new array to store everything but smallest shape
		int steadyIndex =0; //used to move over data without skipping an element position

		for(int i=0; i < sa.length; i++){
			
		if(i == smallestIndex) { 
			System.out.println("Skipping smallest shape: " + sa[smallestIndex]); 
			continue;
		}//skip this element
		
		if(sa[i] == null ) {break; } //end of shapes in array , stop copying
		
		newArrayValues[steadyIndex] = sa[i]; //copy valid index into new array , compensating for index counter as one element is removed
		steadyIndex++; //increment the index for the new array
		}
		return newArrayValues;
	}
	
	public static void printAll(Shape[] ar){
		for(Shape sh : ar){
			if(sh == null ) { break; }
			System.out.println(sh);
		}
	}
	
	public static Circle[] delLargest(Circle[] sa){
		double largest = sa[0].calcPeri();//set first shape to largest
		int currentIndex=0; //tracks current traversing index 
		int largestIndex=0;//holds index of last known largest shape
		for(Shape sh : sa) {
			if(sh==null){ 
				break; 
			}//null array index exception workaround
			
			if (sh.calcPeri() > largest){
				System.out.println(sh + " Is larger than " + largest);
				largest = sh.calcPeri();//if another shape is smaller, replace it as smallest
				largestIndex = currentIndex;//confirm the new current smallest index
				currentIndex++;//increment index counter for future catches			
			}
			else{
			currentIndex++;//if this shape is not larger, increment index counter
			}
		}
		
		System.out.println(sa[largestIndex] + " Is the Largest element in the array");
		Circle newArrayValues[] = new Circle[99]; //new array to store everything but smallest shape
		int steadyIndex =0; //used to move over data without skipping an element position

		for(int i=0; i < sa.length; i++){
			
		if(i == largestIndex) { 
			System.out.println("Skipping Largest shape: " + sa[largestIndex]); 
			continue;
		}//skip this element
		
		if(sa[i] == null ) {break; } //end of shapes in array , stop copying
		
		newArrayValues[steadyIndex] = sa[i]; //copy valid index into new array , compensating for index counter as one element is removed
		steadyIndex++; //increment the index for the new array
		}
		return newArrayValues;
	}
	
	public static double calcAll(Shape[] sa){
	
		double total = 0;
		for(Shape sh : sa){
			if(sh == null ) { break; } //best way i could think of dealing with static array sizes right now and null pointer exceptions, rest of array is always null 
			total += sh.calcPeri();
			total = (double) Math.round(total * 100) / 100;
		}
		return total;

	}
	
	//work in progress Task 4 fix? wish i had better data structure design :( its 3AM thursday
	/*
	static <T> void bubblesort(T[] ar) {
		for (int i = (ar.length - 1); i >= 0; i--){ //for the entire length of array, moving backwards to 0 starts at 99 )
		      for (int j = 1; j <= i; j++){ //as long as second index has not met up with initial starting point
		    	  double n1 = ((Shape) ar[j]).calcPeri(); //original algo only supported sorting int arrays
		    	  double n2 = ((Shape) ar[j-1]).calcPeri();
		         if (n2 > n1){ //example if 0 is bigger than 1 
		        	 T temp = (T) new T(ar[j-1]); // <=========== wish this would have worked, cannot instantize T
		        	 ar[j-1] = ar[j];
		        	 ar[j] = temp;
		         } 
		      } 
		   } 
	}
	*/
	
	//I Apologize for creating multiple bubble sorts, as you can see above generic type could not instantize type T
	public static void bubbleSortC(Circle[] ar){
	   for (int i = (ar.length - 1); i >= 0; i--){ //for the entire length of array, moving backwards to 0 starts at 99 )
	      for (int j = 1; j <= i; j++){ //as long as second index has not met up with initial starting point
	    	  if(ar[j] == null || ar[j-1] == null ){break;} //static array null exception work around
	    	  double n1 = ar[j].calcPeri(); //original algo only supported sorting int arrays
	    	  double n2 = ar[j-1].calcPeri();
	         if (n2 > n1){ //example if 0 is bigger than 1 
	        	 //Shape temp = new Shape();//wish this would have worked
	        	 Circle temp = new Circle(ar[j-1]);
	        	 ar[j-1] = ar[j];
	        	 ar[j] = temp;
	         } 
	      } 
	   } 
	}
	
	public static void bubbleSortR(Rectangle[] ar){
		   for (int i = (ar.length - 1); i >= 0; i--){ //for the entire length of array, moving backwards to 0 starts at 99 )
		      for (int j = 1; j <= i; j++){ //as long as second index has not met up with initial starting point
		    	  if(ar[j] == null || ar[j-1] == null ){break;} //static array null exception work around
		    	  double n1 = ar[j].calcPeri(); //original algo only supported sorting int arrays
		    	  double n2 = ar[j-1].calcPeri();
		         if (n2 > n1){ //example if 0 is bigger than 1 
		        	 //Shape temp = new Shape();//wish this would have worked
		        	 Rectangle temp = new Rectangle(ar[j-1]);
		        	 ar[j-1] = ar[j];
		        	 ar[j] = temp;
		         } 
		      } 
		   } 
		}
	
	public static void bubbleSortP(Parallelogram[] ar){
		   for (int i = (ar.length - 1); i >= 0; i--){ //for the entire length of array, moving backwards to 0 starts at 99 )
			   //it does the entire array loop once, but its not perfectly sorted, so it starts again from index 0 ,
			   //hence the use of i loop, and largest number will be on top, so i-- to hasten effect
		      for (int j = 1; j <= i; j++){ //as long as second index has not met up with initial starting point
		    	  if(ar[j] == null || ar[j-1] == null ){break;} //static array null exception work around
		    	  double n1 = ar[j].calcPeri(); //original algo only supported sorting int arrays
		    	  double n2 = ar[j-1].calcPeri();
		         if (n2 > n1){ //example if 0 is bigger than 1 
		        	 //Shape temp = new Shape();//wish this would have worked
		        	 Parallelogram temp = new Parallelogram(ar[j-1]);
		        	 ar[j-1] = ar[j];
		        	 ar[j] = temp;
		         } 
		      } 
		   } 
		}
	
	public static void bubbleSortS(Square[] ar){
		   for (int i = (ar.length - 1); i >= 0; i--){ //for the entire length of array, moving backwards to 0 starts at 99 )
		      for (int j = 1; j <= i; j++){ //as long as second index has not met up with initial starting point
		    	  if(ar[j] == null || ar[j-1] == null ){break;} //static array null exception work around
		    	  double n1 = ar[j].calcPeri(); //original algo only supported sorting int arrays
		    	  double n2 = ar[j-1].calcPeri();
		         if (n2 > n1){ //example if 0 is bigger than 1 
		        	 //Shape temp = new Shape();//wish this would have worked
		        	 Square temp = new Square(ar[j-1]);
		        	 ar[j-1] = ar[j];
		        	 ar[j] = temp;
		         } 
		      } 
		   } 
		}
	
	public static void bubbleSortT(Triangle[] ar){
		   for (int i = (ar.length - 1); i >= 0; i--){ //for the entire length of array, moving backwards to 0 starts at 99 )
		      for (int j = 1; j <= i; j++){ //as long as second index has not met up with initial starting point
		    	  if(ar[j] == null || ar[j-1] == null ){break;} //static array null exception work around
		    	  double n1 = ar[j].calcPeri(); //original algo only supported sorting int arrays
		    	  double n2 = ar[j-1].calcPeri();
		         if (n2 > n1){ //example if 0 is bigger than 1 
		        	 //Shape temp = new Shape();//wish this would have worked
		        	 Triangle temp = new Triangle(ar[j-1]);
		        	 ar[j-1] = ar[j];
		        	 ar[j] = temp;
		         } 
		      } 
		   } 
		}
	
	public static void buildShapesFromFile(String fileName, Shape [] cArray, Shape [] sArray , Shape [] rArray , Shape [] pArray, Shape [] tArray) { // had ... after static

	       String path = System.getProperty("user.dir");
	       Shape sp = null; //buffer shape used to append into arrays from file
	       String s; //holds line in file

	       //try (BufferedReader br = new BufferedReader(new FileReader("./Root/" + fileName))) { // code board
	       // if you run locally on your environment use: new FileReader(path + "/src/" + fileName)
	       
	       try (BufferedReader br = new BufferedReader(new FileReader(path + "/src/package1/" + fileName))) {
	    	   
               int circleIndex = 0;//Stores current available array index & used to count successful shapes created
               int squareIndex = 0;
               int rectangleIndex = 0;
               int parallelogramIndex = 0;
               int triangleIndex = 0;
               int badLine = 0;
               int badShape = 0;
               
	           while ((s = br.readLine()) != null) {
	        	   //System.out.println(s); //prints file contents for testing
	               String[] tok = s.split(","); //Split string "s" into tok string array using "," as delimiter
	               
	            // Second tryblock used to prevent entire method from terminating upon a single invalid line
	            // Could replace second tryblock by catching exceptions within shape constructor instead, but wanted specify practice
	               try{ 
		               switch(tok[0]){
		               
		               case "Circle": 
		               sp = new Circle( Double.parseDouble(tok[1]) ); //create shape
		               cArray[circleIndex]= sp; //append shape to respective array
		               System.out.println(cArray[circleIndex]); //print recently appended shape
		               circleIndex++; //increment index                
		               break;
		               
		               case "Square": 
		               sp = new Square( Double.parseDouble(tok[1]) ); //create shape
		               sArray[squareIndex]= sp; //append shape to respective array
		               System.out.println(sArray[squareIndex]); //print recently appended shape
		               squareIndex++; //increment index                
		               break;
		               
		               case "Rectangle": 
		               sp = new Rectangle( Double.parseDouble(tok[1]) , Double.parseDouble(tok[2]) ); //create shape
		               rArray[rectangleIndex]= sp; //append shape to respective array
		               System.out.println(rArray[rectangleIndex]); //print recently appended shape
		               rectangleIndex++; //increment index                
		               break;
		               
		               case "Parallelogram":
		               sp = new Parallelogram( Double.parseDouble(tok[1]) , Double.parseDouble(tok[2]) ); //create shape
		               pArray[parallelogramIndex]= sp; //append shape to respective array
		               System.out.println(pArray[parallelogramIndex]); //print recently appended shape
		               parallelogramIndex++; //increment index                
		               break;
		               
		               case "Triangle":
		               sp = new Triangle( Double.parseDouble(tok[1]) , Double.parseDouble(tok[2]), Double.parseDouble(tok[3]) ); //create shape
		               tArray[triangleIndex]= sp; //append shape to respective array
		               System.out.println(tArray[triangleIndex]); //print recently appended shape
		               triangleIndex++; //increment index                
		               break;
		               
		               default: System.out.println(tok[0] + " is not a valid shape");
		            	   		badShape++;
		               break;       
		               }//End of Switch(tok[0])
		               
		               //prevent entire method from terminating by just reporting a bad line 
	               }catch(IllegalArgumentException e){
	            	   badLine++;
	            	   System.out.println(e.getMessage());   
	               }
	                
	           }//While there are lines still being read 
	           
	           System.out.println("Number of Circle's Created: " + circleIndex);
	           System.out.println("Number of Square's Created: " + squareIndex);
	           System.out.println("Number of Retangle's Created: " + rectangleIndex);
	           System.out.println("Number of Parallelogram's Created: " + parallelogramIndex);
	           System.out.println("Number of Triangle's Created: " + triangleIndex);
	           System.out.println("Number of Incorrect Arguments : " + badLine);
	           System.out.println("Number of Incorrect Shapes : " + badShape);
	           
	           //catch exceptions related to BufferedReader and FileReader
	       } catch (IOException e) {
	           System.out.println(e.getMessage());
	       }

	       //void return
	}//Build shapes from file End
	
}
