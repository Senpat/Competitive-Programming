//make sure to make new file!
import java.io.*;
import java.util.*;

public class D131{
   
   
   public static int[] bits;
   public static int n;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         int[] array = new int[n+1];
         Integer[] order = new Integer[n];
         
         for(int k = 1; k <= n; k++){
            array[k] = Integer.parseInt(st.nextToken());
            order[k-1] = k;
         }
         
         Arrays.sort(order, new Comparator<Integer>(){
            public int compare(Integer o1, Integer o2){
               int x = (int)o1;
               int y = (int)o2;
               if(array[x] == array[y]) return y-x;
               return array[y]-array[x];
            }
         });
         //Arrays.sort(order,(x,y) -> array[x] == array[y] ? y-x : array[y]-array[x]);
         /*for(int k = 0; k < n; k++){
            out.print(order[k] + " ");
         }
         out.println();
         */
         
         bits = new int[n+1];
         
         int[] answer = new int[n+1];
         for(int k = 0; k < n; k++){
            int i = order[k];
            //put the biggest number possible in answer[i];
            //figure out biggest number
            int big = n;
            if(array[i] != 0) big = i/array[i];
            
            int l = 1;
            int r = big;
            int ans = -1;
            while(l <= r){
               int mid = l + (r-l)/2;
               
               int sum = psum(r) - psum(mid-1);
               int d = big-mid+1;
               if(sum == d){
                  r = mid-1;
               } else {
                  l = mid+1;
                  ans = mid;
               }
            }
            
            answer[i] = ans;
            update(ans,1);
         }
         
         StringJoiner sj = new StringJoiner(" ");
         for(int k = 1; k <= n; k++){
            sj.add("" + answer[k]);
         }
         out.println(sj.toString());
            
         

      }
      
      
      
      
      out.close();
   }
   
   public static void update(int i, int x){
      for(; i <= n; i += i&-i){
         //System.out.println(i);
         bits[i]+=x;
      }
   
   }
   
   public static int psum(int i){
      int sum = 0;
      for(; i > 0; i -= i&-i){
         //System.out.println(i);
         sum += bits[i];
      }
      return sum;
   
   }
      
}