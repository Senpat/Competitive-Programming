//make sure to make new file!
import java.io.*;
import java.util.*;
//bug: account for end
public class E{
   
   public static void main(String[] args)throws IOException{
      FastScanner fs = new FastScanner();
      PrintWriter out = new PrintWriter(System.out);
      
      int t = fs.nextInt();
      
      for(int q = 1; q <= t; q++){
      
         int n = fs.nextInt();
         int m = fs.nextInt();
         
         String[] array = new String[n];
         
         Trie head = new Trie();
         for(int k = 0; k < n; k++){
            array[k] = fs.next();
            
            Trie cur = head;
            for(int j = 0; j < array[k].length(); j++){
               int ci = array[k].charAt(j) - 'a';
               if(cur.children[ci] == null){
                  cur.children[ci] = new Trie();
               }
               cur = cur.children[ci];
               
               cur.freq++;
            }
            cur.end++;
         }
         
         StringJoiner sj = new StringJoiner("");
         Trie cur = head;
         
         while(true){
            int all = 0;
            int numdiff = cur.end;
            for(int k = 0; k < 26; k++){
               if(cur.children[k] != null){
                  numdiff++;
                  all += cur.children[k].freq;
               }
            }
            //out.println(all + " " + numdiff);
            if(all <= 1) break;
            //out.println(numdiff);
            if(numdiff >= m) break;
            
            int tot = numdiff;
            for(int k = 0; k < 26; k++){
               if(cur.children[k] == null) continue;
               
               if(tot + cur.children[k].freq-1 >= m){
                  char c = (char)(k+'a');
                  sj.add("" + c);
                  
                  cur = cur.children[k];
                  m -= tot-1;
                  break;
               }
               
               tot += cur.children[k].freq-1;
               
            }
         }
         
         String answer = sj.toString();
         if(answer.length() == 0){
            out.println("EMPTY");
         } else {
            out.println(answer);
         }

      }
      
      
      
      
      out.close();
   }
   
   public static class Trie{
      Trie[] children;
      int freq;
      int end;
      
      public Trie(){
         children = new Trie[26];
         freq = 0;
         end = 0;
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