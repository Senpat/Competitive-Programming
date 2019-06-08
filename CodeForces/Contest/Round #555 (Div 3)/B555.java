//make sure to make new file!
import java.io.*;
import java.util.*;

public class B555
{
   
   public static void main(String[] args)throws IOException{
      BufferedReader fr = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(fr.readLine());
      
      char[] carray = fr.readLine().toCharArray();
      
      int[] num = new int[n];
      for(int k = 0; k < n; k++){
         num[k] = Character.getNumericValue(carray[k]);
      }
      
      StringTokenizer st = new StringTokenizer(fr.readLine());
      
      int[] f = new int[10];
      for(int k = 1; k <= 9; k++){
         f[k] = Integer.parseInt(st.nextToken());
      }
      
      for(int k = 0; k < n; k++){
         if(num[k] < f[num[k]]){
            int index = k;
            while(index < n && num[index] <= f[num[index]]){
               num[index] = f[num[index]];
               index++;
            }
            break;
         }
      }
      
      for(int k = 0; k < n; k++){
         out.print(num[k]);
      }

      
      
      
      
      out.close();
   }
   
      
}