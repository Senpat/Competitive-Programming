//make sure to make new file!
import java.io.*;
import java.util.*;
 
public class CGB22b{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      int N = 205;
      
      for(int q = 1; q <= t; q++){
 
         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         long[] array = new long[n];
         for(int k = 0; k < n; k++){
            array[k] = Long.parseLong(st.nextToken());
         }
         
         boolean[][] mods = new boolean[N][N];
         boolean fail = false;
         
         for(int k = 0; k < n; k++){
            for(int j = k+1; j < n; j++){
               long diff = Math.abs(array[k]-array[j]);
               if(diff == 0L){
                  fail = true;
                  break;
               }

               //mods[diff][(int)(array[k]%(long)diff)] = true;
               for(int h = 2; h <= (int)Math.min((long)(N-1),diff); h++){
                  if(diff%(long)h == 0L){
                     mods[h][(int)(array[k]%(long)h)] = true;
                  }
               }
            }
            
            if(fail) break;
         }
         
         for(int k = 2; k < N; k++){
            boolean found = false;
            for(int j = 0; j < k; j++){
               if(!mods[k][j]) found = true;
            }
            
            if(!found){
               fail = true;
               break;
            }
         } 
      
         if(fail){
            out.println("NO");
         } else {
            out.println("YES");
         }
 
      }
      
      
      
      
      out.close();
   }
   
      
}
