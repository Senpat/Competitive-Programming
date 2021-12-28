import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val (n,m) = f.readLine().split(" ").map{it.toInt()}
	val input = f.readLine().toCharArray()

	val array = IntArray(n){j ->
		if(input[j] == '?') -1
		else input[j]-'a'
	}

	fun check(x : Int) : Boolean{
		//precomp
		val precomp = Array(m){IntArray(n){Int.MAX_VALUE}}
		for(k in 0 until m){

			var streak = 0
			for(j in n-1 downTo 0){
				if(array[j] == -1 || array[j] == k){
					streak++
				} else {
					streak = 0
				}

				if(streak >= x){
					precomp[k][j] = j+x
				} else {
					if(j < n-1){
						precomp[k][j] = precomp[k][j+1]
					}
				}
			}

		}

		val dp = IntArray(1 shl m){Int.MAX_VALUE}
		dp[0] = 0

		val q = LinkedList<Int>()
		q.add(0)
		val added = HashSet<Int>()
		added.add(0)

		while(!q.isEmpty()){
			val mask = q.poll()
			if(dp[mask] == Int.MAX_VALUE || dp[mask] == n) continue

			for(i in 0 until m){
				val shift = (1 shl i)

				if(mask and shift != 0) continue

				val newd = precomp[i][dp[mask]]
				val newmask = mask or shift
				dp[newmask] = min(dp[newmask],newd)
				if(!added.contains(newmask)){
					added.add(newmask)
					q.add(newmask)
				}

			}
		}

		return dp[(1 shl m)-1] <= n

	}

	var l = 1
	var r = n
	var ans = 0

	while(l <= r){
		val mid = l + (r-l)/2
		if(check(mid)){
			l = mid+1
			ans = mid
		} else {
			r = mid-1
		}
	}

	println(ans)
}
