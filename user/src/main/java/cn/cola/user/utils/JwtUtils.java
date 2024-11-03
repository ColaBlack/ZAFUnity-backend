package cn.cola.user.utils;

import cn.cola.user.constant.UserConstant;
import cn.cola.service.user.model.vo.UserVO;
import cn.hutool.jwt.JWT;
import io.micrometer.common.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT工具类
 *
 * @author ColaBlack
 */
public class JwtUtils {

    private JwtUtils() {
    }

    /**
     * 根据 userVO 生成 token
     *
     * @param userVO 用户信息
     * @return token
     */
    public static String generateToken(UserVO userVO) {
        Map<String, Object> map = new HashMap<>(11);
        Class<? extends UserVO> voClass = userVO.getClass();
        Arrays.stream(voClass.getDeclaredFields())
                .toList().
                forEach(field -> {
                    try {
                        // 获取 getter 方法名
                        String getterMethodName = "get" + capitalize(field.getName());
                        // 获取 getter 方法并调用
                        Method getterMethod = voClass.getMethod(getterMethodName);
                        Object value = getterMethod.invoke(userVO);
                        map.put(field.getName(), value);
                    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                });
        return generateToken(map);
    }

    /**
     * 首字母大写
     *
     * @param str 字符串
     * @return 字符串首字母大写
     */
    private static String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }


    /**
     * 根据 map 生成 jwt
     *
     * @param map 携带数据
     * @return jwt
     */
    public static String generateToken(Map<String, Object> map) {
        JWT jwt = JWT.create();
        // 设置携带数据
        map.forEach(jwt::setPayload);
        // 设置密钥
        jwt.setKey(UserConstant.JWT_SECRET_KEY);
        // 设置过期时间
        jwt.setExpiresAt(new Date(System.currentTimeMillis() + UserConstant.JWT_EXPIRE * 1000));
        return jwt.sign();
    }

    /**
     * token 校验
     *
     * @param token token
     * @return 是否通过校验
     */
    public static boolean verify(String token) {
        return !StringUtils.isBlank(token) && JWT.of(token).setKey(UserConstant.JWT_SECRET_KEY).verify();
    }

    /**
     * token 校验，并获取 userDto
     *
     * @param token token
     * @return userVO
     */
    public static UserVO verifyAndGetUserVO(String token) {
        if (!verify(token)) {
            return null;
        }
        JWT jwt = JWT.of(token);
        // 验证数据
        if (!jwt.verify()) {
            return null;
        }
        Class<UserVO> voClass = UserVO.class;
        UserVO userVO = new UserVO();
        //利用反射获取字段名
        Arrays.stream(voClass.getDeclaredFields())
                .toList()
                .forEach(field -> {
                    //获取jwt中携带的字段名
                    String fieldName = field.getName();
                    //获取字段类型
                    Class<?> fieldType = field.getType();
                    //获取字段值
                    Object fieldValue = jwt.getPayload(fieldName);
                    //设置字段值
                    try {
                        Method setterMethod = voClass.getMethod("set" + capitalize(fieldName), fieldType);
                        setterMethod.invoke(userVO, fieldValue);
                    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                });
        return userVO;
    }

}
