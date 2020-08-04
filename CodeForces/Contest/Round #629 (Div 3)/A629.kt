//kotlin practice
fun main(){
	for(q in 1..readLine()!!.toInt()){
		val (n,m) = readLine()!!.split(" ").map{it.toInt()}
		if(n%m == 0) println("0")
		else{
			val d = n/m
			println(m*(d+1)-n)
		}
	}
}
