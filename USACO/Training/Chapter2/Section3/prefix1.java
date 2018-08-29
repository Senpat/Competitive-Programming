/*
USER: pgz11901
TASK: prefix
LANG: JAVA
*/

import java.io.*;
import java.util.*;
//too slow for last question
class prefix1{

   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("prefix.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("prefix.out")));
      
      ArrayList<String> p = new ArrayList<String>();
      
      String input1 = f.readLine();
      while(!input1.contains(".")){
         StringTokenizer s = new StringTokenizer(input1);
         while(s.hasMoreTokens()){
            p.add(0,s.nextToken());
         }
         input1 = f.readLine();
      }
      System.out.println(p);
      String seq = "";
      while(f.ready()){
         seq+=f.readLine();
      }
      
      int answer=thing(seq,0,p,0);
      
      System.out.println(answer);
      out.println(answer);
      out.close();
   }
   
   public static int thing(String seq, int index, ArrayList<String> p, int count){
      PriorityQueue<Integer> pq = new PriorityQueue<Integer>(10,Collections.reverseOrder());
      for(int k = 0; k < p.size(); k++){
         String s = p.get(k);
         if(seq.length()>=index+s.length() && seq.substring(index,index+s.length()).equals(s)){
            
            pq.add(thing(seq,index+s.length(),p,count+s.length()));
         }
      }
      if(pq.size()>0) return pq.poll();
      return count;
   }     
   
}