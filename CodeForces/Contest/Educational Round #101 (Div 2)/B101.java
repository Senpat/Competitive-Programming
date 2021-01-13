//make sure to make new file!
import java.io.*;
import java.util.*;

public class B101{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int nr = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         int[] r = new int[nr];
         
         for(int k = 0; k < nr; k++){
            r[k] = Integer.parseInt(st.nextToken());
         }
         
         int nb = Integer.parseInt(f.readLine());
      
         st = new StringTokenizer(f.readLine());
         int[] b = new int[nb];
         
         for(int k = 0; k < nb; k++){
            b[k] = Integer.parseInt(st.nextToken());
         }
         
         int rmax = 0;
         int rpref = 0;
         
         for(int k = 0; k < nr; k++){
            rpref += r[k];
            rmax = Math.max(rmax,rpref);
         }
         
         int bmax = 0;
         int bpref = 0;
         
         for(int k = 0; k < nb; k++){
            bpref += b[k];
            bmax = Math.max(bmax,bpref);
         }
         
         out.println(rmax+bmax);
         
      

      }
      
      
      
      
      out.close();
   }
   
      
}