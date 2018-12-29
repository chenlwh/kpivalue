/**
 * 
 */
$().ready(function(){
	  $.post("/rest/kpiAnalyse/allServer", {},function(data){
			if(data.suc=="yes"){
				var result = data.allServerList;
				var onlineList = new Array();
				for(var i=0;i<result.length;i++){
					if(result[i].status=="1"){
						onlineList.push(result[i]);
					}				
				}
				initAllTable(result,280);
				initOnlineTable(onlineList,280);
				
				var monthList = data.monthList;			
				for(var i=0; i<monthList.length; i++){
					monthlyServerNumOption.xAxis[0].data[i]= monthList[i].addPeriod;
					monthlyServerNumOption.series[0].data[i] = monthList[i].offlineNum;
					monthlyServerNumOption.series[1].data[i] = monthList[i].onlineNum;
					monthlyServerChart.setOption(monthlyServerNumOption);
				}
			}else{
				$.messager.alert('异常','异常!',"error");
			}
	  });
});

function initAllTable(data, h1) {
	$('#allServerTable').datagrid({
		data: data,
		maxHeight: h1,
		striped:true,
		fitColumns: true,
		rownumbers: true,
		singleSelect:true,
		loadMsg: '正在加载，请稍候...',
		rowStyler: function(index, row) {
			if (row.status == "0") {
				return 'color:red;';
			}

		},
		onDblClickRow:function(index,row){
			var id = row.id;
			fetchAjaxData(id);
		},
		columns:[[
			{field:"id",title:"id",hidden:true},
			{field:"address",title:"Portal服务地址",width:"38%"},
			{field:"status",title:"状态",width:"10%",
				formatter:function(value,row,index){
					if(value=="0"){
						return "断开";
					}else if(value=="1"){
						return "正常";
					}
					
				}},
			{field:"inserttime",title:"部署时间",width:"20%",
					formatter:function(value,row,index){
						return convertToDate(value);
					}},
			{field:"updatetime",title:"更新时间",width:"20%",
						formatter:function(value,row,index){
							return convertToDate(value);
					}},
			{field:"kpi",title:"指标分析",width:"10%",align:"center",
						formatter:function(value,row,index){
							return "<a target='_blank' href='kpiAnalyse.html?id="+row.id+"&name="+row.address+"'>查看</a>";
						},
					styler:function(value,row,index){
								return "color:green";
					}}
		]]
	});
}	
	
function initOnlineTable(data, h1) {
	$('#onlineServerTable').datagrid({
		data: data,
		maxHeight: h1,
		striped:true,
		fitColumns: true,
		rownumbers: true,
		singleSelect:true,
		loadMsg: '正在加载，请稍候...',
		columns:[[
			{field:"id",title:"id",hidden:true},
			{field:"address",title:"Portal服务地址",width:"38%"},
			{field:"status",title:"状态",width:"10%",
				formatter:function(value,row,index){
					if(value=="0"){
						return "断开";
					}else if(value=="1"){
						return "正常";
					}
					
				}},
			{field:"inserttime",title:"部署时间",width:"20%",
					formatter:function(value,row,index){
						return convertToDate(value);
					}},
			{field:"updatetime",title:"更新时间",width:"20%",
					formatter:function(value,row,index){
						return convertToDate(value);
					}},
			{field:"kpi",title:"指标分析",width:"10%",align:"center",
					formatter:function(value,row,index){
						return "<a target='_blank' href='kpiAnalyse.html?id="+row.id+"&name="+row.address+"'>查看</a>";
					},
					styler:function(value,row,index){
							return "color:green";
				   }}
		]],
		onDblClickRow:function(index,row){
			var id = row.id;
			fetchAjaxData(id);
		}
	});

}

function convertToDate(millis){
	if(millis==null){
		return "";
	}
	var date = new Date(millis);
	var year = date.getFullYear();
	var month= date.getMonth()+1;
	var day = date.getDate();
	var hour = date.getHours();
	var min = date.getMinutes();
	var second = date.getSeconds();
	
	if(month<10){
		month = "0"+month;
	}
	if(day<10){
		day ="0" + day;
	}
	if(hour<10){
		hour ="0"+hour;
	}
	if(min<10){
		min ="0"+min;
	}
	if(second<10){
		second="0"+second;
	}
	return year+"-"+month+"-"+day+" "+hour+":"+min+":"+second;
}