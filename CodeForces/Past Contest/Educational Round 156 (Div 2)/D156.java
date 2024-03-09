//make sure to make new file!
import java.io.*;
import java.util.*;

public class D156{
   
   public static long MOD = 998244353L;
   
   public static long[] fac;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int N = 300005;
      fac = new long[N];
      fac[0] = 1L;
      for(int k = 1; k < N; k++){
         fac[k] = (fac[k-1] * (long)k + MOD)%MOD;
      }
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int q = Integer.parseInt(st.nextToken());
      
      char[] input = f.readLine().toCharArray();
      //stores if the element is filled with > or <
      boolean[] array = new boolean[n-1];
      int numl = 0;
      int numg = 0;
      for(int k = 0; k < n-1; k++){
         if(input[k] == '<') numl++;
         if(input[k] == '>') numg++;
         array[k] = (input[k] != '?');
      }
      
      TreeSet<Range> ranges = new TreeSet<Range>();
      
      int totempty = 1;
      int curstart = -1;
      for(int k = 0; k < n-1; k++){
         if(!array[k]){
            totempty++;
            if(curstart == -1) curstart = k;
         } else {
            if(curstart != -1){
               ranges.add(new Range(curstart,k-1));
               curstart = -1;
            }
         }
      }
      if(curstart != -1){
         ranges.add(new Range(curstart,n-2));
      }
      
      long[] answer = new long[q+1];
      answer[0] = calcanswer(ranges,totempty,numl,numg);
      
      for(int t = 1; t <= q; t++){
         st = new StringTokenizer(f.readLine());
         int i = Integer.parseInt(st.nextToken())-1;
         char ch = st.nextToken().charAt(0);
         boolean c = (ch != '?');
         
         //update numl and numg
         if(input[i] == '<') numl--;
         if(input[i] == '>') numg--;
         if(ch == '<') numl++;
         if(ch == '>') numg++;
         
         input[i] = ch;
         
         if(c != array[i]){
            //update totempty and ranges
            array[i] = c;
            Range range = new Range(i,i);
            if(c){
               totempty--;
               //split range
               
               Range cur = ranges.floor(range);
               if(cur == null || cur.r < i){
                  //cur is too small, try higher
                  cur = ranges.ceiling(range);
               }
               
               if(cur != null && cur.l <= i && cur.r >= i){
                  ranges.remove(cur);
                  if(i != cur.l){
                     ranges.add(new Range(cur.l,i-1));
                  }
                  if(i != cur.r){
                     ranges.add(new Range(i+1,cur.r));
                  }
               }
            } else {
               totempty++;
               //join ranges
               
               //join left
               Range left = ranges.lower(range);
               if(left != null && left.r == i-1){
                  range.l = left.l;
                  ranges.remove(left);
               }
               //join right
               Range right = ranges.higher(range);
               if(right != null && right.l == i+1){
                  range.r = right.r;
                  ranges.remove(right);
               }
               
               ranges.add(range);
            }
         }
         
         answer[t] = calcanswer(ranges,totempty,numl,numg);
      }
      
      StringJoiner sj = new StringJoiner("\n");
      for(int t = 0; t <= q; t++){
         sj.add("" + answer[t]);
      }
      out.println(sj.toString());
      
      out.println(numl + " " + numg + " " + totempty);
      for(int k = 0; k < n-1; k++){
         out.print(input[k]);
      }
      out.println();
      
      
      
      
      
      
      
      
      out.close();
   }
   
   public static long calcanswer(TreeSet<Range> ranges, int totempty, int numl, int numg){
      //System.out.println("size: " + ranges.size());
      if(ranges.size() == 0) return 1L;
      
      Range first = ranges.first();
      //System.out.println("range: " + first.l + " " + first.r);
      //System.out.println("totempty: " + totempty);
      if(first.l == 0) return 0L;
      if(numl == 0 || numg == 0) return fac[totempty-1];
      if(first.l >= 2) return fac[totempty];
      return (fac[totempty] * modInverse(first.len()+1,MOD) + MOD)%MOD;
   }
   
   public static class Range implements Comparable<Range>{
      int l;
      int r;
      public Range(int a, int b){
         l = a;
         r = b;
      }
      
      public int len(){
         return r-l+1;
      }
      
      //sort by left
      public int compareTo(Range range){
         if(l == range.l) return r-range.r;
         return l-range.l;
      }
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