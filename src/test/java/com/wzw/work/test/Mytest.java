package com.wzw.work.test;

import ch.qos.logback.core.util.FileUtil;
import com.wzw.work.entity.Person;
import com.wzw.work.entity.User2;
import com.wzw.work.entity.UserInfo;
import com.wzw.work.service.User2Service;
//import com.wzw.work.util.EasyPoiUtil;
import com.wzw.work.service.UserInfoService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Created by wuzhangwei on 2018/8/218:05
 * @Description: TODO
 */
public class Mytest extends BaseTest{

    @Autowired
    private UserInfoService UserInfoService;

    @Test
    public void esTest() throws Exception {

        UserInfo user = new UserInfo();
        user.setId(5L);
        user.setName("章鱼哥");
        user.setAge(26);
        user.setSex("男");
        user.setTel("18212345678");
        user.setCreatetm("2018-9-1 11:28:42");
        user.setDescription("章鱼哥是一个全栈工程师，对任何新技术都怀有浓厚的热情。");
        UserInfoService.insert(user);

        System.out.println("save");
    }

    @Test
    public void esSearch() throws Exception {

        List<UserInfo> userList = UserInfoService.search("张三");
        for (UserInfo user : userList) {
            System.out.println("query user:" + user.getName() + " " + user.getDescription());
        }
    }
    @Test
    public void esDelete() throws Exception {
        UserInfo user = new UserInfo();
        user.setName("唐三");
         UserInfoService.deleteUser(user);

    }


//    @Test
//    public void export(HttpServletResponse response) throws Exception{
//
//        System.out.println("1111111111111111111111111111111111111111");
//        //模拟从数据库获取需要导出的数据
//        List<Person> personList = new ArrayList<>();
//        Person person1 = new Person("路飞","1",new Date());
//        Person person2 = new Person("娜美","2", new Date());
//        Person person3 = new Person("索隆","1", new Date());
//        Person person4 = new Person("小狸猫","1", new Date());
//        personList.add(person1);
//        personList.add(person2);
//        personList.add(person3);
//        personList.add(person4);

        //导出操作
//        EasyPoiUtil.exportExcel(personList,"花名册","草帽一伙",Person.class,"海贼王.xls",response);
   // }

//    @Test
//    public void importExcel(){
//        String filePath = "F:\\海贼王.xls";
//        //解析excel，
//        List<Person> personList = FileUtil.importExcel(filePath,1,1,Person.class);
//        //也可以使用MultipartFile,使用 FileUtil.importExcel(MultipartFile file, Integer titleRows, Integer headerRows, Class<T> pojoClass)导入
//        System.out.println("导入数据一共【"+personList.size()+"】行");
//
//    }

//    }

}
