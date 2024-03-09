//Slime Escape
import java.io.*;
import java.util.*;

public class D1734{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         long[] array = new long[n];
         st = new StringTokenizer(f.readLine());
         for(int k = 0; k < n; k++){
            array[k] = Long.parseLong(st.nextToken());
         }
         
         int l = m-1;
         int r = m-1;
         
         long curhealth = array[m-1];
         
         int lto = l;
         int rto = r;
         long lneed = 0L;
         long lgain = 0L;
         long rneed = 0L;
         long rgain = 0L;
         
         while(true){
            if(l == 0 || r == n-1) break;
            
            if(lto == l){
               //see minimum way to expand such that net is 0, or you reach the end
               int li = l-1;
               long cursum = array[li];
               long min = Math.min(0L,array[li]);
               
               while(li-1 >= 0 && cursum < 0){
                  cursum += array[li-1];
                  min = Math.min(min,cursum);
                  li--;
               }
               
               lto = li;
               lgain = cursum;
               lneed = -1L*min;
            }
            
            if(rto == r){
               int ri = r+1;
               long cursum = array[ri];
               long min = Math.min(0L,array[ri]);
               
               while(ri+1 < n && cursum < 0){
                  cursum += array[ri+1];
                  min = Math.min(min,cursum);
                  ri++;
               }
               
               rto = ri;
               rgain = cursum;
               rneed = -1L*min;
            }
            
            //try left
            if(lneed <= curhealth){
               l = lto;
               curhealth += lgain;
               continue;
            }
            
            //try right
            if(rneed <= curhealth){
               r = rto;
               curhealth += rgain;
               continue;
            }
            
            //couldn't extend anything
            break;
            
         }
         
         if(l == 0 || r == n-1){
            out.println("YES");
         } else {
            out.println("NO");
         }

      }
      
      
      
      
      out.close();
   }
   
      
}