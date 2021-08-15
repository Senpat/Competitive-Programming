//make sure to make new file!
import java.io.*;
import java.util.*;

public class E727{

   public static int n;
   public static int K = 25;
   
   public static int[][] llst;
   public static int[][] lrst;
   public static int[][] rlst;
   public static int[][] rrst;
   
   public static int[] log;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      int[] queries = new int[n];
      int[] llhand = new int[n];
      int[] lrhand = new int[n];
      int[] rlhand = new int[n];
      int[] rrhand = new int[n];
      
      for(int k = 0; k < n; k++){
         queries[k] = Integer.parseInt(f.readLine());
         
         st = new StringTokenizer(f.readLine());
      
         llhand[k] = Integer.parseInt(st.nextToken());
         lrhand[k] = Integer.parseInt(st.nextToken());
         
         st = new StringTokenizer(f.readLine());
      
         rlhand[k] = Integer.parseInt(st.nextToken());
         rrhand[k] = Integer.parseInt(st.nextToken());
      }
      
      //sparse table precomp
      
      log = new int[n+1];
      log[1] = 0;
      for(int i = 2; i <= n; i++){
         log[i] = log[i/2]+1;
      }
      
      llst = new int[n][K+1];                      //max
      lrst = new int[n][K+1];                      //min
      rlst = new int[n][K+1];                      //max
      rrst = new int[n][K+1];                      //min
      
      for(int k = 0; k < n; k++){
         llst[k][0] = llhand[k];
         lrst[k][0] = lrhand[k];
         rlst[k][0] = rlhand[k];
         rrst[k][0] = rrhand[k];
      }
      
      for(int j = 1; j <= K; j++){
         for(int i = 0; i + (1 << j) <= n; i++){
            llst[i][j] = Math.max(llst[i][j-1], llst[i + (1 << (j-1))][j-1]);
            lrst[i][j] = Math.min(lrst[i][j-1], lrst[i + (1 << (j-1))][j-1]);
            rlst[i][j] = Math.max(rlst[i][j-1], rlst[i + (1 << (j-1))][j-1]);
            rrst[i][j] = Math.min(rrst[i][j-1], rrst[i + (1 << (j-1))][j-1]);
         }
      }
      
      
      
      int[] ll = new int[n];
      int[] lr = new int[n];
      int[] rl = new int[n];
      int[] rr = new int[n];
      Arrays.fill(ll,-1);
      Arrays.fill(lr,-1);
      Arrays.fill(rl,-1);
      Arrays.fill(rr,-1);
      
      int[] pl = new int[n];           //parent left
      int[] pr = new int[n];           //parent right
      Arrays.fill(pl,-1);
      Arrays.fill(pr,-1);
      
      
      //initial
      //find how far zero goes on left
      int l = 0;
      int r = n-1;
      int zerol = -1;
      
      while(l <= r){
         int mid = l + (r-l)/2;
         if(queryll(0,mid) <= 0 && querylr(0,mid) >= 0){
            zerol = mid;
            l = mid+1;
         } else {
            r = mid-1;
         }
      }
      
      //find how far zero goes on right
      l = 0;
      r = n-1;
      int zeror = -1;
      while(l <= r){
         int mid = l + (r-l)/2;
         if(queryrl(0,mid) <= 0 && queryrr(0,mid) >= 0){
            zeror = mid;
            l = mid+1;
         } else {
            r = mid-1;
         }
      }
      
      boolean fail = false;
      for(int k = 0; k < n; k++){
         //bs for left (how far does it go if you put in left)
         l = k;
         r = n-1;
         int curl = -1;
         while(l <= r){
            int mid = l + (r-l)/2;
            if(queryll(k,mid) <= queries[k] && querylr(k,mid) >= queries[k]){
               curl = mid;
               l = mid+1;
            } else {
               r = mid-1;
            }
         }
         
         //bs for right (how far does it go if you put in right)
         l = k;
         r = n-1;
         int curr = -1;
         while(l <= r){
            int mid = l + (r-l)/2;
            if(queryrl(k,mid) <= queries[k] && queryrr(k,mid) >= queries[k]){
               curr = mid;
               l = mid+1;
            } else {
               r = mid-1;
            }
         }
         
         if(k == 0){
            ll[k] = curl;
            lr[k] = zeror;
            rl[k] = zerol;
            rr[k] = curr;
         } else {
            ll[k] = curl;
            if(lr[k-1] >= rr[k-1]){
               //take left
               lr[k] = lr[k-1];
               pl[k] = 0;
            } else {
               lr[k] = rr[k-1];
               pl[k] = 1;
            }
            
            rr[k] = curr;
            if(ll[k-1] >= rl[k-1]){
               rl[k] = ll[k-1];
               pr[k] = 0;
            } else {
               rl[k] = rl[k-1];
               pr[k] = 1;
            }
         }
         
         if(ll[k] < k || lr[k] < k){
            ll[k] = -1;
            lr[k] = -1;
         }
         if(rl[k] < k || rr[k] < k){
            rl[k] = -1;
            rr[k] = -1;
         }
         
         if(ll[k] == -1 && rl[k] == -1){
            fail = true;
            break;
         }
      }
      
      if(fail){
         out.println("No");
         out.close();
         return;
      }
      
      int[] answer = new int[n];
      if(ll[n-1] != -1){
         answer[n-1] = 0;
      } else {
         answer[n-1] = 1;
      }
      
      for(int k = n-2; k >= 0; k--){
         if(answer[k+1] == 0){
            answer[k] = pl[k+1];
         } else {
            answer[k] = pr[k+1];
         }
      }
      
      out.println("Yes");
      StringJoiner sj = new StringJoiner(" ");
      for(int k = 0; k < n; k++){
         sj.add("" + answer[k]);
      }
      out.println(sj.toString());
      
      out.close();
   }
   
   
   public static int queryll(int l, int r){
      int j = log[r-l+1];
      return Math.max(llst[l][j], llst[r - (1 << j) +1][j]);
   }
   
   public static int querylr(int l, int r){
      int j = log[r-l+1];
      return Math.min(lrst[l][j], lrst[r - (1 << j) +1][j]);
   }
   
   public static int queryrl(int l, int r){
      int j = log[r-l+1];
      return Math.max(rlst[l][j], rlst[r - (1 << j) +1][j]);
   }
   
   public static int queryrr(int l, int r){
      int j = log[r-l+1];
      return Math.min(rrst[l][j], rrst[r - (1 << j) +1][j]);
   }   
}