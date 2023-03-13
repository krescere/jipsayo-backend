package krescere.jipsayobackend.controller

import krescere.jipsayobackend.service.DealService
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.platform.commons.logging.LoggerFactory
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.filter.CharacterEncodingFilter

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DealControllerTest{
    @Autowired
    val dealService: DealService? = null
    @Autowired
    val context: WebApplicationContext? = null

    @LocalServerPort
    val port: Int = 0

    var mvc: MockMvc? = null

    @Before
    fun setUp() {
        mvc = MockMvcBuilders
            .webAppContextSetup(context)
            .addFilters<DefaultMockMvcBuilder>(CharacterEncodingFilter("UTF-8", true))
            .alwaysDo<DefaultMockMvcBuilder?>(MockMvcResultHandlers.print())
            .build()
    }

    @Test
    fun 거래내역조회() {
        val deals=dealService!!.getDeals()
        println(deals)
        assertThat(deals).isNotNull
    }
}