layui.extend({
	layuiXtree:'../../../layuiadmin/lib/layuiXtree'
}).define([ 'form', 'tree', 'layer','comExt','layuiXtree'],
		function(exports) {
	var layer = parent.layer === undefined ? layui.layer : top.layer,
		comExt = layui.comExt,
		layuiXtree = layui.layuiXtree,
		form = layui.form,
		$ = layui.jquery,
		index = parent.layer.getFrameIndex(window.name),//获取窗口索引
		tree = layui.tree,
		parent$ = window.parent.layui.jquery;
	
	var resultData;
	
	//得到图标的json文件，生成HTML页面
	$(document).ready(function () {
		comExt.ajax({
            url: "/getJson/iconJson",
            type: 'POST',
	        async: false,
            success: function(data){
            	resultData = data;
	        	var vm = new Vue({
	        	    el: '#main',
	        	    data() {
	        			return {
	        				result:resultData
	        				}
	        			}
	        	})
              }
        });
		/*$.ajax({
	        url: '/getJson/iconJson',
	        type: 'POST',
	        async: false,
	        success: function (data) {
	        	resultData = data;
	        	var vm = new Vue({
	        	    el: '#main',
	        	    data() {
	        			return {
	        				result:resultData
	        				}
	        			}
	        	})
	        }
		});*/
	});
	
	
	$(".small-icon").click(function(e) {
		var clickDomObj = e.target;
		var row;
//		var row=clickDomObj.nextElementSibling.innerText; //获取显示内容
		if (clickDomObj.children.length==0) {
			row=clickDomObj.classList[1]; //获取显示内容
		}else {
			row=clickDomObj.children[1].innerText; //获取显示内容
		}
		
		parent$('#resourceName').val(row);
        parent.layer.close(index);
	});
	
 	 exports('resource/iconInfo', {});	  

})
