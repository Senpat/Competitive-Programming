//Divide by 3, multiply by 2

import java.io.*;
import java.util.*;

public class D479{

   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      
      int n = Integer.parseInt(f.readLine());
      
      long[] array = new long[n];
      
      HashSet<Long> hs = new HashSet<Long>();
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      for(int k = 0; k < n; k++){
         array[k] = Long.parseLong(st.nextToken());
         hs.add(array[k]);
      }
      
      long cur = -1;
      for(int k = 0; k < n; k++){
         if(hs.contains(array[k]*3) || (array[k]%2==0 && hs.contains(array[k]/2))) continue;
         cur = array[k];
      }
      
      while(true){
         System.out.print(cur + " ");
         if(cur%3 == 0 && hs.contains(cur/3)){
            cur/=3;
         } else if(hs.contains(cur*2)){
            cur*=2;
         } else {
            break;
         }
      }
      
   }
}