#include <bits/stdc++.h>
//translated from CSM.java, upsolve
//file input
using namespace std;

struct Edge{
   int to;
   long long w;
   int i;
};

struct FullEdge{
   int a;
   int b;
   long long w;
};

struct Query{
   int a;
   int b;
};

int P = 20;
long long mask = (1LL << P)-1;
struct Point{
   int a;
   int b;
   long long pl;
   Point(int c, int d){
      a = min(c,d);
      b = max(c,d);
      pl = (((long long)a) << P) + (long long)b;
   }
};

long long getlong(int a, int b){
   long long x = (long long)min(a,b);
   long long y = (long long)max(a,b);
   
   return (x << P) + y;
}

Point fromlong(long long pl){
   return Point((int)(pl & mask),(int)(pl >> P));
}

int n;

vector<vector<Edge>> adj;
unordered_map<long long, long long> dists;

int compnum;
vector<int> components;
int extra_edge;

long long getdist(int a, int b){
   if(a == b) 
      return 0LL;
   return dists[getlong(a,b)];
}

void setdist(Point p, long long d){
   dists[p.pl] = d;
}

//lca and dist stuff with weighted edges
const int N = 100005;
const int MAXD = 17;
int lca[N][MAXD];
int depth[N];
long long rootdist[N];

void initLCA(){
   for(int d = 1; d < MAXD; d++) {
      for(int i = 1; i < n+1; i++) {
         lca[i][d] = lca[lca[i][d-1]][d-1];
      }
   }
}

int getlca(int a, int b){
   if(depth[a] < depth[b]){
      //swap a and b
      int temp = a;
      a = b;
      b = temp;
   }
      
   for(int i = MAXD-1; i >= 0; i--){
      if (((depth[a]-depth[b]) & (1<<i)) != 0){
         //if(depth[a] - (1<<i) > depth[b]){
         a = lca[a][i];
      }
   }
   if(a==b) 
      return a;
      
   for(int i = MAXD-1; i >= 0; i--){
      if(lca[a][i] != lca[b][i]){
         a = lca[a][i];
         b = lca[b][i];
      }
   }
   return lca[a][0];
}

long long dist(int a, int b){
   return rootdist[a] + rootdist[b] - 2*rootdist[getlca(a,b)];
}

void initdfs(int v, int p){
   components[v] = compnum;
   
   for(Edge e : adj[v]){
      if(e.to == p) 
         continue;
      if(components[e.to] != -1){
            //this is the extra edge
         extra_edge = e.i;
         continue;
      }
      depth[e.to] = depth[v]+1;
      lca[e.to][0] = v;
      rootdist[e.to] = rootdist[v] + e.w;
      initdfs(e.to,v);
   }
}


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   ifstream fin ("csmtest.in");
   ofstream fout ("csmtest.out");
   
   fin >> n;
   
   adj = vector<vector<Edge>>(n+1,vector<Edge>());
   
   int edge_index = 0;
   vector<FullEdge> fulledges;
   for(int k = 1; k <= n; k++){
      int to;
      long long w;
      fin >> to >> w;
      
      adj[k].push_back({to,w,edge_index});
      adj[to].push_back({k,w,edge_index});
      
      fulledges.push_back({k,to,w});
      edge_index++;
   }
   
   int L;
   fin >> L;
   
   vector<FullEdge> additional(L);
   for(int k = 0; k < L; k++){
      int a,b;
      long long w;
      fin >> a >> b >> w;
      
      additional[k] = {a,b,w};
   }
   
   int q;
   fin >> q;
   vector<Query> queries(q);
   unordered_set<long long> allpoints;
   for(int k = 0; k < q; k++){
      int a,b;
      fin >> a >> b;
      
      queries[k] = {a,b};
      
      allpoints.insert(getlong(a,b));
   }
   
   cout << "read input" << endl;
   
   dists = unordered_map<long long,long long>();
   
   compnum = 0;
   components = vector<int>(n+1);
   fill(components.begin(),components.end(),-1);
   vector<int> comp_extra_edges;
   for(int k = 1; k <= n; k++){
      if(components[k] == -1){
         extra_edge = -1;
         depth[k] = 0;
         initdfs(k,-1);
         
         comp_extra_edges.push_back(extra_edge);
         
         compnum++;
      }
   }
   
   initLCA();
   
   vector<vector<Point>> comp_points(compnum,vector<Point>());
   
   for(int v = 1; v <= n; v++){
      for(int k = 0; k < L; k++){
         if(v != additional[k].a) allpoints.insert(getlong(v,additional[k].a));
         if(v != additional[k].b) allpoints.insert(getlong(v,additional[k].b));
      }
   }
   cout << "finish precomp" << endl;
   //add all points to dists (should be all points needed to query)
   for(long long pl : allpoints){
      dists[pl] = LLONG_MAX;
      
      Point p = fromlong(pl);
      //cout << pl << " " << p.a << " " << p.b << endl;
      //cout << components[p.a] << " " << components[p.b] << endl;
      if(components[p.a] == components[p.b]){
         comp_points[components[p.a]].push_back(p);
      }
   }
   cout << "add all points to dists" << endl;
   //incorporate extra edge
   for(int comp = 0; comp < compnum; comp++){
      //cout << comp_extra_edges[comp] << endl;
      FullEdge fe = fulledges[comp_extra_edges[comp]];
      for(Point p : comp_points[comp]){
         long long curdist = dist(p.a,p.b);
         //use the extra edge
         curdist = min(curdist,dist(p.a,fe.a) + fe.w + dist(fe.b,p.b));
         curdist = min(curdist,dist(p.a,fe.b) + fe.w + dist(fe.a,p.b));
         
         setdist(p,curdist);
      }
   }
   cout << "incorporate extra edge" << endl;
   //incorporate additional edges
   for(int k = 0; k < L; k++){
      for(long long pl : allpoints){
         long long startdist = dists[pl];
         long long curdist = startdist;
         long long d1,d2;
         int pa = (int)(pl & mask);
         int pb = (int)(pl >> P);
         d1 = getdist(pa,additional[k].a);
         d2 = getdist(additional[k].b,pb);
         if(d1 < LLONG_MAX && d2 < LLONG_MAX){
            curdist = min(curdist,d1+d2+additional[k].w);
         }
         d1 = getdist(pa,additional[k].b);
         d2 = getdist(additional[k].a,pb);
         if(d1 < LLONG_MAX && d2 < LLONG_MAX){
            curdist = min(curdist,d1+d2+additional[k].w);
         }
         
         if(curdist < startdist){
            dists[pl] = curdist;
         }
         
      }
   }
   cout << "incorporate additional edges" << endl;
   for(int t = 0; t < q; t++){
      long long curdist = getdist(queries[t].a,queries[t].b);
      if(curdist == LLONG_MAX){
         cout << -1 << endl;
      } else {
         cout << curdist << endl;
      }
   }
   
   return 0;
}