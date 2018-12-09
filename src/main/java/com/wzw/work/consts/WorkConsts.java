package com.wzw.work.consts; /**
 * ClassName:SalesresCenterConsts <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     2017年10月21日 下午9:29:12 <br/>  
 * @author   96949  
 * @version    
 * @since    JDK 1.7  
 * @see        
 */
/**
 * @author 54424
 *
 */
public class WorkConsts {
    
    //oracle当天时间
    public static final String ORACLE_CURR_DATE = "sysdate";
    public static final String DEFUALT_END_DATE = "2030-01-01 12:12:12";

    //默认管理区域
    public static String DEFAULT_MANAGE_REGION_ID = "731";
    
    public static final String DEFAULT_MANAGE_CODE = "10000000";
    
    public static final String DEFAULT_DEPART_ID = "1100";
    
    public static final String DEFAULT_STAFF = "-1";
    
    public static final String MANAGE_MODE_0 = "0";
    public static final String MANAGE_MODE_1 = "1";
    
    //申請类型
    //领用
    public static String APP_TYPE_L = "L";
    //退库
    public static String APP_TYPE_T = "T";
    //下发
    public static String APP_TYPE_X = "X";
    //调拨
    public static String APP_TYPE_J = "J";
    //采购
    public static String APP_TYPE_C = "C";
    //报废
    public static String APP_TYPE_B = "B";
    //退货
    public static String APP_TYPE_H = "H";
    
	public static int DEFAULT_ROW_COUNT = 20;
    
    //出库
    public static String RC_EVENT_IN_OUT_TYPE_OUT = "10";
    //入库
    public static String RC_EVENT_IN_OUT_TYPE_IN = "11";
    
  //实体资源产品
    public static final String PROD_TYPE_CD_AA = "aa";   
    
    //接口操作类型
    public static class ActType{
        public static String ADD = "ADD";
        public static String MOD = "MOD";
        public static String DEL = "DEL";
    }
    
    /***********对数据库操作类型****************/
    public final static String OPER_FLAG_A = "A"; //操作类型为新增/添加
    public final static String OPER_FLAG_D = "D"; //操作类型为删除/取消
    public final static String OPER_FLAG_K = "K"; //操作类型未做修改
    public final static String OPER_FLAG_M = "M"; //操作类型为修改类型
    
    public final static String TRM_NUM_SECT_STATUS_CD_1001 = "1001";//未生成号码
    public final static String TRM_NUM_SECT_STATUS_CD_1101 = "1101";//正在生成号码
    public final static String TRM_NUM_SECT_STATUS_CD_1002 = "1002";//已生成号码
    public final static String TRM_NUM_SECT_STATUS_CD_1003 = "1003";//失效
    
    public final static String AVAILABLE_STATE = "1000"; //有效状态
    public final static String INAVAILABLE_STATE = "1100"; //无效状态
    public final static String CONFIGURING_STATE = "1200"; //配置中
    
    /******仓库使用对象类型******/
    //组织
    public final static String RES_STORE_OBJ_REL_OBJ_TYPE_ORG = "1100";
    //销售品
    public final static String RES_STORE_OBJ_REL_OBJ_TYPE_PROD = "1200";
    //人员
    public final static String RES_STORE_OBJ_REL_OBJ_TYPE_STAFF = "1300";
    //系统
    public final static String RES_STORE_OBJ_REL_OBJ_TYPE_SYS = "1400";
    //区域
    public final static String RES_STORE_OBJ_REL_OBJ_TYPE_AREA = "1500";
    
    /******仓库使用对象类型******/
    
    /******仓库使用对象使用资源范围******/
    //全部
    public final static String USE_MKT_TYPE_ALL = "1000";
    //营销资源类别
    public final static String USE_MKT_TYPE_RES_TYPE = "1100";
    //营销资源
    public final static String USE_MKT_TYPE_RES = "1200";
    /******仓库使用对象类型******/
    
    public final static String TERMINAL_TYPE_3G = "22"; //终端类型3g
    public final static String TERMINAL_TYPE_4G = "23"; //终端类型4g
    
