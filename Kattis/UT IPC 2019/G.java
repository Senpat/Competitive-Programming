//make sure to make new file!
import java.io.*;
import java.util.*;

public class G{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      String s = st.nextToken();
      int n = Integer.parseInt(st.nextToken());
      
      int[] r = {1,5,10,15,20};
      int[] c = {1,7,14,20};
      int[] d = {1,4,8,12,16,20};
      
      int i = 0;
      if(s.equals("residential")){
         while(n > r[i]){
            i++;
         }
      } else if(s.equals("commercial")){
         while(n > c[i]){
            i++;
         }
      } else {
         while(n > d[i]){
            i++;
         }
      }
      out.println(i);
         
      

      
      
      
      
      out.close();
   }
   
      
}