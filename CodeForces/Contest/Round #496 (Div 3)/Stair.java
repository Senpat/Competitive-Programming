//Round 496 (Div 3)
//Tanya and Stairways

import java.util.*;
import java.io.*;

public class Stair{

   public static void main(String[] args) throws IOException{
      Scanner sc = new Scanner(System.in);
      
      int n = Integer.parseInt(sc.nextLine());
      
      int[] array = new int[n];
      
      StringTokenizer st = new StringTokenizer(sc.nextLine());
      
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      ArrayList<Integer> list = new ArrayList<Integer>();
      
      for(int k = 1; k < n; k++){
         if(array[k] == 1){
            list.add(array[k-1]);
         }
      }
      list.add(array[n-1]);
      
      System.out.println(list.size());
      for(int i : list){
         System.out.print(i + " ");
      }
      System.out.println();
   }
}