layui.define([ 'form', 'table', 'layer', 'laydate','comExt','laytpl'],
		function(exports) {
    	var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,updatePwd
        laytpl = layui.laytpl,
        table = layui.table;

     $("#updatePwd").click(function(){
           var aUserIds = [];
     	    //获取密码
     	    var adminPwd =$("#adminPwd").val();
     	    var userPwd =$(".userPwd").val();
          var userPwd_sub=$(".userPwd_sub").val();
          if(adminPwd == '' || userPwd == '' || userPwd_sub == ''){
        	  return;
          }
          if(userPwd==userPwd_sub){
          	var updatePwdCondition = {adminPwd:adminPwd,userPwd:userPwd,userPwdSub:userPwd_sub};
          	    var userinfo=layui.data('loginInfo').loginInfo;
     	    	updatePwdCondition.userId = userinfo.userId;
     		    updatePwdCondition.userIds = userinfo.userId;
   		$.ajax({
      		type: "post",
      		url: "/userInfo/updateUserPwdByAdmin",
      		data: JSON.stringify(updatePwdCondition),
      		dataType: "json",
      		headers: {
      			'Content-Type': 'application/json;charset=utf-8'
      		},
      		success: function(o){
      		     if(o.success==true){
  		        	top.layer.msg("密码修改成功！"); 
	        		setTimeout(function(){
	        			$.get("/servlet/sessionServlet",function(res){
	        				layui.admin.exit(function(){
	        					location.href = 'user/login.html';
	        				});
	        			})
	                },500);
      		     }else{
      		    	 if(o.msg == "管理员密码错误！"){
      		    		 top.layer.msg("原始密码输入错误",{time:5*1000}); 
      		    	 }else {
      		    		top.layer.msg(o.msg,{time:5*1000}); 
      		    	 }
      		     }
      		
      		},
   			});
          	
          }else{
          	layer.msg("两次输入的密码不一致，请重新输入！",{time:5*1000});
          	return
          }
    	 
     })
			//修改密码验证
    	$('#userPwd').on('blur',function () {
			var str = $('#userPwd').val();
			if(str.length<8||str>20){
				layer.msg("密码长度需要大于8位小于20位",{time:5*1000});
				$('#userPwd').val("");
			}
			var reg = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,20}$/;
			if(!reg.test(str)){
				layer.msg("输入的密码必须是字母和数字的组合",{time:5*1000});
				$('#userPwd').val("");
			}

		})
        $('#close').on('click',function(){
 		layer.confirm("是否退出当前页面？",function(index){
 			//当你在iframe页面关闭自身时
			var index = parent.layer.getFrameIndex(window.name);
	 		parent.layer.close(index);
	 		layer.closeAll();
 		})
 		
 	})
 	 exports('user/updatePerosonalPwd', {});	  

})
