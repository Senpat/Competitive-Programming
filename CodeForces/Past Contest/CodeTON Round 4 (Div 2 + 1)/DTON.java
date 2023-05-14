//make sure to make new file!
import java.io.*;
import java.util.*;

public class DTON{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int tt = 1; tt <= t; tt++){
      
         int q = Integer.parseInt(f.readLine());
         
         long[] answer = new long[q];
         
         long max = Long.MAX_VALUE;
         long min = 0L;
         for(int qq = 0; qq < q; qq++){
            StringTokenizer st = new StringTokenizer(f.readLine());
            
            int i = Integer.parseInt(st.nextToken());
            long a = Long.parseLong(st.nextToken());
            long b = Long.parseLong(st.nextToken());
            if(i == 1){
               long n = Long.parseLong(st.nextToken());
               
               long curmax = (a-b)*(n-1) + a;
               long curmin = (a-b)*(n-1) + b+1;
               
               if(n == 1){
                  curmax = a;
                  curmin = 1;
               }
               
               if(curmin > max || curmax < min){
                  answer[qq] = 0;
               } else {
                  answer[qq] = 1;
                  max = Math.min(max,curmax);
                  min = Math.max(min,curmin);
               }
            } else {
               //how many days if tree height was max
               long xmax = (max-a + a-b-1)/(a-b) + 1;
               long xmin = (min-a + a-b-1)/(a-b) + 1;
               
               if(a >= max) xmax = 1;
               if(a >= min) xmin = 1;
               
               if(xmax == xmin){
                  answer[qq] = xmax;
               } else {
                  answer[qq] = -1;
               }
            }
         }
         
         StringJoiner sj = new StringJoiner(" ");
         for(int k = 0; k < q; k++){
            sj.add("" + answer[k]);
         }
         out.println(sj.toString());
      }
      
      
      
      
      out.close();
   }
   
      
}