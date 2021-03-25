//全局客户类型(1:公众;2:政企)
var g_custType="1";
//数据日期
var g_dataDate="20191231"
//全局渠道信息
var g_channelName="当前时间：";


//门店基本信息
function loadChannelBaseInfo(data){
	console.log("data is", data)
	//设置渠道当日客流量、订单量
	$("#base-info").find("div[type='keyNums']").text(data.keyNums);
	$("#base-info").find("div[type='taskNums']").text(data.handledTaskNums);
	$("#base-info").find("div[type='expireKeyNums']").text(data.expireKeyNums);

	//设置门店基本信息-排队机耗时
	$("#base-info").find("div[type='runTime']").text(data.runDuration + "秒");
    
    $("#base-info").find(".channel-name").text(g_channelName+new Date().toLocaleDateString() + " " +new Date().toLocaleTimeString());
}
//渠道报告内容建议说明
function channelReportContent(rePortData){
	var reportContent="很抱歉，目前暂无可分析的数据";
	if(undefined!=rePortData.channelAvgTime && ""!=rePortData.channelAvgTime){
		if(rePortData.channelAvgTime>20){
			if(rePortData.popval<=0.3){
				reportContent="业绩很棒，受理偏慢，请适当加快受理";
			}else{
				reportContent="受理速度偏慢，影响客户感知，请加强受理技能培训";
			}
		}else{
			//判断厅是否有排队机
			if(rePortData.lineUpData.length>0){
				var callTime=rePortData.lineUpData[rePortData.lineUpData.length-1];
				var t1=rePortData.channelAvgTime-callTime;
				if(t1>=3){
					if(rePortData.popval<=0.3){
						reportContent="门店运营良好，可适当提升受理速度";
					}else{
						reportContent="门店运营良好，建议加强营销";
					}
				}else if(callTime>rePortData.channelAvgTime){
					reportContent="门店台席规划不合理，存在客户积压，请按业务类型分流";
				}else if(0<=t1 && t1<3){
					if(rePortData.popval<=0.3){
						reportContent="门店运营状态良好，台席规划合理，请继续保持";
					}else{
						reportContent="台席规划合理，建议适当加强营销";
					}
				}
			}else{
				var custNum=rePortData.mapTaskNum[rePortData.mapTaskNum.length-1] || 0;
				if(custNum<=50){
					if(rePortData.popval<=0.3){
						reportContent="营业效能规划合理，厅内客流量仍有上升空间";
					}else{
						reportContent="客流较小，请加强营销手段";
					}
				}else{
					if(rePortData.popval<=0.3){
						reportContent="厅店客流量大，营业效能规划合理，请继续保持";
					}else{
						reportContent="厅店客流量大，请把握机会加强营销";
					}
				}
			}
		}
	}
	$(".analysis-info").text(reportContent);
}