    /**
     * 记录营销资源仓库之间的关系,LOVB=RES-C-0014
	 *	1000 调拨关系
     */
    public static final String REL_TYPE = "1000";
/*    *//******实例资源状态类型******//*
    *//**
     * 已分货未验货
     *//*
    public final static String CURR_STATE_N = "N"; 
    *//**
     * 可用
     *//*
    public final static String CURR_STATE_A = "A"; 
    *//**
     * 预占
     *//*
    public final static String CURR_STATE_E = "E"; 
    *//**
     * 维修
     *//*
    public final static String CURR_STATE_A1 = "A1"; 
    *//**
     * 已售(未补贴)
     *//*
    public final static String CURR_STATE_B = "B"; 
    *//**
     * 已售(已补贴)
     *//*
    public final static String CURR_STATE_b = "b"; 
    *//**
     * 报损
     *//*
    public final static String CURR_STATE_C = "C"; 
    *//**
     * 换货
     *//*
    public final static String CURR_STATE_c = "c"; 
    *//**
     * 退售
     *//*
    public final static String CURR_STATE_H = "H"; 
    *//**
     * 退货
     *//*
    public final static String CURR_STATE_h = "h"; 
    *//**
     * 冻结
     *//*
    public final static String CURR_STATE_Q = "Q"; 
    
    *//**
     * 本省已激活
     *//*
    public final static String CURR_STATE_ZC = "ZC"; 
    *//**
     * 已报损作废
     *//*
    public final static String CURR_STATE_BF = "BF"; 
    *//**
     * 已认领待下发
     *//*
    public final static String CURR_STATE_L = "L"; 
    *//**
     * 销售未激活
     *//*
    public final static String CURR_STATE_SN = "SN"; 
    *//**
     * 销售已激活
     *//*
    public final static String CURR_STATE_SY = "SY"; 
    *//**
     * 待审核
     *//*
    public final static String CURR_STATE_DSH = "DSH"; 
    *//**
     * 回收
     *//*
    public final static String CURR_STATE_G = "G"; 
    *//**
     * 
     *//*
    public final static String CURR_STATE_RA = "RA"; 
    *//******实例资源状态类型******/
    /******实例资源状态类型******/
    /**
     * 已作废
     */
    public final static String CURR_STATE_1110="1110";
    /**
     * 已分货未验货
     */
    public final static String CURR_STATE_N = "1233"; 
    /**
     * 可用
     */
    public final static String CURR_STATE_A = "1000"; 
    /**
     * 预占
     */
    public final static String CURR_STATE_E = "1102"; 
    /**
     * 维修
     */
    public final static String CURR_STATE_A1 = "1111"; 
    /**
     * 已售(未补贴)
     */
    public final static String CURR_STATE_B = "1203"; 
    /**
     * 已售(已补贴)
     */
    public final static String CURR_STATE_b = "1204"; 
    /**
     * 报损
     */
    public final static String CURR_STATE_C = "1107"; 
    /**
     * 换货
     */
    public final static String CURR_STATE_c = "1248"; 
    /**
     * 退售
     */
    public final static String CURR_STATE_H = "1214"; 
    /**
     * 退货
     */
    public final static String CURR_STATE_h = "1205"; 
    /**
     * 冻结
     */
    public final static String CURR_STATE_Q = "1229"; 
    
    /**
     * 本省已激活
     */
    public final static String CURR_STATE_ZC = "1231"; 
    /**
     * 已报损作废
     */
    public final static String CURR_STATE_BF = "1228"; 
    /**
     * 已认领待下发
     */
    public final static String CURR_STATE_L = "1249"; 
    /**
     * 销售未激活
     */
    public final static String CURR_STATE_SN = "1241"; 
    /**
     * 销售已激活
     */
    public final static String CURR_STATE_SY = "1242"; 
    /**
     * 待审核
     */
    public final static String CURR_STATE_DSH = "1246"; 
    /**
     * 回收
     */
    public final static String CURR_STATE_G = "1213"; 
    /**
     * 制卡
     */
    public final static String CURR_STATE_RA = "1234"; 
    /******实例资源状态类型******/
    /********仓库类型********/
    //100 省级库
    public final static String STORE_TYPE_100 = "1000";
    //101 本地网
    public final static String STORE_TYPE_101 = "1100";

