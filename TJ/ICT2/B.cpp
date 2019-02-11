#include <bits/stdc++.h>

using namespace std;

#define INF 1000000000

string a,b;
int n;
vector<vector<pair<int,long long>>> adj(26);
long long djik[26][26];
unordered_set<int> seen;

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   
   cin >> a >> b;
   
   if(a.length() != b.length()){
      cout << "-1" << endl;
      return 0;
   }
   
   cin >> n;
   
   for(int k = 0; k < n; k++){
      char c1,c2;
      long long w;
      
      cin >> c1 >> c2 >> w;
      
      adj[c1-'a'].push_back(make_pair(c2-'a',w));
   }
   
   //fill djik
   for(int k = 0; k < 26; k++){
      for(int j = 0; j < 26; j++){
         djik[k][j] = INF;
      }
   }
   
   for(int k = 0; k < 26; k++){
      
      //seen.insert(k);
      
      djik[k][k] = 0;
      
      for(int i = 0; i < 26; i++){
         int v = -1;
         for(int j = 0; j < 26; j++){
            //cout << k << " " << j << " " << djik[k][j] << " " << v << " " << djik[k][v] << endl;
            if(seen.find(j) == seen.end() && (v == -1 || djik[k][j] < djik[k][v])){
               v = j;
            }
         }
         
         //cout << v << " " << djik[k][v] << endl;
         if(djik[k][v] == INF){
            //cout << k << " " << v << " " << i << endl;
            break;
         }
         
         seen.insert(v);
         for(auto edge : adj[v]){
            int to = edge.first;
            long long w = edge.second;
            
            if(djik[k][v] + w < djik[k][to]){
               djik[k][to] = djik[k][v] + w;
            }
         }
      }
      
      seen.clear();
   }
   
   vector<char> answer;
   long long sum = 0;
   for(int k = 0; k < a.length(); k++){
      //find min
      char minchar = '?';
      long long mincost = INF;
      int ai = a[k]-'a';
      int bi = b[k]-'a';
      if(ai == bi){
         answer.push_back(a[k]);
         continue;
      }
      if(djik[ai][bi] < mincost){
         minchar = b[k];
         mincost = djik[ai][bi];
      }
      if(djik[bi][ai] < mincost){
         minchar = a[k];
         mincost = djik[bi][ai];
      }
      
      for(int j = 0; j < 26; j++){
         //if(j == 23) cout << k << " " << j << " " << djik[ai][j] << " " << djik[bi][j] << endl;
         if(djik[ai][j] == INF || djik[bi][j] == INF) continue;
         if(djik[ai][j] + djik[bi][j] < mincost){
            mincost = djik[ai][j] + djik[bi][j];
            minchar = j + 'a';
         }
         else if(djik[ai][j] + djik[bi][j] == mincost){
            if(j + 'a' < minchar){
               minchar = j + 'a';
            }
         }
      }
      if(minchar == '?'){
         cout << "-1" << endl;
         return 0;
      }
      answer.push_back(minchar);
      sum += mincost;
   }
   cout << sum << endl;
   for(auto c : answer){
      cout << c;
   }
   
   
   
   return 0;
}