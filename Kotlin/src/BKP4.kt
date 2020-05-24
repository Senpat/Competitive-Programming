import java.io.*
import java.util.*

fun main(){
    val f = BufferedReader(InputStreamReader(System.`in`))

    val t = f.readLine().toInt()

    for(q in 1..t){
        val st1 = StringTokenizer(f.readLine())
        val st2 = StringTokenizer(f.readLine())

        val a1 = st1.nextToken().toInt()
        val b1 = st1.nextToken().toInt()
        val a2 = st2.nextToken().toInt()
        val b2 = st2.nextToken().toInt()

        if((a1 == b2 && b1+a2 == a1) || (b1 == a2 && a1 + b2 == a2) || (a1 == a2 && b1 + b2 == a1) || (b1 == b2 &&   a1 + a2 == b2)) println("Yes") else println("No")

    }
}