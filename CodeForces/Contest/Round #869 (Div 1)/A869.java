//make sure to make new file!
import java.io.*;
import java.util.*;

public class A869{
   
   public static int[] bits;
   public static int n;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
         
      bits = new int[n+1];
      
      st = new StringTokenizer(f.readLine());
      int[] array = new int[n+1];
         
      for(int k = 1; k <= n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
         
      TreeSet<Integer> starts = new TreeSet<Integer>();
         
      int[] len = new int[n+1];           //stores length of streak starting at that node
      int[] ends = new int[n+1];   
      
      int start = 1;
      for(int k = 2; k <= n; k++){
         if(array[k] > array[k-1]){
               //end streak
            int end = k-1;
            int d = end-start+1;
            if(d >= 3){
               starts.add(start);
               len[start] = d-2;
               ends[start] = end;
               update(start,d-2);
            }
            start = k;
         }
      }
         
         //end streak
      int end = n;
      int d = end-start+1;
      if(d >= 3){
         starts.add(start);
         len[start] = d-2;
         ends[start] = end;
         update(start,d-2);
      }
         
      int[] answer = new int[m];
      for(int qq = 0; qq < m; qq++){
         st = new StringTokenizer(f.readLine());
            
         int l = Integer.parseInt(st.nextToken());
         int r = Integer.parseInt(st.nextToken());
            
         int ans = r-l+1;
            
         int psum = psum(r)-psum(l-1);
            
            //streak right before r might be overcounted
         Integer floor = starts.floor(r);
         if(floor == null){
            answer[qq] = ans;
            continue;
         } else if(floor <= l && ends[floor] >= r){
               //streak covers entire range
            answer[qq] = Math.min(ans,2);
            continue;
         } else if(ends[floor] > r){
               //subtract
            psum -= len[floor];
               
               //add back right length
            psum += Math.max(0,r-floor+1-2);
         }
            
            //might not have added streak before l
         floor = starts.floor(l-1);
            
         if(floor != null){
            psum += Math.max(0,len[floor]-(l-floor));
         }
            
         answer[qq] = ans-psum;
      }
         
      StringJoiner sj = new StringJoiner("\n");
      for(int k = 0; k < m; k++){
         sj.add("" + answer[k]);
      }
      out.println(sj.toString());
      
      
      
      
      
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