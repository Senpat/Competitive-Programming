/*
USER: pgz11901
TASK: zerosum
LANG: JAVA
*/

import java.io.*;
import java.util.*;

class zerosum{

   public static PrintWriter out;
   
   public static int n;
   
   public static char[] signs = {' ','+','-'};

   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("zerosum.in"));
      out = new PrintWriter(new BufferedWriter(new FileWriter("zerosum.out")));
   
      n = Integer.parseInt(f.readLine());
      
      ArrayList<Character> sums = new ArrayList<Character>();
      
      fill(new ArrayList<Character>(),0);
      
      out.close();
   
   
   
   
   
   }
   
   public static void fill(ArrayList<Character> al,int index){
      if(index >= n-1){
         if(calculate(al)){
            String s = "1";
            for(int k = 0; k < al.size(); k++){
               s+=al.get(k);
               s+= "" + (k+2);
            }
            System.out.println(s);
            out.println(s);
         }
         return;
      } 
      else {
         for(int k = 0; k < signs.length; k++){
            ArrayList<Character> newsums = (ArrayList<Character>)al.clone();
            newsums.add(signs[k]);
            fill(newsums,index+1);
         }
      }
      return;
   }
   
   public static boolean calculate(ArrayList<Character> al){
      ArrayList<Character> ops = new ArrayList<Character>();
      ArrayList<Integer> nums = new ArrayList<Integer>();
      
      nums.add(1);
      
      for(int k = 0; k < al.size(); k++){
         if(al.get(k) == '+'){
            nums.add(k+2);
            ops.add('+');
         } 
         else if(al.get(k) == '-'){
            nums.add(k+2);
            ops.add('-');
         } 
         else if(al.get(k) == ' '){
            int last = nums.get(nums.size()-1);
            nums.set(nums.size()-1, Integer.parseInt(""+last + (k+2)));
         }
      }
      
      int sum = nums.get(0);
      
      for(int k = 0; k < ops.size();k++){
         if(ops.get(k) == '+'){
            sum+=nums.get(k+1);
         } 
         else if(ops.get(k) == '-'){
            sum-=nums.get(k+1);
         } 
         else {
            System.out.println("wut");
         }
      }
      
      return sum == 0;
   }



}
