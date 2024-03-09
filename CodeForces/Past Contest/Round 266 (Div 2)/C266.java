//make sure to make new file!
import java.io.*;
import java.util.*;
//wrong:
/*
11
1 0 1 -1 0 1 0 1 -1 0 1
*/
public class C266{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      long[] array = new long[n];
      long sum = 0L;
      for(int k = 0; k < n; k++){
         array[k] = Long.parseLong(st.nextToken());
         sum += array[k];
      }
      
      if(sum == 0L){
         long cursum = 0L;
         long num0s = 0L;
         for(int k = 0; k < n-1; k++){
            cursum += array[k];
            if(cursum == 0L){
               num0s++;
            }
         }
         
         if(num0s == 0){
            out.println(0);
         } else {
            out.println(num0s * (num0s-1L) / 2L);
         }
         out.close();
         return;
      }
      
      if(sum % 3L != 0L){
         out.println(0);
         out.close();
         return;
      }
      long thresh = sum/3L;
      
      long answer = 1L;
      
      long cursum = 0L;
      long mul = 1L;
      
      int i = 0;
      int numequal = 0;
      while(i < n){
         //process the element
         cursum += array[i];
         if(cursum == thresh){
            answer *= mul;
            numequal++;
            if(numequal == 3){
               break;
            }
            mul = 1L;
            cursum = 0L;
         } else if(cursum == 0L){
            mul++;
         }
         
         i++;
         
      }
         
      if(numequal == 3){
         out.println(answer);
      } else {
         out.println(0);
      }
      
      
      
      
      
      out.close();
   }
   
      
}