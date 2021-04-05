package com.edgelab.hospital

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class IntegrationTests {

  companion object {
    private var BAOS: ByteArrayOutputStream? = null
  }

  @BeforeEach
  fun redirectPrintOutStream() {
    BAOS = ByteArrayOutputStream()
    System.setOut(PrintStream(BAOS))
  }

  @Test
  fun noArgument() {
    main(arrayOf())
    Assertions.assertEquals("F:0,H:0,D:0,T:0,X:0", BAOS.toString().replace("([\\r\\n])".toRegex(), ""))
  }

  @Test
  fun emptyArguments() {
    main(arrayOf("", ""))
    Assertions.assertEquals("F:0,H:0,D:0,T:0,X:0", BAOS.toString().replace("([\\r\\n])".toRegex(), ""))
  }

  @Test
  fun noneSense() {
    main(arrayOf("foo", "bar"))
    Assertions.assertEquals("F:0,H:0,D:0,T:0,X:0", BAOS.toString().replace("([\\r\\n])".toRegex(), ""))
  }

  @Test
  fun noPatientsMultiDrugs() {
    main(arrayOf("", "P,As"))
    Assertions.assertEquals("F:0,H:0,D:0,T:0,X:0", BAOS.toString().replace("([\\r\\n])".toRegex(), ""))
  }

  @Test
  fun onePatient() {
    main(arrayOf("D"))
    Assertions.assertEquals("F:0,H:0,D:0,T:0,X:1", BAOS.toString().replace("([\\r\\n])".toRegex(), ""))
  }

  @Test
  fun onePatientNoDrug() {
    main(arrayOf("F", ""))
    Assertions.assertEquals("F:1,H:0,D:0,T:0,X:0", BAOS.toString().replace("([\\r\\n])".toRegex(), ""))
  }

  @Test
  fun onePatientOneDrug() {
    main(arrayOf("F", "P"))
    Assertions.assertEquals("F:0,H:1,D:0,T:0,X:0", BAOS.toString().replace("([\\r\\n])".toRegex(), ""))
  }

  @Test
  fun multiPatientsNoDrugs() {
    main(arrayOf("D,D", ""))
    Assertions.assertEquals("F:0,H:0,D:0,T:0,X:2", BAOS.toString().replace("([\\r\\n])".toRegex(), ""))
  }

  @Test
  fun multiPatientsMultiDrugs() {
    main(arrayOf("F,D,T,T", "P,An"))
    Assertions.assertEquals("F:0,H:3,D:0,T:0,X:1", BAOS.toString().replace("([\\r\\n])".toRegex(), ""))
  }

}