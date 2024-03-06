package day10.revision.car;

public class Honda extends Car {

   public Honda() {
      this.setMake("HONDA");
   }

   @Override
   public void horn() {
      System.out.printf("^^^^^ HONK! ^^^^^^^\n");
   }
}
