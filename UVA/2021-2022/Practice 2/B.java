//make sure to make new file!
import java.io.*;
import java.util.*;
//circlebounce, upsolve, richard's sol
public class B{

   public static long MOD = 1000000007L;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      long b = Long.parseLong(st.nextToken());
      long a = Long.parseLong(st.nextToken());
      long n = Long.parseLong(st.nextToken());
      
      //sin theta = 2ab/(a^2+b^2)
      //cos theta = (b^2-a^2)/(a^2+b^2)
      
      long a2 = (a*a + MOD)%MOD;
      long b2 = (b*b + MOD)%MOD;
      long a2pb2 = (a2 + b2 + MOD)%MOD;
      long denom = modInverse(a2pb2,MOD);
      
      long sinnumer = (2L*a*b + MOD)%MOD;
      long sin = (sinnumer * denom + MOD)%MOD;
      long nsin = (MOD-sin)%MOD;
      
      long cosnumer = ((b2-a2)%MOD+MOD)%MOD;
      long cos = (cosnumer * denom + MOD)%MOD;
      
      /*
      clockwise rotation:
      cos sin
      -sin cos
      */
      
      /*long[][] matrix = new long[2][2];
      matrix[0][0] = cos;
      matrix[0][1] = sin;
      matrix[1][0] = nsin;
      matrix[1][1] = cos;
      */
      
      long[][] matrix = new long[][]{new long[]{cos,sin},new long[]{nsin,cos}};
      
      long[][] transform = exp(matrix,n+1);
      
      //multiply transform by [-1, 0], answer is negative transform[0][0]
      long answer = (MOD-transform[0][0])%MOD;
      out.println(answer);
      out.flush();
      /*
      long[][] test = new long[2][2];
      test[0][0] = 555990223;
      test[0][1] = 93398534;
      test[1][0] = 906601473;
      test[1][1] = 555990223;
      printmatrix(exp(test,0));
      printmatrix(exp(test,1));
      printmatrix(exp(test,2));
      printmatrix(exp(test,3));
      printmatrix(exp(test,4));
      printmatrix(exp(test,5));
      */
      //printmatrix(matrix);
      
      
      
      
      
      
      out.close();
   }
   
   public static void printmatrix(long[][] a){
      System.out.println(a[0][0] + " " + a[0][1]);
      System.out.println(a[1][0] + " " + a[1][1]);
      System.out.println();
   }
   
   public static long[][] matmul(long[][] a, long[][] b){
		int len = a.length;
      long[][] ret = new long[len][len];

		for(int k = 0; k < len; k++){
			for(int j = 0; j < len; j++){
				for(int h = 0; h < len; h++){
					//ret[k][j] = (ret[k][j] + a[h][k]*b[j][h] + MOD)%MOD;
				   ret[k][j] = (ret[k][j] + a[k][h]*b[h][j] + MOD)%MOD;
            }
			}
		}
		return ret;
	}

	public static long[][] exp(long[][] base, long power){
		if(power == 0) return new long[][]{new long[]{1,0},new long[]{0,1}};
		if(power == 1) return base;
		long[][] ans = exp(base,power/2);
		ans = matmul(ans,ans);
		if(power%2 == 1) ans = matmul(ans, base);
		return ans;
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