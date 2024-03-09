//make sure to make new file!
import java.io.*;
import java.util.*;
//upsolve
public class E157{

   public static int[] to;
   //-2 -> not seen, -1 -> lose, 0 -> draw, 1 -> win
   public static int[] val;
   
   public static boolean[] seen;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st1 = new StringTokenizer(f.readLine());
         StringTokenizer st2 = new StringTokenizer(f.readLine());
         Card[] a = new Card[n];
         
         for(int k = 0; k < n; k++){
            a[k] = new Card(Integer.parseInt(st1.nextToken()),Integer.parseInt(st2.nextToken()));
         }
         
         int m = Integer.parseInt(f.readLine());
         st1 = new StringTokenizer(f.readLine());
         st2 = new StringTokenizer(f.readLine());
         Card[] b = new Card[m];
         
         for(int k = 0; k < m; k++){
            b[k] = new Card(Integer.parseInt(st1.nextToken()),Integer.parseInt(st2.nextToken()));
         }
         
         Arrays.sort(a);
         Arrays.sort(b);
         
         //get max defend in every suffix
         int[] asuffmax = new int[n];
         asuffmax[n-1] = n-1;
         for(int k = n-2; k >= 0; k--){
            if(a[k].defend > a[asuffmax[k+1]].defend) asuffmax[k] = k;
            else asuffmax[k] = asuffmax[k+1];
         }
         int[] bsuffmax = new int[m];
         bsuffmax[m-1] = m-1;
         for(int k = m-2; k >= 0; k--){
            if(b[k].defend > b[bsuffmax[k+1]].defend) bsuffmax[k] = k;
            else bsuffmax[k] = bsuffmax[k+1];
         }
         
         to = new int[n+m];
         Arrays.fill(to,-1);           //-1 means end
         
         //play the card with the highest defend that has enough attack
         for(int k = 0; k < n; k++){
            //binary search to find the minimum index b with enough attack
            int l = 0;
            int r = m-1;
            int ans = -1;
            while(l <= r){
               int mid = l + (r-l)/2;
               
               if(b[mid].attack > a[k].defend){
                  ans = mid;
                  r = mid-1;
               } else {
                  l = mid+1;
               }
            }
            
            if(ans != -1){
               to[k] = bsuffmax[ans] + n;
            }
         }
         
         for(int k = 0; k < m; k++){
            int l = 0;
            int r = n-1;
            int ans = -1;
            while(l <= r){
               int mid = l + (r-l)/2;
               
               if(a[mid].attack > b[k].defend){
                  ans = mid;
                  r = mid-1;
               } else {
                  l = mid+1;
               }
            }
            
            if(ans != -1){
               to[k+n] = asuffmax[ans];
            }
         }
         
         val = new int[n+m];
         Arrays.fill(val,-2);
         for(int k = 0; k < n; k++){
            if(to[k] == -1) val[k] = 1;
         }
         for(int k = 0; k < m; k++){
            if(to[k+n] == -1) val[k+n] = -1;
         }
         
         seen = new boolean[n+m];
         for(int k = 0; k < n+m; k++){
            if(val[k] != -2) continue;
            
            dfs(k);
         }
         
         int a1 = 0;
         int a2 = 0;
         int a3 = 0;
         for(int k = 0; k < n; k++){
            if(val[k] == 1) a1++;
            if(val[k] == 0) a2++;
            if(val[k] == -1) a3++;
         }
         
         out.println(a1 + " " + a2 + " " + a3);
         
      }
      
      
      
      
      out.close();
   }
   
   public static void dfs(int v){
      seen[v] = true;
      if(val[to[v]] != -2){
         val[v] = val[to[v]];
      } else if(seen[to[v]]){
         val[v] = 0;
      } else {
         dfs(to[v]);
         val[v] = val[to[v]];
      }
   }
   
   public static class Card implements Comparable<Card>{
      int attack;
      int defend;
      public Card(int a, int b){
         attack = a;
         defend = b;
      }
      
      //sort by increasing attack, tiebreacker increasing defend
      public int compareTo(Card c){
         if(attack == c.attack) return defend-c.defend;
         return attack-c.attack;
      }
   }
}