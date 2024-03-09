#include <bits/stdc++.h>

using namespace std;

struct Edge{
   int to;
   long long w;
   int i;
};


//https://github.com/ucf-programming-team/hackpack-cpp/blob/master/content/graphs/DominatorTree.h
/**
 * Description: Given a digraph, return the edges of the dominator tree given as an adj. list (directed tree downwards from the root)
 * Time: $O((n + m) * log n)$ where n is the number of verticies in the graph and m is the number of edges
 * Status: Tested
 */
vector<vector<int>> dominator_tree(const vector<vector<int>>& adj, int root) {
	int n = adj.size() + 1, co = 0;
	vector<vector<int>> ans(n), radj(n), child(n), sdomChild(n);
	vector<int> label(n), rlabel(n), sdom(n), dom(n), par(n), bes(n);
	auto get = [&](auto self, int x) -> int {
		if (par[x] != x) {
			int t = self(self, par[x]);
			par[x] = par[par[x]];
			if (sdom[t] < sdom[bes[x]]) bes[x] = t;
		}
		return bes[x];
	};
	auto dfs = [&](auto self, int x) -> void {
		label[x] = ++co, rlabel[co] = x;
		sdom[co] = par[co] = bes[co] = co;
		for (auto y : adj[x]) {
			if (!label[y])
				self(self, y), child[label[x]].push_back(label[y]);
			radj[label[y]].push_back(label[x]);
		}
	};
	dfs(dfs, root);
	for (int i = co; i >= 1; --i) {
		for (auto j : radj[i])
			sdom[i] = min(sdom[i], sdom[get(get, j)]);
		if (i > 1) sdomChild[sdom[i]].push_back(i);
		for (auto j : sdomChild[i]) {
			int k = get(get, j);
			if (sdom[j] == sdom[k]) dom[j] = sdom[j];
			else dom[j] = k;
		}
		for (auto j : child[i]) par[j] = i;
	}
	for (int i = 2; i < co + 1; ++i) {
		if (dom[i] != sdom[i]) dom[i] = dom[dom[i]];
		ans[rlabel[dom[i]]].push_back(rlabel[i]);
	}
	return ans;
}


vector<vector<int>> domadj;
vector<int> parent;
void dfs(int v, int p){
   parent[v] = p;
   for(int nei : domadj[v]){
      if(nei == p) continue;
      dfs(nei,v);
   }
}


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,m;
   cin >> n >> m;
   
   vector<vector<Edge>> adj(n+1,vector<Edge>());
   vector<vector<Edge>> radj(n+1,vector<Edge>());
   
   for(int k = 0; k < m; k++){
      int a,b;
      long long w;
      cin >> a >> b >> w;
      
      adj[a].push_back({b,w,k});
      radj[b].push_back({a,w,k});
   }
   
   vector<long long> dist(n+1,LLONG_MAX);
   dist[1] = 0LL;
   
   priority_queue<pair<long long,int>, vector<pair<long long,int>>, greater<pair<long long,int>>> pq;
   pq.push({0LL,1});
   
   while(!pq.empty()){
      auto [d,v] = pq.top();
      pq.pop();
      
      if(d != dist[v]) continue;
      
      for(Edge e : adj[v]){
         long long newd = d + e.w;
         if(newd < dist[e.to]){
            dist[e.to] = newd;
            pq.push({newd,e.to});
         }
      }
   }
   
   queue<int> q;
   q.push(n);
   vector<bool> seen(n+1,false);
   seen[n] = true;
   
   //adj matrix using only edges that contribute to the shortest path between 1 and n
   vector<vector<int>> dadj(n+1,vector<int>());
   while(!q.empty()){
      int v = q.front();
      q.pop();
      
      for(Edge e : radj[v]){
         if(dist[e.to] + e.w == dist[v]){
            dadj[e.to].push_back(v);
            if(!seen[e.to]){
               seen[e.to] = true;
               q.push(e.to);
            }
         }
      }
   }
   
   domadj = dominator_tree(dadj,1);
   parent = vector<int>(n+1);
   dfs(1,-1);
   
   vector<int> answer;
   int i = n;
   while(i != -1){
      answer.push_back(i);
      i = parent[i];
   }
   
   sort(answer.begin(),answer.end());
   
   cout << answer.size() << endl;
   for(int i : answer){
      cout << i << " ";
   }
   
   return 0;
}