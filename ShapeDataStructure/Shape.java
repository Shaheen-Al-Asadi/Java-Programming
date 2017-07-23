/**
 * Last Name: Al-Asadi
 * First Name: Shaheen
 * Student ID: 013 090 154
 * codeboard UserName: Sal-Asadi
 */

package package1;

//PLEASE NOTE - I am not familiar with DataStructures in Java so i used quick and dirty Arrays, its sloppy but it works

//Abstract classes can define default methods to extend from
//Interfaces require [forces] new implementations for each method instead, in this case for each unique shape

public interface Shape {
	
	public String toString(); // Will return a text representation of a shape object
	public boolean equals(Object o); //Will return true if Object o is similar to this shape object in values
	public int hashCode(); //Returns a unique digit representation of this object's State
	public double calcPeri(); // Will return the Perimeter of an object using a lambda function
		
	interface functionalInterface{
		public double calcPeriLambda();
	}
	
}// Shape Class End
