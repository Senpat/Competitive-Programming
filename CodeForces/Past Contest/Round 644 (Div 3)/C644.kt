import java.util.*

fun main(){
	for(q in 1..readLine()!!.toInt()){
		val n = readLine()!!.toInt()
		val array = readLine()!!.split(" ").map{it.toInt()}.sorted()
		var evensum = 0
		var has1 = false
		for(k in 0 until n){
			if(array[k]%2 == 0) evensum ++
			if(k > 0 && array[k] == array[k-1]+1) has1 = true
		}

		if(evensum % 2 == 0 || has1){
			println("YES")
		} else {
			println("NO")
		}

	}
}
