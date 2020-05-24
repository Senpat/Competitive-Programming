//make sure to make new file!
import java.io.*;
import java.util.*;

public class F167{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      String[] array = new String[n];
      for(int k = 0; k < n; k++){
         array[k] = f.readLine();
      }
      
      int[] eo = new int[n];      //extra open
      int[] ec = new int[n];      //extra closed
      
      
      for(int k = 0; k < n; k++){
         
         char[] cur = array[k].toCharArray();
         Stack<Character> stk = new Stack<Character>();
         
         for(int j = 0; j < cur.length; j++){
            if(cur[j] == ')'){
               if(stk.isEmpty()){
                  ec[k]++;
               } else {
                  stk.pop();
               }
            } else {
               stk.add('(');
            }
         }
         
         eo[k] += stk.size();
      }
      
      HashSet<Integer> oo = new HashSet<Integer>();      //only extra open
      HashSet<Integer> both = new HashSet<Integer>();
      HashSet<Integer> oc = new HashSet<Integer>();      //only extra closed
      
      for(int k = 0; k < n; k++){
         if(eo[k] == 0 && ec[k] == 0) continue;
         if(ec[k] == 0) oo.add(k);
         else if(eo[k] == 0) oc.add(k);
         else both.add(k);
      }
      
      int open = 0;
      for(int i : oo){
         open += eo[i];
      }
      
      int closed = 0;
      for(int i : both){
         open -= ec[i];
         closed += eo[i];
      }
      
      for(int i : oc){
         closed -= ec[i];
      }
      
      if(open == 0 && closed == 0){
         out.println("Yes");
      } else {
         out.println("No");
      }
      
      
      
      
      
      
      out.close();
   }
   
      
}