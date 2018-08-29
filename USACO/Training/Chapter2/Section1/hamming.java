/*
USER: pgz11901
TASK: hamming
LANG: JAVA
*/

import java.util.*;
import java.io.*;

class hamming{
   
   public static int n,b,d;
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("hamming.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hamming.out")));
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      n = Integer.parseInt(st.nextToken());
      b = Integer.parseInt(st.nextToken());
      d = Integer.parseInt(st.nextToken());
      
      ArrayList<Integer> al = new ArrayList<Integer>();
      
      al.add(0);
      int i = 1;
      while(al.size()<n){
         if(check(i,al)){
            al.add(i);
         }
         i++;
      }
      
      for(int k = 0; k < al.size(); k++){
         System.out.print(al.get(k) + " ");
         out.print(al.get(k));
         if(k<al.size()-1 && k%10!=9) out.print(" ");
         if(k%10==9 && k < al.size()-1){
            System.out.println();
            out.println();
         }
      }
      
      out.println();
      out.close();
      
   }
   
   
   
   public static boolean check(int i, ArrayList<Integer> al){
      for(Integer a : al){
         String s = Integer.toBinaryString(a^i);
         if(s.length()-s.replace("1","").length()<d) return false;
      }
      return true;
   }
   
   
   
}