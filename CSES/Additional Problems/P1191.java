//make sure to make new file!
import java.io.*;
import java.util.*;
//unproven solution
public class P1191{
   
   public static int n;
   public static long m;
   public static long[] array;
   public static int laststart;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      n = Integer.parseInt(st.nextToken());
      m = Long.parseLong(st.nextToken());
      
      array = new long[n];
      st = new StringTokenizer(f.readLine());
      for(int k = 0; k < n; k++){
         array[k] = Long.parseLong(st.nextToken());
      }
      
      getnum(0);
      int answer = 0;
      for(int rep = 0; rep < 10; rep++){
         answer = getnum(laststart);
      }
      
      out.println(answer);
      
      
      
      
      out.close();
   }
   
   public static int getnum(int start){
      laststart = start;
      long cursum = 0L;
      int num_sub = 1;
      for(int k = 0; k < n; k++){
         int i = (k+start)%n;
         cursum += array[i];
         if(cursum > m){
            num_sub++;
            laststart = i;
            cursum = array[i];
         }
      }
      
      return num_sub;
   }
      
}