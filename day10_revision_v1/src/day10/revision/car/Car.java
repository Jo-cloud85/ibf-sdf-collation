package day10.revision.car;

import java.util.UUID;

public class Car {

   public static int wheels;

   private String make;
   private String owner;
   private String registration;

   public String getMake() { return make; }
   public void setMake(String make) { this.make = make; }
   public String getOwner() { return owner; }
   public void setOwner(String owner) { this.owner = owner; }
   public String getRegistration() { return registration; }
   public void setRegistration(String registration) { this.registration = registration; }

   public void horn() {
      System.out.printf("honk!, honk!\n");
   }

   public void generateRegistration() {
      setRegistration(UUID.randomUUID().toString().substring(0, 8));
   }

   public static int getWheels() { return wheels; }
   public static void setWheels(int wheels) { Car.wheels = wheels; }

   public static void assignRegistration(Car inst) {
      String reg = UUID.randomUUID().toString().substring(0, 8);
      inst.setRegistration(reg);
   }

}