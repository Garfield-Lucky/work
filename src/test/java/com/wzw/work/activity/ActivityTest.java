package com.wzw.work.activity;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.history.HistoricIdentityLink;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @author Created by wuzhangwei on 2018/8/519:11
 * @Description: TODO
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ActivityTest {



    @Test
    public void deploymentProcessDefinition() {
        //创建核心引擎对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        Deployment deployment = processEngine.getRepositoryService()// 与流程定义和部署对象相关的service
                .createDeployment()// 创建一个部署对象
                .name("审批测试3")// 添加部署的名称
                .addClasspathResource("bpmn/test3.bpmn")// classpath的资源中加载，一次只能加载
                // 一个文件
                .addClasspathResource("bpmn/test3.png")// classpath的资源中加载，一次只能加载
                // 一个文件
                .deploy();// 完成部署
        System.out.println("部署ID:" + deployment.getId());
        System.out.println("部署名称：" + deployment.getName());

    }

    /**
     * 启动流程实例
     */
    @Test
    public void startProcessInstance() {
        // 流程定义的key
        String processDefinitionKey = "myProcess_test3";
        Map<String, Object> map =new HashMap<String, Object>();
        map.put("employer","张三");
        map.put("manager","李四,王五,赵六");
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        ProcessInstance pi = processEngine.getRuntimeService()// 于正在执行的流程实例和执行对象相关的Service
                .startProcessInstanceByKey(processDefinitionKey,map);// 使用流程定义的key启动流程实例，key对应hellworld.bpmn文件中id的属性值，使用key值启动，默认是按照最新版本的流程定义启动
        System.out.println("流程实例ID:" + pi.getId());// 流程实例ID 101
        System.out.println("流程定义ID:" + pi.getProcessDefinitionId()); // 流程定义ID HelloWorld:1:4
        try {
            //让主线程休息10s
            Thread.sleep(5000);
        }catch(Exception e){
            System.out.println("error");
        }
    }

    /**
     * 查询当前人的个人任务
     */
    @Test
    public void findMyPersonTask() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        String assignee = "张三";
        List<Task> list = processEngine.getTaskService()// 与正在执行的认为管理相关的Service
                .createTaskQuery()// 创建任务查询对象
                .taskAssignee(assignee)// 指定个人认为查询，指定办理人
//                .taskCandidateUser(assignee)//指定组任务查询
                .list();

        if (list != null && list.size() > 0) {
            for (Task task:list) {
                System.out.println("任务ID:"+task.getId());
                System.out.println("任务名称:"+task.getName());
                System.out.println("任务的创建时间"+task);
                System.out.println("任务的办理人:"+task.getAssignee());
                System.out.println("流程实例ID:"+task.getProcessInstanceId());
                System.out.println("执行对象ID:"+task.getExecutionId());
                System.out.println("流程定义ID:"+task.getProcessDefinitionId());
                System.out.println("#################################");
            }
        }
    }

    //查询组任务成员列表

    @Test
    public void findGroupUser(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        String taskId = "102502";

        List<IdentityLink> list = processEngine.getTaskService()//

                .getIdentityLinksForTask(taskId);

        for(IdentityLink identityLink:list ){

            System.out.println("userId="+identityLink.getUserId());

            System.out.println("taskId="+identityLink.getTaskId());

            System.out.println("piId="+identityLink.getProcessInstanceId());

            System.out.println("######################");

        }

    }

//查询组任务成员历史列表

    @Test
    public void findGroupHisUser(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        String taskId = "102502";

        List<HistoricIdentityLink> list = processEngine.getHistoryService()//

                .getHistoricIdentityLinksForTask(taskId);

        for(HistoricIdentityLink identityLink:list ){

            System.out.println("userId="+identityLink.getUserId());

            System.out.println("taskId="+identityLink.getTaskId());

            System.out.println("piId="+identityLink.getProcessInstanceId());

            System.out.println("######################");

        }

    }

    @Test
    public void claim(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //任务ID
        String taskId = "102502";
        //分配的办理人
        String userId = "王五";
        processEngine.getTaskService()//
                .claim(taskId, userId);

    }

    //可以分配个人任务回退到组任务，（前提之前是个组任务）

    @Test
    public void setAssigneeTask(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //任务ID
        String taskId = "102502";
        processEngine.getTaskService()//
                .setAssignee(taskId, null);

    }

//向组任务中添加成员

    @Test
    public void addUser(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        String taskId = "102502";

        String userId = "项羽";

        processEngine.getTaskService().addCandidateUser(taskId, userId);

    }



