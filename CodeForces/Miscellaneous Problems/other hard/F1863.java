//make sure to make new file!
import java.io.*;
import java.util.*;
//danny
public class F1863{
   
   public static void main(String[] args)throws IOException{
      FastScanner fs = new FastScanner();
      PrintWriter out = new PrintWriter(System.out);
      
      int t = fs.nextInt();
      
      for(int q = 1; q <= t; q++){

         int n = fs.nextInt();
         long[] array = fs.nextLongs(n);
         /*
         //reverse every number
         for(int k = 0; k < n; k++){
            long i = 0L;
            for(int j = 0; j < 60; j++){
               i <<= 1;
               if((array[k]&1) != 0){
                  i++;
               }
               array[k] >>= 1;
            }
            array[k] = i;
         }
         */
               
         
         long xor = 0L;
         for(int k = 0; k < n; k++){
            xor ^= array[k];
         }
         
         /*
         if(xor == 0L){
            //everything is possible
            StringJoiner sj = new StringJoiner("");
            for(int k = 0; k < n; k++){
               sj.add("1");
            }
            out.println(sj.toString());
            continue;
         }
         */
         /*
         //get msb of xor
         int xmsb = 0;
         while(xor > 1){
            xor >>= 1;
            xmsb++;
         }*/
         
         
         boolean[] answer = new boolean[n];
         long[] left = new long[n];          //left[x] is a mask where bit i is set if an interval that starts at x with an xor with msb of i is reachable
         long[] right = new long[n];
         boolean[] left0 = new boolean[n];   //if there is a reachable interval starting at x with xor 0
         boolean[] right0 = new boolean[n];
         left[0] |= getmsb(xor);
         right[n-1] |= getmsb(xor);
         if(xor == 0){
            left0[0] = true;
            right0[n-1] = true;
         }
         
         for(int d = n-1; d > 1; d--){
            long curxor = 0L;
            for(int l = 0; l < d; l++){
               curxor ^= array[l];
            }
            for(int l = 0; l + d-1 < n; l++){
               int r = l+d-1;
               if(left0[l] || right0[r] || ((curxor & left[l]) > 0L) || ((curxor & right[r]) > 0L)){
                  if(curxor == 0){
                     left0[l] = true;
                     right0[r] = true;
                  } else {
                     left[l] |= getmsb(curxor);
                     right[r] |= getmsb(curxor);
                  }
               }
               if(l+d < n){
                  curxor ^= array[l] ^ array[l+d];
               }
            }
         }
         
         for(int k = 0; k < n; k++){
            answer[k] = (left0[k] || right0[k] || (array[k] & left[k]) > 0L || (array[k] & right[k]) > 0L);
         }
         
         StringJoiner sj = new StringJoiner("");
         for(int k = 0; k < n; k++){
            if(answer[k]){
               sj.add("1");
            } else {
               sj.add("0");
            }
         }
         out.println(sj.toString());
         
      

      }
      
      
      
      
      out.close();
   }
   
   public static long getmsb(long x){
      x |= x >> 1;
      x |= x >> 2;
      x |= x >> 4;
      x |= x >> 8;
      x |= x >> 16;
      x |= x >> 32;
      return x ^ (x >> 1);
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
        long[] res = new long[N];
        for (int i = 0; i < N; i++) {
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