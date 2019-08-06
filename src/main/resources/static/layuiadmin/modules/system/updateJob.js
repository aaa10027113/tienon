/*
layui.config({
	base : '../../../layuiadmin/' //静态资源所在路径
}).extend({
   formSelects: 'modules/operate/formSelects-v4',
   comExt: 'modules/comExt'
}).use([ 'formSelects','layer','form','comExt'], function() {
	var formSelects = layui.formSelects,
		$ = layui.jquery,
		form = layui.form,
		comExt = layui.comExt,
		layer = parent.layer === undefined ? layui.layer : top.layer;


//------------
});
*/

layui.define([ 'form', 'table', 'layer', 'laydate','comExt','layedit' ], function(
		exports) {
    var form = layui.form
        layer = parent.layer === undefined ? layui.layer : top.layer,
        comExt = layui.comExt,
        $ = layui.jquery,
        layedit = layui.layedit;


	$(function(){
		var id = location.href.split("?")[1];
		$.post("/system/job/queryInfoById",{id:id},function(res) {
			console.log(res);
			if (!res.success) {
				layer.alert(res.msg, {
					icon: 5,
					title: "错误"
				});
				return false;
			}
			var msg = res.obj;
			$("#id").val(msg.id);
			$("#beanName").val(msg.beanName);
			$("#methodName").val(msg.methodName);
			$("#parameter").val(msg.parameter);
			$("#cron").val(msg.cron);
			$("#description").val(msg.description);
			$("#operator").val(msg.operator);
		})
	});

  //获取用户信息
	comExt.ajax({
		url : '/session/getSessionData',//实际使用请改成服务端真实接口
		success : function(data) {
			layui.data('loginInfo', {
				key : 'loginInfo',
				value : data.obj.loginInfo
			});
			var userName = layui.data('loginInfo').loginInfo.userName;
			$('#operator').val(userName);

		}
	});

    form.on("submit(updateJob)",function(data){
    	data.field = comExt.trim(data.field);
    	$.ajax({
            type: "post",
            url: "/job/updateJobInfo",
            data: JSON.stringify(data.field),
            dataType: "json",
            headers: {
            'Content-Type': 'application/json;charset=utf-8'
            },
            success: function(o){
            	if(o.success==true){
            		layer.msg("操作成功");
            		parent.location.reload();
                	
            	}else{
            		layer.msg(o.msg,{time:5*1000});
            		
            	}
            	
               },
        });
        return false;
    });
    $('#close').on('click',function(){
 		layer.confirm("是否退出当前页面？",function(index){
 			//当你在iframe页面关闭自身时
			var index = parent.layer.getFrameIndex(window.name);
	 		parent.layer.close(index);
	 		layer.closeAll();
 		})
 		
 	})

});
