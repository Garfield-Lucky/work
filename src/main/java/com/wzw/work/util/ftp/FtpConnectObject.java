package com.wzw.work.util.ftp;

import lombok.Data;

/**
* @ClassName: FtpConnectObject
* @Description:ftp服务器连接信息
* @author wuzhangwei
* @date 2018年11月21日
*/
@Data
public class FtpConnectObject {
	
	private String ftpUserName;
	private String ftpPassWord;
	private String ftpHostName;
	private String ftpWorkDir;
	private String ftpPort;
    

}