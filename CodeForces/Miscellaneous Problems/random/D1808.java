//make sure to make new file!
import java.io.*;
import java.util.*;

public class D1808{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      st = new StringTokenizer(f.readLine());
      int[] array = new int[n];
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      int N = 200005;
      ArrayList<ArrayList<Integer>> odd = new ArrayList<>(N);
      ArrayList<ArrayList<Integer>> even = new ArrayList<>(N);
      
      for(int k = 0; k < N; k++){
         odd.add(new ArrayList<Integer>());
         even.add(new ArrayList<Integer>());
      }
      
      for(int k = 0; k < n; k++){
         if(k%2 == 0){
            even.get(array[k]).add(k);
         } else {
            odd.get(array[k]).add(k);
         }
      }
      
      long answer = 0L;
      for(int k = 0; k < N; k++){
         for(int i : odd.get(k)){
            int min = calcmin(n,m,i);
            int max = calcmax(n,m,i);
            
            if(min == -1) continue;
            //out.println(i + ": " + min + " " + max);
            
            int up = numle(odd.get(k),max);
            int down = numle(odd.get(k),min-1);
            
            int tot = (max-min)/2+1;
            int has = up-down;
            
            answer += (long)(tot-has);
         }
         
         for(int i : even.get(k)){
            int min = calcmin(n,m,i);
            int max = calcmax(n,m,i);
            
            if(min == -1) continue;
            //out.println(i + ": " + min + " " + max);
            int up = numle(even.get(k),max);
            int down = numle(even.get(k),min-1);
            
            int tot = (max-min)/2+1;
            int has = up-down;
            
            answer += (long)(tot-has);
         }
      }
      
      out.println(answer);
      
      
      
      
      out.close();
   }
   
   public static int calcmin(int n, int m, int j){
      if(j >= n-m/2-1) return -1;
      if(j < m/2) return m-j-1;
      return j+2;
   }
   
   public static int calcmax(int n, int m, int j){
      if(j >= n-m/2-1) return -1;
      if(j+m-1 >= n) return n-1+n-m - j;
      return j+m-1;
   }
   
   //number of elements <= x
   public static int numle(ArrayList<Integer> alist, int x){
      int l = 0;
      int r = alist.size()-1;
      int ans = 0;
      
      while(l <= r){
         int mid = l + (r-l)/2;
         
         if(alist.get(mid) <= x){
            ans = mid+1;
            l = mid+1;
         } else {
            r = mid-1;
         }
      }
      
      return ans;
   }
      
}