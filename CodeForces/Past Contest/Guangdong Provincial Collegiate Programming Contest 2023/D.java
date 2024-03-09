//make sure to make new file!
import java.io.*;
import java.util.*;

public class D{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         int maxnonnei = m-n;
         long answer = 0L;
         long allb = 0L;
         Person[] people = new Person[n];
         for(int k = 0; k < n; k++){
            st = new StringTokenizer(f.readLine());
            long a = Long.parseLong(st.nextToken());
            long b = Long.parseLong(st.nextToken());
            
            people[k] = new Person(a,b);
            answer += a;
            allb += b;
         }
         
         Arrays.sort(people);
         
         //at least 2 neighbors
         for(int k = 0; k < maxnonnei && k < n-2; k++){
            if(people[n-k-1].diff > 0) answer += people[n-k-1].diff;
         }
         
         if(m >= n*2-1){
            answer = Math.max(answer,allb);
         }
         
         if(n == 1){
            answer = allb;
         }
         
         out.println(answer);
         

      }
      
      
      
      
      out.close();
   }
   
   public static class Person implements Comparable<Person>{
      long a;
      long b;
      long diff;
      
      public Person(long c, long d){
         a = c;
         b = d;
         diff = b-a;
      }
      
      public int compareTo(Person p){
         if(diff < p.diff) return -1;
         if(diff > p.diff) return 1;
         return 0;
      }
   }
   
      
}