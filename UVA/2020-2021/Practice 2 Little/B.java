//make sure to make new file!
import java.io.*;
import java.util.*;
import java.text.DecimalFormat;

public class B{
    
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
        
      StringTokenizer st = new StringTokenizer(f.readLine());
        
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      double[] b = new double[n];
      st = new StringTokenizer(f.readLine());
      for(int k = 0; k < n; k++){
         b[k] = Double.parseDouble(st.nextToken());
      }
      
      double[][] a = new double[n][m];
      double[] x = new double[m];
      double[] c = new double[m];
      
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
         for(int j = 0; j < n; j++){
            a[j][k] = Double.parseDouble(st.nextToken())/100;
         }
         c[k] = Double.parseDouble(st.nextToken());
      }
      
      double answer = simplex(a,b,c,x);
      DecimalFormat df = new DecimalFormat("#.00");
      out.println(df.format(answer));
        
        
        
        
        
        
        
        
        
      out.close();
   }
    
    
    // returns max c*x such that A*x <= b, x >= 0
   public static double simplex(double[][] A, double[] b, double[] c, double[] x) {
      int m = A.length;
      int n = A[0].length + 1;
      int[] index = new int[n + m];
      for (int i = 0; i < n + m; i++) {
         index[i] = i;
      }
      double[][] a = new double[m + 2][n + 1];
      for (double[] a1 : a) {
         Arrays.fill(a1, 0.0);
      }
      int L = m;
      for (int i = 0; i < m; i++) {
         for (int j = 0; j < n - 1; j++) {
            a[i][j] = A[i][j]*-1.0;
         }
         a[i][n - 1] = 1.0;
         a[i][n] = b[i];
         if (a[L][n] > a[i][n]) {
            L = i;
         }
      }
      for (int j = 0; j < n - 1; j++) {
         a[m][j] = c[j];
      }
      a[m + 1][n - 1] = -1.0;
      for (int E = n - 1;;) {
         if (L < m) {
            int t = index[E];
            index[E] = index[L + n];
            index[L + n] = t;
            a[L][E] = 1.0/a[L][E];
            for (int j = 0; j <= n; j++) {
               if (j != E) {
                  a[L][j] = a[L][j]*a[L][E]*-1.0;
               }
            }
            for (int i = 0; i <= m + 1; i++) {
               if (i != L) {
                  for (int j = 0; j <= n; j++) {
                     if (j != E) {
                        a[i][j] = a[i][j]+(a[L][j]*a[i][E]);
                     }
                  }
                  a[i][E] = a[i][E]*a[L][E];
               }
            }
         }
         E = -1;
         for (int j = 0; j < n; j++) {
            if (E < 0 || index[E] > index[j]) {
               if (a[m + 1][j] > 0 || a[m + 1][j] == 0 && a[m][j] > 0) {
                  E = j;
               }
            }
         }
         if (E < 0) {
            break;
         }
         L = -1;
         for (int i = 0; i < m; i++) {
            if (a[i][E] < 0) {
               double d;
               if (L < 0 || (d = a[L][n]/(a[L][E])-(a[i][n]/(a[i][E]))) < 0 || d == 0 && index[L + n] > index[i + n]) {
                  L = i;
               }
            }
         }
         if (L < 0) {
            return Double.MAX_VALUE;
         }
      }
      if (a[m + 1][n] < 0) {
         return -99999.9;
      }
      if (x != null) {
         Arrays.fill(x, 0.0);
         for (int i = 0; i < m; i++)
            if (index[n + i] < n - 1)
               x[index[n + i]] = a[i][n];
      }
      return a[m][n];
   }
       
}