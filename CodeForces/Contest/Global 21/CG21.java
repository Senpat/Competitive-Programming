//make sure to make new file!
import java.io.*;
import java.util.*;

public class CG21{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int na = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         st = new StringTokenizer(f.readLine());
         long suma = 0L;
         long[] a = new long[na];
         for(int k = 0; k < na; k++){
            a[k] = Long.parseLong(st.nextToken());
            suma += a[k];
         }
         int nb = Integer.parseInt(f.readLine());
         long sumb = 0L;
         st = new StringTokenizer(f.readLine());
         long[] b = new long[nb];
         for(int k = 0; k < nb; k++){
            b[k] = Long.parseLong(st.nextToken());
            sumb += b[k];
         }
         
         if(suma != sumb){
            out.println("NO");
            continue;
         }
         
         ArrayList<Long> alist = new ArrayList<Long>();
         long[] vala = new long[na];
         long[] freqa = new long[na];
         for(int k = 0; k < na; k++){
            long i = a[k];
            while(i % m == 0) i /= m;
            
            vala[k] = i;
            freqa[k] = a[k]/vala[k];
         }
         
         boolean fail = false;         
         int bi = 0;
         for(int k = 0; k < nb; k++){
            
            long small = b[k];
            while(small % m == 0) small /= m;
            //look for small b[k]/small times
            int num = (int)(b[k]/small);
            
            int found = num;
            while(found > 0 && bi < na && vala[bi] == small){
               if(freqa[bi] <= found){
                  found -= freqa[bi];
                  bi++;
               } else {
                  freqa[bi] -= found;
                  found = 0;
               }
            }
            
            if(found != 0){
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