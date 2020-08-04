import java.io.*
import java.util.*

fun main(){
	fun check(array : Array<Pair<Int,Int>>, point : Int): Boolean{
		var one = false

		for(p in array){
			if(point <= p.second && point >= p.first){
				if(one) return false
				one = true
			}
		}
		return one
	}

	fun solve(array: Array<Pair<Int,Int>>): Int{
		for(p in array){
			if(check(array,p.first-1)) return p.first-1
			if(check(array,p.first)) return p.first
			if(check(array,p.second)) return p.second
			if(check(array,p.second+1)) return p.second+1
		}

		return -1
	}

	val f = BufferedReader(InputStreamReader(System.`in`))



	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()

		val array = Array(n){
			val (a,b) = f.readLine().split(" ").map{it.toInt()}
			Pair(a,b)
		}

		println(solve(array))


	}


}
