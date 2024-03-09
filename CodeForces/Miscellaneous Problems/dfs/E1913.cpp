#include <bits/stdc++.h>
//bug: each col has n elements, not m
using namespace std;


//from kactl https://github.com/kth-competitive-programming/kactl/blob/main/content/graph/MinCostMaxFlow.h
#include <bits/extc++.h>
const long long INF = numeric_limits<long long>::max() / 4;

struct MCMF {
	struct Edge {
		int from, to, rev;
		long long cap, cost, flow;
      bool isbackedge;
	};
	int N;
	vector<vector<Edge>> ed;
	vector<int> seen;
	vector<long long> dist, pi;
	vector<Edge*> par;

	MCMF(int N) : N(N), ed(N), seen(N), dist(N), pi(N), par(N) {}

	void addEdge(int from, int to, long long cap, long long cost) {
		if (from == to) return;
		ed[from].push_back(Edge{ from,to,(int)ed[to].size(),cap,cost,0 ,false});
		ed[to].push_back(Edge{ to,from,(int)ed[from].size()-1,0,-cost,0 ,true});
	}

	void path(int s) {
		fill(seen.begin(),seen.end(), 0);
		fill(dist.begin(),dist.end(), INF);
		dist[s] = 0; long long di;

		__gnu_pbds::priority_queue<pair<long long, int>> q;
		vector<decltype(q)::point_iterator> its(N);
		q.push({ 0, s });

		while (!q.empty()) {
			s = q.top().second; q.pop();
			seen[s] = 1; di = dist[s] + pi[s];
			for (Edge& e : ed[s]) if (!seen[e.to]) {
				long long val = di - pi[e.to] + e.cost;
				if (e.cap - e.flow > 0 && val < dist[e.to]) {
					dist[e.to] = val;
					par[e.to] = &e;
					if (its[e.to] == q.end())
						its[e.to] = q.push({ -dist[e.to], e.to });
					else
						q.modify(its[e.to], { -dist[e.to], e.to });
				}
			}
		}
		for(int i = 0; i < N; i++) pi[i] = min(pi[i] + dist[i], INF);
	}

	pair<long long, long long> maxflow(int s, int t) {
		long long totflow = 0, totcost = 0;
		while (path(s), seen[t]) {
			long long fl = INF;
			for (Edge* x = par[t]; x; x = par[x->from])
				fl = min(fl, x->cap - x->flow);

			totflow += fl;
			for (Edge* x = par[t]; x; x = par[x->from]) {
				x->flow += fl;
				ed[x->to][x->rev].flow -= fl;
			}
		}
		for(int i = 0; i < N; i++) for(Edge& e : ed[i]) totcost += e.cost * e.flow;
		return {totflow, totcost/2};
	}

	// If some costs can be negative, call this before maxflow:
	void setpi(int s) { // (otherwise, leave this out)
		fill(pi.begin(),pi.end(), INF); pi[s] = 0;
		int it = N, ch = 1; long long v;
		while (ch-- && it--)
			for(int i = 0; i < N; i++) if (pi[i] != INF)
			  for (Edge& e : ed[i]) if (e.cap)
				  if ((v = pi[i] + e.cost) < pi[e.to])
					  pi[e.to] = v, ch = 1;
		assert(it >= 0); // negative cost cycle
	}
};



int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,m;
   cin >> n >> m;
   
   vector<vector<int>> board(n,vector<int>(m));
   vector<int> row1(n);
   vector<int> col1(m);
   for(int k = 0; k < n; k++){
      for(int j = 0; j < m; j++){
         cin >> board[k][j];
         row1[k] += board[k][j];
         col1[j] += board[k][j];
      }
   }
   
   vector<int> row2(n);
   int r2sum = 0;
   for(int k = 0; k < n; k++){
      cin >> row2[k];
      r2sum += row2[k];
   }
   vector<int> col2(m);
   int c2sum = 0;
   for(int k = 0; k < m; k++){
      cin >> col2[k];
      c2sum += col2[k];
   }
   
   //row is [0,n), col is [n,n+m), source is n+m, sink is n+m+1
   
   MCMF flow(n+m+2);
   
   int source = n+m;
   int sink = n+m+1;
   for(int k = 0; k < n; k++){
      flow.addEdge(source,k,row1[k],0LL);
      flow.addEdge(k,sink,row2[k],0LL);
   }
   //flip column weights
   for(int k = 0; k < m; k++){
      flow.addEdge(source,k+n,n-col1[k],0LL);
      flow.addEdge(k+n,sink,n-col2[k],0LL);
   }
   
   for(int k = 0; k < n; k++){
      for(int j = 0; j < m; j++){
         if(board[k][j] == 0){
            //going on this edge would increase row and decrease col (go from col to row)
            flow.addEdge(j+n,k,1LL,1LL);
         } else {
            flow.addEdge(k,j+n,1LL,1LL);
         }
      }
   }
   
   auto ret = flow.maxflow(source,sink);
   if(ret.first == (long long)(n*m) && r2sum == c2sum){
      cout << ret.second << endl;
   } else {
      cout << "-1\n";
   }
   
   return 0;
}