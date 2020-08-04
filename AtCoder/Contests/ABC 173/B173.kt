import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val n = f.readLine().toInt()

	var ac = 0
	var wa = 0
	var tle = 0
	var re = 0

	for(k in 0 until n){
		val s = f.readLine()
		if(s.equals("AC")) ac++
		if(s.equals("WA")) wa++
		if(s.equals("TLE")) tle++
		if(s.equals("RE")) re++
	}

	println("AC x $ac")
	println("WA x $wa")
	println("TLE x $tle")
	println("RE x $re")
}
