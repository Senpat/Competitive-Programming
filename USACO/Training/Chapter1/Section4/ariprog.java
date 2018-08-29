/*
ID: pgz11901
TASK: ariprog
LANG: JAVA
*/

import java.io.*;
import java.util.*;

class ariprog{
   public static int n;
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("ariprog.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ariprog.out")));
      
      n = Integer.parseInt(f.readLine());
      int m = Integer.parseInt(f.readLine());
      
      ArrayList<Integer> alist = new ArrayList<Integer>();
      boolean[] bool = new boolean[m*m*2+11];
      
      for(int k = 0; k <= m; k++){
         for(int j = k; j <= m; j++){
            int i = (int)Math.pow(k,2) + (int)Math.pow(j,2);
            if(!bool[i]){
               alist.add(i);
               bool[i] = true;
            }
         }
      }
      Collections.sort(alist);
      System.out.println(alist);
      
      boolean is = false;
      /*for(int i = 1; i < 300; i++){
         for(int start = 0; start < m*m*2+10; start++){
            if(bool[start]){
               if(thereis(bool, start, i)){
                  is = true;
                  System.out.println(start + " " + i);
                  out.println(start + " " + i);
               }
            }
         }
      }*/
      ArrayList<Prog> ans = new ArrayList<Prog>();
      for(int start = 0; start < m*m*2+10; start++){
         if(bool[start]){
            for(int i = 1; i < 5000; i++){
               int at = start;
               int c;
               for(c = 0; c < n; c++){
                  if(at < bool.length && bool[at]){
                     at+=i;
                  } else {
                     break;
                  }
               }
               if(c>=n)
                  ans.add(new Prog(start,i));
                  
            }
         }
      }
      Collections.sort(ans);
      
      for(Prog p : ans){
         System.out.println(p);
         out.println(p);
      }
      
      if(ans.size() == 0){
         System.out.println("NONE");
         out.println("NONE");
      }
      
      out.close();
      
   }
   
   public static boolean thereis(boolean[] a, int start, int i){
      for(int k = 0; k < n; k++){
         if(!a[start + k*i]){
            return false;
         }
      }
      
      return true;
      
   }
   
   public static class Prog implements Comparable<Prog>{
      int start;
      int incre;
      public Prog(int a, int b){
         start = a;
         incre = b;
         
      }
      
      public int compareTo(Prog p){
         if(p.incre!=incre){
            return incre-p.incre;
         } else {
            return start - p.start;
         }
      }
      
      public String toString(){
         return start + " " + incre;
      }
   }
      
}
