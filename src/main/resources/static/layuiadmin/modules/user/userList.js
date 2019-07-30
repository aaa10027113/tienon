var rolepwd="";
layui.define([ 'form', 'table', 'layer', 'laydate','comExt','laytpl' ],
		function(exports) {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table,
        comExt = layui.comExt;
        
 
    //用户列表
    var tableIns = table.render({
        elem: '#userList',
        url : '/userInfo/getUserInfoList',
        cellMinWidth : 95,
        page : true,
        limits : [10,15,20,25],
        contentType:"application/json;charset=utf-8",
        id : "userListTable",
        method : 'post',
        request: {
        	  pageName: 'page' //页码的参数名称，默认：page
        	  ,limitName: 'rows' //每页数据量的参数名，默认：limit
        	},
        response: {
        	  statusName: 'success' //数据状态的字段名称，默认：code
        	  ,statusCode: true //成功的状态码，默认：0
        	  ,msgName: 'msg' //状态信息的字段名称，默认：msg
        	  ,countName: 'obj/jsonData/total' //数据总数的字段名称，默认：count
        	  ,dataName: 'obj/jsonData/rows' //数据列表的字段名称，默认：data
        	} ,
        cols : [[
            {type: "radio", fixed:"left", width:50},
            {field: 'userId', title: '用户编号', minWidth:150, sort: true },
            {field: 'loginName', title: '登录名', minWidth:150, align:"center"},
            {field: 'userName', title: '用户姓名', minWidth:150, align:"center"},
            {field: 'orgNo', title: '机构代码', minWidth:150, align:"center"},
            {field: 'orgName', title: '机构名称', minWidth:150, align:"center"},
            {field: 'phone', title: '手机号码', minWidth:150, align:"center"},
            {field: 'enableDate', title: '启用日期', minWidth:150, align:"center"},
            {field: 'invalidDate', title: '失效日期', minWidth:150, align:"center"},
            {field: 'email', title: '用户邮箱', minWidth:200, align:'center',templet:function(d){
            	d.email = d.email==null?'':d.email;
                return '<a class="layui-blue" href="mailto:'+d.email+'">'+d.email+'</a>';
            }},
            {field: 'enable', title: '用户状态',  align:'center',templet:function(d){
                return d.enable == "1" ? "正常使用" : "限制使用";
            }},
            {title: '操作', minWidth:175,fixed:"right",align:"center",templet:function(d){
                    return '<a class="layui-btn layui-btn-xs" lay-event="edit">修改</a>';
                // if(d.enable == "1"){
                // 	return '<a class="layui-btn layui-btn-xs" lay-event="edit">修改</a>'+
                // 	'<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del" id="delAll_btn">禁用</a>';
                // }else if(d.enable == "0"){
                // 	return '<a class="layui-btn layui-btn-xs" lay-event="edit">修改</a>'+
                // 	'<a class="layui-btn layui-btn-xs" lay-event="abled" id="abledAll_btn">启用</a>';
                // }
            }}
        ]]
    });
    
    //监听搜索按钮事件
    form.on('submit(searchIfo)', function(data){
    	var searchCondition = {searchCondition : data.field};
    	var tableIns = table.render({
            elem: '#userList',
            url : '/userInfo/getUserInfoList',
            cellMinWidth : 95,
            page : true,
            height : "full-125",
            limits : [10,15,20,25],
            contentType:"application/json;charset=utf-8",
            id : "userListTable",
            method : 'post',
            where: searchCondition,
            request: {
            	  pageName: 'page' //页码的参数名称，默认：page
            	  ,limitName: 'rows' //每页数据量的参数名，默认：limit
            	},
            response: {
            	  statusName: 'success' //数据状态的字段名称，默认：code
            	  ,statusCode: true //成功的状态码，默认：0
            	  ,msgName: 'msg' //状态信息的字段名称，默认：msg
            	  ,countName: 'obj/jsonData/total' //数据总数的字段名称，默认：count
            	  ,dataName: 'obj/jsonData/rows' //数据列表的字段名称，默认：data
            	} ,
            cols : [[
                {type: "radio", fixed:"left", width:50},
                {field: 'userId', title: '用户编号', minWidth:100, align:"center"},
                {field: 'loginName', title: '登录名', minWidth:100, align:"center"},
                {field: 'userName', title: '用户姓名', minWidth:100, align:"center"},
                {field: 'orgNo', title: '机构代码', minWidth:50, align:"center"},
                {field: 'orgName', title: '机构名称', minWidth:100, align:"center"},
                {field: 'phone', title: '手机号码', minWidth:100, align:"center"},
                {field: 'enableDate', title: '启用日期', minWidth:100, align:"center"},
                {field: 'invalidDate', title: '失效日期', minWidth:100, align:"center"},
                {field: 'email', title: '用户邮箱', minWidth:200, align:'center',templet:function(d){
                	if(d.email!=null){
                		return '<a class="layui-blue" href="mailto:'+d.email+'">'+d.email+'</a>';
                	}else {
                		return '<a class="layui-blue" href="mailto:'+d.email+'"></a>';
                	}
                }},
                {field: 'enable', title: '用户状态',  align:'center',templet:function(d){
                    return d.enable == "1" ? "正常使用" : "限制使用";
                }},
                {title: '操作', minWidth:175,fixed:"right",align:"center",templet:function(d){
                    if(d.enable == "1"){
                    	return '<a class="layui-btn layui-btn-xs" lay-event="edit">修改</a>'+
                    	'<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del" id="delAll_btn">禁用</a>';
                    }else if(d.enable == "0"){
                    	return '<a class="layui-btn layui-btn-xs" lay-event="edit">修改</a>'+
                    	'<a class="layui-btn layui-btn-xs" lay-event="abled" id="abledAll_btn">启用</a>';
                    }
                }}
            ]]
        });
        return false;
    });
   
    //添加用户
    function addUser(edit){
        var index = layui.layer.open({
            title : "用户信息",
            type : 2,
            content : "userAdd.html",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
            }
        })
        layui.layer.full(index);
        window.sessionStorage.setItem("index",index);
    }
    
    //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
    $(window).on("resize",function(){
    	var index = window.sessionStorage.getItem("index");
    	if(!$('#layui-layer'+ index)[0]){
    		return;
    	}
        layui.layer.full(index);
    })
    
    
    //绑定角色
    function bindRole(edit){
        var index = layui.layer.open({
            title : "角色绑定",
            type : 2,
            area:'auto',
            content: ['bindingrole.html', 'no'],
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                body.find("#userId").val(edit.userId);
                body.find("#loginName").val(edit.loginName);
            }
        })
        layui.layer.full(index);
        window.sessionStorage.setItem("index",index);
    }
    
    
    $("#bindRole_btn").click(function(){
        var checkStatus = table.checkStatus('userListTable'),
        data = checkStatus.data,
        newsId = [];
       if(data.length <1) {
      	layer.msg("请选择一个需要操作的用户！",{time:5*1000});
      	 
       }else if(data.length >1){
      	 layer.msg("每次只能选择一个用户！",{time:5*1000});
       } else{
      	 bindRole(data[0]); 
       }    
      })
     
    
    //修改用户
    function updateUser(edit){
    	var index = layui.layer.open({
    		title : "修改用户信息",
    		type : 2,
    		content : "updateAdd.html",
    		success : function(layero, index){
    			var body = layui.layer.getChildFrame('body', index);
    			comExt.fillInput(body,edit);
    			body.find(".userName").val(edit.userName);  //用户姓名
    		}
    	})
    	layui.layer.full(index);
    	window.sessionStorage.setItem("index",index);
    	
    }
    $("#addNews_btn").click(function(){
        addUser();
    })

    //批量禁用 
    $("#delAll_btn").click(function(){
    	
        var checkStatus = table.checkStatus('userListTable'),
            data = checkStatus.data,
            newsId = [];
        if(data.length > 0) {
            for (var i in data) {
                newsId.push(data[i].userId);
            }
            var oReqData = {
                    userIds: newsId.join(',')
                }
            layer.confirm('确定禁用选中的用户？', {icon: 3, title: '提示信息'}, function (index) {
            	layer.close(index);
            	comExt.ajax({
            		url: "/userInfo/disableUserInfoByIds",
            		data: JSON.stringify(oReqData),
            		success: function(o){
            			location.reload();
            			top.layer.msg("用户信息禁用成功！");
            		}
            	});
            })
        }else{
            layer.msg("请选择需要禁用的用户",{time:5*1000});
        }
    });
    
   
    //列表操作
    table.on('tool(userList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;

        if(layEvent === 'edit'){ //编辑
        	updateUser(data);
        }else if(layEvent === 'usable'){ //启用禁用
            var _this = $(this),
                usableText = "是否确定禁用此用户？",
                btnText = "已禁用";
            if(_this.text()=="已禁用"){
                usableText = "是否确定启用此用户？",
                btnText = "已启用";
            }
            layer.confirm(usableText,{
                icon: 3,
                title:'系统提示',
                cancel : function(index){
                    layer.close(index);
                }
            },function(index){
                _this.text(btnText);
                layer.close(index);
            },function(index){
                layer.close(index);
            });
        }else if(layEvent === 'del'){ //禁用
        	 var newsId = []; 
             newsId.push(data.userId);
             var oReqData = {
                     userIds: newsId.join(',')
                 }
             layer.confirm('确定禁用选中的用户？', {icon: 3, title: '提示信息'}, function (index) {
             	layer.close(index);
             	comExt.ajax({
             		url: "/userInfo/disableUserInfoByIds",
             		data: JSON.stringify(oReqData),
             		success: function(o){
             			location.reload();
             			top.layer.msg("用户信息禁用成功！");
             		}
             	});
             })
        }else if(layEvent === 'abled'){ //启用
       	 var newsId = []; 
         newsId.push(data.userId);
         var oReqData = {
                 userIds: newsId.join(',')
             }
         layer.confirm('确定启用选中的用户？', {icon: 3, title: '提示信息'}, function (index) {
         	layer.close(index);
         	comExt.ajax({
         		url: "/userInfo/enableUserInfoByIds",
         		data: JSON.stringify(oReqData),
         		success: function(o){
         			location.reload();
         			top.layer.msg("用户信息启用成功！");
         		}
         	});
         })
    }
    });

    exports('user/userList', {});
})
