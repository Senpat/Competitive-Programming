//make sure to make new file!
import java.io.*;
import java.util.*;
//solves wrong problem oops
public class E642{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         char[] array = f.readLine().toCharArray();
         
         int total1 = 0;
         
         int[] freq1 = new int[m];
         for(int k = 0; k < n; k++){
            if(array[k] == '1'){
               freq1[k%m]++;
               total1++;
            }
         }
         
         int min = total1;
         
         for(int k = 0; k < m; k++){
            int cur = n/m;
            if(k < n%m) cur++;
            
            int to1 = cur-freq1[k];
            int to0 = total1-freq1[k];
            
            min = Math.min(min,to1+to0);
         }
         
         out.println(min);
         

      }
      
      
      
      
      out.close();
   }
   
      
}