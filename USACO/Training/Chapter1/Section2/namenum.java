/*
USER: patrickgzhang
LANG: JAVA
TASK: namenum
*/

import java.io.*;
import java.util.*;

class namenum{
   public static char[][] matrix = {{'A','B','C'},{'D','E','F'},{'G','H','I'},{'J','K','L'},{'M','N','O'},{'P','R','S'},{'T','U','V'},{'W','X','Y'}};;
   public static BufferedReader d;
   public static PrintWriter out;
   public static String[] dict;
   public static boolean printed = false;
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("namenum.in"));
      out = new PrintWriter(new BufferedWriter(new FileWriter("namenum.out")));
      
      d = new BufferedReader(new FileReader("dict.txt"));
      
      dict = new String[5000];
      int count = 0;
      while(d.ready()){
         dict[count] = d.readLine();
         count++;
      }
      
      long num = Long.parseLong(f.readLine());
      
      dothing(num,"");
      
      if(!printed)
         out.println("NONE");
         
      
      out.close();
      
   }
 
   public static void dothing(long n, String s)throws IOException{
      int variable = firstDigit(n)-2;
      if(n < 10){
         for(int i = 0; i < 3; i++){
            //System.out.println(s);
            if(check(s + matrix[variable][i])){
               out.println(s + matrix[variable][i]);
               printed = true;
            }
         }
         
      }
      else {
         for(int i = 0; i < 3; i++){
            if(checkin(s + matrix[variable][i]))
               dothing(n-(long)(firstDigit(n)*Math.pow(10,(int)Math.log10(n))),s + matrix[variable][i]);
         }
      }
   }
         
         
         
         
   public static boolean check(String s)throws IOException{
      for(int k = 0; k < dict.length; k++){
         if(dict[k] != null && dict[k].equals(s)){
            return true;
         }
      }
      return false;
   }
   public static boolean checkin(String s)throws IOException{
      System.out.println(s);
      for(int k = 0; k < dict.length; k++){
         if(dict[k] != null && dict[k].length() >= s.length()){
            if(dict[k].substring(0,s.length()).equals(s))
               return true;
         }
      }
      return false;
   }
   
   public static int firstDigit(long n)throws IOException{
      while (9 < n)
         n /= 10;
      return (int)Math.abs(n);
   }
}