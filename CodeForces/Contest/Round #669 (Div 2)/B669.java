//make sure to make new file!
import java.io.*;
import java.util.*;

public class B669{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int[] array = new int[n];
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
         }
         
         //get max
         HashSet<Integer> seen = new HashSet<Integer>();
         ArrayList<Integer> answer = new ArrayList<Integer>();
         
         int maxindex = 0;
         for(int k = 0; k < n; k++){
            if(array[k] > array[maxindex]){
               maxindex = k;
            }
         }
         
         seen.add(maxindex);
         answer.add(array[maxindex]);
         
         int curgcd = array[maxindex];
         
         
         for(int k = 1; k < n; k++){
            int maxgcd = -1;
            maxindex = -1;
            for(int j = 0; j < n; j++){
               if(seen.contains(j)) continue;
               if(gcd(array[j],curgcd) > maxgcd){
                  maxgcd = gcd(array[j],curgcd);
                  maxindex = j;
               }
            }
            seen.add(maxindex);
            answer.add(array[maxindex]);
            curgcd = maxgcd;
         }   
         
         StringJoiner sj = new StringJoiner(" ");
         for(int i : answer){
            sj.add("" + i);
         }
         out.println(sj.toString());

      }
      
      
      
      
      out.close();
   }
   
   public static int gcd(int a, int b){
      if(a == 0) return b;
      if(b == 0) return a;
      if(a == b) return a;
      if(a < b){
         return gcd(a,b%a);
      } else {
         return gcd(b,a%b);
      }
   }
   
      
}