#include <bits/stdc++.h>

using namespace std;

vector<vector<int>> adj;
int n;

vector<long long> subsize;

int most = -1;
int best = -1;

long long c2(long long x){
   return x*(x-1)/2LL;
}

void dfs(int v, int p){
   
   long long connected = 0LL;
   
   long long max1 = 0;
   long long max2 = 0;
   
   for(int nei : adj[v]){
      if(nei == p) continue;
      dfs(nei,v);
      
      long long sub = subsize[nei]+1;
      
      connected += c2(sub);
      subsize[v] += sub;
      
      if(sub > max1){
         max2 = max1;
         max1 = sub;
      } else if(sub > max2){
         max2 = sub;
      }
   }
   
   long long parsize = (long long)(n) - subsize[v];
   connected += c2(parsize);
   
   if(parsize > max1){
      max2 = max1;
      max1 = parsize;
   } else if(parsize > max2){
      max2 = parsize;
   }
   
   long long dis = c2((long long)(n)) - connected;
   
   //cout << v << ": " << parsize << " " << max1 << " " << max2 << endl;
  
   
   long long curbest = c2((long long)n) - (connected - c2(max1) - c2(max2) + c2(max1+max2));
   
   //cout << dis << " " << curbest << endl;
    
   if(dis > most){
      most = dis;
      best = curbest;
   }
   
}


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
      
   cin >> n;
   
   for(int k = 0; k <= n; k++){
      adj.push_back(vector<int>());
      subsize.push_back(0LL);
   }
   
   for(int k = 0; k < n; k++){
      int a,b;
      cin >> a >> b;
      adj[a].push_back(b);
      adj[b].push_back(a);
   }
   
   
   dfs(0,-1);
   
   
   cout << most << " " << best << endl;
   
   return 0;
}