//向组任务中删除成员

    @Test
    public void removeUser(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        String taskId = "102502";

        String userId = "刘邦";

        processEngine.getTaskService().deleteCandidateUser(taskId, userId);

    }

    /**
     * 完成我的任务
     */
    @Test
    public void completeMyPersonTask(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //任务Id
        String taskId="110006";
        processEngine.getTaskService()//与正在执行的认为管理相关的Service
                .complete(taskId);
        System.out.println("完成任务:任务ID:"+taskId);

    }

    /**
     * 查询所有的流程定义
     */
    @Test
    public void findProcessDefinition() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        List<ProcessDefinition> list = processEngine.getRepositoryService()// 与流程定义和部署对象先相关的service
                .createProcessDefinitionQuery()// 创建一个流程定义的查询
                /** 指定查询条件，where条件 */
                // .deploymentId(deploymentId) //使用部署对象ID查询
                // .processDefinitionId(processDefinitionId)//使用流程定义ID查询
                // .processDefinitionNameLike(processDefinitionNameLike)//使用流程定义的名称模糊查询

                /* 排序 */
                .orderByProcessDefinitionVersion().asc()
                // .orderByProcessDefinitionVersion().desc()

                /* 返回的结果集 */
                .list();// 返回一个集合列表，封装流程定义
        // .singleResult();//返回惟一结果集
        // .count();//返回结果集数量
        // .listPage(firstResult, maxResults);//分页查询

        if (list != null && list.size() > 0) {
            for (ProcessDefinition pd : list) {
                System.out.println("流程定义ID:" + pd.getId());// 流程定义的key+版本+随机生成数
                System.out.println("流程定义的名称:" + pd.getName());// 对应helloworld.bpmn文件中的name属性值
                System.out.println("流程定义的key:" + pd.getKey());// 对应helloworld.bpmn文件中的id属性值
                System.out.println("流程定义的版本:" + pd.getVersion());// 当流程定义的key值相同的相同下，版本升级，默认1
                System.out.println("资源名称bpmn文件:" + pd.getResourceName());
                System.out.println("资源名称png文件:" + pd.getDiagramResourceName());
                System.out.println("部署对象ID：" + pd.getDeploymentId());
                System.out.println("#########################################################");
            }
        }
    }


    /**
     * 附加功能，查询最新版本的流程定义
     */
    @Test
    public void findLastVersionProcessDefinition() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        List<ProcessDefinition> list = processEngine.getRepositoryService()
                .createProcessDefinitionQuery()
                .orderByProcessDefinitionVersion().asc() // 使用流程定义的版本升序排列
                .list();

        /**
         * Map<String,ProcessDefinition> map集合的key：流程定义的key map集合的value：流程定义的对象
         * map集合的特点：当map集合key值相同的情况下，后一次的值将替换前一次的值
         */
        Map<String, ProcessDefinition> map = new LinkedHashMap<String, ProcessDefinition>();
        if (list != null && list.size() > 0) {
            for (ProcessDefinition pd : list) {
                map.put(pd.getKey(), pd);
            }
        }

        List<ProcessDefinition> pdList = new ArrayList<ProcessDefinition>(
                map.values());
        if (pdList != null && pdList.size() > 0) {
            for (ProcessDefinition pd : pdList) {
                System.out.println("流程定义ID:" + pd.getId());// 流程定义的key+版本+随机生成数
                System.out.println("流程定义的名称:" + pd.getName());// 对应helloworld.bpmn文件中的name属性值
                System.out.println("流程定义的key:" + pd.getKey());// 对应helloworld.bpmn文件中的id属性值
                System.out.println("流程定义的版本:" + pd.getVersion());// 当流程定义的key值相同的相同下，版本升级，默认1
                System.out.println("资源名称bpmn文件:" + pd.getResourceName());
                System.out.println("资源名称png文件:" + pd.getDiagramResourceName());
                System.out.println("部署对象ID：" + pd.getDeploymentId());
                System.out.println("#########################################################");
            }
        }
    }

    /**
     * 查看流程图
     */
    @Test
    public void viewPic() throws IOException {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 将生产的图片放到文件夹下
        String deploymentId = "10052";//
        // 获取图片资源名称
        List<String> list = processEngine.getRepositoryService()
                .getDeploymentResourceNames(deploymentId);

        // 定义图片资源名称
        String resourceName = "";
        if (list != null && list.size() > 0) {
            for (String name : list) {
                if (name.indexOf(".png") >= 0) {
                    resourceName = name;
                }
            }
        }

        // 获取图片的输入流
        InputStream in = processEngine.getRepositoryService()
                .getResourceAsStream(deploymentId, resourceName);

        File file = new File("D:/" + resourceName);
        // 将输入流的图片写到D盘下
//        FileUtils.copyInputStreamToFile(in, file);
    }

    /**
     * 删除流程定义(删除key相同的所有不同版本的流程定义)
     */
    @Test
    public void delteProcessDefinitionByKey() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 流程定义的Key
        String processDefinitionKey = "myProcess_test3";
        // 先使用流程定义的key查询流程定义，查询出所有的版本
        List<ProcessDefinition> list = processEngine.getRepositoryService()
                .createProcessDefinitionQuery()
                .processDefinitionKey(processDefinitionKey)// 使用流程定义的key查询
                .list();
        // 遍历，获取每个流程定义的部署ID
        if (list != null && list.size() > 0) {
            for (ProcessDefinition pd : list) {
                // 获取部署ID
                String deploymentId = pd.getDeploymentId();
                //		/*
                //		 * 不带级联的删除， 只能删除没有启动的流程，如果流程启动，就会抛出异常
                //		 */
                //		 processEngine.getRepositoryService().deleteDeployment(deploymentId);
                System.out.println("deploymentId:"+deploymentId);
                /**
                 * 级联删除 不管流程是否启动，都可以删除
                 */
                //processEngine.getRepositoryService().deleteDeployment(deploymentId, true);

            }

        }
    }



    }


