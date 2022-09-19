//make sure to make new file!
import java.io.*;
import java.util.*;

public class B808{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      int[] freqa = new int[500005];
      int[] freqb = new int[500005];
      
      int amax = 0;
      int bmax = 0;
      
      for(int q = 1; q <= t; q++){
         amax = 0;
         bmax = 0;
         
         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         int[] array = new int[n];
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
            freqa[array[k]]++;
            amax = Math.max(amax,array[k]);
         }
         
         int answer = 0;
         for(int rep = 0; rep < n-1; rep++){
            
            int last = -1;
            for(int k = 0; k <= amax; k++){
               if(freqa[k] == 0) continue;
               if(last == -1){
                  freqb[0] += freqa[k]-1;
                  last = k;
                  
               } else {
                  freqb[k-last]++;
                  freqb[0] += Math.max(0,freqa[k]-1);
                  
                  bmax = Math.max(bmax,k-last);
                  
                  last = k;
                  
               }
            }
            
            for(int k = 0; k <= bmax; k++){
               freqa[k] = freqb[k];
               freqb[k] = 0;
            }
            for(int k = bmax+1; k <= amax; k++){
               freqa[k] = 0;
            }
            //out.println(amax + " " + bmax);
            amax = bmax;
            bmax = 0;
            /*
            for(int k = 0; k <= amax; k++){
               for(int j = 0; j < freqa[k]; j++){
                  out.print(k + " ");
               }
            }
            out.println(" ");
            */
            if(rep == n-2){
               answer = amax;
            }

         }
      
         freqa[amax] = 0;
         
         out.println(answer);
         
      }
      
      
      
      
      out.close();
   }
   
      
}