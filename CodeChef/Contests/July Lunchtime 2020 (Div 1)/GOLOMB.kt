import java.io.*
import java.util.*
import kotlin.math.*

fun main(roger : Array<String>){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val MOD : Long = 1000000007L

	val MAX = 3000000

	val nums = mutableListOf<Long>()
	nums.add(0L)
	nums.add(1L)
	nums.add(2L)
	nums.add(2L)

	var i = 3

	while(nums.size < MAX+1){
		for(k in 0 until nums[i]){
			nums.add(i.toLong())
			if(nums.size == MAX+1) break
		}

		i++
	}

	val squares = LongArray(MAX+1)
	for(k in 1..MAX){
		val square = (k.toLong()*k.toLong() + MOD)%MOD
		squares[k] = (square*nums[k] + MOD)%MOD
	}

	val pnums = LongArray(MAX+1)
	pnums[0] = 0L
	val psquares = LongArray(MAX+1)
	psquares[0] = 0L
	for(k in 1..MAX){
		pnums[k] = pnums[k-1] + nums[k]
		psquares[k] = psquares[k-1] + squares[k]
	}
	fun solve(l : Long, r : Long) : Long{
		//binary search l and r

		var s = 1
		var e = MAX
		var ansl = 0
		while(s <= e){
			val mid = s + (e-s)/2
			if(pnums[mid] <= l-1){
				ansl = mid
				s = mid+1
			} else {
				e = mid-1
			}
		}

		s = 1
		e = MAX
		var ansr = -1
		while(s <= e){
			val mid = s + (e-s)/2
			if(pnums[mid] <= r){
				ansr = mid
				s = mid+1
			} else {
				e = mid-1
			}
		}


		val ansl1sq = ((ansl+1).toLong()*(ansl+1).toLong() + MOD)%MOD
		val ansr1sq = ((ansr+1).toLong()*(ansr+1).toLong() + MOD)%MOD
		var leftpart = if (ansl == 0) 0L else (psquares[ansl] + (l-1-pnums[ansl])*ansl1sq + MOD)%MOD
		var rightpart = (psquares[ansr] + (r-pnums[ansr])*ansr1sq + MOD)%MOD

		//println("$ansl $leftpart $ansr $rightpart")

		return ((rightpart-leftpart+MOD)%MOD+MOD)%MOD
	}

	for(q in 1..f.readLine().toLong()){
		val (l,r) = f.readLine().split(" ").map{it.toLong()}
		println(solve(l,r))
	}
}
