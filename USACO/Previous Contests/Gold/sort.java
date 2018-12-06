/*
TASK: sort
LANG: JAVA
*/
//semi-t
//FENWICK TREE
import java.io.*;
import java.util.*;

class sort{
   
   public static int[] bits;
   public static Pair[] array;
   public static int n;
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("sort.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sort.out")));
      
      n = Integer.parseInt(f.readLine());
      
      array = new Pair[n+1];
      array[0] = new Pair(0,0);
      for(int k = 1; k <= n; k++){
         array[k] = new Pair(Integer.parseInt(f.readLine()),k);
         //System.out.println(array[k].value);
      }
      
      Arrays.sort(array);
      
      bits = new int[n+1];
      
      int answer = 0;
      for(int k = 1; k <= n; k++){
         modify(array[k].index,1);
         
         answer = Math.max(answer,k-psums(k));
      }
      
      out.println(Math.max(1,answer));
      System.out.println(Math.max(1,answer));
      
      
      
      out.close();
   }
   
   public static void modify(int i, int x){
      for(; i <= n; i+=i&-i){
         bits[i]+=x;
      }
      
   }
   
   public static int psums(int i){
      int sum = 0;
      for(; i > 0; i-=i&-i){
         sum += bits[i];
      }
      return sum;
   }
   
   public static class Pair implements Comparable<Pair>{
      int value;
      int index;
      
      public Pair(int a, int b){
         value = a;
         index = b;
      }
      
      public int compareTo(Pair p){
         //System.out.println(value + " " + p.value);
         return value - p.value;
      }
   }
   
   
}