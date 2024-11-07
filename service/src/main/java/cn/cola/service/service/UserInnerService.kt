package cn.cola.service.service

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping

/**
 * 用户服务（内部接口）
 *
 * @author ColaBlack
 */
@FeignClient(name = "user-service", path = "/api/user/inner")
interface UserInnerService {

    /**
     * 验证登录状态
     * @param token 登录token
     * @return 验证结果
     */
    @PostMapping("/valid/login/status")
    fun validLoginStatus(token: String): Boolean

}