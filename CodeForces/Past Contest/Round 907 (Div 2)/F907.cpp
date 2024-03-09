#include <bits/stdc++.h>
#ifdef LOCAL16
#include "debug.h"
#else
#define debug(...)
#endif
 
#define endln '\n'
#define print(x) cout << (x) << endln
//eddie's sol with better segtree
using namespace std;
using ll = long long;
const ll INF = INT_MAX - 1;
const ll LINF = LLONG_MAX - 1;
 
const ll maxN = 5e5 + 5;
vector<ll> connections[maxN];
int tim = 0;
ll add[maxN];
pair<int,int> timeio[maxN];
ll sz = 1;
 
struct Query
{
    ll t;
    ll v, x, ti;
};

struct Op{
   int type;  //1 for assign, 2 for add
   ll value;
};

class Segtree{
   private:
   vector<Op> ops;
   vector<ll> a;
   
   public:
   Segtree(int size){
      ops = vector<Op>(4*size);
      a = vector<ll>(4*size,0LL);
      for(int k = 0; k < 4*size; k++){
         ops[k] = {2,0LL};
      }
   }
   
   Op combineOp(Op prev, Op op){
      if(op.type == 1){
         return op;
      } else {
         return {prev.type,prev.value + op.value};
      }
   }
      
   void applyOp(int v, Op op, long len){
      
      if(op.type == 1){
         a[v] = len*op.value;
      } else {
         a[v] += len*op.value;
      }
      
      ops[v] = combineOp(ops[v],op);
   }
      
   void propagate(int v, int l, int r){
      int mid = (l+r)/2;
      ll lenl = (ll)(mid-l+1);
      ll lenr = (ll)(r-(mid+1)+1);
      
      applyOp(2*v+1,ops[v],lenl);
      applyOp(2*v+2,ops[v],lenr);
      ops[v] = {2,0LL};
   }
      
   void update(int v, int l, int r, int ql, int qr, Op op){
      if(l >= ql && r <= qr){
         ll len = (ll)(r-l+1);
         applyOp(v,op,len);
      } else if(r < ql || l > qr){
         return;
      } else {
         int mid = (l+r)/2;
         
         propagate(v,l,r);
         
         update(2*v+1,l,mid,ql,qr,op);
         update(2*v+2,mid+1,r,ql,qr,op);
         
         a[v] = a[2*v+1] + a[2*v+2];
      }
         
   }
      
   ll range_sum(int v, int l, int r, int ql, int qr){
      if(l >= ql && r <= qr){
         return a[v];
      } else if(r < ql || l > qr){
         return 0LL;
      } else {
         int mid = (l+r)/2;
         
         propagate(v,l,r);
         
         return range_sum(2*v+1,l,mid,ql,qr) + range_sum(2*v+2,mid+1,r,ql,qr);
      }
   }
};

void dfs(ll node)
{
    timeio[node].first = tim;
    tim++;
    for (ll i : connections[node])
    {
        dfs(i);
    }
    timeio[node].second = tim;
    //tim++;
}
 
void solve()
{
    ll q;
    cin >> q;
    tim = 1;
    sz = 1;
    for (ll i = 0; i <= q + 3; i++)
    {
        timeio[i] = {0, 0};
        connections[i].clear();
    }
    vector<Query> v2;
    for (ll i = 0; i < q; i++)
    {
        ll t;
        cin >> t;
        if (t == 1)
        {
            ll v;
            cin >> v;
            sz++;
            connections[v].push_back(sz);
            v2.push_back({1, sz, 0, i});
        }
        else
        {
            ll v, x;
            cin >> v >> x;
            v2.push_back({2, v, x, i});
        }
    }
    dfs(1);
    Segtree segtree(sz+1);
    for (Query x : v2)
    {
        debug(x.t, x.v, x.x);
        if (x.t == 1)
        {
            Op op = {1,0LL};
            segtree.update(0,0,sz,timeio[x.v].first, timeio[x.v].second-1, op);
        }
        else
        {
            Op op = {2, x.x};
            segtree.update(0,0,sz,timeio[x.v].first, timeio[x.v].second-1, op);
        }
    }
    for (ll i = 1; i <= sz; i++)
    {
        cout << segtree.range_sum(0,0,sz,timeio[i].first, timeio[i].first) << " ";
    }
    cout << endln;
}
 
signed main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    ll t;
    cin >> t;
    while (t--)
    {
        solve();
    }
}