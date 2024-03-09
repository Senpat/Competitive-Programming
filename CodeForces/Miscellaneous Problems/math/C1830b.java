//Hyperregular Bracket Strings
import java.io.*;
import java.util.*;
//wa tc 2
//fails on this case:
/*
1
10 3
3 6
5 10
1 8
*/
public class C1830b{

   public static long MOD = 998244353L;

   public static long[] fac;
   public static long[] ifac;
   public static long[] catalan;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int N = 200005;
      
      fac = new long[N];
      ifac = new long[N];
      catalan = new long[N];
      
      fac[0] = 1L;
      
      for(int k = 1; k < N; k++){
         fac[k] = (fac[k-1] * (long)k + MOD)%MOD;
      }
      
      ifac[N-1] = modInverse(fac[N-1],MOD);
      
      for(int k = N-2; k >= 0; k--){
         ifac[k] = (ifac[k+1] * (long)(k+1) + MOD)%MOD;
      }
      
      for(int k = 2; k < 200001; k += 2){
         int i = k/2;
         catalan[k] = (modInverse(2*i + 1, MOD) * choose(2*i + 1,i) + MOD)%MOD;
      }
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
      
         ArrayList<ArrayList<Integer>> start = new ArrayList<ArrayList<Integer>>(n+1);
         ArrayList<ArrayList<Integer>> end = new ArrayList<ArrayList<Integer>>(n+1);
         
         for(int k = 0; k <= n; k++){
            start.add(new ArrayList<Integer>());
            end.add(new ArrayList<Integer>());
         }
         start.get(0).add(0);
         end.get(n).add(0);
         
         Interval[] intervals = new Interval[m+1];
         intervals[0] = new Interval(0,n);
         for(int k = 1; k <= m; k++){
            st = new StringTokenizer(f.readLine());
      
            int l = Integer.parseInt(st.nextToken())-1;
            int r = Integer.parseInt(st.nextToken());
            
            start.get(l).add(k);
            end.get(r).add(k);
            
            intervals[k] = new Interval(l,r);
         }
         
         //sort by size (process smaller intervals before larger ones for both start and end
         Comparator<Integer> comparator = new Comparator<Integer>(){
            public int compare(Integer ai, Integer bi){
               int a = (int)ai;
               int b = (int)bi;
               
               return intervals[a].len - intervals[b].len;
            }
         };
         for(int k = 0; k <= n; k++){
            Collections.sort(start.get(k), comparator);
            Collections.sort(end.get(k), comparator);
         }
         
         int[] compstart = new int[m+2];
         int compi = 1;
         ArrayList<HashSet<Integer>> components = new ArrayList<HashSet<Integer>>();
         components.add(new HashSet<Integer>());      //one-indexed
         Stack<Integer> stack = new Stack<Integer>();
         
         for(int k = 0; k <= n; k++){
            for(int i : end.get(k)){
               //components contained fully within this interval
               while(!stack.isEmpty() && stack.peek() < 0 && compstart[-1*stack.peek()] >= intervals[i].l){
                  //out.println("rem " + i + " " + stack.peek());
                  stack.pop();
               }
               
               int top = stack.peek();
               //out.println(i + " " + top);
               //out.flush();
               if(top == i){
                  compstart[compi] = intervals[top].l;
                  HashSet<Integer> hset = new HashSet<Integer>();
                  hset.add(intervals[i].l);
                  hset.add(intervals[i].r);
                  components.add(hset);
                  compi++;
                  stack.pop();
               } else if(top < 0){
                  //in component -1*top
                  int comp = -1*top;
                  compstart[comp] = Math.min(compstart[comp],intervals[i].l);
                  components.get(comp).add(intervals[i].l);
                  components.get(comp).add(intervals[i].r);
               } else {
                  //make new component
                  //1 2 1 2 pattern (where i is 1)
                  compstart[compi] = intervals[i].l;
                  HashSet<Integer> hset = new HashSet<Integer>();
                  hset.add(intervals[i].l);
                  hset.add(intervals[i].r);
                  components.add(hset);
                  
                  //pop until i
                  while(!stack.isEmpty() && stack.peek() != i){
                     stack.pop();
                  }
                  stack.pop();    //pop i
                  
                  stack.push(-1 * compi);       //push comp marker
                  
                  compi++;
               }
            }
            
            for(int i : start.get(k)){
               stack.push(i);
            }
            
         }
         