//各数据类型任务数
function loadChannelHandleDetail(data){
	var option = {
		    tooltip: {trigger: 'axis',axisPointer: {lineStyle: {color: '#fff'}}},
		    legend: {
		        icon: 'rect',
		        itemWidth: 14,itemHeight: 5,itemGap:10,
		        data: ['String', 'List', 'Set','Map'],
		        right: '10px',top: '0px',
		        textStyle: {fontSize: 12,color: '#fff'}
		    },
		    xAxis: [{
		        type: 'category',boundaryGap: false,axisLine: {lineStyle: {color: '#57617B'}},axisLabel: {textStyle: {color:'#fff'}},
		        data: data.times
		    }],
		    yAxis: [{
		        type: 'value',
		        axisTick: {
		            show: false
		        },
		        axisLine: {lineStyle: {color: '#57617B'}},
		        axisLabel: {margin: 10,textStyle: {fontSize: 12},textStyle: {color:'#fff'},formatter:'{value}'},
		        splitLine: {lineStyle: {color: '#57617B'}}
		    }],
		    series: [{
		        name: 'String',type: 'line',smooth: true,lineStyle: {normal: {width: 2}},
		        yAxisIndex:0,
		        areaStyle: {
		            normal: {
		                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
		                    offset: 0,
		                    color: 'rgba(185,150,248,0.3)'
		                }, {
		                    offset: 0.8,
		                    color: 'rgba(185,150,248,0)'
		                }], false),
		                shadowColor: 'rgba(0, 0, 0, 0.1)',
		                shadowBlur: 10
		            }
		        },
		        itemStyle: {normal: { color: '#B996F8'}},
		        data: data.stringTaskCounts
		    }, {
		        name: 'List',type: 'line',smooth: true,lineStyle: { normal: {width: 2}},
		        yAxisIndex:0,
		        areaStyle: {
		            normal: {
		                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
		                    offset: 0,
		                    color: 'rgba(3, 194, 236, 0.3)'
		                }, {
		                    offset: 0.8,
		                    color: 'rgba(3, 194, 236, 0)'
		                }], false),
		                shadowColor: 'rgba(0, 0, 0, 0.1)',
		                shadowBlur: 10
		            }
		        },
		        itemStyle: {normal: {color: '#03C2EC'}},
		        data: data.listTaskCounts
		    }, {
		        name: 'Set',type: 'line',smooth: true,lineStyle: {normal: {width: 2}},
		        yAxisIndex:0,
		        areaStyle: {
		            normal: {
		                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
		                    offset: 0,
		                    color: 'rgba(218, 57, 20, 0.3)'
		                }, {
		                    offset: 0.8,
		                    color: 'rgba(218, 57, 20, 0)'
		                }], false),
		                shadowColor: 'rgba(0, 0, 0, 0.1)',
		                shadowBlur: 10
		            }
		        },
		        itemStyle: {normal: {color: '#DA3914'}},
		        data: data.setTaskCount
		    },{
		        name: 'Map',type: 'line',smooth: true,lineStyle: {normal: {width: 2}},
		        yAxisIndex:0,
		        areaStyle: {
		            normal: {
		                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
		                    offset: 0,
		                    color: 'rgba(232, 190, 49, 0.3)'
		                }, {
		                    offset: 0.8,
		                    color: 'rgba(232, 190, 49, 0)'
		                }], false),
		                shadowColor: 'rgba(0, 0, 0, 0.1)',
		                shadowBlur: 10
		            }
		        },
		        itemStyle: {normal: {color: '#E8BE31'}},
		        data:data.mapTaskCount
		    }]
		    
		    
	};
	var myChart = echarts.init(document.getElementById('channel_handle_detail'));
	myChart.clear();
	if(data.mapTaskCount.length>0){
		myChart.setOption(option);
	}else{
		noDataTip($("#channel_handle_detail"));
	}
}