    //102 县级库
    public final static String STORE_TYPE_102 = "1298";
    //103 营业班
    public final static String STORE_TYPE_103 = "1297";
    //103 其他
    public final static String STORE_TYPE_999 = "1299";
  //施工班
    public final static String STORE_TYPE_1296 = "1296";
  //本地网下级库
    public final static String STORE_TYPE_1200 = "1200";
  //实物库
    public final static String STORE_TYPE_2100 = "2100";
  //虚拟库
    public final static String STORE_TYPE_2200 = "2200";
    /********仓库类型********/

    /********事件类型*********/
    //108 入库
    public final static String EVENT_TYPE_108 = "108";
    //109 出库
    public final static String EVENT_TYPE_109 = "109";
    //106 调拨
    public final static String EVENT_TYPE_106 = "106";
    //105 营销资源采购
    public final static String EVENT_TYPE_105 = "105";
    //105 作废
    public final static String EVENT_TYPE_102 = "102";
    //101回收
    public final static String EVENT_TYPE_101 = "101";
    //103 更换
    public final static String EVENT_TYPE_103 = "103";
    //107 退货
    public final static String EVENT_TYPE_107 = "107";
    //999 其他
    public final static String EVENT_TYPE_999 = "999";
    
    //1001 入库
    public final static String EVENT_TYPE_IN_STORE = "1001";
    //1002 调拨
    public final static String EVENT_TYPE_ALLOCATE = "1002";
    //1003 订单销售
    public final static String EVENT_TYPE_ORDER_SALE = "1003";
    //1004 领用
    public final static String EVENT_TYPE_USE = "1004";
    //1005 回收
    public final static String EVENT_TYPE_RECYCLE = "1005";
    //1006 回缴
    public final static String EVENT_TYPE_PAY_BACK = "1006";
    //1007 作废
    public final static String EVENT_TYPE_CANCEL = "1007";
    //1008 礼品赠送
    public final static String EVENT_TYPE_GIFT = "1008";
    //1009 返销
    public final static String EVENT_TYPE_SALE_BACK = "1008";
    //1013 裸机销售
    public final static String EVENT_TYPE_BARE_SALE = "1013";
    /********事件类型*********/
    
    /********库存变动事件状态*********/
    //新建
    public final static String STATUS_CD_NEW = "1001";
    //处理中
    public final static String STATUS_CD_PROCESSING = "1002";
    //完成
    public final static String STATUS_CD_COMPLETE = "1003";
    //取消
    public final static String STATUS_CD_CANCEL = "1004";
    /********库存变动事件状态*********/

    /********仓库STATUS_CD*********/
    //有效
    public final static String STORE_STATE_AVALIABLE = "1000";
    //无效
    public final static String STORE_STATE_INAVAILABLE = "1100";
    /********仓库STATUS_CD*********/

    /********仓库REC_TYPE*********/
    //本地网回收
    public final static String STORE_REC_TYPE_LOCAL = "1000";
    /********仓库STATUS_CD*********/
    
    /********仓库STORE_SUB_TYPE*********/
    //终端串码池
    public final static String STORE_SUB_TYPE_TERMINALCODE = "1300";
    //储备号码池
    public final static String STORE_SUB_TYPE_STORENUM = "1101";
    /********仓库STORE_SUB_TYPE*********/
    
    /********仓库STORE_GRADE*********/
    //省级库
    public final static String STORE_GRADE_PROVINCE= "1000";
    //本地网库
    public final static String STORE_GRADE_LOCAL = "1100";
    //本地网下级库
    public final static String STORE_GRADE_SUBLOCAL = "1200";
    /********仓库STORE_GRADE*********/
    
    public final static String OBJECT_TYPE_CD_04= "1004";
    public final static String OBJECT_TYPE_CD_01 = "1001";
    /****资源规格序列化标识****/
    /**
     * 是
     */
    public final static String ORDERED_FLAG_T="1";
    /**
     * 否
     */
    public final static String ORDERED_FLAG_F="2";

    /**********资源类别编码**********/
    public final static String RES_TYPE_NBR_OTHER = "999999000";
    
    
    


    /** 营销资源实例中有效状态 **/
    public static final String RcEntityStateValide = "00A";
    /** 营销资源实例中在途状态 **/
    public static final String RcEntityStateOnuse = "00H";
    /** 营销资源实例中无效状态 **/
    public static final String RcEntityStateInValide = "00X";
    
