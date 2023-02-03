//make sure to make new file!
import java.io.*;
import java.util.*;
//upsolve, suffix array practice
public class C{
   
   public static int[] log;
   public static int[][] spt;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int N = 100005;
      int K = 17;
      
      log = new int[N];
      log[1] = 0;
      
      for(int k = 2; k < N; k++){
         log[k] = log[k/2]+1;
      }
      
      
      String s = f.readLine();
      int n = s.length();
      s += '$';
      
      int[] suffixarray = make_suffix_array(s);
      
      int[] lcp = make_lcp(s,suffixarray);
      
      //index in suffix array
      int[] indexof = new int[n+1];
      
      for(int k = 0; k <= n; k++){
         indexof[suffixarray[k]] = k;
      }
      
      //sparse table on lcp
      spt = new int[N][K];
      for(int k = 0; k < n; k++){
         spt[k][0] = lcp[k];
      }
      for(int j = 1; j < K; j++){
         for(int k = 0; k + (1 << j) < N; k++){
            spt[k][j] = Math.min(spt[k][j-1],spt[k+(1 << (j-1))][j-1]);  
         }
      }
      
      //next lower element to the right and left for each element in suffix array
      int[] nextlowerright = new int[n+1];
      int[] nextlowerleft = new int[n+1];
      Arrays.fill(nextlowerright,-1);
      Arrays.fill(nextlowerleft,-1);
      
      //fill left
      Stack<Integer> stack = new Stack<Integer>();
      
      for(int k = 0; k <= n; k++){
         while(!stack.isEmpty() && suffixarray[stack.peek()] > suffixarray[k]){
            stack.pop();
         }
         
         if(!stack.isEmpty()){
            nextlowerleft[k] = stack.peek();
         }
         
         stack.push(k);
      }
      
      //fill right
      stack = new Stack<Integer>();
      
      for(int k = n; k >= 0; k--){
         while(!stack.isEmpty() && suffixarray[stack.peek()] > suffixarray[k]){
            stack.pop();
         }
         
         if(!stack.isEmpty()){
            nextlowerright[k] = stack.peek();
         }
         
         stack.push(k);
      }
      
      ArrayList<Range> ranges = new ArrayList<Range>();
      for(int k = 0; k <= n; k++){
         int max = -1;
         if(nextlowerleft[k] != -1){
            max = Math.max(max,sptmin(nextlowerleft[k],k-1));
         }
         if(nextlowerright[k] != -1){
            max = Math.max(max,sptmin(k,nextlowerright[k]-1));
         }
         
         if(max == -1) continue;
         
         if(max > 3){
            ranges.add(new Range(suffixarray[k],suffixarray[k]+max-1,max-3));
         }
      }
      
      Collections.sort(ranges);
      
      int[] dp = new int[n];
      dp[0] = 0;
      
      int rangei = 0;
      for(int k = 1; k < n; k++){
         dp[k] = dp[k-1];
         
         while(rangei < ranges.size() && ranges.get(rangei).r == k){
            dp[k] = Math.max(dp[k],dp[ranges.get(rangei).l-1] + ranges.get(rangei).w);
            rangei++;
         }
      }
      
      int answer = n-dp[n-1];
      out.println(answer);
      
      
      
      
      out.close();
   }
   
   public static int sptmin(int l, int r){
      int lg = log[r-l+1];
      return Math.min(spt[l][lg],spt[r-(1 << lg)+1][lg]);
   }
   
   public static class Range implements Comparable<Range>{
      int l;
      int r;
      int w;
      public Range(int a, int b, int c){
         l = a;
         r = b;
         w = c;
      }
      public int compareTo(Range range){
         return r-range.r;
      }
   
      
   }
   
   
   public static int[] make_lcp(String s, int[] suffixarray){
      int n = s.length();
      
      int[] pos = new int[n];
      for(int k = 0; k < n; k++){
         pos[suffixarray[k]] = k;
      }
      
      int[] lcp = new int[n-1];
      int x = 0;
      for(int k = 0; k < n; k++){
         if(pos[k] == n-1){
            x = 0;
            continue;
         }
         
         int j = suffixarray[pos[k]+1];
         while(k+x < n && j+x < n && s.charAt(k+x) == s.charAt(j+x)){
            x++;
         }
         lcp[pos[k]] = x;
         if(x > 0){
            x--;
         }
      }
      
      return lcp;
   }
   
   
   //https://cp-algorithms.com/string/suffix-array.html#on-log-n-approach
   public static int[] make_suffix_array(String s){
      int n = s.length();
      int alphabet = 256;
      int[] p = new int[n];
      int[] c = new int[n];
      int[] cnt = new int[Math.max(alphabet,n)];
      
      for(int k = 0; k < n; k++){
         cnt[ctoi(s.charAt(k))]++;
      }
      for(int k = 1; k < alphabet; k++){
         cnt[k] += cnt[k-1];
      }
      for(int k = 0; k < n; k++){
         p[--cnt[ctoi(s.charAt(k))]] = k;
      }
      c[p[0]] = 0;
      int classes = 1;
      for(int k = 1; k < n; k++){
         if(s.charAt(p[k]) != s.charAt(p[k-1])){
            classes++;
         }
         c[p[k]] = classes-1;
      }
      
      int[] pn = new int[n];
      int[] cn = new int[n];
      
      for(int h = 0; (1 << h) < n; h++){
         for(int k = 0; k < n; k++){
            pn[k] = p[k] - (1 << h);
            if(pn[k] < 0){
               pn[k] += n;
            }
         }
         
         Arrays.fill(cnt,0);
         
         for(int k = 0; k < n; k++){
            cnt[c[pn[k]]]++;
         }
         for(int k = 1; k < classes; k++){
            cnt[k] += cnt[k-1];
         }
         for(int k = n-1; k >= 0; k--){
            p[--cnt[c[pn[k]]]] = pn[k];
         }
         cn[p[0]] = 0;
         classes = 1;
         for(int k = 1; k < n; k++){
            int cur1 = c[p[k]];
            int cur2 = c[(p[k] + (1 << h)) % n];
            int prev1 = c[p[k-1]];
            int prev2 = c[(p[k-1] + (1 << h)) % n];
            
            if(cur1 != prev1 || cur2 != prev2){
               classes++;
            }
            cn[p[k]] = classes-1;
         }
         
         //swap c and cn
         int[] temp = c;
         c = cn;
         cn = temp;
      }
      
      return p;
   }
            
              
   //may need to edit (as well as alphabet variable)
   public static int ctoi(char ch){
      return (int)ch;
   }
   
   
}