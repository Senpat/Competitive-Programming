//make sure to make new file!
import java.io.*;
import java.util.*;

public class E337{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      n--;
      
      int p = 1;
      ArrayList<ArrayList<Integer>> alist = new ArrayList<ArrayList<Integer>>();
      
      while(p <= n){
         ArrayList<Integer> cur = new ArrayList<Integer>();
         for(int k = 0; k <= n; k++){
            if((k|p) == k){
               cur.add(k+1);
            }
         }
         alist.add(cur);
         
         p <<= 1;
      }
      
      out.println(alist.size());
      for(int k = 0; k < alist.size(); k++){
         out.print(alist.get(k).size());
         for(int i : alist.get(k)){
            out.print(" " + i);
         }
         out.println();
      }
      out.flush();
      
      char[] x = f.readLine().toCharArray();
      
      //read in like a backwards binary string
      p = 1;
      int answer = 0;
      for(int k = 0; k < x.length; k++){
         if(x[k] == '1'){
            answer += p;
         }
         p <<= 1;
      }
      
      answer++;
      out.println(answer);
      
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}