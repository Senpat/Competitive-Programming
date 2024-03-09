#include <bits/stdc++.h>
//tutorial
using namespace std;

//https://codeforces.com/contest/316/submission/3873637
/*
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
*/
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
   
   MinCostFlow flow(n*m+2,source,sink);
   
   //source -> even k+j -> odd k+j -> sink
   for(int k = 0; k < n; k++){
      for(int j = 0; j < m; j++){
         int num = k*m+j;
         if((k+j)%2 == 0){
            flow.addEdge(source,num,1,0);
            if(k+1 < n){
               int cost = (board[k][j] == board[k+1][j]) ? 0LL : 1LL;
               flow.addEdge(num,num+m,1LL,cost);
            }
            if(j+1 < m){
               int cost = (board[k][j] == board[k][j+1]) ? 0 : 1;
               flow.addEdge(num,num+1,1,cost);
            }
         } else {
            flow.addEdge(num,sink,1LL,0LL);
            if(k+1 < n){
               int cost = (board[k][j] == board[k+1][j]) ? 0 : 1;
               flow.addEdge(num+m,num,1,cost);
            }
            if(j+1 < m){
               int cost = (board[k][j] == board[k][j+1]) ? 0 : 1;
               flow.addEdge(num+1,num,1,cost);
            }
         }
      }
   }
   
   auto ret = flow.flow();
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