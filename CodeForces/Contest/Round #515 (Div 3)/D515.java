//Boxes Packing
//semi-t
import java.io.*;
import java.util.*;

public class D515{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      
      st = new StringTokenizer(f.readLine());
      
      int[] array = new int[n];
      
      for(int k = 0; k < n; k++) array[k] = Integer.parseInt(st.nextToken());
      
      int begin = 0;
      int middle;
      int end = n-1;
      
      int max=0;
      boolean cur;
      while(begin <= end){
         middle = (end + begin)/2;
         //out.println(middle);
         cur = check(m,b,middle,array);
         if(cur){
            end = middle-1;
            //middle = (end + begin)/2;
            max = Math.max(max,n-middle);
         } else {
            begin = middle+1;
            //middle = (end+begin)/2;
         }
      }
      
      out.println(max);
      
      out.close();
   }
   
   public static boolean check(int m, int b, int i, int[] array){
      
      while(m>0 && i < array.length){
         int c = b;
         while(i < array.length && array[i] <= c){
            c -= array[i++];
            
         }
         m--;
      }
      
      
      return i == array.length;
   }
   
}