//make sure to make new file!
import java.io.*;
import java.util.*;

public class P2test{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      
      int lowrepeat = 0;
      int range5 = 0;
      
      int sumlessmin5 = 0;
      for(int t = 0; t < 1000; t++){
      
         int[] freq = new int[20];
      
         for(int k = 0; k < 100; k++){
            freq[(int)(Math.random()*20)]++;
         }
      
         int min = 101;
         int max = 0;
         for(int k = 0; k < 20; k++){
            min = Math.min(min,freq[k]);
            max = Math.max(max,freq[k]);
         }
         
         int summin = 0;
         for(int k = 0; k < 20; k++){
            if(freq[k] == min) summin++;
         }
         
         if(summin > 1) lowrepeat++;
         if(max-min >= 5) range5++;
         
         for(int k = 0; k < 20; k++){
            if(freq[k] < min+5) sumlessmin5++;
         }
      }
      
      
      out.println("Low Repeat: " + (double)lowrepeat/1000.0);
      out.println("Range 5: " + (double)range5/1000.0);
      out.println("Less than Min+5: " + (double)sumlessmin5/1000.0);
      
      
      
      
      
      
      out.close();
   }
   
      
}