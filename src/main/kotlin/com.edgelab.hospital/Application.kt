package com.edgelab.hospital

import com.edgelab.hospital.entities.Drug
import com.edgelab.hospital.entities.Hospital
import com.edgelab.hospital.entities.Patient
import com.edgelab.hospital.entities.State

fun main(args: Array<String>) {
  if (args.size != 2) return
  val patients = State.parseStates(args[0]).map { Patient(it) }
  val hospital = Hospital(patients)
  val drugs = Drug.parseDrugs(args[1])
  println(hospital.runSimulation(drugs))
}