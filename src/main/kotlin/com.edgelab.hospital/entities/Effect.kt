package com.edgelab.hospital.entities

import com.edgelab.hospital.entities.Drug.*
import com.edgelab.hospital.entities.State.*
import java.util.*
import java.util.function.BiFunction


object Effect {

  var random = Random()

  private var RESURRECTION_RATIO = 0.000001

  val death = BiFunction { drugs: List<Drug>, state: State ->
    val newState = if (drugs.contains(PARACETAMOL) && drugs.contains(ASPIRIN) ||
        !drugs.contains(INSULIN) && state == DIABETES) {
      DEAD
    } else {
      state
    }
    newState
  }

  val side = BiFunction { drugs: List<Drug>, state: State ->
    val newState = if (drugs.contains(INSULIN) && drugs.contains(ANTIBIOTIC) && state == HEALTHY) {
      FEVER
    } else {
      state
    }
    newState
  }

  val cure = BiFunction { drugs: List<Drug>, state: State ->
    val newState = if (drugs.contains(ASPIRIN) && state == FEVER ||
        drugs.contains(PARACETAMOL) && state == FEVER ||
        drugs.contains(ANTIBIOTIC) && state == TUBERCULOSIS ||
        state == DEAD && random.nextDouble() <= RESURRECTION_RATIO) {
      HEALTHY
    } else {
      state
    }
    newState
  }

}