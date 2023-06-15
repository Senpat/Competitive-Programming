//make sure to make new file!
import java.io.*;
import java.util.*;

public class P2216{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      int[] array = new int[n];
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      int[] indexof = new int[n+1];
      Arrays.fill(indexof,-1);
      int[] next = new int[n];
      Arrays.fill(next,-1);
      
      for(int k = n-1; k >= 0; k--){
         indexof[array[k]] = k;
         if(array[k] == n || indexof[array[k]+1] == -1){
            next[k] = -1;
         } else {
            next[k] = indexof[array[k]+1];
         }
      }
      
      int answer = 1;
      int i = indexof[1];
      for(int k = 2; k <= n; k++){
         if(next[i] == -1){
            answer++;
            i = indexof[k];
         } else {
            i = next[i];
         }
      }
      
      out.println(answer);
      
      
      
      
      
      
      out.close();
   }
   
      
}