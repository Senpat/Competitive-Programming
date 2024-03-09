//make sure to make new file!
import java.io.*;
import java.util.*;

public class P2208{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         boolean hasodd = false;
         int[] array = new int[n];
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
            if(array[k]%2 == 1) hasodd = true;
         }
         
         //all even is a losing state (the opponent can just match what you do)
         //if there is >= 1 odd, then it is winning because you can make it all even
         
         if(hasodd){
            out.println("first");
         } else {
            out.println("second");
         }
      
      

      }
      
      
      
      
      out.close();
   }
   
      
}