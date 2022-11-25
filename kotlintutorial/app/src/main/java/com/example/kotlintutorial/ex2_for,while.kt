package com.example.marathon

fun forwhile(){
    val students = arrayListOf<String>("KK", "HB", "SM", "YH")

    for (name in students){
        println("$name")
    }

    for ((index : Int, name : String) in students.withIndex()){
        println("${index+1} 번째 학생의 이름 $name")
    }

    var sum : Int = 0
    for (i : Int in 1..10 step 2){
        sum += i
    }
    println(sum)

    var index : Int = 0
    while(index<10){
        println("current index is : $index")
        index++
    }

}

fun main(){
    forwhile()
}