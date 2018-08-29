//Less or Equal

import java.io.*;
import java.util.*;

public class C479{

   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int i = Integer.parseInt(st.nextToken());
      
      ArrayList<Integer> array = new ArrayList<Integer>(n);
      
      st = new StringTokenizer(f.readLine());
      
      for(int k = 0; k < n; k++){
         array.add(Integer.parseInt(st.nextToken()));
      }
      
      Collections.sort(array);
      
      if(i==0){
         if(array.get(0)==1){
            System.out.println(-1);
         } 
         else {
            System.out.println(1);
         }
      } 
      else {
      
         if(i!=n){
            if(array.get(i-1) < array.get(i)){              //!= doesnt work?!?!
               System.out.println(array.get(i-1));
            } else {
               System.out.println(-1);
            }
         } else {
            System.out.println(array.get(i-1));
         }
      }
   }
}