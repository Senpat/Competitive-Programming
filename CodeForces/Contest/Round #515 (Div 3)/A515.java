//make sure to make new file!
import java.io.*;
import java.util.*;

public class A515{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      //int t = Integer.parseInt(f.readLine());
      
      //for(int k = 0; k < t; k++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int p = Integer.parseInt(st.nextToken());
         int l = Integer.parseInt(st.nextToken());
         int r = Integer.parseInt(st.nextToken());
         
         long answer = 0L;
         
         answer += (long)(l-1)/p;
         answer += (long)(n/p*p-r+1)/p;
         
         if(p==1) answer--;
         
         out.println(answer);
         
      //}
      
      out.close();
   }
   
}