/**
 @Name：layuiAdmin 公共业务
 */
 
layui.define('comExt',function(exports){
  var $ = layui.$
  ,layer = layui.layer
  ,laytpl = layui.laytpl
  ,setter = layui.setter
  ,view = layui.view
  ,admin = layui.admin
  ,comExt = layui.comExt;
  
  //公共业务的逻辑处理可以写在此处，切换任何页面都会执行
  //……
  
  //退出
  admin.events.logout = function(){
    $.get("/servlet/sessionServlet",function(res){
admin.exit(function(){
      location.href = '/login.html';
            });
    })
  };
  
  //禁用鼠标右键
  window.document.oncontextmenu = function(){return false;};
  
  //添加水印
  /*comExt.water({watermark_txt:"天用唯勤版权"});*/
  
  	/**
	 * 单击行勾选radio事件
	 * @author tanghuakui
	 */
	var count = 0;
	$(document).on("click", ".layui-table-body table.layui-table tbody tr", function (e) {
		//解决treetable页面打开就选中问题
		if ($("#resourceListHidden").val()=="noClick") {
			count++;
			if (count == $("#superiorIdCount").val()) {
				$("#resourceListHidden").attr("value","canClick");
				count = 0;
			}
			return false;
	  	}
	    if ($(e.target).hasClass("layui-table-col-special") || $(e.target).parent().hasClass("layui-table-col-special")) {
	        return false;
	    }
	    var index = $(this).attr('data-index'), tableBox = $(this).closest('.layui-table-box'),
	        tableFixed = tableBox.find(".layui-table-fixed.layui-table-fixed-l"),
	        tableBody = tableBox.find(".layui-table-body.layui-table-main"),
	        tableDiv = tableFixed.length ? tableFixed : tableBody,
	        checkCell = tableDiv.find("tr[data-index=" + index + "]").find("td div.laytable-cell-checkbox div.layui-form-checkbox i"),
	        radioCell = tableDiv.find("tr[data-index=" + index + "]").find("td div.laytable-cell-radio div.layui-form-radio i");
		    if (checkCell.length) {
		        checkCell.click();
		    }
		    if (radioCell.length) {
		        radioCell.click();
		    }
		});
	  
	$(document).on("click", "td div.laytable-cell-checkbox div.layui-form-checkbox,td div.laytable-cell-radio div.layui-form-radio", function (e) {
	    e.stopPropagation();
	});
  
  //对外暴露的接口
  exports('common', {});
});