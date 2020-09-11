//make sure to make new file!
import java.io.*;
import java.util.*;
//uses kmp from https://www.geeksforgeeks.org/kmp-algorithm-for-pattern-searching/
public class D344b{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      Letter[] ao = new Letter[n];            //t, uncompressed
      Letter[] bo = new Letter[m];            //s, uncompressed
      
      st = new StringTokenizer(f.readLine());
      for(int k = 0; k < n; k++){
         String s = st.nextToken();
         ao[k] = new Letter(Long.parseLong(s.substring(0,s.length()-2)),s.charAt(s.length()-1));
      }
      
      st = new StringTokenizer(f.readLine());
      for(int k = 0; k < m; k++){
         String s = st.nextToken();
         bo[k] = new Letter(Long.parseLong(s.substring(0,s.length()-2)),s.charAt(s.length()-1));
      }
      
      //compress each string
      ArrayList<Letter> a = new ArrayList<Letter>();        //t, compressed
      ArrayList<Letter> b = new ArrayList<Letter>();        //s, compressed
      
      a.add(ao[0]);
      b.add(bo[0]);
      
      for(int k = 1; k < n; k++){
         if(ao[k].c == a.get(a.size()-1).c){
            a.get(a.size()-1).t += ao[k].t;
         } else {
            a.add(ao[k]);
         }
      }
      
      for(int k = 1; k < m; k++){
         if(bo[k].c == b.get(b.size()-1).c){
            b.get(b.size()-1).t += bo[k].t;
         } else {
            b.add(bo[k]);
         }
      }
      
      n = a.size();
      m = b.size();
      
      if(m == 1){
         long answer = 0L;
         for(int k = 0; k < n; k++){
            if(a.get(k).c == b.get(0).c) answer += Math.max(0L,a.get(k).t-b.get(0).t+1);
         }
         
         out.println(answer);
      
      } else if(m == 2){
         int answer = 0;
         
         for(int k = 0; k < n-1; k++){
            if(a.get(k).c == b.get(0).c && a.get(k).t >= b.get(0).t && a.get(k+1).c == b.get(1).c && a.get(k+1).t >= b.get(1).t) answer++;
         }
         
         out.println(answer);
         
      
      } else {
         Letter[] b2 = new Letter[m-2];
         for(int k = 1; k < m-1; k++){
            b2[k-1] = b.get(k);
         }
         
         //find matches of b2 in a
         
         ArrayList<Integer> matches = new ArrayList<Integer>();            //stores index of last character of match of b2 in a
         
         int[] lps = new int[m-2];
         
         lps[0] = 0;
         int len = 0;
         int i = 1;
         
         while(i < m-2){
            if(b2[i].equals(b2[len])){
               len++;
               lps[i] = len;
               i++;
            } else {
               if(len != 0){
                  len = lps[len-1];
               } else {
                  lps[i] = len;
                  i++;
               }
            }
         }
         
         
         i = 0;
         int j = 0;
         while(i < n){
            if(b2[j].equals(a.get(i))){
               i++;
               j++;
            }
            if(j == m-2){
               matches.add(i-1);
               j = lps[j-1];
            } else if(i < n && !b2[j].equals(a.get(i))){
               if(j != 0){
                  j = lps[j-1];
               } else {
                  i++;
               }
            }
         }
         
         int answer = 0;
         
         for(int x : matches){
            if(x < m-2 || x == n-1) 
               continue;
            //xndex of fxrst char xs x-(m-2) and xndex of last char xs x+1
            if(a.get(x-(m-2)).c == b.get(0).c && a.get(x-(m-2)).t >= b.get(0).t && a.get(x+1).c == b.get(m-1).c && a.get(x+1).t >= b.get(m-1).t) answer++;
         }
         
         out.println(answer);
      }
         
      
      
      
      
      
      
      
      
      out.close();
   }
   
   public static class Letter{
      long t;
      char c;
      public Letter(long a, char b){
         t = a;
         c = b;
      }
      public boolean equals(Letter l){
         return t == l.t && c == l.c;
      }
   }
      
}