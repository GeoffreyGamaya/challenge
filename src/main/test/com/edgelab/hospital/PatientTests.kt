package com.edgelab.hospital

import com.edgelab.hospital.entities.Drug.*
import com.edgelab.hospital.entities.Effect
import com.edgelab.hospital.entities.Patient
import com.edgelab.hospital.entities.State.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.util.*

internal class PatientTests {

  @Test
  fun medicineNoEffect() {
    val patient = Patient(TUBERCULOSIS)
    patient.treat(listOf(INSULIN))
    Assertions.assertEquals(TUBERCULOSIS, patient.state)
  }

  @Test
  fun cureFeverWithAspirin() {
    val patient = Patient(FEVER)
    patient.treat(listOf(ASPIRIN))
    Assertions.assertEquals(HEALTHY, patient.state)
  }

  @Test
  fun cureFeverWithParacetamol() {
    val patient = Patient(FEVER)
    patient.treat(listOf(PARACETAMOL))
    Assertions.assertEquals(HEALTHY, patient.state)
  }

  @Test
  fun cureTuberculosis() {
    val patient = Patient(TUBERCULOSIS)
    patient.treat(listOf(ANTIBIOTIC))
    Assertions.assertEquals(HEALTHY, patient.state)
  }

  @Test
  fun cureDiabetes() {
    val patient = Patient(DIABETES)
    patient.treat(listOf(INSULIN))
    Assertions.assertEquals(DIABETES, patient.state)
  }

  @Test
  fun cureDeathRandomly() {
    val randomMock = Mockito.mock(Random::class.java)
    Mockito.`when`(randomMock.nextDouble()).thenReturn(0.0000011).thenReturn(0.0000010)
    Effect.random = randomMock
    val patient = Patient(DEAD)
    patient.treat(listOf(ANTIBIOTIC))
    Assertions.assertEquals(DEAD, patient.state)
    patient.treat(listOf(ANTIBIOTIC))
    Assertions.assertEquals(HEALTHY, patient.state)
  }

  @Test
  fun diabetesDiesWithoutInsulin() {
    val patient = Patient(DIABETES)
    patient.treat(listOf(ANTIBIOTIC))
    Assertions.assertEquals(DEAD, patient.state)
  }

  @Test
  fun diabetesSurvivesWithInsulin() {
    val patient = Patient(DIABETES)
    patient.treat(listOf(INSULIN))
    Assertions.assertEquals(DIABETES, patient.state)
  }

  @Test
  fun mixInsulinWithAntibiotic() {
    val patient = Patient(HEALTHY)
    patient.treat(Arrays.asList(INSULIN, ANTIBIOTIC))
    Assertions.assertEquals(FEVER, patient.state)
  }

  @Test
  fun mixParacetamolWithAspirin() {
    val patient = Patient(HEALTHY)
    patient.treat(Arrays.asList(PARACETAMOL, ASPIRIN))
    Assertions.assertEquals(DEAD, patient.state)
  }

  @Test
  fun stateChangesOnlyOnce() {
    val patient = Patient(HEALTHY)
    patient.treat(Arrays.asList(INSULIN, ANTIBIOTIC, ASPIRIN))
    Assertions.assertEquals(FEVER, patient.state)
  }

  @Test
  fun DeathTakesPrecedence() {
    val patient = Patient(FEVER)
    patient.treat(Arrays.asList(PARACETAMOL, ASPIRIN))
    Assertions.assertEquals(DEAD, patient.state)
  }
}