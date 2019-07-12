layui.define([ 'form', 'table', 'layer', 'laydate','comExt' ],
		function(exports) {
		var form = layui.form,
		layer = layui.layer,
		$ = layui.$,
		comExt = layui.comExt;
		
		//提交新增/修改
	 	form.on("submit(addOrg)",function(data){
	 		data.field = comExt.trim(data.field);
	 		var action = "/orgInfo/insertOrgInfo";//新增
	 		comExt.ajax({
	            url: action,
	            data: JSON.stringify(data.field),
	            success: function(o){
	            	if(o.success){
	            		var index = parent.layer.getFrameIndex(window.name);
		    	 		parent.layer.close(index);
		            	parent.location.reload();
		            	layer.msg("操作成功");
		            	return ; 
	            	}
	            	
                 }
       		 });
	 	});
		
	 	var disabled = true;
		$('form').on('change','input',function(){
			disabled = false;
		})
	 	$('#close').on('click',function(){
	 		var index = parent.layer.getFrameIndex(window.name);
	 		if(disabled){
		 		layer.confirm("是否退出当前页面？",function(index){
		 			// 当你在iframe页面关闭自身时
					var index = parent.layer.getFrameIndex(window.name);
			 		parent.layer.close(index);
			 		layer.closeAll();
		 		});
	 		}else{
	 			layer.confirm("信息未提交，确认退出？",function(i){
		 			//当你在iframe页面关闭自身时
			 		parent.layer.close(index);
		 		})
	 		}
	 	})
	 	
		//清除
	 	layui.data("layer",null);
		
		  exports('org/orgAdd', {});
	  });