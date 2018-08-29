//Adjacent Replacements

import java.io.*;
import java.util.*;

public class A498{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      
      int n = Integer.parseInt(f.readLine());
      StringTokenizer st = new StringTokenizer(f.readLine());
      for(int k = 0; k < n; k++){
         int i = Integer.parseInt(st.nextToken());
         if(i%2==0){
            System.out.print((i-1) + " ");
         } else {
            System.out.print(i + " ");
         }
      }
   }
}