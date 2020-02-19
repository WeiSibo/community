package abo.community.provider;

import abo.community.dto.AccessToken;
import abo.community.dto.GithubUser;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static okhttp3.Request.*;

@Component
public class GithubProvider {

   public String getAccessToken(@JSONField(name = "accesstoken") AccessToken accessTokenDTO) throws IOException {
       MediaType mediaType = MediaType.get("application/json; charset=utf-8");
       OkHttpClient client = new OkHttpClient();

       RequestBody body = RequestBody.create(JSON.toJSONString(accessTokenDTO), mediaType);
       Request request = new Builder()
               .url("https://github.com/login/oauth/access_token")
               .post(body)
               .build();
       try (Response response = client.newCall(request).execute()) {
           String string = response.body().string();
           String token = string.split("&")[0].split("=")[1];
           return token;
       } catch (Exception e) {
           e.printStackTrace();
       }
       return null;
   }

   public GithubUser getUser(String accessToken) throws IOException {
       OkHttpClient client = new OkHttpClient();
       Request request = new Request.Builder()
               .url("https://api.github.com/user?access_token=" + accessToken)
               .build();
       try {
           Response response = client.newCall(request).execute();
           String string = response.body().string();
           GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
           return githubUser;
       } catch (IOException e) {
       }
       return null;
   }
}
