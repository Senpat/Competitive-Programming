/*
TASK: hayfeast
LANG: JAVA
*/

import java.io.*;
import java.util.*;

class hayfeast{
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("hayfeast.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hayfeast.out")));
      
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      long m = Long.parseLong(st.nextToken());
      
      long[] flavors = new long[n];
      int[] spices  = new int[n];
      
      for(int k = 0; k < n; k++){
         st = new StringTokenizer(f.readLine());
         flavors[k] = Long.parseLong(st.nextToken());
         spices[k]  = Integer.parseInt(st.nextToken());
      }
      
      int[] gr = new int[n];                 //first number greater on right
      int[] gl = new int[n];
      Arrays.fill(gr,n-1);
      Arrays.fill(gl,0);
      
      Stack<Num> stack = new Stack<Num>();
      
      stack.push(new Num(spices[0],0));
      
      for(int k = 1; k < n; k++){
         Num next = new Num(spices[k],k);
         
         while(!stack.empty() && stack.peek().value < next.value){
            Num cur = stack.pop();
            gr[cur.index] = next.index;
         }
         stack.push(next);
      }
      
      //for(int k = 0; k < n; k++) System.out.println(gr[k] + " ");
     
      stack = new Stack<Num>();
      
      stack.push(new Num(spices[n-1],n-1));
      
      for(int k = n-2; k >= 0; k--){
         Num next = new Num(spices[k],k);
         
         while(!stack.empty() && stack.peek().value < next.value){
            Num cur = stack.pop();
            gl[cur.index] = next.index;
         }
         stack.push(next);
      }
        
        
      //for(int k = 0; k < n; k++) System.out.println(gl[k] + " ");
      
      long[] psums = new long[n+1];
      
      for(int k = 1; k <= n; k++){
         psums[k] = psums[k-1] + flavors[k-1];
      }
      
      int min = Integer.MAX_VALUE;
      
      for(int k = 0; k < n; k++){
         if(psums[gr[k]] - psums[gl[k]] >= m){
            min = Math.min(spices[k],min);
         }
      }
      
      System.out.println(min);
      out.println(min);
      
      
      out.close();
   }
   
   public static class Num implements Comparable<Num>{
      int value;
      int index;
      public Num(int a, int b){
         value = a;
         index = b;
      }
      public int compareTo(Num n){
         return value - n.value;
      }
   }
      
}