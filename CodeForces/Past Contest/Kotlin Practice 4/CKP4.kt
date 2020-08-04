import java.io.*
import java.util.*

fun main(){
    val f = BufferedReader(InputStreamReader(System.`in`))

    val t = f.readLine().toInt()

    for(q in 1..t){
        val s = f.readLine()
        val answer = mutableListOf<String>()

        for(i in 0 until s.length){
            if(s[i] == '0') continue
            answer.add("" + s[i] + "0".repeat(s.length-i-1))
        }

        println(answer.size)
        for(st in answer){
            print("$st ")
        }
        println()

    }
}