//加载最近过期数据
function loadStaffHandleDetail(data){
	//获取员工违规信息
	var staffWgInfo=data.staffWgInfo;
	//获取员工积分信息
	// var channelJfMap=data.channelJfMap;
	var channelStaffLen=data.channelStaffLen;
	var expireDataInfo=data.keysInfos || [],staffHandleInfoLen=expireDataInfo.length;
	var html=[],index=0;
	for(var i = 0; i < expireDataInfo.length; i = i+2){
		index++;
		var indexClass=(1==index)?"first":"";
		var tr1=[
			'<tr class="td-avg-time">',
			'<td colspan="3">',
					'<div class="index '+indexClass+'">'+expireDataInfo[i].index+'</div>',
					'<div class="staff-name">'+expireDataInfo[i].keyName+'</div>',
				'</td>',
			'</tr>',
			'<tr>',
				'<td> ',
					'<div class="staff-cust-time">',
					   '<span style="font-size:15px;">数据类型</span><br/><span style="color:#00A8FE;font-size:18px;font-weight:600;">'+expireDataInfo[i].type+'</span>',
					'</div>',
				'</td>',
				'<td>',
					'<div class="staff-order-count">',
					    '<span style="font-size:15px;">访问次数</span><br/><span style="color:#00A8FE;font-size:18px;font-weight:600;">'+expireDataInfo[i].visitNum+' 次</span>',
					'</div>',
				'</td>',
				'<td>',
					'<div class="staff-alarm">',
					   '<span style="font-size:15px;">占用内存</span><br/><span style="color:#00A8FE;font-size:18px;font-weight:600;">'+expireDataInfo[i].memory+' B</span>',
					'</div>',
			    '</td>',
			'</tr> ',
			'<tr class="td-integral"> ',
				'<td colspan="3"> ',
				'<div class="integral-label">过期时间: </div> ',
				'<div class="integral-value" style="width:60px;">'+expireDataInfo[i].expireTime+'</div>',
				'</td> ',
			'</tr> ',
		];
		var tr2=[];
		if(i < expireDataInfo.length){
			index++;
			var indexClass=(2==index)?"second":"";
			tr2=[
				'<tr><td colspan="3"><div class="split-line"></div></td></tr>',
				'<tr class="td-avg-time">',
				'<td colspan="3">',
						'<div class="index '+indexClass+'">'+expireDataInfo[i+1].index+'</div>',
						'<div class="staff-name">'+expireDataInfo[i+1].keyName+'</div>',
					'</td>',
				'</tr>',
				'<tr>',
					'<td> ',
						'<div class="staff-cust-time">',
						   '<span style="font-size:15px;">数据类型</span><br/><span style="color:#00A8FE;font-size:18px;font-weight:600;">'+expireDataInfo[i+1].type+'</span>',
						'</div>',
					'</td>',
					'<td>',
						'<div class="staff-order-count">',
							'<span style="font-size:15px;">访问次数</span><br/><span style="color:#00A8FE;font-size:18px;font-weight:600;">'+expireDataInfo[i+1].visitNum+' 次</span>',
						'</div>',
					'</td>',
					'<td>',
						'<div class="staff-alarm">',
						   '<span style="font-size:15px;">占用内存</span><br/><span style="color:#00A8FE;font-size:18px;font-weight:600;">'+expireDataInfo[i+1].memory+' B</span>',
						'</div>',
					'</td>',
				'</tr> ',
				'<tr class="td-integral"> ',
					'<td colspan="3"> ',
					'<div class="integral-label">过期时间: </div> ',
					'<div class="integral-value" style="width:60px;">'+expireDataInfo[i+1].expireTime+'</div>',
					'</td> ',
				'</tr> ',
			]
		}
		var staffHtml=[
            '<div class="swiper-slide">',
                '<table>',
	                tr1.join(""),
	                tr2.join(""),
			    '</table>',
            '</div> '
		];
		html.push(staffHtml.join(""));
	}
	$("#staff-info").html("");
	if(html.length>0){
		var dataHtml="<div class='swiper-container visual_swiper_staffInfo'><div class='swiper-wrapper'>"+html.join("")+"</div>"
		$("#staff-info").html(dataHtml);
		var mySwiper1 = new Swiper('.visual_swiper_staffInfo', {
			autoplay: true,//可选选项，自动滑动
			speed:1500,//可选选项，滑动速度
			autoplay: {
			    delay: 15000,//毫秒
			} 
		});
	}else{
		noDataTip($("#staff-info"));
	}
}

