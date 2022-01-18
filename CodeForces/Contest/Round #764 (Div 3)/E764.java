//make sure to make new file!
import java.io.*;
import java.util.*;

public class E764{
   
   public static void main(String[] args)throws IOException{
      FastScanner fs = new FastScanner();
      PrintWriter out = new PrintWriter(System.out);
      StringJoiner sj = new StringJoiner("\n");
      
      int t = fs.nextInt();
      
      for(int q = 1; q <= t; q++){
         //blank line
         //fs.nextLine();
      
         int n = fs.nextInt();
         int m = fs.nextInt();
         
         int[][] matrix = new int[n][m];
         for(int k = 0; k < n; k++){
            char[] ch = fs.next().toCharArray();
            for(int j = 0; j < m; j++){
               matrix[k][j] = Character.getNumericValue(ch[j]);
            }
         }
         
         char[] ch = fs.next().toCharArray();
         int[] target = new int[m];
         for(int k = 0; k < m; k++){
            target[k] = Character.getNumericValue(ch[k]);
         }
         
         ArrayList<Integer>[][] precomp2 = new ArrayList[10][10];
         ArrayList<Integer>[][][] precomp3 = new ArrayList[10][10][10];
         
         for(int k = 0; k <= 9; k++){
            for(int j = 0; j <= 9; j++){
               precomp2[k][j] = new ArrayList<Integer>();
               for(int h = 0; h <= 9; h++){
                  precomp3[k][j][h] = new ArrayList<Integer>();
               }
            }
         }
         
         for(int k = 0; k < m; k++){
            if(k+1 < m){
               precomp2[target[k]][target[k+1]].add(k);
            }
            if(k+2 < m){
               precomp3[target[k]][target[k+1]][target[k+2]].add(k);
            }
         }
         
         
         
         Triple[] twos = new Triple[m];
         Triple[] threes = new Triple[m];
         
         boolean[][] seen2 = new boolean[10][10];
         boolean[][][] seen3 = new boolean[10][10][10];
         
         for(int k = 0; k < n; k++){
            for(int j = 0; j < m; j++){
               //check two
               if(j+1 < m && !seen2[matrix[k][j]][matrix[k][j+1]]){
                  seen2[matrix[k][j]][matrix[k][j+1]] = true;
                  for(int i : precomp2[matrix[k][j]][matrix[k][j+1]]){
                     twos[i] = new Triple(j,j+1,k);
                  }
               }
               //check three
               if(j+2 < m && !seen3[matrix[k][j]][matrix[k][j+1]][matrix[k][j+2]]){
                  seen3[matrix[k][j]][matrix[k][j+1]][matrix[k][j+2]] = true;
                  for(int i : precomp3[matrix[k][j]][matrix[k][j+1]][matrix[k][j+2]]){
                     threes[i] = new Triple(j,j+2,k);
                  }
               }
            }
         }
         
         //stores -1 if can't get there, else 2 if u did a double to get there, 3 if you did a triple
         int[] dp = new int[m+1];
         Arrays.fill(dp,-1);
         dp[0] = 0;
         for(int k = 0; k < m; k++){
            if(dp[k] == -1) 
               continue;
            //try 2
            if(twos[k] != null) dp[k+2] = 2;
            if(threes[k] != null) dp[k+3] = 3;
         }
         
         if(dp[m] == -1){
            sj.add("-1");
            continue;
         }
         
         //back track
         ArrayList<Triple> triples = new ArrayList<Triple>();
         int i = m;
         while(i > 0){
            if(dp[i] == 2){
               triples.add(twos[i-2]);     
               i-=2;
            } else {
               triples.add(threes[i-3]);
               i-=3;
            }
         }
          
         
         sj.add("" + triples.size());
         for(int k = triples.size()-1; k >= 0; k--){
            sj.add(triples.get(k).toString());
         }
         
      
      }
      
      
      out.println(sj.toString());
      
      
      out.close();
   }
   
   public static class Triple{
      int l;
      int r;
      int i;
      public Triple(int a, int b, int c){
         l = a;
         r = b;
         i = c;
      }
      public String toString(){
         return "" + (l+1) + " " + (r+1) + " " + (i+1);
      }
   }
   
   
      static class FastScanner
   {
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
            if (size == -1) 
               return NC;
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
         if (c > 32) 
            return true;
         while (true) {
            c = getChar();
            if (c == NC) 
               return false;
            else if (c > 32) 
               return true;
         }
      }
   }
   
}