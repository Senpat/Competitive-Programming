//make sure to make new file!
import java.io.*;
import java.util.*;

public class C662{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int[] array = new int[n];
         int[] freq = new int[n+1];
         
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
            freq[array[k]]++;
         }
         
         
         int max = 0;
         int maxfreq = 0;
         
         for(int k = 1; k <= n; k++){
            if(freq[k] > max){
               max = freq[k];
               maxfreq = 1;
            } else if(freq[k] == max){
               maxfreq++;
            }
         }
         
         int answer = (n-maxfreq)/(max-1)-1;
         out.println(answer);

      }
      
      
      
      
      out.close();
   }
   
      
}