//Fancy Fence
import java.io.*
import java.util.*
import kotlin.math.*
//(remove debugging comments)
fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val MOD : Long = 1000000007L
	val inv2 : Long = 500000004L

	val n = f.readLine().toInt()
	val h = f.readLine().split(" ").map{it.toLong()}.toLongArray()
	val w = f.readLine().split(" ").map{it.toLong()}.toLongArray()

	fun c2(x : Long) : Long{
		val prod = (x * (x-1) + MOD)%MOD
		return (prod*inv2 + MOD)%MOD
	}

	fun getself(k : Int) : Long{
		val area = (h[k]*w[k] + MOD)%MOD
		val vert = (c2(h[k])*w[k] + MOD)%MOD
		val hori = (c2(w[k])*h[k] + MOD)%MOD
		val all = ((c2(area)-vert-hori)%MOD+MOD)%MOD

		return (area + vert + hori + all*inv2 + MOD)%MOD
	}

	fun sum1n(x : Long) : Long{
		val prod = (x*(x+1) + MOD)%MOD
		return (prod*inv2 + MOD)%MOD
	}

	var answer = 0L

	var canextend = 0L
	var canextendsum = 0L
	val stk = Stack<Pair<Long,Long>>() 					//stores extenders. <height, index>

	var sumw = 0L
	for(k in 0 until n){
		answer = (answer + getself(k) + MOD)%MOD

		//calculate how much you extend
		var curpair = Pair(h[k],sumw)
		while(!stk.isEmpty() && stk.peek().first >= h[k]){
			val p = stk.pop()
			val ceminus = ((curpair.second-p.second)*(sum1n(p.first)-sum1n(h[k]))%MOD + MOD)%MOD
			canextend = ((canextend - ceminus)%MOD+MOD)%MOD
			curpair = Pair(h[k],p.second)
		}

		answer = (answer + canextend*w[k] + MOD)%MOD

		canextendsum = sum1n(h[k])
		canextend = (canextend + canextendsum*w[k] + MOD)%MOD
		stk.add(curpair)

		sumw = (sumw + w[k] + MOD)%MOD
	}

	println(answer)
}
