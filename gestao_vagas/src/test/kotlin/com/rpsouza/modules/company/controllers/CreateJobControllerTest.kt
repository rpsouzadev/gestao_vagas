//package com.rpsouza.modules.company.controllers
//
//import com.rpsouza.modules.company.dto.CreateJobDTO
//import com.rpsouza.modules.utils.TestUtils
//import org.junit.Before
//import org.junit.Test
//import org.junit.jupiter.api.BeforeEach
//import org.junit.runner.RunWith
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.context.SpringBootTest
//import org.springframework.http.MediaType
//import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers
//import org.springframework.test.context.ActiveProfiles
//import org.springframework.test.context.junit4.SpringRunner
//import org.springframework.test.web.servlet.MockMvc
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers
//import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
//import org.springframework.test.web.servlet.setup.MockMvcBuilders
//import org.springframework.web.context.WebApplicationContext
//import java.util.*
//
//@RunWith(SpringRunner::class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@ActiveProfiles("test")
//class CreateJobControllerTest {
//  private lateinit var mvc: MockMvc
//
//  @Autowired
//  private lateinit var context: WebApplicationContext
//
//  @Before
//  fun setup() {
//    mvc = MockMvcBuilders
//      .webAppContextSetup(context)
//      .apply { SecurityMockMvcConfigurers.springSecurity() }
//      .build()
//  }
//
//  @Test
//  fun should_be_able_to_create_a_new_job() {
//    val createdJobDTO = CreateJobDTO(
//      benefits = "BENEFITS_TEST",
//      description = "DESCRIPTION_TEST",
//      level = "LEVEL_TEST"
//    )
//
//    val result = mvc.perform(
//      MockMvcRequestBuilders.post("/company/job/")
//        .contentType(MediaType.APPLICATION_JSON)
//        .content(TestUtils.objectToJSON(createdJobDTO))
//        .header(
//          "Authorization",
//          TestUtils.generateToken(
//            UUID.fromString("c2ff00a2-4ac6-466f-8f42-7e0781ce70ba"),
//            "KOTLIN_@123#"
//          )
//        )
//    )
//      .andExpect(MockMvcResultMatchers.status().isOk)
//
//    println(result)
//  }
//}