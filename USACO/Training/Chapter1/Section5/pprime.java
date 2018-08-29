/*
USER: pgz11901
TASK: pprime
LANG: JAVA
*/

import java.io.*;
import java.util.*;

class pprime{

   public static int a,b;
   public static PrintWriter out;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("pprime.in"));
      out = new PrintWriter(new BufferedWriter(new FileWriter("pprime.out")));
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      a = Integer.parseInt(st.nextToken());
      b = Integer.parseInt(st.nextToken());
      
      if(5<=b || 9>=a){
         c1();
      }
      if(10<=b || 99>=a){
         c2();
      }
      if(100<=b || 999>=a){
         c3();
      }
      if(1000<=b || 9999>=a){
         c4();
      }
      if(10000<=b || 99999>=a){
         c5();
      }
      if(100000<=b || 999999>=a){
         c6();
      }
      if(1000000<=b || 9999999>=a){
         c7();
      }
      if(10000000<=b || 99999999>=a){
         c8();
      }
      
         
      out.close();
   }
   
   
   public static void c1(){
      if(p(5)){
         out.println(5);
      }
      if(p(7)){
         out.println(7);
      }
   }
   
   public static void c2(){
      if(p(11)){
         out.println(11);
      }
   }
   
   public static void c3(){
      for(int k = 1; k <= 9; k++){
         for(int j = 0; j <= 9; j++){
            int i = k*100 + j * 10 + k;
            if(p(i) && prime(i)){
               out.println(i);
            }
         }
      }
   }
   
   public static void c4(){
      for(int k = 1; k <= 9; k++){
         for(int j = 0; j <= 9; j++){
            int i = k*1000 + j*100 + j * 10 + k;
            if(p(i) && prime(i)){
               out.println(i);
            }
         }
      }
   }
   
   public static void c5(){
      for(int k = 1; k <= 9; k++){
         for(int j = 0; j <= 9; j++){
            for(int l = 0; l <= 9; l++){
               int i = k*10000 + j*1000 + l*100 + j * 10 + k;
               if(p(i) && prime(i)){
                  out.println(i);
               }
            }
         }
      }
   }
   
   public static void c6(){
      for(int k = 1; k <= 9; k++){
         for(int j = 0; j <= 9; j++){
            for(int l = 0; l <= 9; l++){
               int i = k*100000 + j*10000 + l*1000 + l*100 + j * 10 + k;
               if(p(i) && prime(i)){
                  out.println(i);
               }
            }
         }
      }
   }
   
   public static void c7(){
      for(int k = 1; k <= 9; k++){
         for(int j = 0; j <= 9; j++){
            for(int l = 0; l <= 9; l++){
               for(int w = 0; w <= 9; w++){
                  int i = k*1000000 + j*100000 + l*10000 + w*1000 + l*100 + j * 10 + k;
                  if(p(i) && prime(i)){
                     out.println(i);
                  }
               }
            }
         }
      }
   }
   
   public static void c8(){
      for(int k = 1; k <= 9; k++){
         for(int j = 0; j <= 9; j++){
            for(int l = 0; l <= 9; l++){
               for(int w = 0; w <= 9; w++){
                  int i = k*10000000 + j*1000000 + l*100000 + + w*10000 + w*1000 + l*100 + j * 10 + k;
                  if(p(i) && prime(i)){
                     out.println(i);
                  }
               }
            }
         }
      }
   }

   public static boolean p(int q){
      return a<=q && b>=q;
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