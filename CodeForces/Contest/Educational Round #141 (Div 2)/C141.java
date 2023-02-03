//make sure to make new file!
import java.io.*;
import java.util.*;

public class C141{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         int[] array = new int[n];
         Integer[] sorted = new Integer[n];
         st = new StringTokenizer(f.readLine());
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
            sorted[k] = array[k];
         }
         
         Arrays.sort(sorted);
         
         int l = 1;
         int r = n;
         int ans = n+1;
         
         while(l <= r){
            int mid = l + (r-l)/2;
            
            boolean can = false;
            
            //beat n-mid+1 total people
            int sum = 0;
            for(int k = 0; k < n-mid+1; k++){
               sum += (int)sorted[k];
            }
            
            if(sum <= m){
               can = true;
            }
            
            //beat #n-mid and beat n-mid-1 others
            int i = 0;
            int beat = 0;
            sum = array[n-mid];
            
            while(beat < n-mid-1){
               if(i < n-1 && array[n-mid] == (int)sorted[i] && (int)sorted[i] != (int)sorted[i+1]){
                  i++;
               } else {
                  sum += (int)sorted[i];  
                  i++;
                  beat++;
               }
            }
            
            if(sum <= m){
               can = true;
            }
            
            if(can){
               ans = mid;
               r = mid-1;
            } else {
               l = mid+1;
            }
         }
      
         out.println(ans);
      }
      
      
      
      
      out.close();
   }
   
      
}