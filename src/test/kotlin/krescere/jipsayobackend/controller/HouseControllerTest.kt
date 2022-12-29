package krescere.jipsayobackend.controller

import com.fasterxml.jackson.databind.ObjectMapper
import krescere.jipsayobackend.dto.HouseSaveRequest
import krescere.jipsayobackend.dto.HouseUpdateRequest
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
    val houseRepository: HouseRepository? = null
    @Autowired
    val context: WebApplicationContext? = null
    
    @LocalServerPort
    val port: Int = 0

    var mvc: MockMvc? = null
    val localhost="http://localhost:"
    val apiV1="/api/v1"

    @Before
    fun setUp() {
        mvc = MockMvcBuilders
            .webAppContextSetup(context!!)
            .addFilters<DefaultMockMvcBuilder>(CharacterEncodingFilter("UTF-8", true))
            .alwaysDo<DefaultMockMvcBuilder?>(MockMvcResultHandlers.print())
            .build()
    }

    @Test
    fun 부동산_저장() {
        // given
        val houseSaveRequest = HouseSaveRequest(
            jibunAddress = "충남 천안시 서북구 성정동 1438",
            roadAddress = "충남 천안시 서북구 성정공원3길 4",
            cost = 1000,
            hangCode = 4413310200,
            danjiName = "학산리젠다빌 3차",
            postCode = 31110,
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
            houseRepository!!.findAll()[0].id.toString()
        )
    }

    @Test
    fun 도로명주소_단지명으로_부동산찾기() {
        // given
        val houseSaveRequest = HouseSaveRequest(
            jibunAddress = "충남 천안시 서북구 성정동 1438",
            roadAddress = "충남 천안시 서북구 성정공원3길 4",
            cost = 1000,
            hangCode = 4413310200,
            danjiName = "학산리젠다빌 3차",
            postCode = 31110,
            latitude = 37.0,
            longitude = 127.0
        )
        houseRepository!!.save(House(
            jibunAddress = houseSaveRequest.jibunAddress,
            roadAddress = houseSaveRequest.roadAddress,
            cost = houseSaveRequest.cost,
            danjiName = houseSaveRequest.danjiName,
            hangCode = houseSaveRequest.hangCode,
            postCode = houseSaveRequest.postCode,
            latitude = houseSaveRequest.latitude,
            longitude = houseSaveRequest.longitude
        ))
        val underRoadAddress="충남_천안시_서북구_성정공원3길_4"
        val underDanjiName="학산리젠다빌_3차"
        // when
        val url="$localhost$port$apiV1/houses"
        val result = mvc!!.perform(get(url)
            .param("roadAddress", underRoadAddress)
            .param("danjiName", underDanjiName))
            .andExpect(status().isOk)
            .andReturn()
        // then
        assertThat(result.response.contentAsString).contains(houseSaveRequest.roadAddress)
    }

    @Test
    fun 부동산찾기_실패() {
        // given
        val houseSaveRequest = HouseSaveRequest(
            jibunAddress = "충남 천안시 서북구 성정동 1438",
            roadAddress = "충남 천안시 서북구 성정공원3길 4",
            cost = 1000,
            hangCode = 4413310200,
            danjiName = "학산리젠다빌 3차",
            postCode = 31110,
            latitude = 37.0,
            longitude = 127.0
        )
        houseRepository!!.save(House(
            jibunAddress = houseSaveRequest.jibunAddress,
            roadAddress = houseSaveRequest.roadAddress,
            cost = houseSaveRequest.cost,
            danjiName = houseSaveRequest.danjiName,
            hangCode = houseSaveRequest.hangCode,
            postCode = houseSaveRequest.postCode,
            latitude = houseSaveRequest.latitude,
            longitude = houseSaveRequest.longitude
        ))
        val anotherRoadAddress="서울특별시_압구정동"
        val anotherDanjiName="압구정푸르지오"
        // when
        val url="$localhost$port$apiV1/houses"
        val result = mvc!!.perform(get(url)
            .param("roadAddress", anotherRoadAddress)
            .param("danjiName", anotherDanjiName))
            .andExpect(status().isOk)
            .andReturn()
        // then
        assertThat(result.response.contentAsString).contains("{}")
    }

    @Test
    fun 도로명주소_단지명으로_업데이트() {
        // given
        val houseSaveRequest = HouseSaveRequest(
            jibunAddress = "충남 천안시 서북구 성정동 1438",
            roadAddress = "충남 천안시 서북구 성정공원3길 4",
            cost = 1000,
            hangCode = 4413310200,
            danjiName = "학산리젠다빌 3차",
            postCode = 31110,
            latitude = 37.0,
            longitude = 127.0
        )
        houseRepository!!.save(House(
            jibunAddress = houseSaveRequest.jibunAddress,
            roadAddress = houseSaveRequest.roadAddress,
            cost = houseSaveRequest.cost,
            danjiName = houseSaveRequest.danjiName,
            hangCode = houseSaveRequest.hangCode,
            postCode = houseSaveRequest.postCode,
            latitude = houseSaveRequest.latitude,
            longitude = houseSaveRequest.longitude
        ))
        val houseUpdateRequest = HouseUpdateRequest(
            jibunAddress = null,
            roadAddress = null,
            cost = 2000,
            hangCode = null,
            danjiName = null,
            postCode = null,
            latitude = null,
            longitude = null
        )
        val underRoadAddress="충남_천안시_서북구_성정공원3길_4"
        val underDanjiName="학산리젠다빌_3차"
        // when
        val url="$localhost$port$apiV1/houses"
        mvc!!.perform(put(url)
            .param("roadAddress", underRoadAddress)
            .param("danjiName", underDanjiName)
            .contentType(MediaType.APPLICATION_JSON)
            .content(ObjectMapper().writeValueAsString(houseUpdateRequest)))
            .andExpect(status().isOk)
        // then
        assertThat(houseRepository!!.findAll()[0].cost).isEqualTo(houseUpdateRequest.cost)
    }

    @Test
    fun 존재하지않는_부동산_업데이트() {
        // given
        val houseSaveRequest = HouseSaveRequest(
            jibunAddress = "충남 천안시 서북구 성정동 1438",
            roadAddress = "충남 천안시 서북구 성정공원3길 4",
            cost = 1000,
            hangCode = 4413310200,
            danjiName = "학산리젠다빌 3차",
            postCode = 31110,
            latitude = 37.0,
            longitude = 127.0
        )
        houseRepository!!.save(House(
            jibunAddress = houseSaveRequest.jibunAddress,
            roadAddress = houseSaveRequest.roadAddress,
            cost = houseSaveRequest.cost,
            danjiName = houseSaveRequest.danjiName,
            hangCode = houseSaveRequest.hangCode,
            postCode = houseSaveRequest.postCode,
            latitude = houseSaveRequest.latitude,
            longitude = houseSaveRequest.longitude
        ))
        val houseUpdateRequest = HouseUpdateRequest(
            jibunAddress = null,
            roadAddress = null,
            cost = 2000,
            hangCode = null,
            danjiName = null,
            postCode = null,
            latitude = null,
            longitude = null
        )
        val anotherRoadAddress="서울특별시_압구정동"
        val anotherDanjiName="압구정빌라"
        // when
        val url="$localhost$port$apiV1/houses"
        mvc!!.perform(put(url)
            .param("roadAddress", anotherRoadAddress)
            .param("danjiName", anotherDanjiName)
            .contentType(MediaType.APPLICATION_JSON)
            .content(ObjectMapper().writeValueAsString(houseUpdateRequest)))
            .andExpect(status().isBadRequest)
        // then
        assertThat(houseRepository!!.findAll()[0].cost).isEqualTo(houseSaveRequest.cost)
    }

    @Test
    fun 도로명주소_단지명으로_삭제() {
        // given
        val houseSaveRequest = HouseSaveRequest(
            jibunAddress = "충남 천안시 서북구 성정동 1438",
            roadAddress = "충남 천안시 서북구 성정공원3길 4",
            cost = 1000,
            hangCode = 4413310200,
            danjiName = "학산리젠다빌 3차",
            postCode = 31110,
            latitude = 37.0,
            longitude = 127.0
        )
        houseRepository!!.save(House(
            jibunAddress = houseSaveRequest.jibunAddress,
            roadAddress = houseSaveRequest.roadAddress,
            cost = houseSaveRequest.cost,
            danjiName = houseSaveRequest.danjiName,
            hangCode = houseSaveRequest.hangCode,
            postCode = houseSaveRequest.postCode,
            latitude = houseSaveRequest.latitude,
            longitude = houseSaveRequest.longitude
        ))
        val underRoadAddress="충남_천안시_서북구_성정공원3길_4"
        val underDanjiName="학산리젠다빌_3차"
        // when
        val url="$localhost$port$apiV1/houses"
        mvc!!.perform(delete(url)
            .param("roadAddress", underRoadAddress)
            .param("danjiName", underDanjiName))
            .andExpect(status().isOk)
        // then
        assertThat(houseRepository!!.findAll().size).isEqualTo(0)
    }

    @Test
    fun 존재하지않는_부동산_삭제() {
        // given
        val houseSaveRequest = HouseSaveRequest(
            jibunAddress = "충남 천안시 서북구 성정동 1438",
            roadAddress = "충남 천안시 서북구 성정공원3길 4",
            cost = 1000,
            hangCode = 4413310200,
            danjiName = "학산리젠다빌 3차",
            postCode = 31110,
            latitude = 37.0,
            longitude = 127.0
        )
        houseRepository!!.save(House(
            jibunAddress = houseSaveRequest.jibunAddress,
            roadAddress = houseSaveRequest.roadAddress,
            cost = houseSaveRequest.cost,
            danjiName = houseSaveRequest.danjiName,
            hangCode = houseSaveRequest.hangCode,
            postCode = houseSaveRequest.postCode,
            latitude = houseSaveRequest.latitude,
            longitude = houseSaveRequest.longitude
        ))
        val anotherJibunAddress="서울특별시_압구정동"
        val anotherDanjiName="압구정빌라"
        // when
        val url="$localhost$port$apiV1/houses"
        mvc!!.perform(delete(url)
            .param("roadAddress", anotherJibunAddress)
            .param("danjiName", anotherDanjiName))
            .andExpect(status().isBadRequest)
        // then
        assertThat(houseRepository!!.findAll()).isNotEmpty
    }
}