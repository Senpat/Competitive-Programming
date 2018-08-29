import java.io.*;
import java.util.*;

class cownomics{
   
   public static String[] spot, plain;
   public static int[][] s,p;
   public static int[] a;
   public static int n,m;
      
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("cownomics.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cownomics.out")));
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      n = Integer.parseInt(st.nextToken());
      m = Integer.parseInt(st.nextToken());
      
      spot = new String[n];
      plain = new String[n];
      
      s = new int[n][m];
      p = new int[n][m];
      
      a = new int[64];
      
      for(int k = 0; k < n; k++){
         String line = f.readLine();
         spot[k] = line;
         for(int j = 0; j < line.length(); j++){
            if(line.charAt(j) == 'A')
               s[k][j] = 0;
            if(line.charAt(j) == 'C')
               s[k][j] = 1;
            if(line.charAt(j) == 'G')
               s[k][j] = 2;
            if(line.charAt(j) == 'T')
               s[k][j] = 3;
         }
      }
      
      for(int k = 0; k < n; k++){
         String line = f.readLine();
         plain[k] = line;
         for(int j = 0; j < line.length(); j++){
            if(line.charAt(j) == 'A')
               p[k][j] = 0;
            if(line.charAt(j) == 'C')
               p[k][j] = 1;
            if(line.charAt(j) == 'G')
               p[k][j] = 2;
            if(line.charAt(j) == 'T')
               p[k][j] = 3;
         }
      }
         
      
      int count = 0;
      
      for(int k1 = 0; k1 < m; k1++){
         for(int k2 = k1+1; k2<m;k2++){
            for(int k3 = k2+1; k3<m;k3++){
               if(test(k1,k2,k3)){
                  count++;
               }
            }
         }
      }
      
      System.out.println(count);
      out.println(count);
      out.close();
   }
   public static boolean test(int k1, int k2, int k3){
      boolean bool = true;
      for(int k = 0; k < n; k++){
         a[16*s[k][k1] + 4*s[k][k2] + s[k][k3]] = 1;
      }
      for(int k = 0; k < n; k++){
         if(a[16*p[k][k1] + 4*p[k][k2] + p[k][k3]] == 1){
            bool = false;
         }
      }
      for(int k = 0; k < n; k++){
         a[16*s[k][k1] + 4*s[k][k2] + s[k][k3]] = 0;
      }
      
      return bool;
   }
      
      
      
      
}