package abo.community.advice;

import abo.community.exception.CustomizeException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author abo
 * @date 2020/3/1 22:06
 * @remarks
 **/
@ControllerAdvice
public class CustomizeExceptionHandler {
    @ExceptionHandler(Exception.class)
    ModelAndView handler(HttpServletRequest request, Throwable ex, Model model){
        HttpStatus status = getStatus(request);
        if(ex instanceof CustomizeException){
            model.addAttribute("message", ex.getMessage());
        }else{
            model.addAttribute("message", "服务器炸了，请稍后再试 ");
        }
        return new ModelAndView("error");
    }

    private HttpStatus getStatus(HttpServletRequest request){
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if(statusCode == null){
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}
