/**
 * 公用函数
 * 删除表格数据用
 * @param postUrl 提交的URL
 * @param rowids 要删除的数据ID值,为多值，如1,2,3,4,5
 * @param table 表格
 * @param tableId 表格的id
 * @author wzw 20180829
 */
function delCheckedData(postUrl,rowids,table,tableId){
    top.layui.layer.confirm('确认要删除吗?',function (index){
        top.layui.layer.close(index);
        $.ajax({
            type: "POST",
            url: postUrl,
            dataType: "json",
            cache: false,
            data: "ids="+rowids,
            beforeSend:function(){
                top.layui.layer.msg('正在删除中');
            },
            success: function(msgJson){
                if (!msgJson || !msgJson.length){
                    if (msgJson.status && msgJson.status == "error" && msgJson.message){
                        top.layui.layer.msg(msgJson.message,{
                            time: 2000,
                        });
                        return;
                    }else if(msgJson.status=="success"){
                        top.layui.layer.msg(msgJson.message,{
                            time: 1000
                        });
                        //刷新列表
                        table.reload(tableId, {
                            page : {
                                curr : 1
                                //重新从第 1 页开始
                            }
                        });
                    }
                }
            },
            error: function ()
            {
                top.layui.layer.msg('发送系统错误,请与系统管理员联系!',{
                    time: 2000,
                });
            }
        });
    });

}


/**
 * 公用函数
 * 打开新的tab
 * @param title tab的标题
 * @param url 要打开的接口路径
 * @param id tab的唯一id
 * @author wzw 20180829
 */
function addTab(title,url,id){

    var index = top.layui.layer.load(1, {shade: false}); //0代表加载的风格，支持0-2
    if(id==null||id==''){
        id=new Date().getTime();
    }
    var card = 'mytab';
    parent.element.tabAdd(card, {
        title: '<span>'+title+'</span>',
        content: '<iframe frameborder="0" name="home" id="home" width="100%" height="600px" src="'+url+'"></iframe>',
        id: id
    });

    parent.element.tabChange(card, id);
    parent.element.init();
    top.layui.layer.close(index);
}

/**
 * 公用函数
 * 打开新的tab 内容是html
 * @param title tab的标题
 * @param url 要打开的接口路径
 * @param id tab的唯一id
 * @author wzw 20180829
 */
function addTabHtml(title,url,id){

    var index = top.layui.layer.load(1, {shade: false}); //0代表加载的风格，支持0-2
    if(id==null||id==''){
        id=new Date().getTime();
    }
    var card = 'mytab';
    parent.element.tabAdd(card, {
        title: '<span style="color:#1E9FFF">'+title+'</span>',
        content:url,
        id: id
    });

    parent.element.tabChange(card, id);
    parent.element.init();
    top.layui.layer.close(index);
}



/**
 * 公用函数
 * 删除tab
 * @param id tab的唯一id
 * @author wzw 20180829
 */
function deleteTab(id){
    var card = 'mytab';
    parent.element.tabDelete(card, id);

}

/**
 * 公用函数
 * 提交表单
 * @param form 表单对象
 * @author wzw 20180910
 */
function ajaxSave(form) {
    var index = top.layui.layer.msg('保存中...', {
        icon: 16
        ,shade: 0.01
    });
    $.ajax({
        type: "POST",
        url: $("form").attr("action"),
        data: $("form").serialize(),
        dataType: "json",
        cache: false,
        success: function(msgJson){
            top.layui.layer.close(index);
            if (!msgJson || !msgJson.length){
                if (msgJson.status && msgJson.status == "error" && msgJson.message){
                    top.layui.layer.msg(msgJson.message);
                    return;
                }else if(msgJson.status=="success"){
                    top.layui.layer.msg(msgJson.message,{time:2000});
                    setTimeout(function () {
                        top.layui.layer.closeAll('dialog');
                        //关闭当前tab
                        deleteTab(tabId);
                    }, 1500);

                }
            }
        },
        error: function(){
            top.layui.layer.closeAll('loading');
            top.layui.layer.msg("保存出错，请联系管理员!");
        }
    });
    return false;
}

/**
 * 公用函数
 * 提交表单  如果页面不存在表单Form；jquery引入版本不正确；jquery.form没有引入的情况下，跳过初始化
 * @param form 表单对象
 * @author wzw 20180912
 */

$(document).ready(function() {
    if(!$('form:first') || !$('form:first').ajaxForm)
        return;
    $('form:first').ajaxForm({
        type: "POST",
        cache: false,
        beforeSubmit:function(formData, form, options){
            top.layui.layer.msg('保存中...', {
                icon: 16
                ,shade: 0.01
            });
        },
        success: function(msgJson){
            top.layui.layer.closeAll('loading');
            if (!msgJson || !msgJson.length){
                if (msgJson.status && msgJson.status == "error" && msgJson.message){
                    top.layui.layer.msg(msgJson.message);
                    return;
                }else if(msgJson.status=="success"){
                    top.layui.layer.msg(msgJson.message);
                    setTimeout(function () {
                        deleteTab(tabId);
                        top.layui.layer.closeAll('dialog');
                    }, 1500);

                }

            }
        },
        error: function(){
            top.layui.layer.closeAll('loading');
            top.layui.layer.msg("保存出错，请联系管理员!");
        }

    });
});
