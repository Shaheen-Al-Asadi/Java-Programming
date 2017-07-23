package package1;

public class Circle implements Shape{

	public double diameter;
	public double radius;
	
	//Constructor should throw an exception if using invalid values 
	Circle(double r) throws IllegalArgumentException{
		if(r <= 0) {
			throw new IllegalArgumentException("IllegalArgumentException: Circle Constructor [ r <= 0 ]");
		}
		radius = r; 
		diameter = radius * 2;
	}
	
	@Override
	public String toString(){
		return "Circle {r=" + radius + "} " + calcPeri();
		//Array.toString(authors) if wanting to include a class array;
	};
	
	@Override
	public boolean equals(Object o){
		if (this == o) return true; //self-referential must be true
		if (!(o instanceof Circle)) return false; //not if similar class must be false
		//if (this.hashCode() = o.hashCode() ) return true; //if equals is true, hashcode MUST be similar - redundant?	
		Circle buffer = (Circle) o; //Create buffer object to test equality of fields
		if (radius != buffer.radius) return false;
		//additional examples of comparing array or string hashes
		//if (!Arrays.equals(authors, buffer.authors)) return false; 
		//if (!title.equals(buffer.title)) return false;
		return true;   
	}
	
	@Override
	public int hashCode(){
		int result=1;
	    result = (int) (31 * (result + radius) ); //cast radius float to INT
	    //result = SomeString.hashCode(); example to convert string to hash 
	    //result = Arrays.hashCode(someArray); convert array to hash
	    return result;
	}
	
	//define public interface from shape
	public double calcPeri() {
		functionalInterface fi = () -> (double) (2 * 3.1415 * radius ); //Implementing lambda for FunctionalInterface
		double result = fi.calcPeriLambda(); //Using fi variable containing lambda logic
		//double result = (double) (2 * 3.1415 * radius ); //non lambda version
		double roundOff = (double) Math.round(result * 100) / 100; //roundOff to 2 decimal places 
		return roundOff;
	}
	
	//Copy Constructor testing for sorting algorithm
	public Circle(Circle another) {
	    this.radius = another.radius;
	    this.diameter = another.diameter; 
	}
}
