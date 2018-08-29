/*
ID: patrickgzhang
LANG: JAVA
TASK: measurement
*/

import java.io.*;
import java.util.*;

class measurement{

   public static final String B = "Bessie";
   public static final String E = "Elsie";
   public static final String M = "Mildred";
   

   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("measurement.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("measurement.out")));
      
      int num = Integer.parseInt(f.readLine());
      
      int b = 7;
      int e = 7;
      int m = 7;
      
      int currenthigh = 7;
      int count = 0;
      
      String[] array = new String[num];
      for(int k = 0; k < num; k++){
         array[k] = f.readLine();
      }
      
      int whilecount = 0;
      int counter = 1;
      while(whilecount < num){
         if(indexof(array,counter)!=-1){
            String s = array[indexof(array,counter)].split(" ")[1];
            int diff = Integer.parseInt(array[indexof(array,counter)].split(" ")[2]);
            System.out.println(counter);
            if(s.equals(B)){
                                 
               b+=diff;
               
               if(b>currenthigh && currenthigh == b-diff && currenthigh != e && currenthigh != m){
                  currenthigh = b;
               }
               else if(currenthigh == b-diff && b>e && b>m && currenthigh != e && currenthigh != m){
                  currenthigh = b;
               }
               else if(b>=currenthigh){
                  currenthigh = b;
                  count++;
               } 
               
               else if(b-diff == currenthigh){
                  currenthigh = Math.max(b,Math.max(e,m));
                  
                  count++;
               }
               
            }
            if(s.equals(E)){
               e+=diff;
               if(e>currenthigh && currenthigh == e-diff && currenthigh != b && currenthigh != m){
                  currenthigh = e;
               }
               else if(currenthigh == e-diff && e>b && e>m && currenthigh != b && currenthigh != m){
                  currenthigh = e;
               }
               else if(e>=currenthigh){
                  currenthigh = e;
                  count++;
               } 
               else if(e-diff == currenthigh){
                  currenthigh = Math.max(b,Math.max(e,m));
                  
                  count++;
               }               
            
            }
            if(s.equals(M)){
               m+=diff;
               if(m>currenthigh && currenthigh == m-diff && currenthigh != e && currenthigh != b){
                  currenthigh = m;
               }
               else if(currenthigh == m-diff && m>e && m>b && currenthigh != e && currenthigh != b){
                  currenthigh = m;
               }
               else if(m>=currenthigh){
                  currenthigh = m;
                  count++;
               } 
               else if(m-diff == currenthigh){
                  currenthigh = Math.max(b,Math.max(e,m));
                  
                  count++;
               }               
            
            }
            
            whilecount++;
         }
         counter++;
      }
         
      out.println(count);
      out.close();
        
         
   }



   public static int indexof(String[] array, int x){
      for(int k = 0; k < array.length; k++){
         if(Integer.parseInt(array[k].split(" ")[0]) == x){
            return k;
         }
         //System.out.println(array[k] + " " + x);
      }
      //System.out.println(x);
      return -1;
   }







}
