//make sure to make new file!
import java.io.*;
import java.util.*;

public class B681{

   public static long MOD = 998244353L;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         int[] a = new int[n];
         int[] b = new int[m];
         
         int[] indexofa = new int[n+1];
         
         st = new StringTokenizer(f.readLine());
         for(int k = 0; k < n; k++){
            a[k] = Integer.parseInt(st.nextToken());
            indexofa[a[k]] = k;
         }
         st = new StringTokenizer(f.readLine());
         HashSet<Integer> bset = new HashSet<Integer>();
         for(int k = 0; k < m; k++){
            b[k] = Integer.parseInt(st.nextToken());
            bset.add(b[k]);
         }
         
         long answer = 1L;
         
         for(int k = 0; k < m; k++){
            long start;
            int i = indexofa[b[k]];
            if(i == 0 || i == n-1) start = 1L;
            else start = 2L;
            
            if(i > 0){
               //if a[i-1] comes after b[k] in b
               if(bset.contains(a[i-1])){
                  start--;
               }
            }
            
            if(i < n-1){
               if(bset.contains(a[i+1])){
                  start--;
               }
            }
            
            bset.remove(b[k]);
            
            answer = (answer * start + MOD)%MOD;
         }
         
         out.println(answer);
      

      }
      
      
      
      
      out.close();
   }
   
   
      
}