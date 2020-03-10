package abo.community.dto;

import abo.community.exception.CustomizeErrorCode;
import lombok.Data;

/**
 * @author abo
 * @date 2020/3/3 23:03
 * @remarks
 **/
@Data
public class ResultDTO {
    private Integer code;
    private String message;

    public static ResultDTO errorOf(Integer code, String message){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        return resultDTO;
    }

    public static ResultDTO errorOf(CustomizeErrorCode errorCode){
        return errorOf(errorCode.getCode(), errorCode.getMessage());
    }
}
