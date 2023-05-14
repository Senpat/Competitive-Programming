//make sure to make new file!
import java.io.*;
import java.util.*;

public class D860{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         long max = Long.MIN_VALUE;
         long min = Long.MAX_VALUE;
         int num0 = 0;
         long[] array = new long[n];
         ArrayList<Long> pos = new ArrayList<Long>();
         ArrayList<Long> neg = new ArrayList<Long>();
         for(int k = 0; k < n; k++){
            array[k] = Long.parseLong(st.nextToken());
            
            max = Math.max(max,array[k]);
            min = Math.min(min,array[k]);
            
            if(array[k] > 0) pos.add(array[k]);
            else if(array[k] < 0) neg.add(-1L * array[k]);
            else num0++;
         }
         
         if(num0 == n){
            out.println("No");
            continue;
         }
         
         long range = max-min;
         
         Collections.sort(pos);
         Collections.sort(neg);
         
         int pi = pos.size()-1;
         int ni = neg.size()-1;
         
         ArrayList<Long> answer = new ArrayList<Long>();
         
         boolean startpos;
         long sum = 0;
         if(pos.get(pi) > neg.get(ni)){
            answer.add(pos.get(pi));
            sum += pos.get(pi);
            pi--;
            startpos = true;
         } else {
            answer.add(-1L*neg.get(ni));
            sum += neg.get(ni);
            ni--;
            startpos = false;
         }
         
         while(pi >= 0 || ni >= 0){
            if(pi < 0){
               answer.add(-1L*neg.get(ni));
               ni--;
            } else if(ni < 0){
               answer.add(pos.get(pi));
               pi--;
            } else {
               if(startpos){
                  if(sum > 0){
                     answer.add(-1L*neg.get(ni));
                     sum -= neg.get(ni);
                     ni--;
                  } else {
                     answer.add(pos.get(pi));
                     sum += pos.get(pi);
                     pi--;
                  }
               } else {
                  if(sum > 0){
                     answer.add(pos.get(pi));
                     sum -= pos.get(pi);
                     pi--;
                  } else {
                     answer.add(-1L*neg.get(ni));
                     sum += neg.get(ni);
                     ni--;
                  }
               }
            }
         }
         
         for(int k = 0; k < num0; k++){
            answer.add(0L);
         }
         
         out.println("Yes");
         StringJoiner sj = new StringJoiner(" ");
         for(int k = 0; k < n; k++){
            sj.add("" + answer.get(k));
         }
         out.println(sj.toString());
         

      }
      
      
      
      
      out.close();
   }
   
      
}