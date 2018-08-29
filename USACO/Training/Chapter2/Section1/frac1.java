/*
USER: pgz11901
TASK: frac1
LANG: JAVA
*/

import java.util.*;
import java.io.*;

class frac1{
   
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("frac1.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("frac1.out")));
      
      int n = Integer.parseInt(f.readLine());
      
      PriorityQueue<Frac> pq = new PriorityQueue<Frac>();
      
      for(int k = 1; k <= n; k++){
         for(int j = 0;j <= k; j++){
            if(r(j,k)) pq.add(new Frac(j,k));
         }
      }
      
      while(!pq.isEmpty()){
         Frac c = pq.remove();
         System.out.println(c);
         out.println(c);
      }
      
      out.close();
      
   }
   
   public static boolean r(int j,int k){                    //check if it is reduced
      if(j==0) return k==1;
      if(j==k) return j==1;
      for(int i = 2; i <= j; i++){
         if(j%i == 0 && k%i == 0){
            return false;
         }
      }
      return true;
   }
   
   public static class Frac implements Comparable<Frac>{
   
      int num;
      int den;
      
      double value;  
      
      public Frac(int a, int b){
         num = a;
         den = b;
         
         value = (double)a/(double)b;
         //System.out.println(a + " " + b + " " + value);
      }
      
      public int compareTo(Frac f){
         return (int)((value-f.value)*100000);
      }
      
      public String toString(){
         return "" + num + "/" + den;
      }
   }
   
   
}