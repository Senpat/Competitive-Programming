//make sure to make new file!
import java.io.*;
import java.util.*;
//in contest attempt
public class D908{
   
   public static void main(String[] args)throws IOException{
      FastScanner fs = new FastScanner();
      PrintWriter out = new PrintWriter(System.out);
      
      int t = fs.nextInt();
      
      for(int q = 1; q <= t; q++){
         int n = fs.nextInt();
         int m = fs.nextInt();
         
         int[] freq = new int[n+1];
         for(int k = 0; k < n; k++){
            int i = fs.nextInt();
            freq[i]++;
         }
         
         int[] s = fs.nextInts(n);
         int[] d = fs.nextInts(n);
         
         TreeSet<Cube> tset = new TreeSet<Cube>();
         for(int k = 1; k <= n; k++){
            if(freq[k] > 0){
               tset.add(new Cube(k,freq[k]));
            }
         }
         
         Shelf[] shelves = new Shelf[n];
         for(int k = 0; k < n; k++){
            shelves[k] = new Shelf(s,d,k);
         }
         Arrays.sort(shelves);
         
         for(int k = 0; k < n; k++){         

      }
      
      
      
      
      out.close();
   }
   
   public static class Shelf implements Comparable<Shelf>{
      int s;
      int d;
      int index;
      
      int[] shelf;
      
      public Shelf(int a, int b, int c){
         s = a;
         d = b;
         index = c;
         
         
         shelf = new int[s];
      }
      
      public int compareTo(Shelf sh){
         return d-sh.d;
      }
      
      
   }
   
   public static class Cube implements Comparable<Cube>{
      int v;
      int i;
      public Cube(int a, int b){
         v = a;
         i = b;
      }
      
      public int compareTo(Cube c){
         return i-c.i;
      }
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