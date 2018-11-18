package com.github.jntakpe.springdataauditingbugreport

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import reactor.test.test

@SpringBootTest
class UserRepositoryTest(@Autowired private val userRepository: UserRepository) {

    @Test
    fun `save should create simple user`() {
        val username = "this one works as expected"
        userRepository.save(User(username)).test()
                .expectSubscription()
                .consumeNextWith {
                    assertThat(it.username).isEqualTo(username)
                    assertThat(it.createdBy).isNull()
                }.verifyComplete()
    }

    @Test
    fun `save should return user with createdBy field`() {
        val username = "this one works as expected"
        userRepository.save(User(username).apply { createdBy = username }).test()
                .expectSubscription()
                .consumeNextWith {
                    assertThat(it.username).isEqualTo(username)
                    assertThat(it.createdBy).isEqualTo(username)
                }.verifyComplete()
    }
}