//make sure to make new file!
import java.io.*;
import java.util.*;

public class C337{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      int[] array = new int[n+1];
      for(int k = 1; k <= n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      int start = -1;
      int[] next = new int[n+1];
      for(int k = 1; k <= n; k++){
         if(array[k] == -1) start = k;
         else next[array[k]] = k;
      }
      
      ArrayList<Integer> answer = new ArrayList<Integer>();
      int x = start;
      answer.add(x);
      
      for(int k = 0; k < n-1; k++){
         x = next[x];
         answer.add(x);
      } 
      
      StringJoiner sj = new StringJoiner(" ");
      for(int k = 0; k < n; k++){
         sj.add("" + answer.get(k));
      }
      out.println(sj.toString());
      
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}