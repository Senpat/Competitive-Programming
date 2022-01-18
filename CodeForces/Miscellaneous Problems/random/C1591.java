//Minimize Distance
import java.io.*;
import java.util.*;

public class C1591{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
      
         st = new StringTokenizer(f.readLine());
         long[] array = new long[n];
         ArrayList<Long> neg = new ArrayList<Long>();
         ArrayList<Long> pos = new ArrayList<Long>();
         
         for(int k = 0; k < n; k++){
            array[k] = Long.parseLong(st.nextToken());
            if(array[k] < 0) neg.add(array[k]);
            if(array[k] > 0) pos.add(array[k]);
         }
         
         Collections.sort(neg);
         Collections.sort(pos);
         
         long negfirst = neg.size() >= 1 ? neg.get(0)*-1 : 0;
         long negtotal = 0L;
         for(int k = 0; k < neg.size(); k += m){
            negtotal += -2L*neg.get(k);
         }
         
         long posfirst = pos.size() >= 1 ? pos.get(pos.size()-1) : 0;
         long postotal = 0L;
         for(int k = pos.size()-1; k >= 0; k -= m){
            postotal += 2L*pos.get(k);
         }
         
         
         
         long answer = negtotal + postotal - Math.max(negfirst,posfirst);
         out.println(answer);
         
      }
      
      
      
      
      out.close();
   }
   
      
}