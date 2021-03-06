layui.define([ 'form', 'table', 'layer', 'laydate','comExt' ], function(
		exports) {
	var table = layui.table;
	var form = layui.form;
	var $ = layui.$,
	layer = layui.layer,
	comExt = layui.comExt;
	options = {
		elem : "#orgList",
		cellMinWidth : 80,// 全局定义常规单元格的最小宽度
		method : 'post',
		url : "/orgInfo/getOrgInfoList",
		contentType : "application/json;charset=utf-8",
		page : true,
		request : {
			pageName : 'page' // 页码的参数名称，默认：page
			,
			limitName : 'rows' // 每页数据量的参数名，默认：limit
		},
		response : {
			statusName : 'success' // 数据状态的字段名称，默认：code
			,
			statusCode : true // 成功的状态码，默认：0
			,
			msgName : 'msg' // 状态信息的字段名称，默认：msg
			,
			countName : 'obj/jsonData/total' // 数据总数的字段名称，默认：count
			,
			dataName : 'obj/jsonData/rows' // 数据列表的字段名称，默认：data
		},
		cols : [ [ // 表头
		{
			type : 'radio',
			fixed : "left",
			width : 50
		}, {
			field : 'orgNo',
			title : '机构编号',
			sort : true,
			minWidth : 150,
			align : "center"
		}, {
			field : 'orgName',
			title : '机构名',
			minWidth : 150,
			align : "center"
		}, {
			field : 'orgState',
			title : '机构状态',
			minWidth : 150,
			align : "center",
			templet : function(d) {
				return d.orgState == "1" ? "启用" : "停用";
			}
		},
		{
			field : "launchDate",
			title : '日期',
			minWidth : 150,
			align : "center"
		}
		// , {
		// 	title : "操作",
		// 	align : 'center',
		// 	fixed : 'right',
		// 	width : 100,
		// 	templet : function(d) {
		// 		if (d.orgState == "1") {
		// 			return '<button class="layui-btn layui-btn-xs layui-bg-red"  lay-event="del">停用</button>';
		// 		}else {
		// 			return '<button class="layui-btn layui-btn-xs"  lay-event="active">启用</button>';
		// 		}
		// 	}
		// }
		] ],
		done : function() {
			var laydate = layui.laydate;
			var date = $('.date');
			$.each(date, function(i, item) {
				var id = "date" + i;
				$(this).attr("id", id);
				laydate.render({
					elem : "#" + id,
					format : 'yyyy年MM月dd日',
					calendar : true
				});
			})

		}
	};
	// 初始化table
	table.render(options);
	// 监听搜索按钮事件
	form.on('submit(searchInfo)', function(data) {
		var searchCondition = {
			searchCondition : data.field
		};
		options.where = searchCondition;
		table.render(options);
	});

	// 监听table内工具条
	table.on('tool(orgList)', function(obj) {
		var data = obj.data;
		if (obj.event === 'detail') {
			showDetail(obj.data);
		} else if (obj.event === 'del') {
			deleteRows(obj.data);
		} else if (obj.event === 'active') {
			activeRows(obj.data);
		} 
	});

	// 监听批量操作按钮
	$('.operate-btn').on('click', 'button', function() {
		var type = $(this).data('type');
		switch (type) {
		case "addNew":
			addNew();
			break;
		case "deleteRows":
			deleteRows();
			break;
		case "showDetail":
			showDetail();
			break;
		default:
			layer.msg("something error", {
				time : 5 * 1000
			});
		}
	})

	// 改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
	$(window).on("resize", function() {
		var index = window.sessionStorage.getItem("index");
		if (!$('#layui-layer' + index)[0]) {
			return;
		}
		layui.layer.full(index);
	})

	function addNew() {
		var index = layui.layer.open({
			title : "新增机构信息",
			type : 2,
			anim : 3,
			content : "orgAdd.html",
			success : function(layero, index) {
				var body = layer.getChildFrame('body', index);
			}
		})
		layui.layer.full(index);
	}

	function deleteRows(data) {
		if (!data) {
			// 获取选中行
			var checkStatus = table.checkStatus('orgList');
			if (checkStatus.data.length == 0) {
				layer.msg("请选择需要停用的机构", {
					time : 5 * 1000
				});
				return;
			}
			data = checkStatus.data;
		}
		if (data == null) {
			layer.msg("请选择需要停用的机构", {
				time : 5 * 1000
			});
			return false;
		}
		var newsId = [];
		if (typeof data == "Array") {
			for (d in data) {
				newsId.push(data[d].orgNo);
			}
		} else {
			newsId.push(data.orgNo);
		}
		var orgNos = {
			"orgNos" : newsId.join()
		};
		layer.confirm('确定停用选中的机构？', {
			title : '提示信息'
		}, function(index) {
			comExt.ajax({
				url : "/orgInfo/removeOrgInfoByIds",
				data : JSON.stringify(orgNos),
				success : function(o) {
					layer.close(index);
					table.render(options);
					top.layer.msg("机构信息禁用成功 ");
				},
				error : function(e) {
					// 如果不需要comExt.ajax提供的error提示，需要在结尾return;
					layer.close(index);
					top.layer.msg("机构信息禁用失败！错误码：" + e.status, {
						time : 5 * 1000
					});
					return;
				}
			});
		})
	}
	
	function activeRows(data) {
		if (!data) {
			// 获取选中行
			var checkStatus = table.checkStatus('orgList');
			if (checkStatus.data.length == 0) {
				layer.msg("请选择需要启用用的机构", {
					time : 5 * 1000
				});
				return;
			}
			data = checkStatus.data;
		}
		if (data == null) {
			layer.msg("请选择需要启用的机构", {
				time : 5 * 1000
			});
			return false;
		}
		var newsId = [];
		if (typeof data == "Array") {
			for (d in data) {
				newsId.push(data[d].orgNo);
			}
		} else {
			newsId.push(data.orgNo);
		}
		var orgNos = {
			"orgNos" : newsId.join()
		};
		layer.confirm('确定启用选中的机构？', {
			title : '提示信息'
		}, function(index) {
			comExt.ajax({
				url : "/orgInfo/activeOrgInfoByIds",
				data : JSON.stringify(orgNos),
				success : function(o) {
					layer.close(index);
					table.render(options);
					top.layer.msg("机构信息启用成功 ");
				},
				error : function(e) {
					// 如果不需要comExt.ajax提供的error提示，需要在结尾return;
					layer.close(index);
					top.layer.msg("机构信息启用失败！错误码：" + e.status, {
						time : 5 * 1000
					});
					return;
				}
			});
		})
	}

	function showDetail(data) {
		if (!data) {
			var checkStatus = table.checkStatus('orgList');
			if (checkStatus.data.length > 1) {
				layer.msg("只能一个需要查看的数据", {
					time : 5 * 1000
				});
				return;
			}
			data = checkStatus.data[0];
		}
		if (data == null) {
			layer.msg("请选择需要查看的机构", {
				time : 5 * 1000
			});
			return false;
		}
		var index = layer.open({
			title : "查看机构信息",
			type : 2,
			content : "orgDetail.html",
			success : function(layero, index) {
				// 下拉列表框渲染，后台数据。
				var selDate = {
					"area" : [ {
						"value" : '启用',
						"key" : "1"
					}, {
						"value" : '停用',
						"key" : "0"
					}, ]

				};
				var body = layer.getChildFrame('body', index);

				// 渲染下拉列表数据
				addSelect(body.find('#orgState'), selDate.area, true, 'key',
						'value');
				// 填充修改数据
				fillInput(body, data);
			}
		})
		layui.layer.full(index);
		window.sessionStorage.setItem("index", index);
		layui.data("detailData", {
			key : "data",
			value : data
		});
	}

	function addSelect(id, data, bool, val, text) {// bool是否添加“请选择”选项
		val = val || 'type';
		text = text || 'name';
		var html = '';
		// var $id = $('#'+id);
		var $id = id;
		$id.html('');
		if (bool) {
			html += '<option value="">请选择</option>';
		}
		for ( var i in data) {
			html += '<option value="' + data[i][val] + '">' + data[i][text]
					+ '</option>';
		}
		$id.html(html);
		form.render("select");
	}

	function fillInput(parent, data) {
		for ( var key in data) {
			var id = "#" + key;
			parent.find(id).val(data[key]);
		}
		form.render();
	}
	
	exports('org/orgAdmin', {});
});
