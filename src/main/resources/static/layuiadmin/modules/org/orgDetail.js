layui.define([ 'form', 'table', 'layer', 'laydate','comExt' ],
		function(exports) {
		var form = layui.form,
		layer = layui.layer,
		$ = layui.$,
		comExt = layui.comExt;
		$('input[name=orgNo]').attr("readonly","readonly");//将input元素设置为readonly 注意顺序
		
		//提交新增/修改
	 	form.on("submit(editOrg)",function(data){
	 		data.field = comExt.trim(data.field);
	 		var action = "/orgInfo/editOrgInfo";//修改
	 		data.field.orgMode="0";
	 		data.field.delayFlag="0";
	 		comExt.ajax({
	            url: action,
	            data: JSON.stringify(data.field),
	            success: function(o){
	            	if(!o.success) return;
	            	var index = parent.layer.getFrameIndex(window.name);
	    	 		parent.layer.close(index);
	            	parent.location.reload();
	            	layer.msg("操作成功");
                 }
        });
	 	});
		var disabled = true;
		$('form').on('change','input',function(){
			disabled = false;
		})
	 	$('#close').on('click',function(){
	 			//当你在iframe页面关闭自身时
	 			var index = parent.layer.getFrameIndex(window.name);
	 			if(!disabled){
	 				layer.confirm('信息未提交，确认退出？',function(i){
	 				 	parent.layer.close(index);
	 				})
	 			}else{
	 				layer.confirm("是否退出当前页面？",function(index){
			 			// 当你在iframe页面关闭自身时
						var index = parent.layer.getFrameIndex(window.name);
				 		parent.layer.close(index);
				 		layer.closeAll();
			 		});
	 			}
	 		
	 	})
	 	
		//清除detailData
	 	layui.data("layer",null);
		
		  exports('org/orgDetail', {});
	  });