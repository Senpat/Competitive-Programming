//make sure to make new file!
import java.io.*;
import java.util.*;
//upsolve
public class BPRc{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         int x = Integer.parseInt(st.nextToken());
         
         int plusone = n%x;
         int max = n/x;
         
         //number of colors that use the plusone
         int numone = 0;
         
         st = new StringTokenizer(f.readLine());
         int[] array = new int[m];
         boolean fail = false;
         for(int k = 0; k < m; k++){
            array[k] = Integer.parseInt(st.nextToken());
            if(array[k] == max+1) numone++;
            if(array[k] > max+1){
               fail = true;
            }
         }
         
         if(numone > plusone){
            fail = true;
         }
         
         if(!fail){
            out.println("YES");
         } else {
            out.println("NO");
         }
         
         

      }
      
      
      
      
      out.close();
   }
   
      
}