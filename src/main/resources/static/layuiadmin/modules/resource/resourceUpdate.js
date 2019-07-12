layui.define([ 'form', 'table', 'layer', 'laydate','comExt','manager' ],
		function(exports) {
    var form = layui.form
    ,laydate = layui.laydate
    ,layer = parent.layer === undefined ? layui.layer : top.layer
    ,$ = layui.jquery
    ,comExt = layui.comExt;
       
    laydate.render({
      elem: '#LAY-component-form-group-date',
    });
    laydate.render({
      elem: '#LAY',
    });

    /* 自定义验证规则 该页面目前没有用到（跟据需求添加校验规则）*/
    form.verify({
      title: function(value){
        if(value.length < 5){
          return '标题至少得5个字符啊';
        }
      },
      pass: [/(.+){6,12}$/, '密码必须6到12位'],
      content: function(value){
        layedit.sync(editIndex);
      }
    });
    
	// 登录名检测
    $('#userName').on('click',function(){
      var checkLoginName = $('input[name="loginName"]').val();
      if(checkLoginName.length === 0){
        layer.msg('请输入有效内容！',{time:5*1000});
        return;
      } else {
        var oReqData = { loginName: checkLoginName };
        oReqData = comExt.trim(oReqData);
        comExt.ajax({
          url: "/userInfo/getUserInfoByName",
          data: JSON.stringify(oReqData),
          success: function(o) {
            if (!o.success) {
              return;
            } else {
             layer.msg(o.msg,{time:5*1000});
            }
          },
        });
      }
    });

    $('.close').on('click',function(){
 		layer.confirm("是否退出当前页面？",function(index){
 			// 当你在iframe页面关闭自身时
			var index = parent.layer.getFrameIndex(window.name);
	 		parent.layer.close(index);
	 		layer.closeAll();
 		})
 		
 	})
 	
 	$("#superiorName_btn").click(function(){
    	resourceInfo();
    })
    
    //添加上级菜单页面
    function resourceInfo(edit){
        var index = layui.layer.open({
            type: 2,
            area: ['350px', '450px'],
            fixed: false,
            maxmin: false,//最大小化按钮，默认：false
            content : "resourceTree.html",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
            }
        })
        window.sessionStorage.setItem("index",index);
    }
    
    $("#resourceName").click(function(){
    	iconInfo();
    })
    
    //添加图标页面
    function iconInfo(edit){
        var index = layui.layer.open({
            type: 2,
            title: '图标',
            area: ['518px', '400px'],
            fixed: false,
            maxmin: false,//最大小化按钮，默认：false
            content : "iconInfo.html",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
            }
        })
        window.sessionStorage.setItem("index",index);
    }

    //监听提交
    form.on("submit(addUser)",function(data){
    	var value = comExt.trim(data.field);
    	comExt.ajax({
            url: "/pageResource/update",
            data: JSON.stringify(value),
            success: function(o){
                if(!o.success) {
                  return ; 
                }
                parent.location.reload();
                layer.msg("操作成功");
              },
        });
        return false;
    });
    
    exports('resource/resourceUpdate', {});
  });