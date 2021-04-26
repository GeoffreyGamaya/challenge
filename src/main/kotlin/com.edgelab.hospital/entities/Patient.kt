package com.edgelab.hospital.entities

import com.edgelab.hospital.entities.Effect.cure
import com.edgelab.hospital.entities.Effect.death
import com.edgelab.hospital.entities.Effect.side

/**
 * A patient of the [com.edgelab.hospital.entities.Hospital]
 * Can be treated only once per simulation
 */
class Patient(var state: State) {

  private var isTreated: Boolean = false

  /**
   * Will ingest a list of [com.edgelab.hospital.entities.Drug] to the patient.
   *
   * @param drugs a list of [com.edgelab.hospital.entities.Drug]
   */
  fun treat(drugs: List<Drug>) {
    listOf(death, side, cure) // the order is important here
        .filter { !isTreated }
        .map { it.apply(drugs, state) }
        .firstOrNull { it != state }
        ?.also { state = it; isTreated = true }
    isTreated = false
  }

}