    /**久库存申请审核状态**/
    //审核通过
    public static final String JKC_AUDIT_STATUS_200 = "200";
    //审核不通过
    public static final String JKC_AUDIT_STATUS_300 = "300";
    //待审核
    public static final String JKC_AUDIT_STATUS_100 = "100";
    /**久库存申请审核状态**/
    
    /**common_region_level**/
    //本地网-97C
    public static final int REGION_LEVEL_20 = 20;
    /**common_region_level**/
    
    /**********营销资源实例状态**************/
    //预占
    public static final String STATUS_CD_PREUSE="1102";
    //实占
    public static final String STATUS_CD_USED="1104";
    //可用
    public static final String STATUS_CD_AVALIABLE="1000";
    
    /**************库存变动事件对象类型*************/
    //1001    资源申请单
    public static final String OBJECT_TYPE_RES_REQUISITION="1001";
    //1002    订单项
    public static final String OBJECT_TYPE_ORDER_ITEM="1002";
    //1003    产品实例
    public static final String OBJECT_TYPE_PRODUCT_INST="1003";
    //1004    销售品实例
    public static final String OBJECT_TYPE_SALES_INST="1004";
    //1005    客户
    public static final String OBJECT_TYPE_CUST="1005";
    //1006    接口
    public static final String OBJECT_TYPE_JK="1006";
    /*************库存变动事件明细出入库类型*****************/
    //1000    出库
    public static final String CHNG_TYPE_OUT_STORE="1000";
    //1100    入库
    public static final String CHNG_TYPE_IN_STORE="1100";



    
    
    //营销资源类别编码前缀
    //移动终端
    public static final String FAMILY_ID_1000_PRE = "101001";
    //电信卡
    public static final String FAMILY_ID_102820_PRE = "103011";
    //礼品
    public static final String FAMILY_ID_1003_PRE = "104002";
    //宽带终端
    public static final String FAMILY_ID_1681_PRE = "104002";
    
    //默认移动终端FamilyId
    //移动终端
    public static final String FAMILY_ID = "1000";
    //移动终端
    public static final String FAMILY_ID_1000 = "1000";
    //电信卡
    public static final String FAMILY_ID_102820 = "102820";
    //礼品
    public static final String FAMILY_ID_1003 = "1003";
    //宽带终端
    public static final String FAMILY_ID_1681 = "1681";
    
    //资源类别等级
    public static final String MKT_RES_TYPE_LEVEL_REG_1 = "[0-9]{3}0{6}";
    public static final String MKT_RES_TYPE_LEVEL_REG_2 = "[0-9]{6}0{3}";
    
    //营销资源类别属性——FAMILY_ID
    public static final String MKT_RES_TYPE_ATTR_FAMILY_ID = "1000";
    
    public static final String MKT_RES_ATTR_VALUE_SPERATOR = "/";
    //营销资源实体属性
    public static final String ATTR_OWNER_TYPE_1 = "1";
    //营销资源规格属性
    public static final String ATTR_OWNER_TYPE_0 = "0";
    
    //根节点父亲
    public static final String ROOT_STORAGE_PAR_ID = "-1";
    //申请单类型
    /**
     * 入库申请单
     */
    public static final String MKT_RES_REQUEST_TYPE_1000="1000";
    /**
     * 出库申请单
     */
    public static final String MKT_RES_REQUEST_TYPE_2000="2000";
    /**
     * 调拨申请单
     */
    public static final String MKT_RES_REQUEST_TYPE_3000="3000";
    
    /**
     * 营销资源申请单状态
     */
    //新建
    public static final String MKT_RES_REQUEST_STATUS_1001 = "1001";
    ///处理中
    public static final String MKT_RES_REQUEST_STATUS_1002 = "1002";
    //完成
    public static final String MKT_RES_REQUEST_STATUS_1003 = "1003";
    //取消
    public static final String MKT_RES_REQUEST_STATUS_1004 = "1004";
    
    /**
     * 营销资源状态
     */
    //新建
    public static final String MKT_RES_REQ_ITEM_STATUS_1001 = "1001";
    
