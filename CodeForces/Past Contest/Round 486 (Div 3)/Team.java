//Round #486 (Div 3) A
//Diverse Team

import java.io.*;
import java.util.*;
import java.math.*;

public class Team{
   
   public static void main(String[] args)throws IOException{
      Scanner sc = new Scanner(System.in);
      
      StringTokenizer st = new StringTokenizer(sc.nextLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      int[] array = new int[n];
      
      st = new StringTokenizer(sc.nextLine());
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      HashSet<Integer> no = new HashSet<Integer>();
      ArrayList<Integer> indeces = new ArrayList<Integer>();
      for(int k = 0; k < n; k++){
         if(!no.contains(array[k])){
            indeces.add(k+1);
            no.add(array[k]);
         }
      }
      
      if(indeces.size()>=m){
         System.out.println("YES");
         for(int k = 0; k < m; k++){
            System.out.print(indeces.get(k) + " ");
         }
      } else {
         System.out.print("NO");
      }
      
      System.out.println();
   }
}
      
      
      