//加载内存占用分析
function loadChannelDeviceDetail(data){
	var html=[];
	//设置台席灯
	// $(".device-alarm").eq(2).find("span[type='greenGrade']").text(data.greenGrade || "0");
	// $(".device-alarm").eq(1).find("span[type='redGrade']").text(data.redGrade || "0");
	// $(".device-alarm").eq(0).find("span[type='grayGrade']").text(data.grayGrade || "0");
	var memoryAnalysis=data;
	var dataHtml=
			'<tr>'+                                                           
				'<td rowspan="3" class="device-img component">'+                            
				'<img src="../images/device-green.png"/>'+
				'</td> '+    
				'<td rowspan="3" class="device-img component">'+                            
				'<img src="../images/device-green.png"/>'+
				'</td> '+                                                                                 
			'</tr>'+                                                           
			'<tr>'+                                                            
			'</tr>'+    
			'<tr>'+                                                            
			'</tr>'+   
			'<tr>'+  
				'<td><div class="label-name component">总存储占用</div></td> '+  
				'<td><div class="label-name component">总存储占用</div></td> '+      
			'</tr>'+ 
			'<tr>'+  
				'<td><div class="label-name score">'+memoryAnalysis.sumMemoryCost+'B</div></td>'+  
				'<td><div class="label-name score">'+memoryAnalysis.sumMemoryCost+'B</div></td>'+                                                           
			'</tr>'+        
			'<tr><td colspan="4"><div class="split-line"></div></td></tr>'+
			'<tr>'+                                                         
				'<td class="label-name"><div>各数据类型内存占用</div></td>'+                        
			'</tr>'+                                                        
			'<tr class="device-use">'+                                                      
					'<td><div class="nei-cun-size component"><span>'+(memoryAnalysis.stringMemory)+'</span></div></td>'+      
					'<td><div class="cpu-use component"><span>'+(memoryAnalysis.listMemory)+'</span></div></td>'+     
			'</tr>'+                                                           
			'<tr>'+                                                      
					'<td><div class="label-value">string</div></td>'+                 
					'<td><div class="label-value">list</div></td>'+            
			'</tr>'+
			'<tr class="device-use">'+                                                       
					'<td><div class="nei-cun-size component"><span>'+(memoryAnalysis.setMemory)+'</span></div></td>'+
					'<td><div class="nei-cun-use component"><span>'+(memoryAnalysis.mapMemory)+'</span></div></td>'+    
			'</tr>'+
			'<tr>'+                                   
					'<td><div class="label-value">set</div></td>'+                 
					'<td><div class="label-value">map</div></td>'+             
			'</tr>';
	$("#device-info").html(dataHtml);
	// if(html.length>0){
	// 	$("#device-info").html(dataHtml);
	// }else{
	// 	noDataTip($("#device-info"));
	// }
}
//加载任务耗时分析
function loadTimeStepDetail(data){
	var option = {
		    tooltip: {trigger: 'axis',},
		    grid: {left:'6%',right: '5%',bottom:'10%',x:40,y:50,x2:45,y2:40},
		    legend: {
		    	icon: 'rect',
		        itemWidth: 14,itemHeight: 5,itemGap:10,
		        data:["执行耗时","执行次数"],
		        left: '10px',top: '0px',
		        textStyle: {fontSize: 12,color: '#fff'}
		    },
		    xAxis: [
		        {
		            type: 'category',
		            axisLine: {lineStyle: {color: '#57617B'}},axisLabel: {interval:0,textStyle: {color:'#fff',}},
		            data:data.tasks,
					name:"Key",
					nameLocation:'start'
		        }
		    ],
		    yAxis: [{
        		type: 'value',
 		        axisTick: {
 		            show: false
 		        },
 		        axisLine: {lineStyle: {color: '#57617B'}},
 		        axisLabel: {margin: 5,textStyle: {fontSize: 1},textStyle: {color:'#fff'},formatter:'{value}ms'},
 		        splitLine: {
 	                show: true,
 	                lineStyle:{
 	                    type:'dashed',
 	                    color: ['#25CEF3']
 	                }
 	            }
		     },{
		        type: 'value',
		        axisTick: {
		            show: false
		        },
		        axisLine: {lineStyle: {color: '#57617B'}},
		        axisLabel: {margin: 5,textStyle: {fontSize: 1},textStyle: {color:'#fff'},formatter:'{value}次'},
		        splitLine: {show: false,lineStyle: {type:'dashed',color: '#57617B'}}
		    }],
		    series: [
		        {
					name:"执行耗时",
		            type:'bar',
		            barWidth:8,
					yAxisIndex:0,
		            itemStyle : { 
		            	normal: {
				        	barBorderRadius:[10, 10, 0, 0],
				            color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [{
				                offset: 0,
				                color: "#009AFD"
				            }, {
				                offset: 0.8,
				                color: "#33DAFF" 
				            }], false),
				            shadowColor: 'rgba(0, 0, 0, 0.1)',
				        }
		            },
		            data:data.timeCost
		        },
		        {
		            name:"执行次数",
		            type:'bar',
		            barWidth:8,
		            barGap:2,
					yAxisIndex:1,
		            itemStyle : { 
		            	normal: {
				        	barBorderRadius:[10, 10, 0, 0],
				            color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [{
				                offset: 0,
				                color: "#E57230"
				            }, {
				                offset: 0.8,
				                color: "#D8AE22" 
				            }], false),
				            shadowColor: 'rgba(0, 0, 0, 0.1)',
				        }
		            },
		            data:data.runTimes
		        }
		    ]
	};
	var myChart = echarts.init(document.getElementById('time-step-detial'));
	myChart.clear();
	if(data.timeCost.length>0){
		myChart.setOption(option);
	}else{
		noDataTip($("#time-step-detial"));
	}
	
}

