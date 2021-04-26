package com.edgelab.hospital.entities

/**
 * Drug contains the known drugs,
 * just add an enum in the list if needed.
 */
enum class Drug(val drugCode: String) {

  ASPIRIN("As"), ANTIBIOTIC("An"), INSULIN("I"), PARACETAMOL("P");

  companion object {
    fun parseDrugs(input: String): List<Drug> {
      return input.split(",").filter { it.isNotEmpty() }.map { values().first { v -> v.drugCode == it } }
    }
  }
}