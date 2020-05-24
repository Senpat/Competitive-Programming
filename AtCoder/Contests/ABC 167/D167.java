//make sure to make new file!
import java.io.*;
import java.util.*;

public class D167{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      long m = Long.parseLong(st.nextToken());
      
      int[] array = new int[n+1];
      st = new StringTokenizer(f.readLine());
      for(int k = 1; k <= n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      //find cycle
      ArrayList<Integer> cycle = new ArrayList<Integer>();
      int[] lastindex = new int[n+1];
      Arrays.fill(lastindex,-1);
      
      int index = 1;
      int time = 0;
      
      while(lastindex[index] == -1){
         if((long)time == m){
            out.println(index);
            out.close();
            return;
         }
         cycle.add(index);
         lastindex[index] = time;
         index = array[index];
         time++;
      }
      
      long mod = (long)(time-lastindex[index]);
      int answerindex = (int)((m-(long)lastindex[index]+mod)%mod) + lastindex[index];
      out.println(cycle.get(answerindex));
      
      
      
      
      
      
      out.close();
   }
   
      
}