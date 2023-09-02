//make sure to make new file!
import java.io.*;
import java.util.*;

public class A317{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int h = Integer.parseInt(st.nextToken());
      int x = Integer.parseInt(st.nextToken());
      
      st = new StringTokenizer(f.readLine());
      
      int answer = -1;
      for(int k = 0; k < n; k++){
         int i = Integer.parseInt(st.nextToken());
         if(answer == -1 && h+i >= x){
            answer = k+1;
         }
      }
      
      out.println(answer);
      
      
      
      
      
      
      
      out.close();
   }
   
      
}