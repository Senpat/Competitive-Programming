//make sure to make new file!
import java.io.*;
import java.util.*;

public class A880{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int[][] p10 = new int[7][2];
      p10[1][0] = 1;
      p10[1][1] = 9;
      for(int k = 2; k <= 6; k++){
         p10[k][0] = p10[k-1][0]*10;
         p10[k][1] = p10[k][0]*10-1;
      }
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         int c = Integer.parseInt(st.nextToken());
         long x = Long.parseLong(st.nextToken());
         
         long curtotal = 0L;
         long aans = -1L;
         long bans = -1L;
         long cans = -1L;
         for(int ka = p10[a][0]; ka <= p10[a][1]; ka++){
            if(ka + p10[b][0] > p10[c][1]) continue;
            if(ka + p10[b][1] < p10[c][0]) continue;
            
            //get # bs added
            long bl = (long)Math.max(p10[b][0],p10[c][0]-ka);
            long br = (long)Math.min(p10[b][1],p10[c][1]-ka);
            
            long newcurtotal = curtotal + (br-bl+1);
            if(newcurtotal >= x){
               aans = (long)ka;
               bans = bl+x-curtotal-1;
               cans = aans+bans;
               break;
            }
            
            curtotal = newcurtotal;
         }
         
         if(aans == -1L){
            out.println(-1);
         } else {
            out.println(aans + " + " + bans + " = " + cans);
         }

      }
      
      
      
      
      out.close();
   }
   
      
}