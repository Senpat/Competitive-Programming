/*
ID: patrickgzhang
LANG: JAVA
TASK: milk
*/

import java.io.*;
import java.util.*;

class milk{
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("milk.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk.out")));
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      PriorityQueue<Farmer> pq = new PriorityQueue<Farmer>();
      
      for(int k = 0; k < m; k++){
         StringTokenizer st1 = new StringTokenizer(f.readLine());
         
         pq.add(new Farmer(Integer.parseInt(st1.nextToken()),Integer.parseInt(st1.nextToken())));
         
      }
      
      int cost = 0;
      int bought = 0;
      
      while(bought < n){
         Farmer farm = pq.poll();
         
         if(bought + farm.max < n){
            bought += farm.max;
            cost += farm.max * farm.price;
         } else {
            cost+=farm.price*(n-bought);
            bought = n;
         }
      }
      
      out.println(cost);
      out.close();
   }
      
      
      
      
      
   
   public static class Farmer implements Comparable<Farmer>{
      int price;
      int max;
      
      public Farmer(int a, int b){
         price = a;
         max = b;
      }
      
      public int compareTo(Farmer f){
         return price-f.price;
      }  
      
   }
}