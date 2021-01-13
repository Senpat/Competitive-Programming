import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val E = 0.00000005

	fun round3(x : Double) : String{
		val x1000 = round(x*1000.0).toInt()
		val s = x1000.toString()
		return s.substring(0,s.length-3) + "." + s.substring(s.length-3)
	}

	for(q in 1..f.readLine().toInt()){
		val (useless,n,m) = f.readLine().split(" ").map{it.toInt()}

		val angle = 2*PI/(n.toDouble())

		val dists = DoubleArray(m+1){0.0}
		dists[0] = 1.0

		//val x = (1.0-cos(angle))/2.0
		//dists[1] = (-sqrt(x)-x)/(x-1.0)

		dists[1] = -sin(angle/2.0)/(sin(angle/2.0)-1.0)
		var legs = DoubleArray(n+1){0.0}
		legs[1] = dists[0]+dists[1]
		for(k in 2..m){
			val a = dists[k-1]+dists[k-2]

			//find dists[k]
			var l = dists[k-1]
			var r = 1000000000000000000.0					//1e18
			var ans = -1.0

			for(j in 0 until 1000){
				val mid = (l+r)/2.0

				//get length of leg
				val leg = sqrt((dists[k-1]+mid)*(dists[k-1]+mid) - dists[k-1]*dists[k-1]) + sqrt(a*a-dists[k-1]*dists[k-1]) + legs[k-2]
				val curangle = acos(1.0-2.0*mid*mid/(leg*leg))
				//println(curangle)
				if(curangle < angle-E){
					l = mid
				} else if(curangle > angle+E){
					r = mid
				} else {
					ans = mid
					break
				}
			}

			dists[k] = ans
			legs[k] = sqrt((dists[k-1]+ans)*(dists[k-1]+ans) - dists[k-1]*dists[k-1]) + sqrt(a*a-dists[k-1]*dists[k-1]) + legs[k-2]
		}
		//println(dists.joinToString(" "))
		val answer = 2.0*dists[m]*(PI+n.toDouble())
		println("$q ${round3(dists[m])} ${round3(answer)}")
	}
}
