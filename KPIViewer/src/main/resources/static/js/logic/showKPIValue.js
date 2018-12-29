/**
 * 
 */
$().ready(function(){
	var id = GetQueryString("id");
	var title = GetQueryString("name");
	$(".pageTopTitle").html("关键指标分析<span style='font-size:18px'>--"+title+"</span>");
	fetchAjaxData(id);

});

function fetchAjaxData(id){
	  $.post("/rest/kpiAnalyse/showKPIValue", {id:id},function(data){
			if(data.suc=="yes"){
				initLogTable(data.logList, 280);
				
				var daily = data.dailyKPIList;
				for(var i=0; i<daily.length; i++){
					var dateKPI = daily[i].insertdate.replace(/-/g,"/");
					var dailyLoginNum = daily[i].dailyloginnum;
					var loginNum = daily[i].loginnum;
					var sessionNum = daily[i].sessionnum;
					
					dailyLoginNumOption.xAxis.data[i]= dateKPI;
					dailyLoginNumOption.series[0].data[i] = dailyLoginNum;
					
					totalLoginNumOption.xAxis.data[i]= dateKPI;
					totalLoginNumOption.series[0].data[i] = loginNum;
					
					sessionNumOption.xAxis.data[i]= dateKPI;
					sessionNumOption.series[0].data[i] = sessionNum;
					
				}
				
				if(daily.length>0){
					dailyLoginNumChart.setOption(dailyLoginNumOption);
					totalLoginNumChart.setOption(totalLoginNumOption);
					sessionNumChart.setOption(sessionNumOption);
				}
				
				var result = data.kpiValueList;
				for(var i=0; i<result.length; i++){
					var dateMillis = result[i].insertDate;
					var registeredNum = result[i].registeredNum==null? "0":result[i].registeredNum;
					var onlineNum = result[i].onlineNum==null? "0":result[i].onlineNum;
					var responseTime = result[i].responseTime==null? "0":result[i].responseTime;
					var runningTime = result[i].runningTime==null? "0":result[i].runningTime;
					var tableSpaceSize = result[i].tableSpaceSize==null? "0":result[i].tableSpaceSize;
					var dbResponseTime = result[i].dbResponseTime==null? "0":result[i].dbResponseTime;
					
					var dateKPI = convertToDate(dateMillis);
					registedNumOption.xAxis.data[i]= dateKPI;
					registedNumOption.series[0].data[i] = registeredNum;
					
					onlineNumOption.xAxis.data[i]= dateKPI;
					onlineNumOption.series[0].data[i] = onlineNum;
						
					serverResTimeOption.xAxis.data[i]= dateKPI;
					serverResTimeOption.series[0].data[i] = responseTime;
					
					runningTimeOption.xAxis.data[i]= dateKPI;
					runningTimeOption.series[0].data[i] = runningTime;
					
					tableSpaceSizeOption.xAxis.data[i]= dateKPI;
					tableSpaceSizeOption.series[0].data[i] = tableSpaceSize;
					
					dbResTimeOption.xAxis.data[i]= dateKPI;
					dbResTimeOption.series[0].data[i] = dbResponseTime;
				}
				
				
				if(result.length>0){
					registedNumChart.setOption(registedNumOption);
					onlineNumChart.setOption(onlineNumOption);
					serverResTimeChart.setOption(serverResTimeOption);
					runningTimeChart.setOption(runningTimeOption);
					tableSpaceSizeChart.setOption(tableSpaceSizeOption);
					dbResTimeChart.setOption(dbResTimeOption);
				}
				
				$("#loader").css("display","none");
			}else{
				$.messager.alert('异常','异常!',"error");
				$("#loader").css("display","none");
			}
	  });
}

function initLogTable(data, h1) {
	$('#logHistoryTable').datagrid({
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
		columns:[[
			{field:"serverid",title:"serverid",hidden:true},
			{field:"address",title:"Portal服务地址",width:"38%"},
			{field:"status",title:"状态",width:"30%",
				formatter:function(value,row,index){
					if(value=="0"){
						return "断开";
					}else if(value=="1"){
						return "连接";
					}
					
				}},
			{field:"updatetime",title:"更新时间",width:"30%",
						formatter:function(value,row,index){
							return convertToDate(value);
					}}
		]]
	});
}

function convertToDate(millis){
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
	return year+"/"+month+"/"+day+"\n"+hour+":"+min+":"+second;
}

function GetQueryString(param){
	var reg = new RegExp("(^|&)"+ param +"=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if(r!=null){
		return unescape(r[2]);
	}else{
		return null;
	}
}
