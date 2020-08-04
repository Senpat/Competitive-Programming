fun main(){
	for(q in 1..readLine()!!.toInt()){
		val n = readLine()!!.toInt()
		if(n < 4){
			println(-1)
		}
		else if(n%2 == 1){
			for(k in 1..n step 2)
				print("$k ")

			print("${n-3} ${n-1}")
			for(k in (n-5) downTo 1 step 2)
				print(" $k")
			println()
		} else {
			for(k in 2..n step 2)
				print("$k ")

			print("${n-3} ${n-1}")
			for(k in (n-5) downTo 1 step 2)
				print(" $k")
			println()
		}
	}
}
