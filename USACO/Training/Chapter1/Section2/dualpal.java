/*
USER: patrickgzhang
LANG: JAVA
TASK: dualpal
*/

import java.io.*;
import java.util.*;

class dualpal{
   public static int n;
   public static int s;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("dualpal.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("dualpal.out")));
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int s = Integer.parseInt(st.nextToken())+1;
      
      int count = 0;
      while(count<n){
         System.out.println(s);
         if(dothing(s,2,0)){
            count++;
            out.println(s);
         }
         s++;
         System.out.println(count);
      }
      
      out.close();
      
      
      
   }
   
   public static boolean dothing(int n10, int base, int b){
      if(b>=2)
         return true;
      if(base>10)
         return false;
      String s = Integer.toString(n10,base);
      System.out.println(s + " " + ispal(s));
      if(ispal(s))
         return dothing(n10,base+1,b+1);
      return dothing(n10,base+1,b);
   }

   public static boolean ispal(String s){
      //String s = Integer.toString(i);
      String back = "";
      for(int k = s.length()-1; k >= 0; k--)
         back+=s.charAt(k);
      //System.out.println(back);
      if(s.equals(back)){
         //System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
         return true;
      }
      return false;
      
   }
   
}



      
      
   