import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()
		val array = f.readLine().split(" ").map{it.toInt()}

		val nums = mutableListOf<Int>()

		var cur = array[0]
		var curnum = 1

		var i = 1
		while(i < 2*n){
			if(array[i] < cur){
				curnum++
			} else {
				nums.add(curnum)
				cur = array[i]
				curnum = 1
			}
			i++
		}

		nums.add(curnum)

		val dp = Array(nums.size+1){BooleanArray(n+1){false}}
		dp[0][0] = true
		//println(nums.joinToString(" "))
		for(k in 0 until nums.size){
			for(j in 0..n){
				if(!dp[k][j]) continue
				//don't add
				dp[k+1][j] = true

				if(j + nums[k] <= n){
					dp[k+1][j+nums[k]] = true
				}
			}
		}

		if(dp[nums.size][n]){
			println("YES")
		} else {
			println("NO")
		}
	}
}
