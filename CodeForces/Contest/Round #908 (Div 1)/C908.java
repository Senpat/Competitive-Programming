//make sure to make new file!
import java.io.*;
import java.util.*;

public class C908{
   
   public static void main(String[] args)throws IOException{
      FastScanner fs = new FastScanner();
      PrintWriter out = new PrintWriter(System.out);
      
      int t = fs.nextInt();
      
      for(int q = 1; q <= t; q++){

         int m = fs.nextInt();
         
         long[] l = new long[m];
         long[] r = new long[m];
         ArrayList<HashMap<Long,Long>> vals = new ArrayList<>(m);
         for(int k = 0; k < m; k++){
            vals.add(new HashMap<Long,Long>());
         }
         long[] sumc = new long[m];
      
         long suml = 0L;
         long sumr = 0L;
         
         long sumn = 0L;
         
         //mapping from long x to all of the multisets that contain x
         HashMap<Long,ArrayList<Integer>> hmap = new HashMap<Long,ArrayList<Integer>>();
      
         for(int k = 0; k < m; k++){
            int n = fs.nextInt();
            l[k] = fs.nextLong();
            r[k] = fs.nextLong();
            sumn += (long)n;
            suml += l[k];
            sumr += r[k];
            
            long[] a = fs.nextLongs(n);
            long[] c = fs.nextLongs(n);
            
            for(int j = 0; j < n; j++){
               vals.get(k).put(a[j],c[j]);
               if(hmap.containsKey(a[j])){
                  hmap.get(a[j]).add(k);
               } else {
                  ArrayList<Integer> temp = new ArrayList<Integer>();
                  temp.add(k);
                  hmap.put(a[j],temp);
               }
               sumc[k] += c[j];
            }
            
            
         
         }
         
         
         if(sumr - suml +1 > sumn){
            out.println(0);
            continue;
         }
         
         //sumr-suml <= 10^5
         long answer = Long.MAX_VALUE;
         for(long x = suml; x <= sumr; x++){
            //try putting exactly x elements
            if(!hmap.containsKey(x)){
               answer = 0L;
               continue;
            }
            
            long nonx = sumr;          //multisets that don't have x put r
            long hasx = 0L;
            long force = 0L;
            for(int ms : hmap.get(x)){
               nonx -= r[ms];
               
               long msx = vals.get(ms).get(x);
               
               if(sumc[ms] - msx < l[ms]){
                  //put l
                  force += l[ms] - (sumc[ms]-msx);
                  hasx += l[ms];
               } else {
                  //put all elements except x
                  hasx += Math.min(r[ms],sumc[ms] - msx);
               }
            }
            
            answer = Math.min(answer, force + Math.max(0,x - (nonx + hasx)));
         }
         
         out.println(answer);
      }
      
      
      
      
      out.close();
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