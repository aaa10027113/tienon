layui.define([ 'form', 'table', 'layer', 'laydate', 'comExt' ], function(
		exports) {
	var form = layui.form, layer = parent.layer === undefined ? layui.layer
			: top.layer, laydate = layui.laydate;
	$ = layui.jquery, comExt = layui.comExt, table = layui.table;
	//开始时间
	laydate.render({
		elem : '#beginTime',
	});
	//结束时间
	laydate.render({
		elem : '#endTime',
	});
	var searchCondition = "";
	function aad() {
		return searchCondition;
	}
	;
	//商标列表
	var tableIns = table.render({
		elem : '#reportList',
		url : '/business/apply/reportList',
		cellMinWidth : 95,
		contentType : "application/json;charset=utf-8",
		id : "roleListTable",
		method : 'post',
		where : aad(),
		request : {
		},
		response : {
			statusName : 'success' //数据状态的字段名称，默认：code
			,
			statusCode : true //成功的状态码，默认：0
			,
			msgName : 'msg' //状态信息的字段名称，默认：msg
			,
			countName : 'obj/total' //数据总数的字段名称，默认：count
			,
			dataName : 'obj/rows' //数据列表的字段名称，默认：data
		},
		cols : [ [ {
			field : 'num',
			title : '序号',
			width : 60,
			align : "center",
			templet : '#num'
		}, {
			field : 'applyNo',
			title : '申请序号',
			width : 121,
			align : "center"
		}, {
			field : 'acceptDate',
			title : '受理日期',
			width : 102,
			align : 'center'
		}, {
			field : 'companyName',
			title : '公司名称',
			width : 150,
			align : "center"
		}, {
			field : 'trademarkName',
			title : '商标名称',
			width : 150,
			align : "center"
		}, {
			field : 'acceptTypeName',
			title : '业务类型',
			width : 200,
			align : "center"
		}, {
			field : 'addType',
			title : '类别',
			width : 330,
			align : "center"
		}, {
			field : 'amt',
			title : '金额',
			width : 100,
			align : "center"
		},

		] ],
	});

	//重新加载查询结果
	$(".queryCommit").click(function() {
		var beginTime = $("#beginTime").val();
		if (beginTime != "" && null != beginTime) {
			beginTime = beginTime + " 00:00:00";
		}

		var endTime = $("#endTime").val();
		if (endTime != "" && null != endTime) {
			endTime = endTime + " 23:59:59";
		}
		search = {
			beginTime : beginTime,
			endTime : endTime
		};
		var searchCondition = {
			searchCondition : search
		};
		tableIns.reload({
			where : searchCondition,
			page : {
				curr : 1
			//重新从第 1 页开始
			}
		});
	})

	var beginTime = $("#beginTime").val();
	if (beginTime != "" && null != beginTime) {
		beginTime = beginTime + " 00:00:00";
	}

	var endTime = $("#endTime").val();
	if (endTime != "" && null != endTime) {
		endTime = endTime + " 23:59:59";
	}
	document.getElementById('export').href = "http://www.baidu.com";
	$("#export").click(
			function() {
				var beginTime = $("#beginTime").val();
				if (beginTime != "" && null != beginTime) {
					beginTime = beginTime + " 00:00:00";
				}

				var endTime = $("#endTime").val();
				if (endTime != "" && null != endTime) {
					endTime = endTime + " 23:59:59";
				}
				window.location.href = "/business/apply/download?beginTime="
						+ beginTime + "&endTime=" + endTime;
			})

	exports('role/roleList', {});
});
