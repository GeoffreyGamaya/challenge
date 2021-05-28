package com.edgelab.hospital

import com.edgelab.hospital.entities.Drug.*
import com.edgelab.hospital.entities.Effect
import com.edgelab.hospital.entities.Patient
import com.edgelab.hospital.entities.State.*
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.*

internal class PatientTests {

  @Test
  fun medicineNoEffect() {
    val patient = Patient(TUBERCULOSIS)
    patient.treat(listOf(INSULIN))
    assertThat(patient.state).isEqualTo(TUBERCULOSIS)
  }

  @Test
  fun cureFeverWithAspirin() {
    val patient = Patient(FEVER)
    patient.treat(listOf(ASPIRIN))
    assertThat(patient.state).isEqualTo(HEALTHY)
  }

  @Test
  fun cureFeverWithParacetamol() {
    val patient = Patient(FEVER)
    patient.treat(listOf(PARACETAMOL))
    assertThat(patient.state).isEqualTo(HEALTHY)
  }

  @Test
  fun cureTuberculosis() {
    val patient = Patient(TUBERCULOSIS)
    patient.treat(listOf(ANTIBIOTIC))
    assertThat(patient.state).isEqualTo(HEALTHY)
  }

  @Test
  fun cureDiabetes() {
    val patient = Patient(DIABETES)
    patient.treat(listOf(INSULIN))
    assertThat(patient.state).isEqualTo(DIABETES)
  }

  @Test
  fun cureDeathRandomly() {
    val randomMock = mockk<Random>()
    every { randomMock.nextDouble() }.returnsMany(listOf(0.0000011, 0.0000010))
    Effect.random = randomMock
    val patient = Patient(DEAD)
    patient.treat(listOf(ANTIBIOTIC))
    assertThat(patient.state).isEqualTo(DEAD)
    patient.treat(listOf(ANTIBIOTIC))
    assertThat(patient.state).isEqualTo(HEALTHY)
  }

  @Test
  fun diabetesDiesWithoutInsulin() {
    val patient = Patient(DIABETES)
    patient.treat(listOf(ANTIBIOTIC))
    assertThat(patient.state).isEqualTo(DEAD)
  }

  @Test
  fun diabetesSurvivesWithInsulin() {
    val patient = Patient(DIABETES)
    patient.treat(listOf(INSULIN))
    assertThat(patient.state).isEqualTo(DIABETES)
  }

  @Test
  fun mixInsulinWithAntibiotic() {
    val patient = Patient(HEALTHY)
    patient.treat(listOf(INSULIN, ANTIBIOTIC))
    assertThat(patient.state).isEqualTo(FEVER)
  }

  @Test
  fun mixParacetamolWithAspirin() {
    val patient = Patient(HEALTHY)
    patient.treat(listOf(PARACETAMOL, ASPIRIN))
    assertThat(patient.state).isEqualTo(DEAD)
  }

  @Test
  fun stateChangesOnlyOnce() {
    val patient = Patient(HEALTHY)
    patient.treat(listOf(INSULIN, ANTIBIOTIC, ASPIRIN))
    assertThat(patient.state).isEqualTo(FEVER)
  }

  @Test
  fun deathTakesPrecedence() {
    val patient = Patient(FEVER)
    patient.treat(listOf(PARACETAMOL, ASPIRIN))
    assertThat(patient.state).isEqualTo(DEAD)
  }

}