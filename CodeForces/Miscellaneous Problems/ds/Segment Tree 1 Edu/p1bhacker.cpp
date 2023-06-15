#include <bits/stdc++.h>
#define int long long
#define endl '\n'
using namespace std;
void solve()
{
    int n=100000,m=100000;
    cout<<n<<' '<<m<<endl;
    for(int i=0;i<n;i++)
    {
        cout<<1;
        if(i!=n-1)cout<<' ';
    }
    cout<<endl;
    for(int i=0;i<100000;i++)
    {
        cout<<2<<' '<<0<<' '<<n<<endl;
    }
}
int32_t main()
{
    ios_base::sync_with_stdio(false);
    cout.tie(nullptr);cin.tie(nullptr);
    int ut=1;
//    cin>>ut;
    while(ut--)solve();
}