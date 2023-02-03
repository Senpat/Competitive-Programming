//make sure to make new file!
import java.io.*;
import java.util.*;

public class C842{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         int[] freq = new int[n+1];
         int[] array = new int[n];
         boolean fail = false;
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
            freq[array[k]]++;
            if(freq[array[k]] >= 3){
               fail = true;
            }
         }
         
         if(freq[1] >= 2){
            fail = true;
         }
         
         if(fail){
            out.println("NO");
            continue;
         }
         
         int[] a = new int[n];
         int[] b = new int[n];
         int[][] indices = new int[n+1][2];
         for(int k = 0; k <= n; k++) Arrays.fill(indices[k],-1);
         
         PriorityQueue<Integer> none = new PriorityQueue<Integer>();
         for(int k = 1; k <= n; k++){
            if(freq[k] == 0) none.add(k);
         }
         
         PriorityQueue<Integer> two = new PriorityQueue<Integer>();
         for(int k = 0; k < n; k++){
            if(freq[array[k]] == 1){
               a[k] = array[k];
               b[k] = array[k];
            } else if(freq[array[k]] == 2){
               if(indices[array[k]][0] == -1) {
                  two.add(array[k]);
                  indices[array[k]][0] = k;
               } else {
                  indices[array[k]][1] = k;
               }
               
            }
            
         }
         
         if(none.size() != two.size()){
            out.println("NO");
            continue;
         }
         
         int num = none.size();
         
         for(int k = 0; k < num; k++){
            int nonei = none.poll();
            int twoi = two.poll();
            
            if(twoi < nonei){
               fail = true;
               break;
            }
            
            a[indices[twoi][0]] = nonei;
            b[indices[twoi][0]] = twoi;
            
            a[indices[twoi][1]] = twoi;
            b[indices[twoi][1]] = nonei;
         }
         
         if(fail){
            out.println("NO");
            continue;
         }
         
         out.println("YES");
         StringJoiner sja = new StringJoiner(" ");
         StringJoiner sjb = new StringJoiner(" ");
         for(int k = 0; k < n; k++){
            sja.add("" + a[k]);
            sjb.add("" + b[k]);
         }
         out.println(sja.toString());
         out.println(sjb.toString());
      
      }
      
      
      
      
      out.close();
   }
   
      
}