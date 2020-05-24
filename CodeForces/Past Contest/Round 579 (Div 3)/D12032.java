//make sure to make new file!
import java.io.*;
import java.util.*;
//solves both subtasks
public class D12032{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      char[] s = f.readLine().toCharArray();
      char[] t = f.readLine().toCharArray();
      
      int ns = s.length;
      int nt = t.length;
      
      int[][] fa = new int[ns][26];              //first after
      int[][] lb = new int[ns][26];              //last before
      
      for(int k = ns-1; k >= 0; k--){
         int i = s[k]-'a';
         fa[k][i] = k;
         for(int j = 0; j < 26; j++){
            if(j==i) continue;
            if(k == ns-1){
               fa[k][j] = -1;
            } else {
               fa[k][j] = fa[k+1][j];
            }
         }
      }
      
      for(int k = 0; k < ns; k++){
         int i = s[k]-'a';
         lb[k][i] = k;
         for(int j = 0; j < 26; j++){
            if(j==i) continue;
            if(k == 0){
               lb[k][j] = -1;
            } else {
               lb[k][j] = lb[k-1][j];
            }
         }
      }
      
      int start = fa[0][t[0]-'a'];
      int end = start;
      
      int[] tlocs = new int[nt];
      tlocs[0] = start;
      
      for(int k = 1; k < nt; k++){
         end = fa[end+1][t[k]-'a'];
         tlocs[k] = end;
      }
      
      int i = nt-1;           //what index l is
      int l = end;
      int r = ns;
      
      int answer = r-l-1;
      
      while(i >= 1){
         r = lb[r-1][t[i]-'a'];
         l = tlocs[i-1];
         
         answer = Math.max(answer,r-l-1);
         
         i--;
      }
      
      //check beginning
      
      r = lb[r-1][t[0]-'a'];
      l = -1;
      
      answer = Math.max(answer,r-l-1);
      
      out.println(answer);

      
      
      
      
      
      out.close();
   }
   
      
}