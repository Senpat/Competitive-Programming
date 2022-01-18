//make sure to make new file!
import java.io.*;
import java.util.*;

public class A714{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         int maxm = n/2;
         if(n%2 == 0) maxm--;
         
         if(m > maxm){
            out.println(-1);
            continue;
         }
         
         int[] array = new int[n];
         int i = 1;
         for(int k = 0; k < m; k++){
            array[i] = n-k;
            i+=2;
         }
         i = 1;
         for(int k = 0; k < n; k++){
            if(array[k] == 0){
               array[k] = i;
               i++;
            }
         }
         
         StringJoiner sj = new StringJoiner(" ");
         for(int k = 0; k < n; k++){
            sj.add("" + array[k]);
         }
         out.println(sj.toString());
         
         

      }
      
      
      
      
      out.close();
   }
   
      
}