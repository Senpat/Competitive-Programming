//make sure to make new file!
import java.io.*;
import java.util.*;
//bad
public class E1140{

   public static long MOD = 998244353;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      int[] array = new int[n];
      
      st = new StringTokenizer(f.readLine());
      
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      long answer = 1;
      
      for(int k = 0; k < n; k++){
         if(array[k] != -1){
            if(k >= 2 && array[k-2] == array[k]){
               answer = 0;
               break;
            }
            if(k < n-2 && array[k+2] == array[k]){
               answer = 0;
               break;
            }
            continue;
         }
         
         long multiplier = m;
         if(k >= 2) multiplier--;
         if(k < n-2 && array[k+2] != -1) multiplier--;
         if(k >= 2 &&
         
         answer = (answer * Math.max(0,multiplier) + MOD) % MOD;
      }
      
      out.println(answer);
         
         
            
      
      
      
      
      out.close();
   }
   
      
}