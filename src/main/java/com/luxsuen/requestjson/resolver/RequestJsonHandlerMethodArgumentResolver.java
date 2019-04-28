package com.luxsuen.requestjson.resolver;

import com.alibaba.fastjson.JSON;
import com.jayway.jsonpath.JsonPath;
import com.luxsuen.requestjson.util.MapWrapper;
import com.luxsuen.requestjson.common.Const;
import com.luxsuen.requestjson.util.IOUtil;
import net.minidev.json.JSONArray;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RequestJsonHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    /*
     * @Author Lux Sun
     * @Param: [methodParameter]
     * @Return: boolean
     */
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(Const.ANNOTATION_CLASS);
    }

    /*
     * @Author Lux Sun
     * @Param: [methodParameter, modelAndViewContainer, nativeWebRequest, webDataBinderFactory]
     * @Return: java.lang.Object
     */
    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {

        String body = getRequestBody(nativeWebRequest);
        String annotationKey = null;
        Object matchObj = null;

        try {
            annotationKey = methodParameter.getParameterAnnotation(Const.ANNOTATION_CLASS).value();

            int midBracket = body.indexOf('[');
            int bigBracket = body.indexOf('{');
            if(bigBracket > midBracket && midBracket != -1 || midBracket != -1 && bigBracket == -1)
            {
                body = "{\"" + annotationKey + "\":" + body + "}";
            }

            matchObj = JsonPath.read(body, annotationKey);
            if (methodParameter.getParameterAnnotation(Const.ANNOTATION_CLASS).required() && matchObj == null) {
                throw new Exception(annotationKey + "not null");
            }
            else if(matchObj instanceof LinkedHashMap && ((Map)matchObj).size()==0)
            {
                throw new Exception("JSONObject is Null");
            }
            else if(matchObj instanceof JSONArray && ((List)matchObj).size()==0)
            {
                throw new Exception("JSONArray is Null");
            }
        }
        catch (Exception e) {
            if (!methodParameter.getParameterAnnotation(Const.ANNOTATION_CLASS).required()) {
                return null;
            }

            e.printStackTrace();
            return null;
        }

        String matchJsonStr = JSON.toJSONString(matchObj);

        final Type type = methodParameter.getGenericParameterType();

        if(MapWrapper.class.isAssignableFrom(methodParameter.getParameterType())){
            if(matchObj instanceof JSONArray)
            {
                matchJsonStr = "{\"" + annotationKey + "\":" + matchJsonStr + "}";
            }
            MapWrapper mapWrapper = new MapWrapper(JSON.parseObject(matchJsonStr));
            return mapWrapper;
        }
        else {
            Object rsObj = null;
            try {
                rsObj = JSON.parseObject(matchJsonStr, type);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return rsObj;
        }
    }

    /*
     * @Author Lux Sun
     * @Param: [nativeWebRequest]
     * @Return: java.lang.String
     */
    private String getRequestBody(NativeWebRequest nativeWebRequest) {
        HttpServletRequest servletRequest = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        String jsonBody = (String) servletRequest.getAttribute(Const.JSON_REQUEST_BODY);
        if (jsonBody == null) {
            try {
                jsonBody = IOUtil.inputStream2Str(servletRequest.getInputStream(),"UTF-8");
                servletRequest.setAttribute(Const.JSON_REQUEST_BODY, jsonBody);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return jsonBody;
    }
}