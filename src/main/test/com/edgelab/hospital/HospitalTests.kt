package com.edgelab.hospital

import com.edgelab.hospital.entities.Drug.*
import com.edgelab.hospital.entities.Hospital
import com.edgelab.hospital.entities.Patient
import com.edgelab.hospital.entities.State.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class HospitalTests {

  @Test
  fun checkHospitalReportGeneration() {
    val patient1 = Mockito.mock(Patient::class.java)
    val patient2 = Mockito.mock(Patient::class.java)
    val patient3 = Mockito.mock(Patient::class.java)
    Mockito.`when`(patient1.state).thenReturn(FEVER)
    Mockito.`when`(patient2.state).thenReturn(HEALTHY)
    Mockito.`when`(patient3.state).thenReturn(TUBERCULOSIS)
    val hospital = Hospital(listOf(patient1, patient2, patient3))
    val results = hospital.runSimulation(listOf(ANTIBIOTIC,ASPIRIN))
    Assertions.assertEquals("F:1,H:1,D:0,T:1,X:0", results)
  }

  @Test
  fun multiSimulations() {
    val patients = listOf(Patient(FEVER), Patient(DIABETES), Patient(TUBERCULOSIS))
    val hospital = Hospital(patients)
    val results1 = hospital.runSimulation(listOf(PARACETAMOL, INSULIN))
    Assertions.assertEquals("F:0,H:1,D:1,T:1,X:0", results1)
    val results2 = hospital.runSimulation(listOf(ANTIBIOTIC,ASPIRIN))
    Assertions.assertEquals("F:0,H:2,D:0,T:0,X:1", results2)
  }
}