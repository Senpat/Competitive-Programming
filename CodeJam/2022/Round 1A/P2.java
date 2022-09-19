//make sure to make new file!
import java.io.*;
import java.util.*;

public class P2{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         int n = Integer.parseInt(f.readLine());
         
         //generate a
         ArrayList<Long> a = new ArrayList<Long>();
         long asum = 0L;
         long i = 1;
         while(i < 1000000000){
            a.add(i);
            asum += i;
            i <<= 1;
         }
         
         ArrayList<Long> b = new ArrayList<Long>();
         i = 10000;
         while(a.size() + b.size() < n){
            b.add(i);
            i++;
         }
         
         StringJoiner sj = new StringJoiner(" ");
         for(long x : a) sj.add("" + x);
         for(long x : b) sj.add("" + x);
         
         out.println(sj.toString());
         out.flush();
         
         StringTokenizer st = new StringTokenizer(f.readLine());
         for(int k = 0; k < n; k++){
            i = Long.parseLong(st.nextToken());
            b.add(i);
         }
         
         Collections.sort(b);
         
         ArrayList<Long> set1 = new ArrayList<Long>();
         ArrayList<Long> set2 = new ArrayList<Long>();
         
         long sum1 = 0L;
         long sum2 = 0L;
         
         for(int k = 0; k < b.size(); k++){
            if(sum1 < sum2){
               set1.add(b.get(k));
               sum1 += b.get(k);
            } else {
               set2.add(b.get(k));
               sum2 += b.get(k);
            }
         }
         
         if(sum1 < sum2){
            //add all of a into sum1
            long dif = (sum1 + asum - sum2)/2L;             //guaranteed to be a positive integer
            
            //the bits of dif go to set2, rest go to set1
            for(int k = 0; k < a.size(); k++){
               if((dif & a.get(k)) == 0){
                  set1.add(a.get(k));
               } else {
                  set2.add(a.get(k));
               }
            }
         
         } else {
            //add all of a into sum2
            long dif = (sum2 + asum - sum1)/2L;
            
            //the bits of dif go into set1, rest go to set2
            for(int k = 0; k < a.size(); k++){
               if((dif & a.get(k)) == 0){
                  set2.add(a.get(k));
               } else {
                  set1.add(a.get(k));
               }
            }
         }
            
         sj = new StringJoiner(" ");
         
         for(long x : set1){
            sj.add("" + x);
         }
         
         out.println(sj.toString());
         out.flush();
      
      
      }
      
      
      
      
      out.close();
   }
   
      
}