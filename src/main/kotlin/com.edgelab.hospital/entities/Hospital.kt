package com.edgelab.hospital.entities

/**
 * An patient of the [com.edgelab.hospital.entities.Hospital] host a list of [com.edgelab.hospital.entities.Patient]
 * We can run a simulation on the patients using a list of [com.edgelab.hospital.entities.Drug].
 * A report will be generated.
 */
class Hospital(private val patients: List<Patient>) {

  /**
   * The simulation respects the processing rules order.
   * It is easy to integrate new rules or modify the existing ones.
   *
   * @param drugs the list of drugs to inoculate to the patients of the hospital.
   */
  fun runSimulation(drugs: List<Drug>): String {
    patients.forEach { it.treat(drugs) }
    return State.values().joinToString(",") {
        it.code + ":" + patients.filter { p -> p.state == it }.count()
    }
  }

}