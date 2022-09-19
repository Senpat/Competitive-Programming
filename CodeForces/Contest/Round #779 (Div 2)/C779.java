//make sure to make new file!
import java.io.*;
import java.util.*;

public class C779{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int[] array = new int[n];
         int i1 = -1;
         boolean fail = false;
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
            if(array[k] == 1){
               if(i1 != -1) fail = true;
               i1 = k;
            }
         }
         
         if(fail || i1 == -1){
            out.println("NO");
            continue;
         }
         
         int last = 1;
         for(int k = 1; k < n; k++){
            int i = (i1+k+n)%n;
            if(array[i]-last >= 2){
               fail = true;
               break;
            }
            last = array[i];
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