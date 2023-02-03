/*
TASK: fracdec
LANG: JAVA
*/

import java.io.*;
import java.util.*;
import java.math.*;

public class fracdec{
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("fracdec.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fracdec.out")));
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      int integer = n/m;
      
      n = n%m;
      
      if(n == 0){
         out.println("" + integer + ".0");
         out.close();
         return;
      }
      
      ArrayList<Integer> decimals = new ArrayList<Integer>();
      
      HashMap<Integer,Integer> hmap = new HashMap<Integer,Integer>();
      
      int repeat = -1;
      int i = 0;
      while(true){
         if(n == 0) break;
         if(hmap.containsKey(n)){
            repeat = hmap.get(n);
            break;
         }
         
         hmap.put(n,i);
         
         decimals.add(n*10/m);
         
         n = (n*10)%m;
         i++;
      }
      
      StringJoiner sj = new StringJoiner("");
      
      sj.add("" + integer + ".");
      
      for(int k = 0; k < decimals.size(); k++){
         if(k == repeat){
            sj.add("(");
         }
         sj.add("" + decimals.get(k));
      }
      
      if(repeat != -1) sj.add(")");
      
      String answer = sj.toString();
      
      sj = new StringJoiner("");
      for(int k = 0; k < answer.length(); k++){
         if(k > 0 && k%76 == 0){
            sj.add("\n");
         }
         sj.add("" + answer.charAt(k));
      }
      out.println(sj.toString());
      
      out.close();
   }
   
   public static String repeat(String s, int x){
      StringBuilder sj = new StringBuilder();
      for(int k = 0; k < x; k++) sj.append(s);
      return sj.toString();
   }
      
}