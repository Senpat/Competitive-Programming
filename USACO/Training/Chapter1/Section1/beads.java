/*
USER: patrickgzhang
LANG: JAVA
TASK: beads
*/

import java.io.*;
import java.util.*;

class beads{
   public static int num;
   public static String necklace;
   public static PrintWriter out;
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("beads.in"));
      out = new PrintWriter(new BufferedWriter(new FileWriter("beads.out")));
      
      StringTokenizer st3 = new StringTokenizer(f.readLine());
      num = Integer.parseInt(st3.nextToken());
      st3 = new StringTokenizer(f.readLine());
      necklace = st3.nextToken();
      
      int max=0;
      for(int k = 0; k < num; k++){
         System.out.println(k);
         max = Math.max(max,calcr(necklace,k) + calcl(necklace,k));
      }
      
      if(max > num){
         max = num;
      }
      out.print(max+ "\n");
      out.close();
      
   }
   
   
   public static int calcr(String s,int n){
      char start = s.charAt(n);
      if(start=='w'){
         int count = 0;
         while(start=='w'){
            if(count >= num-1){
               out.print(num + "\n");
               out.close();
               System.exit(0);
            }
            start = s.charAt((n+count)%num);
            count++;
         }
      }
      int c = 0;
      while(start == s.charAt((n+c)%num) || s.charAt((n+c)%num)=='w'){
         System.out.println(" " + c);
         if(c >= num-1){
            out.print(num + "\n");
            out.close();
            System.exit(0);
         }
         c++;
      }
      return c; 
      
   }
   public static int calcl(String s,int n){
      char start = s.charAt(in(n-1));
      if(start=='w'){
         int count = 0;
         while(start=='w'){
            if(count >= num-1){
               out.print(num + "\n");
               out.close();
               System.exit(0);
            }
            start = s.charAt(in(n-1-count));
            count++;
         }
      }
      int c = 0;
      while(start==s.charAt(in(n-1-c)) || s.charAt(in(n-1-c))=='w'){
         System.out.println("  " + c);
         if(c >= num-1){
            out.print(num + "\n");
            out.close();
            System.exit(0);
         }
         c++;
      }
      return c; 
   } 
   
   public static int in(int n){
      if(n<0)
         return n + num;
      return n;
   }
}