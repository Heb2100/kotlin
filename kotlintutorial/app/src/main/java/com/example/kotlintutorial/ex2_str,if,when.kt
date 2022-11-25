package com.example.marathon

fun main(){
    string()
    println("this is maxby ${maxby(1, 2)}")
    println("this is maxby2 ${maxby2(1, 2)}")
    checknum(1)
}

//  3. String template

fun string(){
    val name = "hyunbok"
    val lastname = "Lee"
    println("my name is ${name+lastname} nice to meet you")
//    중괄호를 해도 되고 안해도 된다.
    println("is this true? ${1==0}")
    println("this is \$2")
//    그냥 $를 쓰고싶다면 앞에 백슬래쉬
}

//  4. 조건문

fun maxby(a : Int, b : Int) : Int{
    if (a > b) {
        return a
    } else{
        return b
    }
}

fun maxby2(a:Int,b:Int) : Int = if(a>b) a else b
//  조건함수를 가볍게 이렇게 쓸수도 있다.

fun checknum(score:Int){
    when(score){
        0 -> println("this is 0")
        1 -> println("this is 1")
        2, 3 -> println("this is 2 or 3")
    }

    var b = when(score){
        1-> 1
        2-> 2
        else -> 3
    }
    println("b : $b")

    when(score){
        in 90..100 -> println("excellent!")
        in 10..90 -> println("okay")
        else -> println("that's okay")
    }
}

//  expression vs statement 차이점
//  expression은 무언가를 반환하는것을 통칭한다. 즉 kotlin에선 lamda든 when이든 함수(unit을 반환하므로)든 모두 expression이다
//  반면 statement는 값을 반환하지 않는 것을 통칭한다. java의 void 함수가 그렇다.