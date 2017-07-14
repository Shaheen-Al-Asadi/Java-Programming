package lab1.automobile;

public class Automobile {

	String carName;
	Wheel frontWheel; 
	Wheel rearWheel;
	Wheel extra;

	/** Wheel inner class */
	class Wheel {
		private String hubcapType;
		private float radius;
		/** default constructor of inner class */
		Wheel() {
			hubcapType = "";
			radius = 0.0f;
		}
		
		Wheel(String type, float r) {
			hubcapType = type;
			radius = r;
		}   
	}

	/** default constructor of outer class */
	public Automobile(String carName) {
		this.carName = carName;
		frontWheel = this.new Wheel();
		rearWheel = this.new Wheel("GoodYear", 1.2f);
	}	
	    
	public Wheel thirdWheel(Automobile car) {
		if (car.extra == null) {
		
			car.extra = car.new Wheel("Reserve", 1.1f);
		
		}
		System.out.println("For the car: " + car.carName);
		System.out.println("Extra type wheel: " + car.extra.hubcapType);
		System.out.println("Extra radius wheel: " + car.extra.radius);    
		return car.extra;
  }

	public static void main(String[] args) {

		Automobile myCar = new Automobile("Honda");
		Automobile yourCar = new Automobile("W");    		
		myCar.extra = myCar.thirdWheel(yourCar);
	}
}