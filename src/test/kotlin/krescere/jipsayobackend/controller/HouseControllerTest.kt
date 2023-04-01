package krescere.jipsayobackend.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import krescere.jipsayobackend.common.handler.DecimalPointHandler.Companion.doubleToBigDecimal
import krescere.jipsayobackend.dto.house.HouseSaveRequest
import krescere.jipsayobackend.entity.House
import krescere.jipsayobackend.repository.HouseRepository
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
class HouseControllerTest {
    @Autowired
    lateinit var houseRepository: HouseRepository
    @Autowired
    lateinit var context: WebApplicationContext

    @LocalServerPort
    val port: Int = 0

    lateinit var mvc: MockMvc
    val localhost="http://localhost:"
    val apiV1="/api/v1"

    // houses
    lateinit var 서북구: House
    lateinit var 호암동: House

    @Before
    fun setUp() {
        mvc = MockMvcBuilders
            .webAppContextSetup(context)
            .addFilters<DefaultMockMvcBuilder>(CharacterEncodingFilter("UTF-8", true))
            .alwaysDo<DefaultMockMvcBuilder?>(MockMvcResultHandlers.print())
            .build()

        서북구 = House(
            jibunAddress = "충남 천안시 서북구 성정동 1438",
            roadAddress = "충남 천안시 서북구 성정공원3길 4",
            hangCode = 4413310200,
            danjiName = "학산리젠다빌 3차",
            postCode = 31110,
            latitude = doubleToBigDecimal(36.8261598),
            longitude = doubleToBigDecimal(127.1413382),
        )
        호암동 = House(
            jibunAddress = "충북 충주시 호암동 152",
            roadAddress = "충북 충주시 호암중앙2로 31",
            hangCode = 4313010700,
            danjiName = "부강아파트",
            postCode = 27481,
            latitude = doubleToBigDecimal(36.9559942),
            longitude = doubleToBigDecimal(127.9403276),
        )
    }

    // 시간 포멧을 yyyy-MM-dd'T'HH:mm:ss.SSS'Z'로 변경
    fun objectMapper(): ObjectMapper {
        val objectMapper = ObjectMapper()
        objectMapper.registerModule(KotlinModule())
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        return objectMapper
    }

    @Test
    fun 부동산_저장() {
        // given
        val houseSaveRequest = HouseSaveRequest(
            jibunAddress = "충남 천안시 서북구 성정동 1438",
            roadAddress = "충남 천안시 서북구 성정공원3길 4",
            hangCode = 4413310200,
            danjiName = "학산리젠다빌 3차",
            postCode = 31110,
            latitude = 36.8261598,
            longitude = 127.1413382,
        )
        // when
        val url="$localhost$port$apiV1/houses"
        val result = mvc.perform(post(url)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper().writeValueAsString(houseSaveRequest)))
            .andExpect(status().isCreated)
            .andReturn()
        // then
        assertThat(result.response.contentAsString).contains(
            houseRepository.findAll()[0].id.toString()
        )
    }

    @Test
    fun 도로명주소_단지명으로_부동산찾기() {
        // given
        houseRepository.save(서북구)
        val underRoadAddress="충남_천안시_서북구_성정공원3길_4"
        val underDanjiName="학산리젠다빌_3차"
        // when
        val url="$localhost$port$apiV1/houses"
        val result = mvc.perform(get(url)
            .param("roadAddress", underRoadAddress)
            .param("danjiName", underDanjiName))
            .andExpect(status().isOk)
            .andReturn()
        // then
        assertThat(result.response.contentAsString).contains(서북구.roadAddress)
    }

    @Test
    fun 부동산찾기_실패() {
        // given
        houseRepository.save(서북구)
        val anotherRoadAddress="서울특별시_압구정동"
        val anotherDanjiName="압구정푸르지오"
        // when
        val url="$localhost$port$apiV1/houses"
        val result = mvc.perform(get(url)
            .param("roadAddress", anotherRoadAddress)
            .param("danjiName", anotherDanjiName))
            .andExpect(status().isOk)
            .andReturn()
        // then
        assertThat(result.response.contentAsString).contains("{}")
    }

    @Test
    fun 도로명주소_단지명으로_삭제() {
        // given
        houseRepository.save(서북구)
        val underRoadAddress="충남_천안시_서북구_성정공원3길_4"
        val underDanjiName="학산리젠다빌_3차"
        // when
        val url="$localhost$port$apiV1/houses"
        mvc.perform(delete(url)
            .param("roadAddress", underRoadAddress)
            .param("danjiName", underDanjiName))
            .andExpect(status().isOk)
        // then
        assertThat(houseRepository.findAll().size).isEqualTo(0)
    }

    @Test
    fun 존재하지않는_부동산_삭제() {
        // given
        houseRepository.save(서북구)
        val anotherJibunAddress="서울특별시_압구정동"
        val anotherDanjiName="압구정빌라"
        // when
        val url="$localhost$port$apiV1/houses"
        mvc.perform(delete(url)
            .param("roadAddress", anotherJibunAddress)
            .param("danjiName", anotherDanjiName))
            .andExpect(status().isBadRequest)
        // then
        assertThat(houseRepository.findAll()).isNotEmpty
    }
}