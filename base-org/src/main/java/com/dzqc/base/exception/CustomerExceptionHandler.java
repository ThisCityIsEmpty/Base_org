package com.dzqc.base.exception;

import com.dzqc.base.entity.AjaxResult;
import com.dzqc.base.util.JsonUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 自定义异常处理器
 */
@Component("exceptionResolver")
public class CustomerExceptionHandler implements HandlerExceptionResolver {

    /**
     *  系统错误信息
     */
    private String SYSTEM_MSG = "数据异常,请稍后再试！";

    /**
     * http请求是否是一步请求
     */
    private Boolean requestIsAjax(HttpServletRequest request){

        String header = request.getHeader("accept");
        String header2 = request.getHeader("X-Requested-With");

        if (StringUtils.isEmpty(header) || StringUtils.isEmpty(header2)){
            return false;
        }

        return (header.indexOf("application/json") > -1 ||
                (header2 != null && header2.indexOf("XMLHttpRequest") > -1)
        );
    }

    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
                                         Object data, Exception exception){
        //非异步请求
        if (!this.requestIsAjax(request)) {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("error");
            // 为安全起见，只有业务异常我们对前端可见，否则否则统一归为系统异常
            if (exception instanceof BusinessException) {
                mav.addObject("error", AjaxResult.fail(exception.getMessage()));

            } else {
                mav.addObject("error", this.SYSTEM_MSG);
            }

            exception.printStackTrace();

            return mav;
        }

        //异步请求
        try {
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter print = response.getWriter();
            if (exception instanceof BusinessException) {
                print.write(JsonUtil.object2Json(AjaxResult.fail(exception.getMessage())));
            } else {
                print.write(JsonUtil.object2Json(AjaxResult.fail(this.SYSTEM_MSG)));
            }
            print.flush();
            print.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }

}
