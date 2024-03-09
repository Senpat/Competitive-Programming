//make sure to make new file!
import java.io.*;
import java.util.*;

public class Khaybales{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      char[] array = f.readLine().toCharArray();
      int n = array.length;
      
      int p = 0;
      int inv = 0;
      
      //# of p in odd
      int po = 0;
      for(int k = 0; k < n; k++){
         if(array[k] == 'C'){
            inv += p;
         } else {
            p++;
            if(k%2 == 1) po++;
         }
      }
      
      //target # of p in odd
      int to = 0;
      for(int k = n-p; k < n; k++){
         if(k%2 == 1) to++;
      }
      
      int parityswitch = Math.abs(po-to);
      
      //parityswitch and inv have same parity.
      //proof: everytime you invert adjacent elements, inversion parity changes, and parityswitch parity changes.
      //they end in 0, so at any point they must have the same parity.
      int answer = parityswitch + (inv - parityswitch)/2;
      
      out.println(answer);
      
      
      
      
      
      out.close();
   }
   
      
}