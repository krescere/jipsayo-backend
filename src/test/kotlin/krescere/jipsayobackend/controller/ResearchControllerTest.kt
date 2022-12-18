package krescere.jipsayobackend.controller

import krescere.jipsayobackend.dto.ResearchSaveRequest
import com.fasterxml.jackson.databind.ObjectMapper
import krescere.jipsayobackend.repository.ResearchRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.filter.CharacterEncodingFilter

@Transactional
@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ResearchControllerTest @Autowired constructor(
    private val context: WebApplicationContext,
    private val researchRepository: ResearchRepository
) {
    @LocalServerPort
    val port: Int = 0

    var mvc: MockMvc? = null
    val localhost="http://localhost:"
    val apiV1="/api/v1"

    @BeforeEach
    fun setUp() {
        mvc = MockMvcBuilders
            .webAppContextSetup(context)
            .addFilters<DefaultMockMvcBuilder>(CharacterEncodingFilter("UTF-8", true))
            .alwaysDo<DefaultMockMvcBuilder?>(MockMvcResultHandlers.print())
            .build()
    }

    @Test
    fun researchSaveTest() {
        // given
        val researchSaveRequest = ResearchSaveRequest(
            savedMoney = 1000,
            moneyPerMonth = 1000,
            jibunAddress = "서울특별시 강남구 역삼동",
            increaseRate = 0.1,
            job = "개발자",
            occupation = "IT"
        )
        // when
        val url="$localhost$port$apiV1/research"
        val result = mvc!!.perform(post(url)
            .contentType(MediaType.APPLICATION_JSON)
            .content(ObjectMapper().writeValueAsString(researchSaveRequest)))
            .andExpect(status().isCreated)
            .andReturn()

        // then
        assertThat(result.response.contentAsString).contains(
            researchRepository.findAll()[0].id.toString()
        )
    }
}