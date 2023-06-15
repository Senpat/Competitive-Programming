//make sure to make new file!
import java.io.*;
import java.util.*;

public class P1753{
   
   public static void main(String[] args)throws IOException{
      FastScanner fs = new FastScanner();
      PrintWriter out = new PrintWriter(System.out);
      
      String s = fs.nextLine();
      String p = fs.nextLine();
      int sn = s.length();
      int pn = p.length();
      
      //p is at index sn+1
      s += "_" + p + "$";
      
      int[] suffixarray = make_suffix_array(s);
      int[] lcp = make_lcp(s,suffixarray);
      
      //find sn+1
      int i = -1;
      for(int k = 0; k < suffixarray.length; k++){
         if(suffixarray[k] == sn+1){
            i = k;
            break;
         }
      }
      
      int answer = 0;
      int cur = i-1;
      while(cur >= 0 && lcp[cur] >= pn){
         answer++;
         cur--;
      }
      
      cur = i;
      while(cur < lcp.length && lcp[cur] >= pn){
         answer++;
         cur++;
      }
      
      out.println(answer);
      
      
      
      
      
      
      
      out.close();
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
      int[] c = new int[2*n];
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
      int[] cn = new int[2*n];
      
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
            int cur2 = c[p[k] + (1 << h)];
            int prev1 = c[p[k-1]];
            int prev2 = c[p[k-1] + (1 << h)];
            
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

class FastScanner
{
    //I don't understand how this works lmao
    private int BS = 1 << 16;
    private char NC = (char) 0;
    private byte[] buf = new byte[BS];
    private int bId = 0, size = 0;
    private char c = NC;
    private double cnt = 1;
    private BufferedInputStream in;
 
    public FastScanner() {
        in = new BufferedInputStream(System.in, BS);
    }
 
    public FastScanner(String s) {
        try {
            in = new BufferedInputStream(new FileInputStream(new File(s)), BS);
        } catch (Exception e) {
            in = new BufferedInputStream(System.in, BS);
        }
    }
 
    private char getChar() {
        while (bId == size) {
            try {
                size = in.read(buf);
            } catch (Exception e) {
                return NC;
            }
            if (size == -1) return NC;
            bId = 0;
        }
        return (char) buf[bId++];
    }
 
    public int nextInt() {
        return (int) nextLong();
    }
 
    public int[] nextInts(int N) {
        int[] res = new int[N];
        for (int i = 0; i < N; i++) {
            res[i] = (int) nextLong();
        }
        return res;
    }
 
    public long[] nextLongs(int N) {
        long[] res = new long[N+1];
        for (int i = 1; i <= N; i++) {
            res[i] = nextLong();
        }
        return res;
    }
 
    public long nextLong() {
        cnt = 1;
        boolean neg = false;
        if (c == NC) c = getChar();
        for (; (c < '0' || c > '9'); c = getChar()) {
            if (c == '-') neg = true;
        }
        long res = 0;
        for (; c >= '0' && c <= '9'; c = getChar()) {
            res = (res << 3) + (res << 1) + c - '0';
            cnt *= 10;
        }
        return neg ? -res : res;
    }
 
    public double nextDouble() {
        double cur = nextLong();
        return c != '.' ? cur : cur + nextLong() / cnt;
    }
 
    public double[] nextDoubles(int N) {
        double[] res = new double[N];
        for (int i = 0; i < N; i++) {
            res[i] = nextDouble();
        }
        return res;
    }
 
    public String next() {
        StringBuilder res = new StringBuilder();
        while (c <= 32) c = getChar();
        while (c > 32) {
            res.append(c);
            c = getChar();
        }
        return res.toString();
    }
 
    public String nextLine() {
        StringBuilder res = new StringBuilder();
        while (c <= 32) c = getChar();
        while (c != '\n') {
            res.append(c);
            c = getChar();
        }
        return res.toString();
    }
 
    public boolean hasNext() {
        if (c > 32) return true;
        while (true) {
            c = getChar();
            if (c == NC) return false;
            else if (c > 32) return true;
        }
    }
}