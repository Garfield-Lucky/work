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
                    if (msgJson.Type && msgJson.Type == "error" && msgJson.Message){
                        top.layui.layer.msg(msgJson.Message,{
                            time: 2000,
                        });
                        return;
                    }else if(msgJson.Type=="Y"){
                        top.layui.layer.msg(msgJson.Message,{
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
        content: '<iframe frameborder="0" name="home" id="home" style="overflow-x:hidden; overflow-y:auto;padding:10px;background-color:#06222e;width:98%;height:774px;"  src="'+url+'"></iframe>',
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
 * @param form 表单对象  type 关闭的类型 0或'' 关闭tab或弹出层  1：仅关闭弹出层并刷新父页面的数据列表
 * @author wzw 20180910
 */
function ajaxSave(form,type) {
    var index = top.layui.layer.msg('保存中...', {
        icon: 16
        ,shade: 0.01
    });
    $.ajax({
        type: "POST",
        url: $(form).attr("action"),
        data: $(form).serialize(),
        dataType: "json",
        cache: false,
        success: function(msgJson){
            top.layui.layer.close(index);
            if (!msgJson || !msgJson.length){
                if (msgJson.Type && msgJson.Type == "error" && msgJson.Message){
                    top.layui.layer.msg(msgJson.Message);
                    return;
                }else if(msgJson.Type=="Y"){
                    top.layui.layer.msg(msgJson.Message,{time:2000});
                    setTimeout(function () {
                        top.layui.layer.closeAll('dialog');
                        if(type==0||type==null||type=='')
                        {
                            //关闭当前tab
                            deleteTab(tabId);
                            x_admin_close();
                            parent.$('#reload').click();

                        }else if(type==1){ //需要刷新父页面的table
                            x_admin_close();
                            parent.$('#reload').click();
                        }else{
                            x_admin_close();
                        }

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
                if (msgJson.Type && msgJson.Type == "error" && msgJson.Message){
                    top.layui.layer.msg(msgJson.Message);
                    return;
                }else if(msgJson.Type=="Y"){
                    top.layui.layer.msg(msgJson.Message);
                    setTimeout(function () {
                        top.layui.layer.closeAll('dialog');
                        deleteTab(tabId);
                        x_admin_close();
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

/*弹出层*/
/*
    参数解释：
    title   标题
    url     请求的url
    id      需要操作的数据id
    w       弹出层宽度（缺省调默认值）
    h       弹出层高度（缺省调默认值）
*/
function x_admin_show(title,url,w,h){
    if (title == null || title == '') {
        title=false;
    };
    if (url == null || url == '') {
        url="404.html";
    };
    if (w == null || w == '') {
        w=890;
    };
    if (h == null || h == '') {
        h=($(window).height() - 50);
    };
    top.layui.layer.open({
        type: 2,
        area: [w+'px', h +'px'],
        fix: false, //不固定
        maxmin: true,
        shadeClose: true,
        shade:0.4,
        title: title,
        content: url
    });
}

/*关闭弹出框口*/
function x_admin_close(){
    var index = parent.top.layui.layer.getFrameIndex(window.name);
    parent.top.layui.layer.close(index);
}