         ArrayList<Interval> finalintervals = new ArrayList<Interval>();
         
         for(HashSet<Integer> hset : components){
            ArrayList<Integer> points = new ArrayList<Integer>(hset);
            Collections.sort(points);
            
            for(int p = 1; p < points.size(); p++){
               finalintervals.add(new Interval(points.get(p-1),points.get(p)));
            }
         }
         
         start = new ArrayList<ArrayList<Integer>>(n+1);
         end = new ArrayList<ArrayList<Integer>>(n+1);
         
         for(int k = 0; k <= n; k++){
            start.add(new ArrayList<Integer>());
            end.add(new ArrayList<Integer>());
         }
         
         int[] len = new int[finalintervals.size()];
         for(int k = 0; k < finalintervals.size(); k++){
            start.get(finalintervals.get(k).l).add(k);
            end.get(finalintervals.get(k).r).add(k);
            len[k] = finalintervals.get(k).r - finalintervals.get(k).l;
            out.println(finalintervals.get(k).l + " " + finalintervals.get(k).r);
            out.flush();
         }
         
         //for start: want later end first (bigger sum)
         //for end: want later start first (bigger sum)
         Comparator<Integer> comparator2 = new Comparator<Integer>(){
            public int compare(Integer ai, Integer bi){
               int a = (int)ai;
               int b = (int)bi;
               
               return (finalintervals.get(b).l + finalintervals.get(b).r) - (finalintervals.get(a).l + finalintervals.get(b).r);
            }
         };
         for(int k = 0; k <= n; k++){
            Collections.sort(start.get(k), comparator2);
            Collections.sort(end.get(k), comparator2);
         }
         
         stack = new Stack<Integer>();
         
         long answer = 1L;
         for(int k = 0; k <= n; k++){
            for(int i : end.get(k)){
               //top should be i
               answer = (answer * catalan[len[i]] + MOD)%MOD;
               
               stack.pop();
               
               if(!stack.isEmpty()){
                  len[stack.peek()] -= finalintervals.get(i).len;
               }
            }
            
            for(int i : start.get(k)){
               stack.push(i);
            }
         }
         
         out.println(answer);
      }
      
      
      
      
      out.close();
   }
   
   
   public static class Interval implements Comparable<Interval>{
      int l;
      int r;
      int len;
      public Interval(int a, int b){
         l = a;
         r = b;
         len = r-l;
      }
      
      //sort by left endpoint, then decreasing size
      public int compareTo(Interval i){
         if(l == i.l){
            return i.len-len;
         }
         return l-i.l;
      }
   
   }
   
   public static long choose(int n, int r){
      long prod = (fac[n] * ifac[r] + MOD)%MOD;
      return (prod * ifac[n-r] + MOD)%MOD;
   }
   
   //from geeksforgeeks
   public static long modInverse(long a, long m) 
   { 
       long m0 = m; 
       long y = 0;
       long x = 1; 
     
       if (m == 1) 
         return 0; 
     
       while (a > 1) 
       { 
           // q is quotient 
           long q = a / m; 
           long t = m; 
     
           // m is remainder now, process same as 
           // Euclid's algo 
           m = a % m;
           a = t; 
           t = y; 
     
           // Update y and x 
           y = x - q * y; 
           x = t; 
       } 
     
       // Make x positive 
       if (x < 0) 
          x += m0; 
       return x; 
   }  
      
}