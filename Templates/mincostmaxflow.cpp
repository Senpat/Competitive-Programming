#include <bits/stdc++.h>

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

//hungarian algorithm
/*
from https://github.com/bqi343/cp-notebook/blob/master/Implementations/content/graphs%20(12)/Matching/Hungarian.h
 * Description: Given J jobs and W workers (J <= W), computes the minimum cost
 * to assign each prefix of jobs to distinct workers.
 *
 * @param C a matrix of dimensions JxW such that C[j][w] = cost to assign j-th
 * job to w-th worker (possibly negative)
 *
 * @return a vector of length J, with the j-th entry equaling the minimum cost
 * to assign the first (j+1) jobs to distinct workers
 * for all jobs return .back()
 * Time: O(J^2W)
 */

bool ckmin(long long& a, const long long& b){
   return b < a ? a=b, 1 : 0;          //set a = min(a,b)
}

vector<long long> hungarian(const vector<vector<long long>> &C) {
	const int J = (int)size(C), W = (int)size(C[0]);
	//assert(J <= W);
	/// job[w] = job assigned to w-th worker, or -1 if no job assigned
	/// note: a W-th worker was added for convenience
	vector<int> job(W + 1, -1);
	vector<long long> ys(J), yt(W + 1); /// potentials
	/// -yt[W] will equal the sum of all deltas
	vector<long long> answers;
	const long long inf = numeric_limits<long long>::max();
	for (int j_cur = 0; j_cur < J; ++j_cur) { /// assign j_cur-th job
		int w_cur = W;
		job[w_cur] = j_cur;
		/// min reduced cost over edges from Z to worker w
		vector<long long> min_to(W + 1, inf);
		vector<int> prv(W + 1, -1); /// previous worker on alternating path
		vector<bool> in_Z(W + 1);   /// whether worker is in Z
		while (job[w_cur] != -1) {  /// runs at most j_cur + 1 times
			in_Z[w_cur] = true;
			const int j = job[w_cur];
			long long delta = inf;
			int w_next;
			for (int w = 0; w < W; ++w) {
				if (!in_Z[w]) {
					if (ckmin(min_to[w], C[j][w] - ys[j] - yt[w]))
						prv[w] = w_cur;
					if (ckmin(delta, min_to[w])) w_next = w;
				}
			}
			/// delta will always be non-negative,
			/// except possibly during the first time this loop runs
			/// if any entries of C[j_cur] are negative
			for (int w = 0; w <= W; ++w) {
				if (in_Z[w]) ys[job[w]] += delta, yt[w] -= delta;
				else min_to[w] -= delta;
			}
			w_cur = w_next;
		}
		/// update assignments along alternating path
		for (int w; w_cur != -1; w_cur = w) job[w_cur] = job[w = prv[w_cur]];
		answers.push_back(-yt[W]);
	}
	return answers;
}

//above two TLEs on https://codeforces.com/contest/316/problem/C2
//this may be faster
struct MinCostFlow {
    MinCostFlow(int _n,int _s,int _t) : n(_n), s(_s), t(_t), fl(0), cost(0) {
        first.resize(n,-1);
        dist.resize(n);
        prev.resize(n);
        a.reserve(8*n);
    };
    struct Edge {
        Edge(int _to,long long _cap,long long _cost,int _next) : to(_to), cap(_cap), cost(_cost), next(_next) {};
        int to,next;
        long long cap,cost;
    };
    void addEdge(int u,int v,long long cap,long long cost) {
        a.push_back(Edge(v,cap,cost,first[u])); first[u]=a.size()-1;
        a.push_back(Edge(u,0,-cost,first[v])); first[v]=a.size()-1;
    }
    bool augment() {
        dist.assign(n,inf);
        dist[s]=0LL;
        queue< pair <long long,int> > q; q.push(make_pair(0LL,s));
        while(q.size()) {
            pair <long long,int> top=q.front(); q.pop();
            if (dist[top.second]!=-top.first) continue;
            int u=top.second;
            for(int e=first[u];e!=-1;e=a[e].next) {
                int v=a[e].to;
                long long ndist=-top.first+a[e].cost;
                if (a[e].cap>0LL&&dist[v]>ndist) {
                    dist[v]=ndist;
                    q.push(make_pair(-ndist,v));
                    prev[v]=e;
                }
            }
        }
        return dist[t]!=inf;
    }
    pair <int,int> flow() {
        while(augment()) {
            int cur=t;
            long long size=inf;
            while(cur!=s) {
                int e=prev[cur];
                size=min(size,a[e].cap);
                cur=a[e^1].to;
            }
            fl+=size; cost+=(dist[t])*size;
            cur=t;
            while(cur!=s) {
                int e=prev[cur];
                a[e].cap-=size;
                a[e^1].cap+=size;
                cur=a[e^1].to;
            }
        }
        return make_pair(fl,cost);
    }
    int n,s,t;
    long long fl,cost;
    const long long inf=LLONG_MAX/2LL;
    vector <int> first,prev;
    vector <long long> dist;
    vector <Edge> a;
};


//same thing but with ints (even faster)
struct MinCostFlow {
    MinCostFlow(int _n,int _s,int _t) : n(_n), s(_s), t(_t), fl(0), cost(0) {
        first.resize(n,-1);
        dist.resize(n);
        prev.resize(n);
        a.reserve(8*n);
    };
    struct Edge {
        Edge(int _to,int _cap,int _cost,int _next) : to(_to), cap(_cap), cost(_cost), next(_next) {};
        int to,next,cap,cost;
    };
    void addEdge(int u,int v,int cap,int cost) {
        a.push_back(Edge(v,cap,cost,first[u])); first[u]=a.size()-1;
        a.push_back(Edge(u,0,-cost,first[v])); first[v]=a.size()-1;
    }
    bool augment() {
        dist.assign(n,inf);
        dist[s]=0;
        queue < pair <int,int> > q; q.push(make_pair(0,s));
        while(q.size()) {
            pair <int,int> top=q.front(); q.pop();
            if (dist[top.second]!=-top.first) continue;
            int u=top.second;
            for(int e=first[u];e!=-1;e=a[e].next) {
                int v=a[e].to,ndist=-top.first+a[e].cost;
                if (a[e].cap>0&&dist[v]>ndist) {
                    dist[v]=ndist;
                    q.push(make_pair(-ndist,v));
                    prev[v]=e;
                }
            }
        }
        return dist[t]!=inf;
    }
    pair <int,int> flow() {
        while(augment()) {
            int cur=t,size=inf;
            while(cur!=s) {
                int e=prev[cur];
                size=min(size,a[e].cap);
                cur=a[e^1].to;
            }
            fl+=size; cost+=(dist[t])*size;
            cur=t;
            while(cur!=s) {
                int e=prev[cur];
                a[e].cap-=size;
                a[e^1].cap+=size;
                cur=a[e^1].to;
            }
        }
        return make_pair(fl,cost);
    }
    int n,s,t,fl,cost;
    const int inf=1000000009;
    vector <int> first,prev,dist;
    vector <Edge> a;
};





int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   
   
   return 0;
}