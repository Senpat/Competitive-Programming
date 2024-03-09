//make sure to make new file!
import java.io.*;
import java.util.*;

public class A170{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      char[] s = f.readLine().toCharArray();
      char[] t = f.readLine().toCharArray();
      
      PriorityQueue<Integer> toa = new PriorityQueue<Integer>();
      PriorityQueue<Integer> tob = new PriorityQueue<Integer>();
      
      int firsta = Integer.MAX_VALUE;
      int lastb = -1;
      for(int k = 0; k < n; k++){
         if(s[k] == 'A' && t[k] == 'B') tob.add(k);
         else if(s[k] == 'B' && t[k] == 'A') toa.add(k);
         
         if(t[k] == 'A' && firsta == Integer.MAX_VALUE){
            firsta = k;
         }
         if(t[k] == 'B'){
            lastb = k;
         }
      }
      
      int answer = 0;
      while(!toa.isEmpty() || !tob.isEmpty()){
         if(toa.isEmpty()){
            int i = tob.poll();
            if(firsta < i){
               answer++;
            } else {
               answer = -1;
               break;
            }
         } else if(tob.isEmpty()){
            int i = toa.poll();
            if(lastb > i){
               answer++;
            } else {
               answer = -1;
               break;
            }
         } else {
            int a = toa.poll();
            int b = tob.poll();
            
            if(a < b){
               answer++;
            } else {
               //do b
               toa.add(a);
               if(firsta < b){
                  answer++;
               } else {
                  answer = -1;
                  break;
               }
            }
         }
      }
      
      out.println(answer);
      
      
      
      
      
      out.close();
   }
   
      
}