//make sure to make new file!
import java.io.*;
import java.util.*;

public class AG11{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int[] array = new int[n];
         int sumpos = 0;
         int sumneg = 0;
         int num0 = 0;
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
            if(array[k] > 0) sumpos += array[k];
            else if(array[k] < 0) sumneg += -1*array[k];
            else num0++;
         }
         
         if(sumpos == sumneg){
            out.println("NO");
            continue;
         }
         
         StringJoiner sj = new StringJoiner(" ");
         if(sumpos > sumneg){
            //add pos first
            for(int k = 0; k < n; k++){
               if(array[k] > 0) sj.add("" + array[k]);
            }
            for(int k = 0; k < n; k++){
               if(array[k] < 0) sj.add("" + array[k]);
            }
         } else {
            for(int k = 0; k < n; k++){
               if(array[k] < 0) sj.add("" + array[k]);
            }
            for(int k = 0; k < n; k++){
               if(array[k] > 0) sj.add("" + array[k]);
            }
         }
         
         for(int k = 0; k < num0; k++){
            sj.add("0");
         }
         
         out.println("YES");
         out.println(sj.toString());
         
      

      }
      
      
      
      
      out.close();
   }
   
      
}