//全局客户类型(1:公众;2:政企)
var g_custType="1";
//数据日期
var g_dataDate="20191231"
//全局渠道信息
var g_channelName="当前时间：";


//门店基本信息
function loadChannelBaseInfo(data){
	// //获取渠道积分数据
	// var staffHandleInfo=data.staffHandleInfo;
	// //获取渠道总积分
	// var channelJfMap=staffHandleInfo.channelJfMap || {};
	// //获取渠道总积分
	// var channelSum=channelJfMap.channelSum || 0;
	// //获取渠道排名
	// var channelOrder=channelJfMap.channelOrder || 1;
	// var channelTotal=channelJfMap.channelTotal || 1
	// var popval=String(parseFloat(channelOrder/channelTotal));
	//获取台席健康度得分
	var memoryAnalysis=data.memoryAnalysis;
	var finalDeviceScore=memoryAnalysis.finalDeviceScore || 0;
	// if(finalDeviceScore<=0){
	// 	$("#base-info .device-score").find("img").attr("src","../static/images/star3.png");
	// }else if(finalDeviceScore<0.95){
	// 	$("#base-info .device-score").find("img").attr("src","../static/images/star2.png");
	// }else{
	// 	$("#base-info .device-score").find("img").attr("src","../static/images/star1.png");
	// }
	// $("#base-info .device-score").find(".score-val").text(finalDeviceScore+"分");
	
	//设置渠道当日客流量、订单量
	$("#base-info").find("div[type='dingdanVal']").text(memoryAnalysis.channelOrderNum || "--");
	$("#base-info").find("div[type='custNumVal']").text(memoryAnalysis.channelCustNum || "--");
	
	//设置门店基本信息-违规行为
	// var channelStartWeiGui=staffHandleInfo.channelStartWeiGui || 0;
	// if(channelStartWeiGui==1){
	// 	$("#base-info .weigui").find("img").attr("src","../static/images/star1.png");
	// }else{
	// 	$("#base-info .weigui").find("img").attr("src","../static/images/star3.png");
	// }
	// $("#base-info .weigui").find(".score-val").text(channelStartWeiGui+"分");
	//设置门店基本信息-营销评级
	// var yingxiaoval=1-popval;
	// yingxiaoval=keepTwoDecimal(yingxiaoval);
	// if(yingxiaoval<=0){
	// 	$("#base-info .yingxiao").find("img").attr("src","../static/images/star3.png");
	// }else if(yingxiaoval<0.95){
	// 	$("#base-info .yingxiao").find("img").attr("src","../static/images/star2.png");
	// }else{
	// 	$("#base-info .yingxiao").find("img").attr("src","../static/images/star1.png");
	// }
	// $("#base-info .yingxiao").find(".score-val").text(yingxiaoval+"分");
	//设置门店基本信息-受理速度
	// var avgTimeScore=0;
	// var slowQueryAnalysis=data.slowQueryAnalysis;
	// if(slowQueryAnalysis.timeCost.length>0){
	// 	avgTimeScore=slowQueryAnalysis.timeCost[4]/slowQueryAnalysis.provinceTime[4];
	// 	if(avgTimeScore>1){
	// 		avgTimeScore=avgTimeScore*0.335;
	// 	}
	// }
	// avgTimeScore=keepTwoDecimal(avgTimeScore*0.5+0.5);
	// avgTimeScore=avgTimeScore>1?1:avgTimeScore;
	// if(avgTimeScore<=0){
	// 	$("#base-info .avgtime").find("img").attr("src","../static/images/star3.png");
	// }else if(avgTimeScore<0.95){
	// 	$("#base-info .avgtime").find("img").attr("src","../static/images/star2.png");
	// }else{
	// 	$("#base-info .avgtime").find("img").attr("src","../static/images/star1.png");
	// }
	// $("#base-info .avgtime").find(".score-val").text(avgTimeScore+"分");
	//设置门店基本信息-业务量
	// var yewuliangVal=0;
	// var channelHandleInfo=data.channelHandleInfo;
	// var setTaskNum=channelHandleInfo.setTaskNum;
	// if(setTaskNum.length>2){
	// 	var t1=setTaskNum[setTaskNum.length-1];
	// 	var t2=setTaskNum[setTaskNum.length-2];
	// 	if(t1!=0 && t2!=0){
	// 		yewuliangVal=t1>t2?t2/t1:t1/t2;
	// 	}else{
	// 		yewuliangVal=0.45;
	// 	}
	// 	if(yewuliangVal>1){
	// 		yewuliangVal=yewuliangVal*0.335;
	// 	}
	// }
	// yewuliangVal=keepTwoDecimal(yewuliangVal*0.5+0.5);
	// yewuliangVal=yewuliangVal>1?1:yewuliangVal;
	// if(yewuliangVal<=0){
	// 	$("#base-info .yewuliang").find("img").attr("src","../static/images/star3.png");
	// }else if(yewuliangVal<0.95){
	// 	$("#base-info .yewuliang").find("img").attr("src","../static/images/star2.png");
	// }else{
	// 	$("#base-info .yewuliang").find("img").attr("src","../static/images/star1.png");
	// }
	// $("#base-info .yewuliang").find(".score-val").text(yewuliangVal+"分");
	//设置门店基本信息-排队机耗时
	var channelHandleInfo=data.channelHandleInfo;
	var callTime=channelHandleInfo.listTaskNum[channelHandleInfo.listTaskNum.length-1];
	$("#base-info").find("div[type='lineUpVal']").text(callTime|| "无");
	//开始计算门店星级
	// var channelTotalScore=parseFloat(finalDeviceScore)+parseFloat(channelStartWeiGui)+parseFloat(yingxiaoval)+parseFloat(avgTimeScore)+parseFloat(yewuliangVal);
	// channelTotalScore=String(channelTotalScore);
	// channelTotalScore=keepTwoDecimal(channelTotalScore);
	// $("#base-info").find(".channel-total-score").text(channelTotalScore+"分" || "--分");
	// $("#base-info").find(".chanenl-star").empty();
	// var starLen=Math.floor(channelTotalScore);
	// for(var idx=0;idx<starLen;idx++){
	// 	var img='<img src="../static/images/star1.png"/>';
	// 	$("#base-info").find(".chanenl-star").append(img);
	// }
	// $("#base-info").find(".chanenl-star").append('<div>'+channelTotalScore+'分</div>');
	
	// if(starLen<channelTotalScore){
	// 	var img='<img src="../static/images/star2.png" style="width:21px;height:20px;"/>';
	// 	$("#base-info").find("#chanenl-star").append(img);
	// }
	// var unit="";
	// if(channelSum>90000){
	// 	unit="万";
	// 	channelSum=keepTwoDecimal(channelSum/10000);
	// }
	// var maxval=channelSum-channelSum*(1-popval),currval=channelSum;
	// //特殊处理，优化饼图数据美观
	// if(0==channelSum ){
	// 	maxval=1000;
	// }
	// var option = {
    //     series: [{
    //         type: 'pie',radius: ['65%', '80%'],color: '#E63F19',label: {normal: {position: 'center'}},
    //         data: [{
    //             value: currval,//当前值
    //             name: '积分',
    //             label: {normal: {formatter: currval + unit,textStyle: {fontSize: 16,color: '#FED546'}}}
    //         },{
    //             value: maxval,//比对值
    //             name: '积分',
    //             label: {normal: {formatter: function (params) {return '\n积分' },textStyle: {color: '#fff',fontSize: 13}}},
    //             itemStyle: {normal: {color: 'rgba(255,255,255,.2)'},emphasis: {color: '#fff'}},
    //         }]
    //     }]
	// };
    
    $("#base-info").find(".channel-name").text(g_channelName || "系统运行状况良好");
    // var popval2=keepTwoDecimal(popval);
    // $("#base-info").find(".popval").text("全省前"+(popval2*100)+"%" || "全省前0%");
    // var rePortData={};
    // //厅办理业务平均耗时
    // rePortData.channelAvgTime=staffHandleInfo.channelAvgTime;
    // //厅排队机
    // rePortData.lineUpData=channelHandleInfo.lineUpData
    // //渠道积分排名占比
    // rePortData.popval=popval;
    // rePortData.custNumData=channelHandleInfo.custNumData;
    // channelReportContent(rePortData);
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
		        data: data.dataDateArr
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
		        data: data.stringTaskNum
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
		        data: data.listTaskNum
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
		        data: data.setTaskNum
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
		        data:data.mapTaskNum
		    }]
		    
		    
	};
	var myChart = echarts.init(document.getElementById('channel_handle_detail'));
	myChart.clear();
	if(data.stringTaskNum.length>0){
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
	var expireDataInfo=data.expireDataInfo || [],staffHandleInfoLen=expireDataInfo.length;
	var html=[],index=0;
	for(var key in expireDataInfo){
		var itemArr=expireDataInfo[key];
		index++;
		var indexClass=(1==index)?"first":"";
		var wgCount=staffWgInfo[itemArr[0].staffCode] || "0";
		var tr1=[
			'<tr class="td-avg-time">',
			'<td colspan="3">',
					'<div class="index '+indexClass+'">'+itemArr[0].index+'</div>',
					'<div class="staff-name">'+itemArr[0].keyName+'</div>',
				'</td>',
			'</tr>',
			'<tr>',
				'<td> ',
					'<div class="staff-cust-time">',
					   '<span style="font-size:15px;">数据类型</span><br/><span style="color:#00A8FE;font-size:18px;font-weight:600;">'+itemArr[0].type+'</span>',
					'</div>',
				'</td>',
				'<td>',
					'<div class="staff-order-count">',
					    '<span style="font-size:15px;">访问次数</span><br/><span style="color:#00A8FE;font-size:18px;font-weight:600;">'+itemArr[0].visitNum+' 次</span>',
					'</div>',
				'</td>',
				'<td>',
					'<div class="staff-alarm">',
					   '<span style="font-size:15px;">占用内存</span><br/><span style="color:#00A8FE;font-size:18px;font-weight:600;">'+itemArr[0].memory+' B</span>',
					'</div>',
			    '</td>',
			'</tr> ',
			'<tr class="td-integral"> ',
				'<td colspan="3"> ',
				'<div class="integral-label">过期时间: </div> ',
				'<div class="integral-value" style="width:60px;">'+itemArr[0].expireTime+'</div>',
				'</td> ',
			'</tr> ',
		];
		var tr2=[];
		if(itemArr.length>1){
			index++;
			var indexClass=(2==index)?"second":"";
			var wgCount=staffWgInfo[itemArr[1].staffCode] || "0";
			tr2=[
				'<tr><td colspan="3"><div class="split-line"></div></td></tr>',
				'<tr class="td-avg-time">',
				'<td colspan="3">',
						'<div class="index '+indexClass+'">'+itemArr[1].index+'</div>',
						'<div class="staff-name">'+itemArr[1].keyName+'</div>',
					'</td>',
				'</tr>',
				'<tr>',
					'<td> ',
						'<div class="staff-cust-time">',
						   '<span style="font-size:15px;">数据类型</span><br/><span style="color:#00A8FE;font-size:18px;font-weight:600;">'+itemArr[1].type+'</span>',
						'</div>',
					'</td>',
					'<td>',
						'<div class="staff-order-count">',
							'<span style="font-size:15px;">访问次数</span><br/><span style="color:#00A8FE;font-size:18px;font-weight:600;">'+itemArr[1].visitNum+' 次</span>',
						'</div>',
					'</td>',
					'<td>',
						'<div class="staff-alarm">',
						   '<span style="font-size:15px;">占用内存</span><br/><span style="color:#00A8FE;font-size:18px;font-weight:600;">'+itemArr[1].memory+' B</span>',
						'</div>',
					'</td>',
				'</tr> ',
				'<tr class="td-integral"> ',
					'<td colspan="3"> ',
					'<div class="integral-label">过期时间: </div> ',
					'<div class="integral-value" style="width:60px;">'+itemArr[1].expireTime+'</div>',
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
	var maxOrder=Math.max.apply(null,data.memory);
	var option = {
			title : {text:'',subtext:'',top:'3',right:'0'},
            tooltip: {trigger: 'axis'},
            grid: {left: '8%',right: '8%',bottom: '10%'},
            xAxis: {type: 'category',axisLine: {lineStyle: {color: '#57617B'}},axisLabel: {interval:0,textStyle: {color:'#fff',}},data: data.keys},
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
		            data:data.queryTimes
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
		            data:data.memory
		        }
          ]
    };
	var myChart = echarts.init(document.getElementById('business-type-time-detial'));
	myChart.clear();
	if(data.memory.length>0){
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
		   "<img src='../static/images/no-data.png' style='width:200px;height:200px;'/>",
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
	 if(data.code==0){
	 	  $(".no-data").remove();
		   //加载门店基本信息
		  loadChannelBaseInfo(data);
		  //加载门店历史受理详情
		  loadChannelHandleDetail(data.channelHandleInfo);
		  //加载营业员受理详情
		  loadStaffHandleDetail(data.expireDataInfo);
		  //加载门店台席健康度详情
		  loadChannelDeviceDetail(data.memoryAnalysis);
		  //加载耗时步骤分析
		  loadTimeStepDetail(data.slowQueryAnalysis);
		  //加载业务类型耗时详情
		  loadBusinessTypeTimeDetail(data.hotSpotInfo);
	 }
	
}
$(function(){
	//加载基础信息
	initBaseInfo();
	//加载页面数据
	loadPageData();

	setInterval(print,1000)
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