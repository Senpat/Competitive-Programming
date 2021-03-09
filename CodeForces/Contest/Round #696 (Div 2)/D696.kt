import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()
		val array = f.readLine().split(" ").map{it.toInt()}.toIntArray()

		var plus = 0
		var minus = 0

		for(k in 0 until n){
			if((n-1-k)%2 == 0) plus += array[k]
			else minus += array[k]
		}

		fun check() : Boolean{
			//println(array.joinToString(" "))
			var cur = array[1]-array[0]
			if(cur < 0) return false
			for(k in 2 until n){
				cur = array[k]-cur
				if(cur < 0) return false
			}
			return cur==0
		}

		if(plus == minus){
			if(check()) println("YES")
			else println("NO")
		} else {
			var found = false
			val switches = mutableListOf<Pair<Int,Int>>()
			for(k in n-1 downTo 0 step 2){
				//pluses
				//check if switching is worth
				if(k < n-1 && (array[k]-array[k+1])*2 == plus-minus){
					switches.add(Pair(k,k+1))
				}

				if(k > 0 && (array[k]-array[k-1])*2 == plus-minus){
					switches.add(Pair(k,k-1))
				}
			}
			switches.shuffle()
			for(k in 0 until min(30,switches.size)){
				var temp = array[switches[k].first]
				array[switches[k].first] = array[switches[k].second]
				array[switches[k].second] = temp
				if(check()){
					found = true
					break
				}
				temp = array[switches[k].first]
				array[switches[k].first] = array[switches[k].second]
				array[switches[k].second] = temp
			}

			if(found){
				println("YES")
			} else {
				println("NO")
			}
		}


	}
}
