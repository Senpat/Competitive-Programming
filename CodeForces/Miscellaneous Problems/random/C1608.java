//Game Master
import java.io.*;
import java.util.*;

public class C1608{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st1 = new StringTokenizer(f.readLine());
         StringTokenizer st2 = new StringTokenizer(f.readLine());
      
         int[] a = new int[n];
         int[] b = new int[n];
         Integer[] sort = new Integer[n];
         
         for(int k = 0; k < n; k++){
            a[k] = Integer.parseInt(st1.nextToken());
            b[k] = Integer.parseInt(st2.nextToken());
            sort[k] = k;
         }
         
         Arrays.sort(sort, new Comparator<Integer>(){
            public int compare(Integer i1, Integer i2){
               return a[(int)i1] - a[(int)i2];
            }
         });
         
         int[] answer = new int[n];
         
         int min = b[sort[n-1]];
         int curmin = min;
         
         int last = n-1;
         
         for(int k = n-2; k >= 0; k--){
            curmin = Math.min(curmin,b[sort[k]]);
            if(b[sort[k]] > min){
               min = curmin;
               last = k;
            }
         }
         
         for(int k = last; k < n; k++){
            answer[sort[k]] = 1;
         }
         
         StringJoiner sj = new StringJoiner("");
         for(int k = 0; k < n; k++){
            sj.add("" + answer[k]);
         }
         out.println(sj.toString());
      }
      
      
      
      
      out.close();
   }
   
      
}