layui.define([ 'form', 'table', 'layer', 'laydate','comExt','layedit' ], function(
		exports) {

    var form = layui.form
        layer = parent.layer === undefined ? layui.layer : top.layer,
        comExt = layui.comExt,
        $ = layui.jquery,
        layedit = layui.layedit;

    form.on("submit(addRole)",function(data){
    	data.field = comExt.trim(data.field);
    	$.ajax({
            type: "post",
            url: "/role/addRoleInfo",
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
    
    //富文本编辑器图片上传
    layedit.set({
    	  uploadImage: {
    	    url: '/upload/textImg' //接口url
    	    ,type: '' //默认post
    	  }
    	});
    
    var description = layedit.build('description'); //建立富文本编辑器
    
    //得到富文本编辑器内容
    form.verify({
    	description: function(value) {
            return layedit.sync(description);
        }
    });


    exports('role/roleAdd', {});
});