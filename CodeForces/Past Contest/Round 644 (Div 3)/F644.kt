import java.util.*

fun main(){

	fun calc(array: Array<Array<Char>>, n: Int, m: Int): String{
		for(k in 0 until n){
			//pretend k is the answer string
			for(j in 0 until m){
				for(h in 0 until n){
					val s = array[k].map{it}.toCharArray()
					s[j] = array[h][j]

					//check
					var fail = false
					for(g in 0 until n){
						var dif = 0
						for(f in 0 until m){
							if(array[g][f] != s[f]) dif++
						}
						if(dif >= 2){
							fail = true
							break
						}
					}
					if(!fail) return s.joinToString("")

				}
			}

		}
		return "-1"
	}



	for(q in 1..readLine()!!.toInt()){
		val (n,m) = readLine()!!.split(" ").map{it.toInt()}
		val array = Array(n){readLine()!!.toCharArray()}

		println(calc(array,n,m))
	}
}
