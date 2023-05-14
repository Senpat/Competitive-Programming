//make sure to make new file!
import java.io.*;
import java.util.*;
import java.math.*;
//upsolve
//stop stalking me :)

public class C{
   
   public static int n,m;
   
   public static int[] numnot;
   public static int[] freq;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      n = Integer.parseInt(st.nextToken());
      m = Integer.parseInt(st.nextToken());
      
      //ith bit of personmask[k] = 1 means kth person is in ith test
      int[] personmask = new int[n+1];
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
         
         int tn = Integer.parseInt(st.nextToken());
         for(int j = 0; j < tn; j++){
            int i = Integer.parseInt(st.nextToken());
            personmask[i] += (1 << k);
         }
      }
      
      //number of numbers not included in tests at the 0 bits in mask
      numnot = new int[1 << m];
      Arrays.fill(numnot,n);
      
      //precompute number of 0s in mask
      int[] num0s = new int[1 << m];
      
      for(int mask = 0; mask < (1 << m); mask++){
         for(int i = 0; i < m; i++){
            if(((mask >> i) & 1) == 0){
               num0s[mask]++;
            }
         }
         
         for(int k = 1; k <= n; k++){
            if((personmask[k] | mask) != mask){
               numnot[mask]--;
            }
         }
      }
      
      Bigb[] bigbs = new Bigb[n];
      
      for(int k = 1; k <= n; k++){
         Bigb curb = new Bigb(k);
         
         //skip all ones mask
         for(int mask = 0; mask < (1 << m)-1; mask++){
            //if k is contained in a test with 0, continue
            if((personmask[k] | mask) != mask) 
               continue;
            
            //if odd then add, if even then subtract
            if(num0s[mask] % 2 == 0){
               curb.sub(numnot[mask]-1);
            } else {
               curb.add(numnot[mask]-1);
            }
            
         }
         
         //add everything to the bigb
         curb.calcbi();
         
         bigbs[k-1] = curb;
      }
      
      Arrays.sort(bigbs);
      
      StringJoiner sj = new StringJoiner(" ");
      for(int k = 0; k < n; k++){
         sj.add("" + bigbs[k].i);
      }
      out.println(sj.toString());
      
      
      
      
      out.close();
   }
   
   //Big Binary class
   public static class Bigb implements Comparable<Bigb>{
      static int B = 1200;
      int i;
      int[] addbits;
      int[] subbits;
      BigInteger bi;
      public Bigb(int a){
         i = a;
         addbits = new int[B];
         subbits = new int[B];
         bi = BigInteger.ZERO;
      }
      
      public void add(int x){
         addbits[x]++;
      }
      
      public void sub(int x){
         subbits[x]++;
      }
      
      public void calcbi(){
         //first combine addbits and subbits
         for(int k = 0; k < B-1; k++){
            addbits[k+1] += addbits[k]/2;
            addbits[k] &= 1;
            
            subbits[k+1] += subbits[k]/2;
            subbits[k] &= 1;
         }
         
         //convert to big integer
         StringJoiner sjadd = new StringJoiner("");
         StringJoiner sjsub = new StringJoiner("");
         
         for(int k = B-1; k >= 0; k--){
            sjadd.add("" + addbits[k]);
            sjsub.add("" + subbits[k]);
         }
         
         BigInteger biadd = new BigInteger(sjadd.toString(),2);
         BigInteger bisub = new BigInteger(sjsub.toString(),2);
         bi = biadd.subtract(bisub);
      }
      
      //-1 if this before b, 1 if this after b
      //decreasing order of bi, increasing order of index
      public int compareTo(Bigb b){
         int compbi = bi.compareTo(b.bi);
         if(compbi != 0) 
            return -1*compbi;
         return i-b.i;
      }
   }
   
   
}