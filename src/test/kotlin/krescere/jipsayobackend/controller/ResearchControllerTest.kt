package krescere.jipsayobackend.controller

import com.fasterxml.jackson.databind.ObjectMapper
import krescere.jipsayobackend.dto.dealHistory.DealHistorySaveRequest
import krescere.jipsayobackend.dto.research.ResearchSaveRequest
import krescere.jipsayobackend.repository.HouseDetailRepository
import krescere.jipsayobackend.repository.ResearchRepository
import krescere.jipsayobackend.service.DealHistoryService
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
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
@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ResearchControllerTest {
    @Autowired
    lateinit var researchRepository: ResearchRepository
    @Autowired
    lateinit var context: WebApplicationContext
    @Autowired
    lateinit var dealHistoryService: DealHistoryService
    @Autowired
    lateinit var houseDetailRepository: HouseDetailRepository
    
    @LocalServerPort
    val port: Int = 0

    lateinit var mvc: MockMvc
    val localhost="http://localhost:"
    val apiV1="/api/v1"

    @Before
    fun setUp() {
        mvc = MockMvcBuilders
            .webAppContextSetup(context)
            .addFilters<DefaultMockMvcBuilder>(CharacterEncodingFilter("UTF-8", true))
            .alwaysDo<DefaultMockMvcBuilder?>(MockMvcResultHandlers.print())
            .build()
    }

    @Test
    fun 설문조사_저장() {
        // given
        dealHistoryService.save(DealHistorySaveRequest(
            pageNo = 1,
            numOfRows = 10,
            lawdCd = "11110",
            dearYmd = "202212"
        ))
        val houseDetailId = houseDetailRepository.findAll()[0].id!!
        val researchSaveRequest = ResearchSaveRequest(
            houseDetailId = houseDetailId,
            savedMoney = 1000,
            moneyPerMonth = 1000,
            increaseRate = 0.1,
            job = "개발자",
            occupation = "IT",
        )
        // when
        val url="$localhost$port$apiV1/research"
        val result = mvc.perform(post(url)
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