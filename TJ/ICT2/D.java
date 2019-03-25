//make sure to make new file!
import java.io.*;
import java.util.*;

public class D{
   
   public static int MAXN = 100005;
   
   public static int[] bits;
   public static int n;

   public static int[] pow2;

   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      n = Integer.parseInt(f.readLine());
      
      char[] s = f.readLine().toCharArray();
      
      pow2 = new int[MAXN];
      bits = new int[MAXN];
   //cout << s << endl;
      pow2[0] = 1;
      for(int k = 1; k < MAXN; k++){
      //if(k%10000 == 0) cout << k << endl;
         pow2[k] = (pow2[k-1] * 2 + 3) % 3;
      //cout << pow2[k] << " ";
      }
   //cout << endl;
   
   //update tree
      for(int k = 0; k < n; k++){
      //cout << k << endl;
         if(s[k] == '1') update(k+1,pow2[n-k-1]);
      }
   //cout << "hi" << endl;
   // for(int k = 1; k <= n; k++){
   //       cout << psum(k) << " ";
   //    }
   //    cout<< endl;
      int q = Integer.parseInt(f.readLine());
   
      for(int k = 0; k < q; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         int w = Integer.parseInt(st.nextToken());
         if(w == 0){
            int i= Integer.parseInt(st.nextToken());
         
            if(s[i] == '0'){
               update(i+1,pow2[n-i-1]);
               s[i] = '1';
            }
         } 
         else {
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int answer = (psum(r+1)-psum(l));
         //for(int k = 1; k <= n; k++){
         //   cout << psum(k) << " ";
         //}
         //cout<< endl;
         //cout << k << " " << answer << endl;
            answer *= modInverse(pow2[n-r-1],3);
         
            out.println((answer+3)%3);
         }
      }
   
      
      
      
      
      out.close();
   }
   
   
   public static void update(int i, int x){
      for(; i <= n; i += i&-i){
         bits[i] += x%3;
      }
   }

   public static int psum(int i){
      int sum = 0;
      for(; i > 0; i -= i&-i){
         sum += bits[i];
      }
      return sum;
   }

//from geeksforgeeks
   public static int modInverse(int a, int m) 
   { 
      int m0 = m; 
      int y = 0, x = 1; 
   
      if (m == 1) 
         return 0; 
   
      while (a > 1) 
      { 
        // q is quotient 
         int q = a / m; 
         int t = m; 
      
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