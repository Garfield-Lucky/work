package com.wzw.work.util.ftp;

import java.io.IOException;
import lombok.extern.slf4j.Slf4j;

/**  
 * ClassName:FtpCfgFactory <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     2018年6月23日 下午4:46:47 <br/>  
 * @author   96949  
 * @version    
 * @since    JDK 1.7  
 * @see        
 */
@Slf4j
public class FtpCfgFactory {

    public static FtpUtil getFtp(FtpCfg cfg) throws IOException{
        if(FtpCfg.simCardImportFtp.key.equals(cfg.key)){
            return getDefaultFtp();
        }
        return null;
    }
    
    public static FtpUtil getMyFtp(FtpConnectObject obj) throws IOException{
  
    	if(obj.getFtpWorkDir() == null){
    		return null;
    	}
    	String ftpUserName = "test1";
        String ftpPassWord = "123456";
        String ftpHostName = "192.168.0.12";
        String ftpPort = "21";
      
    	obj.setFtpHostName(ftpHostName);
    	obj.setFtpUserName(ftpUserName);
    	obj.setFtpPassWord(ftpPassWord);
    	obj.setFtpPort(ftpPort);
        log.info(ftpUserName + "|" + "|" + ftpHostName + "|" + obj.getFtpWorkDir() + "|" + ftpPort);
        return new FtpUtil(obj.getFtpHostName(), obj.getFtpPort(), obj.getFtpUserName(), obj.getFtpPassWord(), obj.getFtpWorkDir());
    }
    
    private static FtpUtil getDefaultFtp() throws IOException{
        String ftpUserName = "";
        String ftpPassWord = "";
        String ftpHostName = "";
        String ftpWorkDir = "";
        String ftpPort = "";
        log.info(ftpUserName + "|" + "|" + ftpHostName + "|" + ftpWorkDir + "|" + ftpPort);
        return new FtpUtil(ftpHostName, ftpPort, ftpUserName, ftpPassWord, ftpWorkDir);
    }
    

    
    public enum FtpCfg{
        simCardImportFtp("simCardImport"), balckCardDealFtp("balckCardDealFtp");
       
        public String key;
        private FtpCfg(String key){
            this.key = key;
        }
    }
}
  
