//make sure to make new file!
import java.io.*;
import java.util.*;

public class D126{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      long m = Long.parseLong(st.nextToken());
      int mi = (int)m;
      
      st = new StringTokenizer(f.readLine());
      long[] array = new long[n];
      for(int k = 0; k < n; k++){
         array[k] = Long.parseLong(st.nextToken());
      }
      
      long[] segs = new long[n];
      
      long answer = 0L;
      long total = 0L;
      long numsegs = 0L;
      for(int k = n-1; k >= 0; k--){
         //add a segment to make (array[k]-total) 0
         long dif = array[k]-total;
         if(dif > 0){
            if(k >= mi-1){
            //add ceil(dif/m) segments
               long add = (dif + m-1)/m;
               segs[k-mi+1] = add;
               total += add*m;
                        
               numsegs += add;
               answer += add;
            } else {
               //add ceil(dif/(k+1)) segments
               long add = (dif + k+1 -1)/(long)(k+1);
               segs[0] += add;
               total += add*(long)(k+1);
               
                        
               numsegs += add;
               answer += add;
            }
         }

         if(k < n-1) numsegs -= segs[k+1];
         total -= numsegs;
      }
      
      out.println(answer);
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}