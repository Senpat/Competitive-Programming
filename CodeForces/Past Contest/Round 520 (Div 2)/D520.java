//make sure to make new file!
import java.io.*;
import java.util.*;
//race against danny solving a rubik's cube
public class D520{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());

      int N = 100005;
      
      long[] answer = new long[N];
      
      answer[2] = 0L;
      
      for(int k = 3; k < N; k++){
         answer[k] += answer[k-1];
         
         //get factors
         for(int j = 2; j*j <= k; j++){
            if(k%j == 0){
               answer[k] += k/j;
               if(j*j != k)
                  answer[k] += j;
            }
         }
      }
      
      out.println(answer[n]*4);
         
      
      

      
      
      
      
      
      out.close();
   }
   
      
}