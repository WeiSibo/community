package abo.community.dto;

import lombok.Data;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.ArrayList;
import java.util.List;

/**
 * @author abo
 * @date 2020/2/22 21:28
 * @remarks
 **/
@Data
public class PaginationDTO {
    private List<PostDTO> posts;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer currentPage;
    private Integer totalPage;
    private List<Integer> pages = new ArrayList<>();

    public void setPagination(Integer totalPage, Integer page, Integer size){
        if(page > totalPage || page < 1){
            page = 1;
        }
        this.currentPage = page;
        this.totalPage = totalPage;
        pages.add(page);
        for(int i=1;i<=3;i++){
            if(page - i > 0){
                pages.add(0, page-i);
            }
            if(page + i <= totalPage){
                pages.add(page+i);
            }
        }
        //是否展示上一页
        if(page == 1){
            showPrevious = false;
        }else{
            showPrevious = true;
        }
        //是否展示下一页
        if(page == totalPage){
            showNext = false;
        }else{
            showNext = true;
        }
        //是否展示快速第一页
        if(pages.contains(1)){
            showFirstPage = false;
        }else{
            showFirstPage = true;
        }
        //是否展示快速最后一页
        if(pages.contains(totalPage)){
            showEndPage = false;
        }else{
            showEndPage = true;
        }
    }
}
