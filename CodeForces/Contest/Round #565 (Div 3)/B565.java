//make sure to make new file!
import java.io.*;
import java.util.*;

public class B565{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 0; q < t; q++){
         int n = Integer.parseInt(f.readLine());
         StringTokenizer st = new StringTokenizer(f.readLine());
         int[] array = new int[n];
         
         int answer = 0;
         int num1 = 0;
         int num2 = 0;
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
            if(array[k]%3 == 0){
               answer++;
            } else if(array[k]%3 == 1){
               num1++;
            } else {
               num2++;
            }
         }
         int min = Math.min(num1,num2);
         answer += min;
         num1-=min;
         num2-=min;
         answer += num1/3;
         answer += num2/3;
         
         out.println(answer);
      
      }
      
      
      
      
      out.close();
   }
   
      
}