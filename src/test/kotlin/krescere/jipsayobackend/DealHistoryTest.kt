package krescere.jipsayobackend

import krescere.jipsayobackend.entity.Deal
import krescere.jipsayobackend.entity.House
import krescere.jipsayobackend.entity.HouseDetail
import krescere.jipsayobackend.repository.DealRepository
import krescere.jipsayobackend.repository.HouseDetailRepository
import krescere.jipsayobackend.repository.HouseRepository
import krescere.jipsayobackend.service.DealService
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import java.math.BigDecimal
import java.time.LocalDateTime

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DealHistoryTest {
    @Autowired
    lateinit var dealService: DealService

    @Autowired
    lateinit var dealRepository: DealRepository
    @Autowired
    lateinit var houseDetailRepository: HouseDetailRepository
    @Autowired
    lateinit var houseRepository: HouseRepository

    @Before
    fun setUp() {
        makeDB()
    }

    fun makeDB(){
        // house
        val house1=houseRepository.save(House(
            jibunAddress = "경기 고양시 덕양구 화정동 870",
            roadAddress = "경기 고양시 덕양구 화중로 164",
            hangCode = 4128112300,
            danjiName = "은빛마을5단지아파트",
            postCode = 10474,
            latitude = BigDecimal(37.6416942),
            longitude = BigDecimal(126.8342239)
        ))
        val house2=houseRepository.save(House(
            jibunAddress = "경기 고양시 덕양구 화정동 1004",
            roadAddress = "경기 고양시 덕양구 백양로 27",
            hangCode = 4128112300,
            danjiName = "옥빛마을14단지아파트",
            postCode = 10503,
            latitude = BigDecimal(37.6285682),
            longitude = BigDecimal(126.8328534)
        ))
        val house3=houseRepository.save(House(
            jibunAddress = "경기 고양시 덕양구 화정동 948",
            roadAddress = "경기 고양시 덕양구 화신로 311",
            hangCode = 4128112300,
            danjiName = "별빛마을9단지아파트",
            postCode = 10502,
            latitude = BigDecimal(37.6313333),
            longitude = BigDecimal(126.8265607)
        ))
        // houseDetail
        val houseDetail1=houseDetailRepository.save(HouseDetail(
            house=house1,
            dedicatedArea= BigDecimal(99.96),
        ))
        val houseDetail2=houseDetailRepository.save(HouseDetail(
            house=house2,
            dedicatedArea=BigDecimal(50.00),
        ))
        val houseDetail3=houseDetailRepository.save(HouseDetail(
            house=house3,
            dedicatedArea=BigDecimal(84.93),
        ))
        // deal
        val deal1=dealRepository.save(Deal(
            cost = 53000,
            dealDate=LocalDateTime.of(2023,4,1,0,0,0),
            houseDetail=houseDetail1
        ))
        val deal2=dealRepository.save(Deal(
            cost = 31300,
            dealDate=LocalDateTime.of(2023,4,1,0,0,0),
            houseDetail=houseDetail2
        ))
        val deal3=dealRepository.save(Deal(
            cost = 48800,
            dealDate=LocalDateTime.of(2023,4,1,0,0,0),
            houseDetail=houseDetail3
        ))
        val deal6=dealRepository.save(Deal(
            cost = 30000,
            dealDate=LocalDateTime.of(2023,4,5,0,0,0),
            houseDetail=houseDetail2
        ))
    }

    // Test
    @Test
    fun 돈_범위_쿼리검색(){
        val deals=dealService.findDealsByCostRange(30000, 50000)
        deals.forEach { println(it.toString()) }
    }

}