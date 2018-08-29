//Two-Gram

import java.io.*;
import java.util.*;

public class B479{

   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      
      int n = Integer.parseInt(f.readLine());
      
      String s = f.readLine();
      
      HashMap<String,Integer> map = new HashMap<String,Integer>();
      
      for(int k = 0; k < n-1; k++){
         String cur = s.substring(k,k+2);
         if(map.containsKey(cur)){
            map.put(cur,map.get(cur)+1);
         } else {
            map.put(cur,1);
         }
      }
      
      String maxs = s.substring(0,2);
      int max = 0;
      
      for(String g : map.keySet()){
         if(map.get(g) > max){
            max = map.get(g);
            maxs = g;
         }
      }
      
      System.out.println(maxs);
   }
}