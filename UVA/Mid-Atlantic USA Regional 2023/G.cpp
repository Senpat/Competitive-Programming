#include <bits/stdc++.h>
//upsolve, hint (hall's theorem)
using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,m;
   cin >> n >> m;
   
   vector<vector<int>> matrix(n,vector<int>(m));
   
   vector<vector<int>> services(m,vector<int>());
   for(int k = 0; k < n; k++){
      string s;
      cin >> s;
      for(int j = 0; j < m; j++){
         matrix[k][j] = s[j]-'0';
         
         if(matrix[k][j] == 1){
            services[j].push_back(k);
         }
      }
   }
   //cout << "h1" << endl;
   vector<int> serv;
   set<int> eng;
   for(int k = 0; k < m; k++){
      if(services[k].size() < 20){
         serv.push_back(k);
         for(int i : services[k]){
            eng.insert(i);
         }
      }
   }
   int sn = serv.size();
   
   //compress eng
   map<int,int> compress;
   int ci = 0;
   for(int i : eng){
      compress[i] = ci++;
   }
   //cout << "h2" << endl;
   vector<bitset<400>> bsets(sn);
   for(int k = 0; k < sn; k++){
      for(int i : services[serv[k]]){
         bsets[k].set(compress[i]);  
      }
   }
   
   int psn = (1 << sn);
   int answer = m;
   for(int mask = 0; mask < psn; mask++){
      bitset<400> cur; 
      int bcount = 0;
      for(int k = 0; k < sn; k++){
         if(((mask>>k)&1) == 1){
            cur |= bsets[k];
            bcount++;
         }
      }
      if(cur.count() < bcount){
         answer = min(answer,bcount-1);
      }
      
   }
   
   cout << answer << endl;
   
   
   return 0;
}