package com.example.marathon

//  dataclass
data class Ticket(val companyname : String, val name : String, var date : String, var seatnumber : Int)

class Ticketnormal(val companyname : String, val name : String, var date : String, var seatnumber : Int)

fun main(){
    val TicketA = Ticket("asiana", "HB", "21.08.22", 10)
    println(TicketA)
    val TicketB = Ticketnormal("korean", "KK", "21.32.12", 10)
    println(TicketB)
}
//  dataclass는 친절하게 내용물을 알려주는 반면 class는 싸가지없게 해시주소만 던져준다.

