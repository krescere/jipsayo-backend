package krescere.jipsayobackend.controller

import com.fasterxml.jackson.databind.ObjectMapper
import krescere.jipsayobackend.dto.HouseSaveRequest
import krescere.jipsayobackend.entity.House
import krescere.jipsayobackend.repository.HouseRepository
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
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
class HouseControllerTest @Autowired constructor(
    private val context: WebApplicationContext,
    private val houseRepository: HouseRepository
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
    fun 부동산_저장() {
        // given
        val houseSaveRequest = HouseSaveRequest(
            jibunAddress = "서울특별시 강남구 역삼동",
            cost = 1000,
            latitude = 37.0,
            longitude = 127.0
        )
        // when
        val url="$localhost$port$apiV1/houses"
        val result = mvc!!.perform(post(url)
            .contentType(MediaType.APPLICATION_JSON)
            .content(ObjectMapper().writeValueAsString(houseSaveRequest)))
            .andExpect(status().isCreated)
            .andReturn()
        // then
        assertThat(result.response.contentAsString).contains(
            houseRepository.findAll()[0].id.toString()
        )
    }

    @Test
    fun 지번주소로_부동산찾기() {
        // given
        val houseSaveRequest = HouseSaveRequest(
            jibunAddress = "서울특별시 강남구 역삼동",
            cost = 1000,
            latitude = 37.0,
            longitude = 127.0
        )
        houseRepository.save(House(
            jibunAddress = houseSaveRequest.jibunAddress,
            cost = houseSaveRequest.cost,
            latitude = houseSaveRequest.latitude,
            longitude = houseSaveRequest.longitude
        ))
        // when
        val url="$localhost$port$apiV1/houses/${houseSaveRequest.jibunAddress}"
        val result = mvc!!.perform(get(url)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andReturn()
        // then
        assertThat(result.response.contentAsString).contains(houseSaveRequest.jibunAddress)
    }
}