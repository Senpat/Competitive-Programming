//make sure to make new file!
import java.io.*;
import java.util.*;
//in contest attempt, completely wrong
public class E131{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         char[] a = f.readLine().toCharArray();
         char[] b = f.readLine().toCharArray();
         
         int[] istart = new int[m+1];
         Arrays.fill(istart,-1);
         istart[0] = 0;
         
         int i = 1;
         for(int k = 0; k < n; k++){
            if(a[k] == b[i]){
               istart[i] = k;
               i++;
            }
         }
         
         if(istart[m-1] == -1){
            out.println(-1);
            continue;
         }
         
         int[] iend = new int[m+1];
         iend[m] = 0;
         int i = 1;
         for(int k = n-1; k >= 0; k--){
            if(a[k] == b[m-i+1]){
               iend[i] = k;
               i++;
            }
         }
         
         int[] home = new int[m+1];
         int[] end = new int[m+1];

      

      }
      
      
      
      
      out.close();
   }
   
      
}