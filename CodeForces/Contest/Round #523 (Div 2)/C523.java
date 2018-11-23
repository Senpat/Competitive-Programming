//make sure to make new file!
import java.io.*;
import java.util.*;

public class C523{

   public static int[] array;
   public static long answer;
   public static long MOD = 1000000007;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      array = new int[n];
      
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      answer = 0L;
      
      for(int k = 0; k < n; k++){
         recur(k,1,1);
      }
      
      out.println((answer+MOD)%MOD);
      
      out.close();
   }
   
   public static void recur(int i, int m, long c){
      //boolean bool = false;
      
      for(int k = i+1; k < array.length; k++){
         if(array[k] % (m+1) == 0){
            //bool = true;
            recur(k,m+1,c+1);
         }
      }
      
      answer = (answer + 1 + MOD)%MOD;
   }
   
}