package org.jeecgframework.web.sms.util.msg.domain;

public abstract interface MsgCommand
{
  public static final int CMPP_CONNECT = 1;
  public static final int CMPP_CONNECT_RESP = -2147483647;
  public static final int CMPP_TERMINATE = 2;
  public static final int CMPP_TERMINATE_RESP = -2147483646;
  public static final int CMPP_SUBMIT = 4;
  public static final int CMPP_SUBMIT_RESP = -2147483644;
  public static final int CMPP_DELIVER = 5;
  public static final int CMPP_DELIVER_RESP = -2147483643;
  public static final int CMPP_QUERY = 6;
  public static final int CMPP_QUERY_RESP = -2147483642;
  public static final int CMPP_CANCEL = 7;
  public static final int CMPP_CANCEL_RESP = -2147483641;
  public static final int CMPP_ACTIVE_TEST = 8;
  public static final int CMPP_ACTIVE_TEST_RESP = -2147483640;
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.sms.util.msg.domain.MsgCommand
 * JD-Core Version:    0.6.2
 */