    /**
     * 营销资源数据通用操作状态-保持
     */
    public static final String MKT_RES_ACT_TYPE_KIP = "KIP";
	/**
     * 营销资源数据通用操作状态-新增
     */
    public static final String MKT_RES_ACT_TYPE_ADD = "ADD";
	/**
     * 营销资源数据通用操作状态-删除
     */
    public static final String MKT_RES_ACT_TYPE_DEL = "DEL";
    
    //查询号码实例状态静态数据配置
    public static final String MKT_RES_NUM_INST_STATUS_CD_STYPE = "668";
    public static final String MKT_RES_NUM_INST_STATUS_CD_PKEY = "668";
    
    //查询营销资源号码实例出标识
    public static final String MKT_RES_NUM_INST_OUT_MARK_CONFIG_STYPE = "20160920";
    public static final String MKT_RES_NUM_INST_OUT_MARK_CONFIG_PKEY = "1";
    
    //号码等级管理模式
    //0表示全省模式(bureau_no为*)
    public static final String MKT_RES_NUM_INST_LEVEL_FLAG_0 = "0";
    //1表示本地网络模式
    public static final String MKT_RES_NUM_INST_LEVEL_FLAG_1 = "1";
    
    public static class DcSystemParamCode{
    	public static String STATUS_CAN_PRE_OCC_NUM = "STATUS_CAN_PRE_OCC_NUM";
        public static String STATUS_CAN_PRE_USE_NUM = "STATUS_CAN_PRE_USE_NUM";
        public static String STATUS_CAN_RELEASE_NUM = "STATUS_CAN_RELEASE_NUM";
        public static String STATUS_CAN_PRE_USE_CARD = "STATUS_CAN_PRE_USE_CARD";
        public static String STATUS_CAN_PRE_SELECT_NUM = "STATUS_CAN_PRE_SELECT_NUM";
        public static String DAYS_NUM_PRE_SELECT = "DAYS_NUM_PRE_SELECT";
        public static String STATUS_CAN_RELEASE_CARD = "STATUS_CAN_RELEASE_CARD";
        public static String VALID_STATUS_OF_PRE_SELECT_NUM = "VALID_STATUS_OF_PRE_SELECT_NUM";
        public static String LIMIT_TIME_TO_RELEASE_PREUSEDNUM = "LIMIT_TIME_TO_RELEASE_PREUSEDNUM";
        public static String LIMIT_TIME_TO_RELEASE_PRESELECTEDNUM = "LIMIT_TIME_TO_RELEASE_PRESELECTEDNUM";
    }
    
    public static class MKT_RES_NUM_INST_STATUS_CD{
        //缓放4
        public static String SLOW_RELEASE = "1120";
        public static String FREE = "1002";
    }
    
    //号码实例M类型
    public static long NUM_INST_MKT_RES_TYPE_CDMA = 1004;
    public static long NUM_INST_MKT_RES_TYPE_ALL = -1;

	//UIM卡审核状态
	public static final String UIM_CARD_AUDIT_WAIT_CROSS = "0";
	public static final String UIM_CARD_AUDIT_CROSS = "1";
	public static final String UIM_CARD_AUDIT_UNCROSS = "2";
	public static final String UIM_CARD_AUDIT_USABLE = "3";
	
	public static final String TRM_NUMBER_LEVEL_ALL_LAN = "-1"; 
	

	/**卡文件导入中*/
	public static final String UIM_CARD_IMPORT_ING = "0";	
	/**卡文件导入失败*/
	public static final String UIM_CARD_IMPORT_FAIL = "-1";	
	/**卡文件导入成功*/
	public static final String UIM_CARD_IMPORT_SUCCESS = "1";
	
	/**营销资源卡实例状态_不可用*/
	public static final String RES_C_0017_1100 = "1100";
	
	//sim卡导入配置
	public static class SIM_CARD_IMPORT_FTP_CFG{
	    public static final String USERNUME = "SIM_CARD_IMPORT_USERNAME";
	    public static final String PASSWORD = "SIM_CARD_IMPORT_PASSWORD";
	    public static final String HOSTNAME = "SIM_CARD_IMPORT_HOSTNAME";
	    public static final String WORK_DIR = "SIM_CARD_IMPORT_WORKDIR";
	    public static final String PORT = "SIM_CARD_IMPORT_PORT";
	    //备份目录
	    public static final String BACK_UP_WORK_DIR = "TMP_LOCAL_WORK_DIR";
	}
	
