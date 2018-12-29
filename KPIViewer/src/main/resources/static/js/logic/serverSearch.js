var searchHistoryList = null;
$(".search-input > input[type=text]").each(function(){  
    var oldValue = $(this).val();
    var btnSearch = $(this).siblings(".btnSearch");

 $(this).focus(function(){
  $(this).siblings(".search-history").show();
  btnSearch.addClass("active");
})
   .blur(function(){
    if($(this).val()==""){
        $(this).val(oldValue)
        btnSearch.removeClass("active");
      }
   });

});

// 点击清除内容
$(".search-input > .btnClear").click(function(){
  $(this).siblings("input[type=text]").val("").focus();
  $(this).hide();
});

$(".btnSearch").click(function(){
  $(".search-history").hide();
  var value = $("#searchInput").val();
  
  $.post("/rest/kpiAnalyse/searchServer", {value:value},function(res){
		if(res.suc=="yes"){
			var result = res.allServerList;
			var onlineList = new Array();
			for(var i=0;i<result.length;i++){
				if(result[i].status=="1"){
					onlineList.push(result[i]);
				}				
			}
			initAllTable(result,280);
			initOnlineTable(onlineList,280);
		}else{
			$.messager.alert('异常','异常!',"error");
		}
  });
});

//搜索历史记录
$(".search-history > ul > li").hover(function(){
  $(this).find(".btnClear").show();
},function(){
  $(this).find(".btnClear").hide();
});
$(".search-history > ul > li > span").on("click",function(){
  var text = $(this).text()
  $(this).parents(".search-input,.search-input-jishi").find("input[type=text]").val(text).focus();
  $(this).parents(".search-history").hide();
});

$(".search-history > ul > li > .btnClear").bind("click",function(){
  $(this).parent("li").remove();//删除历史
});

//点击空白隐藏历史记录
$(document).bind("click",function(e){
var target = $(e.target);
if(target.closest(".search-input,.search-input-jishi,.btnClear").length == 0){
$(".search-input > .search-history,.search-input-jishi > .search-history").hide();
};
});

