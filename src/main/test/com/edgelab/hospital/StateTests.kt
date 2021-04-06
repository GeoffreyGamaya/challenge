package com.edgelab.hospital

import com.edgelab.hospital.entities.State.*
import com.edgelab.hospital.entities.State.Companion.parseStates
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test

class StateTests {

  @Test
  fun emptyState() {
    assertEquals(listOf<String>(), parseStates(""))
  }

  @Test
  fun parseFever() {
    assertEquals(FEVER, parseStates("F")[0])
  }

  @Test
  fun parseHealthy() {
    assertEquals(HEALTHY, parseStates("H")[0])
  }

  @Test
  fun parseDiabetes() {
    assertEquals(DIABETES, parseStates("D")[0])
  }

  @Test
  fun parseTuberculosis() {
    assertEquals(TUBERCULOSIS, parseStates("T")[0])
  }

  @Test
  fun parseMultipleStates() {
    assertEquals(listOf(FEVER, HEALTHY, DIABETES, TUBERCULOSIS, DEAD), parseStates("F,H,D,T,X"))
    assertNotEquals(listOf(FEVER, HEALTHY, DIABETES, TUBERCULOSIS, DEAD), parseStates("H,F,D,T,X"))
  }
}