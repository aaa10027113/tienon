layui.config({
	base : '../../../layuiadmin/' // 静态资源所在路径
}).extend({
   formSelects: 'modules/business/formSelects-v4',
   comExt: 'modules/comExt'
}).use([ 'formSelects','layer','form','comExt'], function() {
	var formSelects = layui.formSelects,
		$ = layui.jquery,
		form = layui.form,
		comExt = layui.comExt,
		layer = parent.layer === undefined ? layui.layer : top.layer;
    // 计算总金额
    $(document).click(function(){
	　　var acceptPrice = $("#acceptType").find("option:selected").val();
	   var addTypeCount = formSelects.value('select1').length*30;
	   var sum = parseFloat(acceptPrice.split(";")[1])+parseFloat(addTypeCount);
	   $("#amt").val(sum);
　　　})
   $(document).mouseout(function(){
	　　var acceptPrice = $("#acceptType").find("option:selected").val();
	   var addTypeCount = formSelects.value('select1').length*30;
	   var sum = parseFloat(acceptPrice.split(";")[1])+parseFloat(addTypeCount);
	   $("#amt").val(sum);
　　　})
});

layui.define([ 'form', 'table', 'layer', 'laydate','comExt','layedit' ], function(
		exports) {
    var form = layui.form
        layer = parent.layer === undefined ? layui.layer : top.layer,
        comExt = layui.comExt,
        $ = layui.jquery,
        layedit = layui.layedit;
    
    $(function(){
    	$.ajax({
            type: "post",
            url: "/business/menu/selectMenuInfo",
            data: {},
            dataType: "json",
            headers: {
            'Content-Type': 'application/json;charset=utf-8'
            },
            success: function(msg){
            	
            	if(msg!=null){
            		var value ="";
            	　　　for (var i = 0; i < msg.length; i++) {
            	　　　　// 如果在select中传递其他参数，可以在option 的value属性中添加参数
            	　　　	value += "<option value='"+msg[i].applyTypeNo+";"+msg[i].applyTypePrice+"'>"+msg[i].applyTypeName+"</option>";
            	　　	}
            	　$("#acceptType").append(value);
            		form.render('select');
            	}
             },   
        });
    });
    
  // 获取用户信息
	comExt.ajax({
		url : '/session/getSessionData' // 实际使用请改成服务端真实接口
		,
		success : function(data) {
			layui.data('loginInfo', {
				key : 'loginInfo',
				value : data.obj.loginInfo
			});
			var userName = layui.data('loginInfo').loginInfo.userName;
			$('#operator').val(userName);
		}
	});
    
    form.on("submit(addApply)",function(data){
    	data.field = comExt.trim(data.field);
    	$.ajax({
            type: "post",
            url: "/business/apply/addApply",
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
 			// 当你在iframe页面关闭自身时
			var index = parent.layer.getFrameIndex(window.name);
	 		parent.layer.close(index);
	 		layer.closeAll();
 		})
 	})
});
