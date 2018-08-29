/*
USER: pgz11901
TASK: preface
LANG: JAVA
*/

import java.io.*;
import java.util.*;

class preface{

   public static HashMap<Character,Integer> counts;

   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("preface.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("preface.out")));
      
      int n = Integer.parseInt(f.readLine());
      
      HashMap<Integer,Character> map = new HashMap<Integer,Character>();
      map.put(1,'I');
      map.put(5,'V');
      map.put(10,'X');
      map.put(50,'L');
      map.put(100,'C');
      map.put(500,'D');
      map.put(1000,'M');
      
      counts = new HashMap<Character,Integer>();
      
      for(int k = 1; k <= n; k++){                       //loop through every number
         String s = String.valueOf(k);                   //loop through every digit
         for(int j = 0; j < s.length(); j++){
            int digit = s.length()-j;
            int value = Character.getNumericValue(s.charAt(j));
            if(value>=1 && value <=3){
               add(map.get((int)Math.pow(10,digit-1)),value);
            } else if(value == 4){
               add(map.get((int)Math.pow(10,digit-1)),1);
               add(map.get(5*(int)Math.pow(10,digit-1)),1);
            } else if(value == 5){
               add(map.get(5*(int)Math.pow(10,digit-1)),1);
            } else if(value >= 5 && value <= 8){
               add(map.get(5*(int)Math.pow(10,digit-1)),1);
               add(map.get((int)Math.pow(10,digit-1)),value-5);
            } else if(value == 9){
               add(map.get((int)Math.pow(10,digit-1)),1);
               add(map.get((int)Math.pow(10,digit)),1);
            }
         }
      }
      //System.out.println(counts);
      String lul = "IVXLCDM";
      
      for(int i = 0; i < lul.length(); i++){
         char ch = lul.charAt(i);
         if(counts.containsKey(ch)){
            System.out.println(ch + " " + counts.get(ch));
            out.println(ch + " " + counts.get(ch));
         }
      }
      
      out.close();
            
            
            
   }
      
   public static void add(char c, int times){
      if(counts.containsKey(c)){
         counts.put(c,counts.get(c)+times);
      } else {
         counts.put(c,times);
      }
      //System.out.println(counts);
   }    
       
}
       
       
       