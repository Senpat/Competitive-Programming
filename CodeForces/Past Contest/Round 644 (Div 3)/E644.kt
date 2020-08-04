fun main(){
	fun check(matrix: Array<String>, n: Int): Boolean{
		for(k in 0 until n-1){
			for(j in 0 until n-1){
				if(matrix[k][j] == '1' && matrix[k+1][j] == '0' && matrix[k][j+1] == '0') return false
			}
		}
		return true
	}

	for(q in 1..readLine()!!.toInt()){
		val n = readLine()!!.toInt()
		val array = Array(n){readLine()!!}
		if(check(array,n)){
			println("YES")
		} else {
			println("NO")
		}
	}


}
