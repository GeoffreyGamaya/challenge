package com.edgelab.hospital

import com.edgelab.hospital.entities.Drug
import com.edgelab.hospital.entities.Drug.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test

class DrugTests {

  @Test
  fun emptyDrug() {
    assertEquals(listOf<String>(), Drug.parseDrugs(""))
  }

  @Test
  fun parseAspirin() {
    assertEquals(ASPIRIN, Drug.parseDrugs("As")[0])
  }

  @Test
  fun parseAntibiotic() {
    assertEquals(ANTIBIOTIC, Drug.parseDrugs("An")[0])
  }

  @Test
  fun parseInsulin() {
    assertEquals(INSULIN, Drug.parseDrugs("I")[0])
  }

  @Test
  fun parseParacetamol() {
    assertEquals(PARACETAMOL, Drug.parseDrugs("P")[0])
  }

  @Test
  fun parseMultipleDrugs() {
    assertEquals(listOf(ASPIRIN, ANTIBIOTIC, INSULIN, PARACETAMOL), Drug.parseDrugs("As,An,I,P"))
    assertNotEquals(listOf(ASPIRIN, ANTIBIOTIC, INSULIN, PARACETAMOL), Drug.parseDrugs("An,As,I,P"))
  }
  
}