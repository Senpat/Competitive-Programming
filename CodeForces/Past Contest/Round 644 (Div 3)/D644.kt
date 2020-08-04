import kotlin.math.min
fun main(){
	for(q in 1..readLine()!!.toInt()){
		val (n,m) = readLine()!!.split(" ").map{it.toInt()}
		var i = 2
		var answer = n
		while (i*i <= n){
			if(n%i == 0){
				if(i <= m) answer = min(answer,n/i)
				if(n/i <= m) answer = min(answer,i)
			}
			i++
		}
		if(n<=m) answer = 1
		println(answer)
	}
}
