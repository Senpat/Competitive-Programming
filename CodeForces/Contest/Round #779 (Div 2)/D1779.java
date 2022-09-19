//make sure to make new file!
import java.io.*;
import java.util.*;

public class D1779{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int B = 18;
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int l = Integer.parseInt(st.nextToken());
         int r = Integer.parseInt(st.nextToken());
         
         int n = r-l+1;
         
         int[] array = new int[n];
         
         st = new StringTokenizer(f.readLine());
         int[] bcarray = new int[B];
         int[] bc = new int[B];
         
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
            
            for(int i = 0; i < B; i++){
               int cur = 1 << i;
               if((array[k]&cur) != 0) bcarray[i]++;
               if((k&cur) != 0) bc[i]++;
            }
         }
         
         int answer = 0;
         
         for(int k = 0; k < B; k++){
            if(bcarray[k] != bc[k]){
               answer += (1<<k);
            }
         }
         
         out.println(answer);
         
         
         

      }
      
      
      
      
      out.close();
   }
   
      
}