	//sim卡上传配置
	public static class SIM_CARD_UPLOAD_FTP_CFG{
	    public static final String USERNUME = "SIM_CARD_UPLOAD_USERNAME";
	    public static final String PASSWORD = "SIM_CARD_UPLOAD_PASSWORD";
	    public static final String HOSTNAME = "SIM_CARD_UPLOAD_HOSTNAME";
	    public static final String WORK_DIR = "SIM_CARD_UPLOAD_WORKDIR";
	    public static final String PORT = "SIM_CARD_UPLOAD_PORT";
	}
	
	public static class BLACK_CARD_DEAL_FTP_CFG{
	    public static final String USERNUME = "BLACK_CARD_DEAL_USERNAME";
	    public static final String PASSWORD = "BLACK_CARD_DEAL_PASSWORD";
	    public static final String HOSTNAME = "BLACK_CARD_DEAL_HOSTNAME";
	    public static final String WORK_DIR = "BLACK_CARD_DEAL_WORKDIR";
	    public static final String PORT = "BLACK_CARD_DEAL_PORT";
	    public static final String CANCEL = "BLACK_CARD_DEAL_CANCEL";
	    public static final String SIGN = "BLACK_CARD_DEAL_SIGN";
	}
	
	public static class IF_MKT_RES_NUM_INST_STATUS_CD_CAN_SWITCH{
	    public static final String CAN = "1";
	    public static final String CANT = "0";
	}
	
	/**号码实例状态：在途*/
	public static final String RES_C_0023_2000 = "2000";
	
	/**卡实例状态：在途*/
	public static final String RES_C_0017_1110 = "1110";
	
	/**stype定义：卡功能*/
	public static final String RES_UIM_FUNC_TYPE = "2018072701";
	
	/**stype定义：省份*/
	public static final String PROVINCE_NBR = "2018082301";
	
	/**stype定义：卡功能小类*/
	public static final String RES_UIM_FUNC_SUB_TYPE = "2018072702";
	
	/**UIM卡默认入的仓库ID*/
	public static final String RES_UIM_DEFAULT_STORE = "RES_UIM_DEFAULT_STORE";
	
	/**卡资源ID*/
	public static final String RES_UIM_RES_ID = "RES_UIM_RES_ID";
	
	/**任务状态：待处理*/
	public static final String TASK_STATUS_CD_1000 = "1000";
	/**任务状态：处理中*/
	public static final String TASK_STATUS_CD_1001 = "1001";
	/**任务状态：处理完成*/
	public static final String TASK_STATUS_CD_1002 = "1002";
	/**任务状态：处理失败*/
	public static final String TASK_STATUS_CD_1004 = "1004";
	/**任务状态：失效*/
	public static final String TASK_STATUS_CD_1005 = "1005";
	
	/**号码类型：移动*/
	public static final String RES_NUM_TYPE_100001000 = "100001000";
	/**号码类型：固话*/
	public static final String RES_NUM_TYPE_100003000 = "100003000";
	
	/**省份 重庆*/
	public static final String CQ = "CQ";
	
	/**号卡网络  2G*/
	public static final String LEN_GEN_1002 = "1002";
	/**号卡网络  3G*/
	public static final String LEN_GEN_1003 = "1003";
	/**号卡网络  4G*/
	public static final String LEN_GEN_1004 = "1004";
	/**号卡网络  5G*/
	public static final String LEN_GEN_1005 = "1005";
	
	/**sim卡文件分片上传大小*/
	public static final long SIM_CHUNK_SIZE = 1048576;
	
	/**sim卡文件分片上传FTP存放路径前缀*/
	public static final String SIM_FTPDIR_PREFIX = "sim";
	
	/**sim卡文件分片上传应用服务器临时存放路径*/
	public static final String SIM_DIR_TEMP = "E:\\temp\\";
	
	/**sim卡文件分片上传结束标志*/
	public static final int SIM_CHUNK_FINISH = 200;
	
	/**sim上传任务名称前缀*/
	public static final String SIM_TASK_NAME_PREFIX = "ImportCardsTask";
	
	
	
}
  
