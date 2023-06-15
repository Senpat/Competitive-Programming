//make sure to make new file!
import java.io.*;
import java.util.*;

public class P2217{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      int n = Integer.parseInt(st.nextToken());
      int q = Integer.parseInt(st.nextToken());
      
      st = new StringTokenizer(f.readLine());
      int[] array = new int[n];
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      int[] indexof = new int[n+1];
      Arrays.fill(indexof,-1);
      int[] next = new int[n+1];
      Arrays.fill(next,-1);
      
      for(int k = n-1; k >= 0; k--){
         indexof[array[k]] = k;
         if(array[k] == n || indexof[array[k]+1] == -1){
            next[array[k]] = -1;
         } else {
            next[array[k]] = indexof[array[k]+1];
         }
      }
      
      int answer = 1;
      int i = indexof[1];
      for(int k = 2; k <= n; k++){
         if(next[array[i]] == -1){
            answer++;
            i = indexof[k];
         } else {
            i = next[array[i]];
         }
      }
      
      
      for(int t = 0; t < q; t++){
         st = new StringTokenizer(f.readLine());
         
         int x1 = Integer.parseInt(st.nextToken())-1;
         int x2 = Integer.parseInt(st.nextToken())-1;
         
         int i1 = array[x1];
         int i2 = array[x2];
         
         //switch
         int temp = array[x1];
         array[x1] = array[x2];
         array[x2] = temp;
         
         indexof[i1] = x2;
         indexof[i2] = x1;
         
         //update i1, i1-1, i2, i2-1
         int[] vals = new int[]{i1, i1-1, i2, i2-1};
         for(int x : vals){
            if(x == 0) continue;
            if(x < n && next[x] == -1) answer--;
            
            if(x < n){
               if(indexof[x+1] > indexof[x]){
                  next[x] = indexof[x+1];
               } else {
                  next[x] = -1;
                  answer++;
               }
            }
         }
         
         out.println(answer);
      }
      
      
      
      
      
      out.close();
   }
   
      
}