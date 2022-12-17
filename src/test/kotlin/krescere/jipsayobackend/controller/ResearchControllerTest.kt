package krescere.jipsayobackend.controller

import krescere.jipsayobackend.dto.ResearchSaveRequest
import krescere.jipsayobackend.repository.ResearchRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.context.WebApplicationContext

import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status as status

@Transactional
@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ResearchControllerTest (
    @LocalServerPort
    private val port: Int,
    private val context: WebApplicationContext
) {
    var mvc: MockMvc? = null

    @BeforeEach
    fun setUp() {
        mvc = MockMvcBuilders
            .webAppContextSetup(context)
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
        val url="http://localhost:$port/research"
        val result = mvc!!.perform(post(url)
            .contentType(MediaType.APPLICATION_JSON)
            .content(ObjectMapper().writeValueAsString(researchSaveRequest)))
            .andExpect(status().isCreated)
            .andReturn()

        // then
        assertThat(result.response.status).isEqualTo(201)
    }
}