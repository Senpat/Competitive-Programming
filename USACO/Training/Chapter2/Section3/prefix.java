/*
USER: pgz11901
TASK: prefix
LANG: JAVA
*/

import java.io.*;
import java.util.*;
import java.lang.*;

class prefix{

   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("prefix.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("prefix.out")));
      
      ArrayList<String> p = new ArrayList<String>();
      
      String input1 = f.readLine();
      while(!input1.contains(".")){
         StringTokenizer s = new StringTokenizer(input1);
         while(s.hasMoreTokens()){
            p.add(s.nextToken());
         }
         input1 = f.readLine();
      }
      StringBuilder sb = new StringBuilder();
      while(f.ready()){
         sb.append(f.readLine());
      }
      
      String seq = sb.toString();
      
      boolean[] dp = new boolean[seq.length()+1];
      Arrays.fill(dp,false);
      
      dp[0] = true;
      for(String s : p){
         if(seq.substring(0,s.length()).equals(s)){
            dp[s.length()] = true;
         }
      }
      for(int k = 1; k < dp.length-1; k++){
         if(dp[k]){
            for(String s : p){
               if(s.length()+k<=seq.length() && seq.substring(k,k+s.length()).equals(s)){
                  dp[s.length()+k] = true;
               }
            }
         }
      }
      for(int k = dp.length-1; k >= 0; k--){
         if(dp[k]){
            System.out.println(k);
            out.println(k);
            break;
         }
      }
      out.close();
      
   }
   
}