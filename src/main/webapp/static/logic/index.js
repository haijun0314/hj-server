 
var data={
	noteList:[]	,
	page	:0
}
 
var index=new Vue({
  el: '#app_index',
  data:data
})

loadData=function(){
	var url="note?noteList&page="+data.page;
	AjaxUtils.loadData(url,'',function(respData){
		data.noteList=respData.datas;
	});
}
loadData();