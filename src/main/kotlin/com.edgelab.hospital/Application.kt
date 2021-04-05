package com.edgelab.hospital

import com.edgelab.hospital.entities.Drug
import com.edgelab.hospital.entities.Hospital
import com.edgelab.hospital.entities.Patient
import com.edgelab.hospital.entities.State

fun main(args: Array<String>) {
  val hospital = Hospital(State.parseStates(args[0]).map { Patient(it) })
  println(hospital.runSimulation(Drug.parseDrugs(args[1])))
}