//make sure to make new file!
import java.io.*;
import java.util.*;

public class Solution{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int t = Integer.parseInt(st.nextToken());
      int w = Integer.parseInt(st.nextToken());
      
      
      for(int q = 1; q <= t; q++){
         int[] a = new int[7];
         for(int k = 1; k <= 6; k++){
            out.println(k);
            out.flush();
            a[k] = Integer.parseInt(f.readLine());
            if(a[k] == -1) System.exit(0);
         }
         
         for(int r1 = 0; r1 <= 100; r1++){
            if(check(r1,a)){
               break;
            }
         }
         
      }

      
      
      
      
      out.close();
   }
   
   public static boolean check(int r1, int[] a){
      int[] r = new int[7];
      r[1] = r1;
      r[2] = a[2]-a[1] - 2*r[1];
      r[3] = a[3]-a[2] - 4*r[1];
      r[4] = a[4]-a[3] - 8*r[1] - 2*r[2];
      r[5] = a[5]-a[4] - 16*r[1];
      r[6] = a[6]-a[5] - 32*r[1] - 4*r[2] - 2*r[3];
      if(2*r[1] + r[2] + r[3] + r[4] + r[5] + r[6] != a[1]) return false;
      
      System.out.println(r[1] + " " + r[2] + " " + r[3] + " " + r[4] + " " + r[5] + " " + r[6]);
      System.out.flush();
      return true;
   }
   
}