//加载热点数据及其频率
function loadBusinessTypeTimeDetail(data){
	var maxOrder=Math.max.apply(null,data.hotSpotMemories);
	var option = {
			title : {text:'',subtext:'',top:'3',right:'0'},
            tooltip: {trigger: 'axis'},
            grid: {left: '8%',right: '8%',bottom: '10%'},
            xAxis: {type: 'category',axisLine: {lineStyle: {color: '#57617B'}},axisLabel: {interval:0,textStyle: {color:'#fff',}},data: data.hotSpotKeys},
            yAxis:[
       	        {
       	          type: 'value',name: '',
       	          axisLine: {lineStyle: {color: '#57617B'}},
  		          axisLabel: {margin: 7,textStyle: {fontSize: 10},textStyle: {color:'#fff'},formatter:'{value}次'},
	  		      splitLine: {show: false}	
       	        },
    	        {
       	          type: 'value',name: '',max:maxOrder+parseInt(maxOrder*0.2),
 		          axisLabel: {margin: 7,textStyle: {fontSize: 10},textStyle: {color:'#fff'},formatter:'{value} B'},
 		          splitLine: {
	  	                show: true,
	  	                lineStyle:{
	  	                    type:'dashed',
	  	                    color: ['#25CEF3']
	  	                }
	  	          }	
    	        }
    		],
            series: [
   		        {
		            name:'查询次数',
		            type:'line',
		            yAxisIndex:0,
		            smooth: false,
		            symbolSize:5,
		            lineStyle: { normal: {width: 2}},
		            areaStyle: {
			            normal: {
			                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
			                    offset: 0,
			                    color: 'rgba(230, 48, 123, 0.8)'
			                }, {
			                    offset: 0.8,
			                    color: 'rgba(230, 48, 123, 0)'
			                }], false),
			                shadowColor: 'rgba(0, 0, 0, 0.1)',
			                shadowBlur: 10
			            }
			        },
			        itemStyle: {normal: { color: '#DA2F78'}},
		            data:data.hotSpotQueryTimes
		        },
		        {
		            name:'占用内存',
		            type:'bar',
		            barWidth:12,
		            yAxisIndex:1,
		            itemStyle : { 
				        normal: {
				        	barBorderRadius:[10, 10, 0, 0],
				            color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [{
				                offset: 0,
				                color: "#4033F9"
				            }, {
				                offset: 0.8,
				                color: "#BA97F9" 
				            }], false),
				            shadowColor: 'rgba(0, 0, 0, 0.1)',
				        }
		            },
		            data:data.hotSpotMemories
		        }
          ]
    };
	var myChart = echarts.init(document.getElementById('business-type-time-detial'));
	myChart.clear();
	if(data.hotSpotMemories.length>0){
		myChart.setOption(option);
	}else{
		noDataTip($("#business-type-time-detial"));
	}
	
}

