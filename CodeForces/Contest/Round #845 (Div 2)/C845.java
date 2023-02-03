//make sure to make new file!
import java.io.*;
import java.util.*;

public class C845{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      int N = 100005;
      ArrayList<ArrayList<Integer>> alist = new ArrayList<ArrayList<Integer>>(N+1);
      for(int k = 0; k < N; k++) alist.add(new ArrayList<Integer>());
      for(int k = 1; k < N; k++){
         for(int j = k; j < N; j += k){
            alist.get(j).add(k);
         }
      }
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
      
         Integer[] array = new Integer[n];
         st = new StringTokenizer(f.readLine());
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
         }
         
         Arrays.sort(array);
         
         int l = 0;
         int r = 0;
         
         int covered = 0;
         int[] covers = new int[m+1];
         
         int answer = Integer.MAX_VALUE;
         
         while(l < n){
            //increment r until covered is m
            while(r < n && covered != m){
               for(int i : alist.get(array[r])){
                  if(i > m) break;
                  covers[i]++;
                  if(covers[i] == 1) covered++;
               }
               r++;
            }
            
            if(covered != m) break;
            
            answer = Math.min(answer,array[r-1]-array[l]);
            
            //remove l
            for(int i : alist.get(array[l])){
               if(i > m) break;
               covers[i]--;
               if(covers[i] == 0) covered--;
            }
            l++;
         }
         
         
         if(answer == Integer.MAX_VALUE){
            out.println(-1);
         } else {
            out.println(answer);
         }
         
         

      }
      
      
      
      
      out.close();
   }
   
      
}