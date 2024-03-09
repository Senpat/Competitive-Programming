#include <bits/stdc++.h>
//tutorial
using namespace std;


//from kactl https://github.com/kth-competitive-programming/kactl/blob/main/content/graph/MinCostMaxFlow.h
//#include <bits/extc++.h>
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
   
   //cost is cost per unit of flow
	void addEdge(int from, int to, long long cap, long long cost) {
      //cout << from << " " << to << " " << cap << " " << cost << endl;
		if (from == to) return;
		ed[from].push_back(Edge{ from,to,(int)ed[to].size(),cap,cost,0 ,false});
		ed[to].push_back(Edge{ to,from,(int)ed[from].size()-1,0,-cost,0 ,true});
	}

	void path(int s, int t) {
		fill(seen.begin(),seen.end(), 0);
		fill(dist.begin(),dist.end(), INF);
		dist[s] = 0; long long di;
      
      
		priority_queue<pair<long long, int>> q;
		q.push({ 0, s });

		while (!q.empty()) {
			s = q.top().second; q.pop();
         if(seen[s]) continue;
			seen[s] = 1; di = dist[s] + pi[s];
			for (Edge& e : ed[s]) if (!seen[e.to]) {
				long long val = di - pi[e.to] + e.cost;
				if (e.cap - e.flow > 0 && val < dist[e.to]) {
					dist[e.to] = val;
					par[e.to] = &e;
				   q.push({ -dist[e.to], e.to });
				}
			}
		}
      
      /*
      while(true){
         s = -1;
         for(int k = 0; k < N; k++){
            if(!seen[k]){
               if(s == -1) s = k;
               else if(dist[s] > dist[k]) s = k;
            }
         }
         cout << s << endl;
         if(s == -1) break;
         seen[s] = 1; di = dist[s] + pi[s];
			for (Edge& e : ed[s]) if (!seen[e.to]) {
				long long val = di - pi[e.to] + e.cost;
				if (e.cap - e.flow > 0 && val < dist[e.to]) {
					dist[e.to] = val;
					par[e.to] = &e;
				}
			}
      }*/
		for(int i = 0; i < N; i++) pi[i] = min(pi[i] + dist[i], INF);
	}

	pair<long long, long long> maxflow(int s, int t) {
		long long totflow = 0, totcost = 0;
		while (path(s,t), seen[t]) {
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
   
   //# of shoes
   int s = n*m/2;
   
   vector<vector<int>> board(n,vector<int>(m));
   vector<vector<pair<int,int>>> locs(s+1,vector<pair<int,int>>());
   
   for(int k = 0; k < n; k++){
      for(int j = 0; j < m; j++){
         cin >> board[k][j];
      }
   }
   
   
   //number of node is k*m + j, nodes fill [0,n*m-1]
   int source = n*m;
   int sink = n*m+1;
   
   MCMF flow(n*m+2);
   
   //source -> even k+j -> odd k+j -> sink
   for(int k = 0; k < n; k++){
      for(int j = 0; j < m; j++){
         int num = k*m+j;
         if((k+j)%2 == 0){
            flow.addEdge(source,num,1LL,0LL);
            if(k+1 < n){
               long long cost = (board[k][j] == board[k+1][j]) ? 0LL : 1LL;
               flow.addEdge(num,num+m,1LL,cost);
            }
            if(j+1 < m){
               long long cost = (board[k][j] == board[k][j+1]) ? 0LL : 1LL;
               flow.addEdge(num,num+1,1LL,cost);
            }
         } else {
            flow.addEdge(num,sink,1LL,0LL);
            if(k+1 < n){
               long long cost = (board[k][j] == board[k+1][j]) ? 0LL : 1LL;
               flow.addEdge(num+m,num,1LL,cost);
            }
            if(j+1 < m){
               long long cost = (board[k][j] == board[k][j+1]) ? 0LL : 1LL;
               flow.addEdge(num+1,num,1LL,cost);
            }
         }
      }
   }
   
   auto ret = flow.maxflow(source,sink);
   //cout << ret.first << endl;
   cout << ret.second << endl;
   /*
   for(int k = 0; k < n*m+2; k++){
      for(MCMF::Edge e : flow.ed[k]){
         if(e.flow > 0 && !e.isbackedge){
            cout << e.from << " " << e.to << endl;
         }
      }
   }
   */
   
   return 0;
}