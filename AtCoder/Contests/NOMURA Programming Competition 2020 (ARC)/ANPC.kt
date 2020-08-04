import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	var (h1, m1, h2, m2, m) = f.readLine().split(" ").map{it.toInt()}

	var answer = 0
	if(m2 > m1) answer = m2-m1
	if(m1 > m2){
		answer = 60-(m1-m2)
		h2--
	}

	answer += 60*(h2-h1)
	answer -= m
	println(answer)
}
