package com.example.marathon

fun main(){
    helloworld()
    println(getandreturn(4, 5))
    println(valvar())
}
//  1. 함수
fun helloworld() : Unit{
//    아무것도 리턴 안하면 unit 생략해도 된다.
    println("helloworld")
}

fun getandreturn(a : Int, b : Int) : Int{
//    자바와 다르게 int 가 뒤에옴.
    return a+b
}

//  2. val vs var
//  val, value, 변할 수 없음
//  var, variable, 변할 수 있음.

fun valvar(){
    val a : Int = 10
    var b : Int = 20

//    a = 100
    b = 100
    val c = 100
    val d = "HB"
    val e : String = "JW"
//    알아서 추론해서 자료형을 붙여준다.


}