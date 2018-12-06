/*
TASK: bphoto
LANG: JAVA
*/
//semi-t

import java.io.*;
import java.util.*;

class bphoto{

   public static int[] bits;
   public static int n;
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("bphoto.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("bphoto.out")));
      
      
      n = Integer.parseInt(f.readLine());
      
      State[] array = new State[n+1];
      array[0] = new State(Integer.MAX_VALUE,0);
      for(int k = 1; k <=n; k++){
         array[k] = new State(Integer.parseInt(f.readLine()),k);
      }
      //System.out.println("1");
      
      Arrays.sort(array);
      
      bits = new int[n+1];
      
      int count = 0;
      
      for(int k = 1; k <= n; k++){
         //System.out.println(array[k].index);
         int left = psum(array[k].index);
         int right = k-1-left;
         
         if(left == 0 && right == 0){
            //don't do anything
         } else if(left == 0){
            count++;
         } else if(right == 0){
            count++;
         } else {
            if(1.0*Math.max(left,right) / (1.0*Math.min(left,right)) > 2){
               count++;
            }
         }
         
         //update
         update(array[k].index,1);
      }
      //System.out.println("1");
      
      System.out.println(count);
      out.println(count);
      
      out.close();
      
        
   }
   
   public static void update(int i, int x){
      for(; i <= n; i += i&-i){
         //System.out.println(i);
         bits[i]+=x;
      }
   
   }
   
   public static int psum(int i){
      int sum = 0;
      for(; i > 0; i -= i&-i){
         //System.out.println(i);
         sum += bits[i];
      }
      return sum;
   
   }
   
   
   
   
   public static class State implements Comparable<State>{
      int value,index;
      public State(int a, int b){
         value = a;
         index = b;
      }
      
      public int compareTo(State s){
         return s.value-value;                  //sort array backwards
      }
   }
      
}