//初始化基础信息
function initBaseInfo(){
	//初始化日期
	/*$("#data-date").my97({
	   dateFmt:'yyyyMMdd',
	   minDate:"%y-{%M}-{%d-30}",
	   maxDate:"%y-%M-{%d}",
	   startDate: [{
	       doubleCalendar: false,
	       isShowWeek: false,
	       isShowClear: false,
	       readOnly: true
	   }],
	   onpicked:function(dp){
		   g_dataDate=$('#data-date').val();
		   $("#query-page-data").trigger("click");
	   }
   });*/
   var dataDate="2019年12月31日";
   $("#td-data-date").text(dataDate);
   
	
	
	$(".cust-type-default").on("click",function(){
		$(this).addClass("active").siblings().removeClass("active");
		g_custType=$(this).attr("type");
		$("#query-page-data").trigger("click");
	});
	
}

//获取渠道参数
function getGroupChannelParam(){
	var areaCode="";
	try{
	    var cityId=$('#selectCity').combobox("getValue");
		var countyId=$('#selectCounty').combobox("getValue");
		if(""==areaCode && ""!=countyId){
			areaCode=countyId;
		}else if(""==areaCode && ""!=cityId){
			areaCode=cityId;
		}
	}catch(e){
	}
	var channelName=$.trim($("#channel_name").val());
	var channelParam={'areaCode':areaCode,"channelName":channelName};
	return channelParam;
}



function noDataTip($selector){
	var currModH=$selector.height();
	var top=currModH>180?"35%":"13%";
	var $li=[
        "<div class='no-data' style='width:90%;position: absolute;top:"+top+";text-align:center;color:#a59999;'>",
		   "<img src='../images/no-data.png' style='width:200px;height:200px;'/>",
		   "<div style='font-size:16px;opacity:0.7;color:#fff;'>暂无数据</div>",
        "</div>"
    ]
	$selector.append($li.join(""));
}

function keepTwoDecimal(currVal){
		 var result = parseFloat(currVal);
		 if (isNaN(result)) {
		   return false;
		 }
		 result = Math.round(currVal *100) / 100;
		 return result;
}

//初始化页面
function loadPageData(){

	 //开始动画特效
	 $('#load').show();
	 $('#load').fadeOut(500,function(){
	 });
	
	 //引入office_efficiency_data.js缓存假数据
	var actualData = data.code == 0?data:get("/monitor");
	console.log(actualData)

	$(".no-data").remove();
	//加载门店基本信息
	loadChannelBaseInfo(actualData.baseInfoSummary);
	//加载门店历史受理详情
	loadChannelHandleDetail(actualData.taskInfoSummary);
	//加载营业员受理详情
	loadStaffHandleDetail(actualData.expireKeysInfoSummary);
	//加载门店台席健康度详情
	loadChannelDeviceDetail(actualData.memoryInfoSummary);
	//加载耗时步骤分析
	loadTimeStepDetail(actualData.slowQueryAnalysisSummary);
	//加载业务类型耗时详情
	loadBusinessTypeTimeDetail(actualData.hotSpotDataInfoSummary);

	
}
$(function(){
	//加载基础信息
	initBaseInfo();
	//加载页面数据
	loadPageData();

	setInterval(loadPageData,1000*15)
})

function print() {
	get("/monitor")
	console.log("hello,world")
}

function get(url) {
	var result = -1
	$.ajax({
        url:url,
        type:'get',
        async:false,
        success:function (data) {
			console.log("Send post request,data is ",data);
			result = data;
        }
    })
	return result;
}