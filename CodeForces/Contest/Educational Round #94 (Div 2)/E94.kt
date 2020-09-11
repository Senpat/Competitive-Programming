import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val n = f.readLine().toInt()
	val array = f.readLine().split(" ").map{it.toInt()}.toIntArray()

	fun calc(l : Int, r : Int, curmin : Int) : Int{
		//println("$l $r")
		if(r < l) return 0
		if(r==l){
			if(array[r] == 0) return 0
			return 1
		}

		//subtract by min
		//find min and occurances of min

		var min = Int.MAX_VALUE
		var mininds = mutableListOf<Int>()
		for(k in l..r){
			if(array[k] == min){
				mininds.add(k)
			} else if(min > array[k]){
				min = array[k]
				mininds = mutableListOf<Int>()
				mininds.add(k)
			}
		}

		var ret = min-curmin

		if(mininds[0] > l){
			ret += calc(l,mininds[0]-1,min)
		}

		for(k in 1 until mininds.size){
			ret += calc(mininds[k-1]+1,mininds[k]-1,min)
		}

		if(mininds[mininds.size-1] < r){
			ret += calc(mininds[mininds.size-1]+1,r,min)
		}


		return min(r-l+1,ret)
	}

	val answer = calc(0,n-1,0)
	println(answer)


}
