//make sure to make new file!
import java.io.*;
import java.util.*;

public class D{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      int[] array = new int[m+2];
      array[0] = 0;
      array[m+1] = n;
      st = new StringTokenizer(f.readLine());
      for(int k = 1; k <= m; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      HashSet<Integer> hset = new HashSet<Integer>();
      for(int k = 0; k <= m+1; k++){
         for(int j = k+1; j <= m+1; j++){
            hset.add(array[j]-array[k]);
         }
      }
      
      StringJoiner sj = new StringJoiner(" ");
      for(int k = 1; k <= n; k++){
         if(hset.contains(k)){
            sj.add("" + k);
         }
      }
      
      out.println(sj.toString());
         
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}