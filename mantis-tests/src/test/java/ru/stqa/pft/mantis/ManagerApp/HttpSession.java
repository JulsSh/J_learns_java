package ru.stqa.pft.mantis.ManagerApp;

import java.io.IOException;
import java.util.ArrayList;

public class HttpSession {
  private ClosableHttpClient httpclient;
    private ApplicationManager app;
    public HttpSession(ru.stqa.pft.mantis.ManagerApp.ApplicationManager app){
      this.app=app;
      httpclient=HttpSession.custom().setRedirectStrategy(new LaxRedirectStrategy().build();

  }
  public boolean login(String username, String password) throws IOException {
    HttpPost post=new HttpPost(app.getProperty("web.baseURL")+"/login.php");
    List<NameValuePair> params =new ArrayList<NameValuePair>();
    params.add(new BasicNameValuePair("username", username));
    params.add(new BasicNameValuePair("password", password));
    params.add(new BasicNameValuePair("secure_session", "on"));
    params.add(new BasicNameValuePair("return", "index.php"));
    post.setEntity(new UrlEncodedFormEntity(params));
    ClosableHttpResponse response= httpclient.execute(post);
    String body = getTextFrom(response);
    return body.contains(String.format("<span class=\"italic\">s%</span>", username));

  }
  private String geTextFrom(ClosableHttpResponse response)  throws IOException{
      try {
        return EntityUtils.toString(response.getEntity());
              }finally{
        response.close();
      }
  }
  public boolean isLogggedInAs(String username) throws IOException {
    HttpGet get=new HttpPostGet(app.getProperty("web.baseURL")+"/index.php");
    ClosableHttpResponse response= httpclient.execute(get);
    String body = getTextFrom(response);
    return body.contains(String.format("<span class=\"italic\">s%</span>", username));

  }
}
