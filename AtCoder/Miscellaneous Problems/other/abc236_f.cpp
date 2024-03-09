#include <bits/stdc++.h>
//xor basis practice
using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n;
   cin >> n;
   
   vector<pair<long long,int>> vals;
   for(int k = 1; k < (1 << n); k++){
      long long c;
      cin >> c;
      vals.push_back({c,k});
   }
   
   sort(vals.begin(),vals.end());
   
   //try putting the values with the smallest costs first (kruskal logic)
   vector<int> basis(n,0);
   long long answer = 0LL;
   for(int k = 0; k < vals.size(); k++){
      int i = vals[k].second;  
      for(int b = 0; b < n; b++){
         if(((i>>b)&1) == 0) continue;
         
         if(basis[b] == 0){
            basis[b] = i;
            answer += vals[k].first;
            
            break;
         }
         
         i ^= basis[b];
      }
   }
   
   cout << answer << endl;
   
   return 0;
}