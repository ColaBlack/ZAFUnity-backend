package cn.cola.user.controller

import cn.cola.common.constant.UserConstant
import cn.cola.common.utils.JwtUtils
import cn.cola.service.service.UserInnerService
import org.redisson.api.RedissonClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.annotation.Resource

/**
 * 用户服务实现类（内部服务）
 *
 * @author ColaBlack
 */
@RestController
@RequestMapping("/inner")
open class UserInnerServiceController : UserInnerService {

    @Resource
    private lateinit var redissonClient: RedissonClient

    /**
     * 验证登录状态
     * @param token 登录token
     * @return 验证结果
     */
    @PostMapping("/valid/login/status")
    override fun validLoginStatus(token: String): Boolean {
        // 验证token是否在redis缓存中
        return redissonClient.getMapCache<String, String>(UserConstant.USER_LOGIN_STATE).containsKey(
            JwtUtils
                .verifyAndGetUserVO(token)
                .userId
                .toString()
        )
    }
}