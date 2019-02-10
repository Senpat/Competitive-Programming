import java.io.*;
import java.util.*;

public class DriverJi
{
   public static void main(String[] args)
   {
      int array[] = new int[10];
      Scanner keyboard = new Scanner(System.in);
      for(int x = 0; x<array.length; x++)
      {
         System.out.print("#"+(x+1)+": ");
         array[x] = keyboard.nextInt();
      }
      for(int x = 0; x<array.length; x++)
      {
         System.out.println(""+(array[x]+2));
      }
   }
}
