//Round #490 (Div 3) D
//Equalize the Remainders

import java.io.*;
import java.util.*;
import java.math.*;

public class Remainders{
   
   public static void main(String[] args)throws IOException{
      Scanner sc = new Scanner(System.in);
      
      StringTokenizer st = new StringTokenizer(sc.nextLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      int K = n/m;
      
      int[] array = new int[n];
      
      st = new StringTokenizer(sc.nextLine());
      
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      ArrayList<ArrayList<Integer>> lists = new ArrayList<ArrayList<Integer>>();
      
      for(int k = 0; k < n; k++){
         lists.add(new ArrayList<Integer>());
      }
      
      for(int k = 0; k < n; k++){
         lists.get(array[k]%m).add(k);
      }
      
      ArrayList<Pair> free = new ArrayList<Pair>();
      
      long answer = 0L;
      
      for(int k = 0; k < 2*m; k++){
         int cur = k%m;
         int cursize = lists.get(cur).size();
         if(cursize > K){
            for(int i = 0; i < cursize-K; i++){
               int v = lists.get(cur).get(lists.get(cur).size()-1);             //get last
               lists.get(cur).remove(lists.get(cur).size()-1);                  //delete last
               free.add(new Pair(v,k));
            }
         }
         if(cursize < K && !free.isEmpty()){
            for(int i = 0; i < K-cursize; i++){
               if(free.isEmpty()) 
                  break;
               Pair p = free.get(free.size()-1);
               free.remove(free.size()-1);
               lists.get(cur).add(p.value);
               array[p.value] += k-p.index;
               answer += k-p.index;
            }
         }
      }
      
      System.out.println(answer);
      for(int i : array) System.out.print(i + " ");
      System.out.println();
               
                  
   }
   
   public static class Pair{
      int value;
      int index;
      
      public Pair(int a, int b){
         value = a;
         index = b;
      }
   }
}