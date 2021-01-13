import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val MOD = 998244353L

	val (n,m) = f.readLine().split(" ").map{it.toInt()}

	val pq = PriorityQueue<Pair<Int,Int>>(compareBy{it.first})
	val array = Array<Pair<Int,Int>>(m){
		val (a,b) = f.readLine().split(" ").map{it.toInt()}
		pq.add(Pair(a,b))
		Pair(a,b)
	}

	//combine
	val pairs = mutableListOf<Pair<Int,Int>>()
	val firstpair = pq.poll()
	var start = firstpair.first
	var end = firstpair.second

	while(!pq.isEmpty()){
		val p = pq.poll()

		if(p.first > end+1){
			pairs.add(Pair(start,end))
			start = p.first
			end = p.second
		} else {
			end = max(end,p.second)
		}
	}

	pairs.add(Pair(start,end))


	val bits = LongArray(n+1)

	fun update(ii : Int, x : Long){
		var i = ii
		while(i <= n){
			bits[i] = (bits[i] + x + MOD)%MOD
			i += (i and -i)
		}
	}

	fun psum(ii : Int): Long{
		var i = ii
		var sum : Long = 0L
		while(i > 0){
			sum = (sum + bits[i] + MOD)%MOD
			i -= (i and -i)
		}
		return sum
	}

	val dp = LongArray(n+1){0L}
	dp[1] = 1L
	update(1,1L)

	for(k in 2..n){
		for(pair in pairs){
			if(pair.first > k) continue
			dp[k] = (dp[k] + psum(k-pair.first) + MOD)%MOD
			if(k-pair.second-1 >= 1) dp[k] = ((dp[k] - psum(k-pair.second-1))%MOD + MOD)%MOD
		}
		update(k,dp[k])
	}

	println(dp[n])


}
