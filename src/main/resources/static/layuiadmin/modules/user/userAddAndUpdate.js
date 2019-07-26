layui.define([ 'form', 'table', 'layer', 'laydate','comExt' ],
		function(exports) {
	var layer=layui.layer;
    var form = layui.form
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        comExt = layui.comExt,
        admin = layui.admin,
    form.on("submit(addUser)",function(data){
    	var userData = JSON.stringify(data.field);
    	userData = comExt.trim(userData);
    	if(Number(userData.enableDate)>Number(userData.invalidDate)){
    		layer.msg("失效日期不应早于启用日期");
    		return;
    	}
    	comExt.ajax({
            url: "/userInfo/insertUserInfo",
            data: userData,
            success: function(o){
            	parent.location.reload();
            	layer.msg("操作成功");
                     },
        });
    	
        return false;
    });
    // 关闭按钮click事件绑定
    $('#addClose').on('click',function(){
 		layer.confirm("是否退出当前页面？",function(index){
 			// 当你在iframe页面关闭自身时
			var index = parent.layer.getFrameIndex(window.name);
	 		parent.layer.close(index);
	 		layer.closeAll();
 		})
 		
 	})
   
    //登录名检测
 /*   $(document).on('click','#loginName',function(){
    	var checkLoginName = $('input[name="loginName"]').val();
    	if(checkLoginName.length ==0){
    		layer.msg('请输入有效内容！',{time:5*1000});
    		return;
    	}else{
    	var oReqData/!*请求数据*!/ = {
                  loginName: checkLoginName
              };
    	comExt.ajax({
            url: "/userInfo/getUserInfoByName",
            data: JSON.stringify(oReqData),
            success: function(o){
            	if (!o.success) {
            		return;
				} else {
					 layer.msg(o.msg,{time:5*1000});
				}
            },
        });
    	}
    });*/

	//登录名校验
    $("#checkName").blur(function(){
		var checkLoginName = $('input[name="loginName"]').val();
		if(checkLoginName.length ==0){
			layer.msg('请输入有效内容！',{time:5*1000});
			return;
		}else{
			var oReqData/*请求数据*/ = {
				loginName: checkLoginName
			};
			comExt.ajax({
				url: "/userInfo/getUserInfoByName",
				data: JSON.stringify(oReqData),
				success: function(o){
					if (!o.success) {
						$("#checkName").val("");
					} else {
                        // $("#last").html("√");
						layer.msg(o.msg,{time:5*1000});
					}
				},

			});
		}
	})
    //登录名检测
    /*$(document).on('blur','#loginName',function(){
    	alert(7777777)
    	var checkLoginName = $('input[name="loginName"]').val();
    	if(checkLoginName.length ==0){
    		layer.msg('请输入有效内容！',{time:5*1000});
    		return;
    	}else{
    	var oReqData/!*请求数据*!/ = {
                  loginName: checkLoginName
              };
    	comExt.ajax({
            url: "/userInfo/getUserInfoByName",
            data: JSON.stringify(oReqData),
            success: function(o){
				var tiShi = document.getElementById("tiShi");
            	if (!o.success) {
            		$("#tiShi").val(o.msg);
            		tiShi.style.setProperty('color','#fff');
				} else {
					$("#tiShi").val(o.msg);
					tiShi.style.setProperty('color','red');
				}
            },
        });
    	}
    });*/

   //ID检测
    $(document).on('click','#userID',function(){
    	var checkLoginId= $('input[name="userId"]').val();
    	if(checkLoginId.length ==0){
    		layer.msg('请输入有效内容！',{time:5*1000});
    		return;
    	}else{
    	  var oReqData/*请求数据*/ = {
    			  userId: checkLoginId
              };
    	comExt.ajax({
            url: "/userInfo/getUserInfoById",
            data: JSON.stringify(oReqData),
            success: function(o){
            	if (!o.success) {
            		return;
				} else {
            		// alert(123)
					 layer.msg(o.msg,{time:5*1000});
				}
            },
        });
    	}
    });
    
    $("#orgInfo_btn").click(function(){
    	orgInfo();
    })
    
    //添加机构
    function orgInfo(edit){
        var index = layui.layer.open({
            type: 2,
            area: ['700px', '650px'],
            fixed: false,
            maxmin: true,
            content : "userOrgInfo.html",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
            }
        })
        window.sessionStorage.setItem("index",index);
    }
    
    
    //监听修改页面提交
    form.on("submit(updateAdd)",function(data){
    	data.field = comExt.trim(data.field);
    	if(Number(data.field.enableDate)>Number(data.field.invalidDate)){
    		layer.msg("失效日期不应早于启用日期");
    		return;
    	}
    	comExt.ajax({
    		url: "/userInfo/editUserInfo",
    		data: JSON.stringify(data.field),
    		success: function(o){
    			parent.location.reload();
    			top.layer.msg("用户信息修改成功！");
    		},
    	});
    	return false;
    });
    
    // 关闭按钮click事件绑定
    $('#updateClose').on('click',function(){
 		layer.confirm("是否退出当前页面？",function(index){
 			// 当你在iframe页面关闭自身时
			var index = parent.layer.getFrameIndex(window.name);
	 		parent.layer.close(index);
	 		layer.closeAll();
 		})
 		
 	})

    //格式化时间
    function filterTime(val){
        if(val < 10){
            return "0" + val;
        }else{
            return val;
        }
    }
    //定时发布
    var time = new Date();
    var submitTime = time.getFullYear()+'-'+filterTime(time.getMonth()+1)+'-'+filterTime(time.getDate())+' '+filterTime(time.getHours())+':'+filterTime(time.getMinutes())+':'+filterTime(time.getSeconds());
    
  //检验规则
	form.verify({
		email : function(value, item) {
			var reg = /^[A-Za-z\d]+([-_.][A-Za-z\d]+)*@([A-Za-z\d]+[-.])+[A-Za-z\d]{2,4}$/;
			if (null != value && value.length != 0 && (!(reg.test(value)))) {
				return '工作邮箱格式不正确';
			}
		}
	}); 
	
	layui.use('laydate', function(){
	  	var laydate = layui.laydate;
		laydate.render({
	   		 elem: '#enableDate'
	   		 ,format: 'yyyyMMdd'
	   		 ,calendar: true
	 	});
		laydate.render({
			elem: '#invalidDate'
			,format: 'yyyyMMdd'
		,calendar: true
	 	});
	});

	exports('user/userAddAndUpdate', {});
	
})