//make sure to make new file!
import java.io.*;
import java.util.*;

public class D550{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int N = 200005;
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      int[] array = new int[n];
      int[] freqs = new int[N];
      int max = 0;
      int maxi = -1;
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
         freqs[array[k]]++;
         
         if(freqs[array[k]] > max){
            max = freqs[array[k]];
            maxi = k;
         }
      }
      
      StringJoiner sj = new StringJoiner("\n");
      sj.add("" + (n-max));
      //go left from maxi
      for(int k = maxi-1; k >= 0; k--){
         if(array[k] == array[maxi]) continue;
         if(array[k] < array[maxi]){
            sj.add("1 " + (k+1) + " " + (k+2));            //add 1 to the indeces when printing
         } else {
            sj.add("2 " + (k+1) + " " + (k+2));
         }
      }
      //go right
      for(int k = maxi+1; k < n; k++){
         if(array[k] == array[maxi]) continue;
         if(array[k] < array[maxi]){
            sj.add("1 " + (k+1) + " " + (k));
         } else {
            sj.add("2 " + (k+1) + " " + (k));
         }
      }
      
      out.println(sj.toString());
      
      
      
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}