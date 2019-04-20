//make sure to make new file!
import java.io.*;
import java.util.*;
//tried some binary search bs before this
public class D552b{

   public static int[] array;
   public static int ba;
   public static int ac;
   
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      ba = Integer.parseInt(st.nextToken());
      ac = Integer.parseInt(st.nextToken());
      
      st = new StringTokenizer(f.readLine());
      
      array = new int[n];
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      int b = ba;
      int a = ac;
      
      int count = 0;
      while((a > 0 || b > 0) && count < n){
         if(a == 0){
            b--;
            if(array[count] == 1){
               a = Math.min(ac,a+1);
            }
         } else if(b == 0){
            a--;
         }
         else if(array[count] == 0 || a == ac){
            a--;
         } else {
            b--;
            a = Math.min(ac,a+1);
         }
         count++;
      }
      
      out.println(count);
      
      
      
      
      
      
      out.close();
   }
}