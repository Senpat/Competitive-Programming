//Round #494 (Div 3) A
//Polycarp's Pockets

import java.io.*;
import java.util.*;

public class Polycarp{
   
   public static void main(String[] args)throws IOException{
      Scanner sc = new Scanner(System.in);
      
      HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
      
      int n = Integer.parseInt(sc.nextLine());
      
      StringTokenizer st = new StringTokenizer(sc.nextLine());
      
      int max = 0;
      for(int k = 0; k < n; k++){
         int i = Integer.parseInt(st.nextToken());
         if(!map.containsKey(i)){
            map.put(i,0);
         }
         map.put(i,map.get(i)+1);
         max = Math.max(map.get(i),max);
      }
      
      System.out.println(max);
   }
}