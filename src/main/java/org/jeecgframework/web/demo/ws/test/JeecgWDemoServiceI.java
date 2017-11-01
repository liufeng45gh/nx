package org.jeecgframework.web.demo.ws.test;

import javax.jws.WebService;

@WebService
public abstract interface JeecgWDemoServiceI
{
  public abstract String say(String paramString);

  public abstract String sayHello();

  public abstract void sayUI();
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.demo.ws.test.JeecgWDemoServiceI
 * JD-Core Version:    0.6.2
 */