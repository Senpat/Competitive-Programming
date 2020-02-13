//make sure to make new file!
import java.io.*;
import java.util.*;

public class C598{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      int d = Integer.parseInt(st.nextToken());
      
      int N = n;
      int[] array = new int[m];
      st = new StringTokenizer(f.readLine());
      for(int k = 0; k < m; k++){
         array[k] = Integer.parseInt(st.nextToken());
         N-=array[k]-1;
      }
      
      int[] cond = new int[N];      //condensed
      
      int maxplaced = 0;      
      for(int k = 0; k < m; k++){
         int i = Math.min(d*(k+1)-1,N-m+k);
         cond[i] = k+1;
         maxplaced = Math.max(maxplaced,i);
      }
      
      if(N-maxplaced > d){
         out.println("NO");
         out.close();
         return;
      }
      
      out.println("YES");
      StringJoiner sj = new StringJoiner(" ");
      
      for(int k = 0; k < N; k++){
         if(cond[k] == 0){
            sj.add("0");
         } else {
            for(int j = 0; j < array[cond[k]-1]; j++){
               sj.add(""+cond[k]);
            }
         }
      }
      
      out.println(sj);
      
      
      
      
      
      
      
      out.close();
   }
   
      
}