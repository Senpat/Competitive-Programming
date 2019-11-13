//make sure to make new file!
import java.io.*;
import java.util.*;

public class A574{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      int[] array = new int[n];
      int[] freq = new int[m+1];
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(f.readLine());
         freq[array[k]]++;
      }
      
      int turns = n/2;
      if(n%2==1)turns++;
      
      int ones = 0;
      for(int k = 1; k <= m; k++){
         ones += freq[k]%2;
         turns-= freq[k]/2;
      }
      
      int answer = n-(ones-turns);
      out.println(answer);
      
      
      
      
      out.close();
   }
   
      
}