//make sure to make new file!
import java.io.*;
import java.util.*;

public class Ac{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      int[] array = new int[n];
      int[] indexof = new int[101];
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(f.readLine());
         indexof[array[k]] = k;
      }
      
      Arrays.sort(array);
      
      int answer = 1;
     
      for(int k = 1; k < n; k++){
         if(indexof[array[k]] < indexof[array[k-1]]) answer++;
      }
      
      out.println(answer);
      
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}