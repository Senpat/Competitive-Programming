#include <bits/stdc++.h>

using namespace std;

long long dist(pair<int,int> p1, pair<int,int> p2){
   return abs(p1.first-p2.first) + abs(p1.second-p2.second);
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int t;
   cin >> t;
   for(int q = 0; q < t; q++){
      int n;
      cin >> n;
      
      vector<pair<int,int>> points;
      
      unordered_map<int,vector<pair<int,int>>> xmap;        //x -> {y,index}
      unordered_map<int,vector<pair<int,int>>> ymap;
      
      vector<vector<int>> adj(n,vector<int>());
      
      for(int k = 0; k < n; k++){
         int a,b;
         cin >> a >> b;
         
         points.push_back({a,b});
         
         xmap[a].push_back({b,k});
         ymap[b].push_back({a,k});
         
         adj.push_back(vector<int>());
      }
      
      
      bool fail = false;
      
      for(auto p : xmap){
         sort(p.second.begin(),p.second.end());
         
         if(p.second.size()%2 == 1){
            fail = true;
            break;
         }
         
         for(int k = 0; k < p.second.size(); k += 2){
            adj[p.second[k].second].push_back(p.second[k+1].second);
            adj[p.second[k+1].second].push_back(p.second[k].second);
         }
      }
      for(auto p : ymap){
         sort(p.second.begin(),p.second.end());
         
         if(p.second.size()%2 == 1){
            fail = true;
            break;
         }
         
         for(int k = 0; k < p.second.size(); k += 2){
            adj[p.second[k].second].push_back(p.second[k+1].second);
            adj[p.second[k+1].second].push_back(p.second[k].second);
         }
      } 
      
      for(int k = 0; k < n; k++){
         if(adj[k].size() != 2){
            fail = true;
            break;
         }
      }
      
      if(fail){
         cout << "-1\n";
         continue;
      }
      
      long long answer = 0LL;
      
      vector<bool> seen(n,false);
      
      seen[0] = true;
      int i = 0;
      int num = 0;
      while(true){
         num++;
         if(!seen[adj[i][0]]){
            seen[adj[i][0]] = true;
            answer += dist(points[i],points[adj[i][0]]);
            i = adj[i][0];
            
         } else if(!seen[adj[i][1]]){
            seen[adj[i][1]] = true;
            answer += dist(points[i],points[adj[i][1]]);
            i = adj[i][1];
         } else {
            if(adj[i][0] == 0 || adj[i][1] == 0){
               answer += dist(points[i],points[0]);
            } else {
               fail = true;
            }
            break;
         }
          
      }
      
      if(num != n) fail = true;
      
      if(fail){
         cout << "-1\n";
      } else {
         cout << answer << "\n";
      }
      
      
   }
   
   
   return 0;
}