import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val s = f.readLine()
	if(s[s.length-1] == 's') println("${s}es")
	else println("${s}s")
}
