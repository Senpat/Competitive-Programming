//make sure to make new file!
import java.io.*;
import java.util.*;

public class A809{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         st = new StringTokenizer(f.readLine());
         int[] array = new int[n];
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken())-1;
         }
         
         char[] ch = new char[m];
         Arrays.fill(ch,'B');
         
         for(int k = 0; k < n; k++){
            int a = Math.min(array[k],m-array[k]-1);
            if(ch[a] == 'A') ch[m-a-1] = 'A';
            else ch[a] = 'A';
         }
         
         String s = new String(ch);
         out.println(s); 
      }
      
      
      
      
      out.close();
   }
   
      
}