package abo.community;

import abo.community.entity.Post;
import abo.community.mapper.PostMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CommunityApplicationTests {

    @Autowired
    private PostMapper postMapper;

    @Test
    void test() {
        //测试mp分页
        QueryWrapper<Post> queryWrapper = new QueryWrapper<>();
        Page<Post> page = new Page<>(3, 5);
        IPage<Post> iPage = postMapper.selectPage(page, queryWrapper);
        List<Post> postList = iPage.getRecords();
        for(Post post: postList){
            System.out.println(post);
        }
    }

}
