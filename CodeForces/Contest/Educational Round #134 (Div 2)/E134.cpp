#include <bits/stdc++.h>
//in contest attempt, naive kmp
using namespace std;

char get(int x, int n, string& s, string& t){
   if(x < n) return s[x];
   else return t[x-n];
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
      
   string s;
   cin >> s;
   int n = s.length();
   
   vector<int> pi(n+10);
   for (int i = 1; i < n; i++) {
      int j = pi[i-1];
      while (j > 0 && s[i] != s[j])
         j = pi[j-1];
      if (s[i] == s[j])
         j++;
      pi[i] = j;
   }
   
   int q = 0;
   cin >> q;
   stringstream ss;
   for(int qq = 0; qq < q; qq++){
      string t;
      cin >> t;
      int tn = t.length();
      
      for(int i = n; i < n+tn; i++){
         int j = pi[i-1];
         while (j > 0 && get(i,n,s,t) != get(j,n,s,t))
            j = pi[j-1];
         if (get(i,n,s,t) == get(j,n,s,t))
            j++;
         pi[i] = j;
      }
      
      for(int k = n; k < n+tn; k++){
         ss << pi[k] << " ";
         pi[k] = 0;
      }
      ss << "\n";
   }
   
   cout << ss.str();
   
   return 0;
}