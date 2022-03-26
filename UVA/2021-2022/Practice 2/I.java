//make sure to make new file!
import java.io.*;
import java.util.*;

public class I{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = 1 << Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      Integer[] array = new Integer[n];
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(f.readLine());
      }
      
      Arrays.sort(array, (a,b) -> (b-a));
      
      int answer = 0;
      
      for(int size = 1; size < n; size*=2){
         int[] a = new int[size];
         int[] b = new int[size];
         
         for(int k = 0; k < size; k++){
            a[k] = array[k];
            b[k] = array[k+size];
         }
         
         int l = 0;
         int r = 0;
         
         while(l < size && r < size){
            if(a[l]-b[r] <= m){
               answer++;
               l++;
               r++;
            } else {
               l++;
            }
         }
      }
      
      out.println(answer);
         
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}