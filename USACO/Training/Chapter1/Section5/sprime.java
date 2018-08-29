/*
USER: pgz11901
TASK: sprime
LANG: JAVA
*/

import java.io.*;
import java.util.*;

class sprime{

   public static PrintWriter out;
   
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("sprime.in"));
      out = new PrintWriter(new BufferedWriter(new FileWriter("sprime.out")));
      
      int n = Integer.parseInt(f.readLine());
      
      dothing(2,1,n);
      dothing(3,1,n);
      dothing(5,1,n);
      dothing(7,1,n);
      
      out.close();
   }
   
   
   public static void dothing(int x, int n, int e){
      if(prime(x)){
         if(n==e){
            out.println(x);
         } else {
            dothing(x*10+1,n+1,e);
            dothing(x*10+3,n+1,e);
            dothing(x*10+7,n+1,e);
            dothing(x*10+9,n+1,e);
         }
      }
   
   
   }
   
   
   public static boolean prime(int a){
      if(a == 2) 
         return true;
      if(a%2 == 0) 
         return false;
      
      for(int k = 3; k*k<=a; k+=2){
         if(a%k==0) 
            return false;
      }
      
      return true;
   }
}