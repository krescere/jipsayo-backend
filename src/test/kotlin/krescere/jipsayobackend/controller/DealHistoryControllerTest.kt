package krescere.jipsayobackend.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import krescere.jipsayobackend.dto.dealHistory.DealHistoryGetRequest
import krescere.jipsayobackend.dto.dealHistory.DealHistorySaveRequest
import krescere.jipsayobackend.repository.DealRepository
import krescere.jipsayobackend.repository.HouseDetailRepository
import krescere.jipsayobackend.repository.HouseRepository
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
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
class DealHistoryControllerTest {
    @Autowired
    lateinit var context: WebApplicationContext

    @LocalServerPort
    val port: Int = 0

    val localhost="http://localhost:"
    val apiV1="/api/v1"

    lateinit var mvc: MockMvc

    @Autowired
    lateinit var dealRepository: DealRepository
    @Autowired
    lateinit var houseDetailRepository: HouseDetailRepository
    @Autowired
    lateinit var houseRepository: HouseRepository
    @Autowired
    lateinit var dealHistoryService: DealHistoryService

    fun objectMapper(): ObjectMapper {
        val objectMapper = ObjectMapper()
        objectMapper.registerModule(KotlinModule())
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        return objectMapper
    }

    @Before
    fun setUp() {
        mvc = MockMvcBuilders
            .webAppContextSetup(context)
            .addFilters<DefaultMockMvcBuilder>(CharacterEncodingFilter("UTF-8", true))
            .alwaysDo<DefaultMockMvcBuilder?>(MockMvcResultHandlers.print())
            .build()
    }

    @Test
    fun save() {
        // given
        val request=DealHistorySaveRequest(
            pageNo = 1,
            numOfRows = 10,
            lawdCd = "11110",
            dearYmd = "202212"
        )
        // when
        val url="$localhost$port$apiV1/dealHistories"
        mvc.perform(post(url)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper().writeValueAsString(request)))
            .andExpect(status().isCreated)
        // then
        assertThat(dealRepository.findAll().size).isGreaterThan(0)
        assertThat(houseDetailRepository.findAll().size).isGreaterThan(0)
        assertThat(houseRepository.findAll().size).isGreaterThan(0)
    }

    @Test
    fun find() {
        // given
        dealHistoryService.save(DealHistorySaveRequest(
            pageNo = 1,
            numOfRows = 10,
            lawdCd = "11110",
            dearYmd = "201512"
        ))
        val request=DealHistoryGetRequest(
            roadAddress = "서울특별시 종로구 사직로 161",
            danjiName = "사직포레스트",
        )
        // when
        val url="$localhost$port$apiV1/dealHistories"

        // then
    }
}