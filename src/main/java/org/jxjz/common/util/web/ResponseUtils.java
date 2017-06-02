package org.jxjz.common.util.web;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;


/**
 * 【功能说明】：Response  通用处理工具类
 * @author lihaijun
 * 【创建时间】2017-05-25
 */
public final class ResponseUtils
{

  public static void renderText(HttpServletResponse response, String text)
  {
    render(response, "text/html;charset=UTF-8", text);
  }

  public static void renderJson(HttpServletResponse response, String text)
  {
    render(response, "application/json;charset=UTF-8", text);
  }

  public static void renderXml(HttpServletResponse response, String text)
  {
    render(response, "text/xml;charset=UTF-8", text);
  }

  public static void render(HttpServletResponse response, String contentType, String text)
  {
	System.out.println(text);
    response.setContentType(contentType);
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-cache, must-revalidate");
	response.setHeader("Pragma", "no-cache");
    try {
      response.getWriter().write(text);
	  response.getWriter().flush();
	  response.getWriter().close();
    } catch (IOException e) {
    }
  }
}