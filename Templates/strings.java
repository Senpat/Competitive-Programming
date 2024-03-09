//make sure to make new file!
import java.io.*;
import java.util.*;

public class strings{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      String s = f.readLine();
      int[] array = new int[s.length()];
      for(int k = 0; k < s.length(); k++) array[k] = ctoi(s.charAt(k));
      int[] kmp = getkmp(array);
      
      for(int i : kmp){
         out.println(i + " ");
      }
      out.println();
      
      
      
      
      out.close();
   }
   
   //kmp:
   //abcabcd -> 0 0 0 1 2 3 0
   public static int[] getkmp(int[] s){
      int n = s.length;
      int[] kmp = new int[n];
      
      for(int k = 1; k < n; k++){
         int j = kmp[k-1];
         while(j > 0 && s[k] != s[j]){
            j = kmp[j-1];
         }
         if(s[k] == s[j]){
            j++;
         }
         kmp[k] = j;
      }
      
      return kmp;
   }
   
   
   //z function:
   //aaabaab -> 0 2 1 0 2 1 0
   public static int[] getz(int[] s){
      int n = s.length;
      int[] z = new int[n];
      int l = -1;
      int r = -1;
      for(int i = 1; i < n; i++){
         z[i] = i >= r ? 0 : Math.min(r - i, z[i - l]);
         while (i + z[i] < s.length && s[i + z[i]] == s[z[i]])
            z[i]++;
         if (i + z[i] > r){
            l = i;
            r = i + z[i];
         }
      }
      
      return z;
   }
   
   public static int ctoi(char ch){
      return ch-'a';
   }

   
   //gets the longest palindrome at every center
   public static int[] manacher(char[] s){
      int n = s.length;
      
      int[] answer = new int[n];
      
      int r = -1;
      int rcenter = -1;
      
      int center = 0;
      while(center < n){
         int len = 1;
         int i = 1;
         
         if(center <= r){
            int rightlen = (r-center)*2+1;
            //copy from other side
            answer[center] = answer[rcenter - (center-rcenter)];
            
            if(answer[center] > rightlen){
               answer[center] = rightlen;
            }
            if(answer[center] != rightlen){
               center++;
               continue;
            }
            
            len = rightlen;
            i = r-center+1;
         }
      
         while(center-i >= 0 && center+i < n && s[center-i] == s[center+i]){
            i++;
            len+=2;
         }
         
         answer[center] = len;
         
         rcenter = center;
         r = center+len/2;
         
         center++;
         
      }
      
      return answer;      
   }
   
   //mingyang c++ manacher (from https://codeforces.com/contest/1827/submission/205873972):
   //not sure how to use it
   /*
   //f[i*2]: i centered; f[i*2+1]: (i,i+1) centered
   void manacher(char *s) {
   	int n=strlen(s),id=0,mx=0;
   	for (int i = 0; i <= 2 * n + 10; i++)
   		f[i] = 0, ch[i] = 0;
   	ch[0]='$'; ch[1]='#';
   	for(int i=1;i<=n;i++)
   		ch[i*2]=s[i-1], ch[i*2+1]='#';
   	ch[n*2+2]='#';
   	for(int i=0;i<=2*n+10;i++) f[i]=0;
   	for(int i=1;i<=2*n+2;i++) {
   		if(i>mx) f[i]=1; else f[i]=min(f[id*2-i],mx-i);
   		while(ch[i-f[i]]==ch[i+f[i]]) f[i]++;
   		if(i+f[i]>mx) {mx=i+f[i]; id=i;}
   	}
   }
   */
}