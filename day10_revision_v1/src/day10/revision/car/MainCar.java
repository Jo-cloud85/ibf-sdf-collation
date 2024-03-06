package day10.revision.car;

public class MainCar {

   public static void main(String[] args) {
      Car toyota = new Car();
      toyota.setMake("toyota");
      Car.assignRegistration(toyota);

      Car honda = new Car();
      honda.setMake("honda");
      Car.assignRegistration(honda);

      Car.setWheels(4);
      System.out.printf("1. toyota: %s, %s, %d\n", toyota.getRegistration()
         , toyota.getMake(), Car.getWheels());

      Car.setWheels(2);
      System.out.printf("2. honda: %s, %s, %d\n", honda.getRegistration()
         , honda.getMake(), Car.getWheels());

      System.out.printf("3. toyota: %s, %d\n", toyota.getMake(), Car.getWheels());

      Honda civic = new Honda();

      System.out.printf("3. civic: %s, %d\n", civic.getMake(), Car.getWheels());
      civic.horn();
   }
   
}