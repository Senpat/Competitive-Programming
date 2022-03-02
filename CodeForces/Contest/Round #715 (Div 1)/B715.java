//make sure to make new file!
import java.io.*;
import java.util.*;

public class B715{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      int LOG = 61;
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         long m = Long.parseLong(st.nextToken());
      
         m--;
         
         if(n <= LOG){
            if(m >= (1L << (n-1))){
               out.println(-1);
               continue;
            }
         }
         
         //bits of m
         int[] bits = new int[n-1];
         Arrays.fill(bits,0);
         for(int k = 0; k < n-1; k++){
            int i = n-1-k-1;
            //out.println((m & (1L << i)));
            if((i >= LOG) || ((m & (1L << i)) == 0)) bits[k] = 0;
            else bits[k] = 1;
         }
         
         ArrayList<Integer> answer = new ArrayList<Integer>();
         
         int last0 = -1;
         int lastadded = 0;
         for(int k = 0; k < n-1; k++){
            if(bits[k] == 0){
               for(int j = lastadded+k-last0; j > lastadded; j--){
                  answer.add(j);
               }
               lastadded += k-last0;
               last0 = k;
            }
         }
         
         //pretend there's another 0
         for(int j = lastadded+n-1-last0; j > lastadded; j--){
            answer.add(j);
         }
         
         StringJoiner sj = new StringJoiner(" ");
         for(int i : answer){
            sj.add("" + i);
         }
         out.println(sj.toString());
      }
      
      
      
      
      out.close();
   }
   
      
}