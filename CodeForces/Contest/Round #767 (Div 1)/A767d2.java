//make sure to make new file!
import java.io.*;
import java.util.*;
//div2 A
public class A767d2{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         int[] a = new int[n];
         int[] b = new int[n];
         
         Integer[] is = new Integer[n];
         
         StringTokenizer st1 = new StringTokenizer(f.readLine());
         StringTokenizer st2 = new StringTokenizer(f.readLine());
         for(int k = 0; k < n; k++){
            a[k] = Integer.parseInt(st1.nextToken());
            b[k] = Integer.parseInt(st2.nextToken());
            is[k] = k;
         }
         
         Arrays.sort(is, (x,y) -> a[x]-a[y]);
         
         int answer = m;
         
         for(int k = 0; k < n; k++){
            if(a[is[k]] > answer) break;
            answer += b[is[k]];
         }
         
         out.println(answer);
         
         
      

      }
      
      
      
      
      out.close();
   }
   
      
}