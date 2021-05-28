package com.edgelab.hospital.entities

/**
 * Drug contains the known drugs,
 * just add an enum in the list if needed.
 */
enum class Drug(val code: String) {

  ASPIRIN("As"), ANTIBIOTIC("An"), INSULIN("I"), PARACETAMOL("P");

  companion object {
    fun of(input: String): List<Drug> {
      return input.split(",").filter { it.isNotEmpty() }.map { values().first { v -> v.code == it } }
    }
  }
}