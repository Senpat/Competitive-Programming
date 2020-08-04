import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	var (r,g,b) = f.readLine().split(" ").map{it.toInt()}
	val n = f.readLine().toInt()
	var answer = 0

	while(g <= r){
		answer++
		g*=2
	}

	while(b <= g){
		answer++
		b*=2
	}

	if(answer <= n) println("Yes")
	else println("No")
}
