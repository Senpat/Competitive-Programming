#include <bits/stdc++.h>

using namespace std;


int ctoi(char ch){
   return (int)ch;
}
 
vector<int> make_suffix_array(const string& s){
   int n = s.length();
   int alphabet = 256;
   vector<int> p(n);
   vector<int> c(2*n);
   vector<int> cnt(max(alphabet,n));
   
   for(int k = 0; k < n; k++){
      cnt[ctoi(s[k])]++;
   }
   for(int k = 1; k < alphabet; k++){
      cnt[k] += cnt[k-1];
   }
   for(int k = 0; k < n; k++){
      p[--cnt[ctoi(s[k])]] = k;
   }
   c[p[0]] = 0;
   int classes = 1;
   for(int k = 1; k < n; k++){
      if(s[p[k]] != s[p[k-1]]){
         classes++;
      }
      c[p[k]] = classes-1;
   }
   
   vector<int> pn(n);
   vector<int> cn(2*n);
   
   for(int h = 0; (1 << h) < n; h++){
      for(int k = 0; k < n; k++){
         pn[k] = p[k] - (1 << h);
         if(pn[k] < 0){
            pn[k] += n;
         }
      }
      
      fill(cnt.begin(),cnt.end(),0);
      
      for(int k = 0; k < n; k++){
         cnt[c[pn[k]]]++;
      }
      for(int k = 1; k < classes; k++){
         cnt[k] += cnt[k-1];
      }
      for(int k = n-1; k >= 0; k--){
         p[--cnt[c[pn[k]]]] = pn[k];
      }
      cn[p[0]] = 0;
      classes = 1;
      for(int k = 1; k < n; k++){
         int cur1 = c[p[k]];
         int cur2 = c[p[k] + (1 << h)];
         int prev1 = c[p[k-1]];
         int prev2 = c[p[k-1] + (1 << h)];
         
         if(cur1 != prev1 || cur2 != prev2){
            classes++;
         }
         cn[p[k]] = classes-1;
      }
      
      //swap c and cn
      vector<int> temp = c;
      c = cn;
      cn = temp;
   }
   
   return p;
}
 
vector<int> make_lcp(const string& s, const vector<int>& suffixarray){
   int n = s.length();
   
   vector<int> pos(n);
   for(int k = 0; k < n; k++){
      pos[suffixarray[k]] = k;
   }
   
   vector<int> lcp(n-1);
   int x = 0;
   for(int k = 0; k < n; k++){
      if(pos[k] == n-1){
         x = 0;
         continue;
      }
      
      int j = suffixarray[pos[k]+1];
      while(k+x < n && j+x < n && s[k+x] == s[j+x]){
         x++;
      }
      lcp[pos[k]] = x;
      if(x > 0){
         x--;
      }
   }
   
   return lcp;
}
 



int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   string s;
   cin >> s;
   int n = s.length();
   
   s += "$";
   
   vector<int> suffixarray = make_suffix_array(s);
   vector<int> lcp = make_lcp(s,suffixarray);
   
   int maxi = 0;
   for(int k = 1; k <= n; k++){
      if(lcp[k-1] > lcp[maxi]){
         maxi = k-1;
      }
   }
   
   if(lcp[maxi] == 0){
      cout << "-1\n";
   } else {
      int start = suffixarray[maxi];
      int len = lcp[maxi];
      
      for(int k = 0; k < len; k++){
         cout << s[start+k];
      }
   }
   return 0;
}