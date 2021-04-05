package com.edgelab.hospital.entities

import com.edgelab.hospital.entities.Drug.*
import com.edgelab.hospital.entities.State.*
import java.util.*
import java.util.function.BiFunction


object Effect {

  var random = Random()

  private var resurrectionRatio = 0.000001

  val death = BiFunction { drugs: List<Drug>, state: State ->
    if (drugs.contains(PARACETAMOL) && drugs.contains(ASPIRIN) ||
        !drugs.contains(INSULIN) && state == DIABETES) DEAD
    state
  }

  val side = BiFunction { drugs: List<Drug>, state: State ->
    if (drugs.contains(INSULIN) && drugs.contains(ANTIBIOTIC) && state == HEALTHY) FEVER
    state
  }

  val cure = BiFunction { drugs: List<Drug>, state: State ->
    if (drugs.contains(ASPIRIN) && state == FEVER ||
        drugs.contains(PARACETAMOL) && state == FEVER ||
        drugs.contains(ANTIBIOTIC) && state == TUBERCULOSIS ||
        state == DEAD && random.nextDouble() <= resurrectionRatio) HEALTHY
    state
  }

}