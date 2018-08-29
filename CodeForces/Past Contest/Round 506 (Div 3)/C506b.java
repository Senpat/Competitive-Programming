//Maximal Intersection
//memory limit exceeded
import java.io.*;
import java.util.*;

public class C506b{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      int[] l = new int[n];
      int[] r = new int[n];
      
      
      for(int k = 0; k < n; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         l[k] = Integer.parseInt(st.nextToken());
         r[k] = Integer.parseInt(st.nextToken());
         
      }
      
      int[] freq = new int[max+1];
      
      int ori = 0;
      for(int k = 0; k < n; k++){
         for(int j = matrix[k][0]; j <= matrix[k][1]; j++){
            freq[j]++;
            if(freq[j] == n) ori++;
         }
      }
      
      if(ori == 0){
         out.println(0);
         out.close();
         System.exit(0);
      }
      
      int ori4 = 0;
      for(int k = 0; k <=max; k++) if(freq[k]==n-1) ori4++;
      
      int ans = ori4;
      
      for(int k = 0; k < n; k++){
         int cur = 0;
         for(int j = matrix[k][0]; j <= matrix[k][1]; j++){
            if(freq[j]==n-1) cur++;
            //if(freq[j]==n) cur++;
         }
         ans = Math.min(ans,cur);
      }
      
      
            
      out.println(ori+ori4-ans-1);
      
      
      
      out.close();
   }
   
/*
4
1 3
2 6
0 4
3 3

*/
   
}