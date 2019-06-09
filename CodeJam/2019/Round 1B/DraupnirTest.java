//make sure to make new file!
import java.io.*;
import java.util.*;

public class DraupnirTest{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("draupnirtest.out")));
      
      out.println("\t\t\tR1\t\t\tR2\t\t\tR3\t\t\tR4\t\t\tR5\t\t\tR6");
      
      long[] array = {-1,1L,1L,1L,1L,1L,1L};
      out.print(0 + "\t\t\t");
      for(int j = 1; j <= 6; j++){
         out.print(array[j] + "\t\t\t");
      }
      out.println();
      
      for(int k = 1; k <= 500; k++){
         
         for(int j = 1; j <= 6; j++){
            if(k%j == 0){
               array[j]*=2;
            }
         }
         out.print(k + "\t\t\t");
         for(int j = 1; j <= 6; j++){
            out.print(array[j] + "\t\t\t");
         }
         out.println();
      }
      
      
      out.close();
   }
   
      
}