import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val MOD : Long= 1000000007L
	val MAX = 200005
	val pow2 = LongArray(MAX)
	pow2[0] = 1L
	for(k in 1 until MAX){
		pow2[k] = (pow2[k-1]*2L + MOD)%MOD
	}

	//INTERVALS GO FROM L TO R-1
	val n = f.readLine().toInt()
	val all = Array<Interval>(n){j ->
		val (a,b,c) = f.readLine().split(" ").map{it.toInt()}
		if(a+b == n-1) Interval(0,n,c)
		else Interval(j-a,j+b+1,c)
	}

	fun conv(trip : Interval, flip : Boolean) : Interval{
		if(trip.l >= 0 && trip.r <= n) return trip
		if(trip.l < 0){
			if(flip){
				return Interval(trip.r,n+trip.l,1-trip.i)
			} else {
				return Interval(trip.r,n+trip.l,trip.i)
			}
		} else {
			//trip.r > n)
			if(flip){
				return Interval(trip.r-n,trip.l,1-trip.i)
			} else {
				return Interval(trip.r-n,trip.l,trip.i)
			}
		}
	}

	fun checkpossible(intervals : MutableList<Interval>) : Boolean{
		val pq = PriorityQueue<Interval>(compareBy({it.l},{it.r}))
		for(interval in intervals) pq.add(interval)

		while(!pq.isEmpty()){
			var cur = pq.poll()
			while(!pq.isEmpty() && pq.peek().l == cur.l){
				val newint = pq.poll()
				if(cur.r == newint.r){
					if(cur.i != newint.i) return false
				} else {
					if(cur.i == newint.i){
						pq.add(Interval(min(cur.r,newint.r),max(cur.r,newint.r),0))
					} else {
						pq.add(Interval(min(cur.r,newint.r),max(cur.r,newint.r),1))
					}
					cur = newint
				}
			}
		}

		return true
	}

	var answer : Long = 0L

	//DO EVENS
	//assume sum of full interval is even
	val evenin = mutableListOf<Interval>()
	for(interval in all){
		evenin.add(conv(interval,interval.i != 0))
	}
	evenin.add(Interval(0,n,0))

	if(checkpossible(evenin)){
		val ends = HashSet<Int>()
		for(interval in evenin){
			ends.add(interval.l)
			ends.add(interval.r)
		}

		answer = (answer + pow2[n-(ends.size-1)] + MOD)%MOD
	}

	//DO ODDS
	//assume sum of full interval is even
	val oddin = mutableListOf<Interval>()
	for(interval in all){
		oddin.add(conv(interval,interval.i != 1))
	}
	oddin.add(Interval(0,n,1))

	if(checkpossible(oddin)){
		val ends = HashSet<Int>()
		for(interval in oddin){
			ends.add(interval.l)
			ends.add(interval.r)
		}

		answer = (answer + pow2[n-(ends.size-1)] + MOD)%MOD
	}

	println(answer)

}
data class Interval(val l : Int, val r : Int, val i : Int)
