//make sure to make new file!
import java.io.*;
import java.util.*;

public class D832{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int q = Integer.parseInt(st.nextToken());
      
      int[] pre0 = new int[n+1];
      int[] is0 = new int[n+1];
      int[] prexor = new int[n+1];
      int[] array = new int[n+1];
      st = new StringTokenizer(f.readLine());
      for(int k = 1; k <= n; k++){
         array[k] = Integer.parseInt(st.nextToken());
         prexor[k] = prexor[k-1]^array[k];
         
         if(array[k] == 0) is0[k] = 1;
         pre0[k] = pre0[k-1] + is0[k];
      }
      
      HashMap<Integer,ArrayList<Integer>> odd = new HashMap<Integer,ArrayList<Integer>>();
      HashMap<Integer,ArrayList<Integer>> even = new HashMap<Integer,ArrayList<Integer>>();
      
      for(int k = 0; k <= n; k++){
         if(k % 2 == 0){
            if(even.containsKey(prexor[k])){
               even.get(prexor[k]).add(k);
            } else {
               ArrayList<Integer> temp = new ArrayList<Integer>();
               temp.add(k);
               even.put(prexor[k],temp);
            }
         } else {
            if(odd.containsKey(prexor[k])){
               odd.get(prexor[k]).add(k);
            } else {
               ArrayList<Integer> temp = new ArrayList<Integer>();
               temp.add(k);
               odd.put(prexor[k],temp);
            }
         }
      }
      
      
      
      
      
      
      StringJoiner sj = new StringJoiner("\n");
      for(int k = 0; k < q; k++){
         st = new StringTokenizer(f.readLine());
         int L = Integer.parseInt(st.nextToken());
         int R = Integer.parseInt(st.nextToken());
         
         if(pre0[R] - pre0[L-1] == R-L+1){
            sj.add("0");
         } else if(prexor[R] != prexor[L-1]){
            sj.add("-1");
         } else if((R-L+1)%2 == 1 || array[R] == 0 || array[L] == 0){
            sj.add("1");
         } else {
            ArrayList<Integer> cur;
            if((L-1)%2 == 1){
               if(!even.containsKey(prexor[L-1])){
                  sj.add("-1");
                  continue;
               } else {
                  cur = even.get(prexor[L-1]);
               }
            } else {
               if(!odd.containsKey(prexor[L-1])){
                  sj.add("-1");
                  continue;
               } else {
                  cur = odd.get(prexor[L-1]);
               }
            }
            
            int l = 0;
            int r = cur.size()-1;
            int ans = n+5;
            
            while(l <= r){
               int mid = l + (r-l)/2;
               
               if(cur.get(mid) > L-1){
                  ans = cur.get(mid);
                  r = mid-1;
               } else {
                  l = mid+1;
               }
            }
            
            if(ans < R){
               sj.add("2");
            } else {
               sj.add("-1");
            }
         }
      }
      
      
      out.println(sj.toString());
      
      
      
      
      out.close();
   }
   
      
}