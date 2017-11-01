package org.jeecgframework.web.demo.service.impl.test;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.web.demo.service.test.JpPersonServiceI;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("jpPersonService")
@Transactional
public class JpPersonServiceImpl extends CommonServiceImpl
  implements JpPersonServiceI
{
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.demo.service.impl.test.JpPersonServiceImpl
 * JD-Core Version:    0.6.2
 */