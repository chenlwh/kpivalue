/**
 * 注册用户数
 */
var registedNumOption = {
		title:{
			text:"注册用户数",
			x:"center"
		},
	    tooltip: {
	        trigger: 'axis',
	        position: function (pt) {
	            return [pt[0], '10%'];
	        }
	    },
//	    tooltip : {
//	        trigger: 'axis',
//	        axisPointer: {
//	            type: 'cross',
//	            animation: false,
//	            label: {
//	                backgroundColor: '#505765'
//	            }
//	        }
//	    },
	    toolbox: {
	        feature: {
	            dataZoom: {
	                yAxisIndex: 'none'
	            },
	            restore: {},
	            saveAsImage: {}
	        }
	    },
	    grid:{
	    	left:80,
	    	right:80,
	    	bottom:80
	    },
		xAxis:{
            boundaryGap : false,
            axisLine: {onZero: false},
			data:[]
		},
	    yAxis: {
	        type: 'value',
	        boundaryGap: [0, '100%']
	    },
	    dataZoom: [{
	        type: 'inside',
	        start: 70,
	        end: 100
	    }, {
	        start: 70,
	        end: 100,
	    }],
		series:[{	
			data:[],
			name:"用户数",
			type:"line",
			smooth:true,
			itemStyle: {
                normal: {
                    color: 'rgb(255, 70, 131)'
                }
            },
            label:{
            	normal:{
            		show:true,
            	}
            },
            areaStyle: {
                normal: {
                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                        offset: 0,
                        color: 'rgb(255, 158, 68)'
                    }, {
                        offset: 1,
                        color: 'rgb(255, 70, 131)'
                    }])
                }
            }
		}]	
};

var registedNumChart = echarts.init($("#registedNumBar")[0]);

/**
 * 在线用户数
 */
var onlineNumOption = {
		title:{
			text:"在线用户数",
				x:"center"
		},
	    tooltip: {
	        trigger: 'axis',
	        position: function (pt) {
	            return [pt[0], '10%'];
	        }
	    },
	    toolbox: {
	        feature: {
	            dataZoom: {
	                yAxisIndex: 'none'
	            },
	            restore: {},
	            saveAsImage: {}
	        }
	    },
	    grid:{
	    	left:80,
	    	right:80,
	    	bottom:80
	    },
		xAxis:{
            boundaryGap : false,
            axisLine: {onZero: false},
			data:[]
		},
	    yAxis: {
	        type: 'value',
	        boundaryGap: [0, '100%']
	    },
	    dataZoom: [{
	        type: 'inside',
	        start: 70,
	        end: 100
	    }, {
	        start: 70,
	        end: 100,
	    }],
		series:[{	
			data:[],
			name:"用户数",
			type:"line",
			smooth:true,
			itemStyle: {
                normal: {
                    color: 'rgb(255, 70, 131)'
                }
            },
            label:{
            	normal:{
            		show:true,
            	}
            },
            areaStyle: {
                normal: {
                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                        offset: 0,
                        color: 'rgb(255, 158, 68)'
                    }, {
                        offset: 1,
                        color: 'rgb(255, 70, 131)'
                    }])
                }
            }
		}]	
};
var onlineNumChart = echarts.init($("#onlineNumBar")[0]);

/**
 * 日登陆人数
 */
var dailyLoginNumOption = {
		title:{
			text:"日登陆人数",
			x:"center"
		},
	    tooltip: {
	        trigger: 'axis',
	        position: function (pt) {
	            return [pt[0], '10%'];
	        }
	    },
	    toolbox: {
	        feature: {
	            dataZoom: {
	                yAxisIndex: 'none'
	            },
	            restore: {},
	            saveAsImage: {}
	        }
	    },
	    grid:{
	    	left:80,
	    	right:80,
	    	bottom:80
	    },
		xAxis:{
            boundaryGap : false,
            axisLine: {onZero: false},
			data:[]
		},
	    yAxis: {
	        type: 'value',
	        boundaryGap: [0, '100%']
	    },
	    dataZoom: [{
	        type: 'inside',
	        start: 70,
	        end: 100
	    }, {
	        start: 70,
	        end: 100,
	    }],
		series:[{	
			data:[],
			name:"用户数",
			type:"line",
			smooth:true,
			itemStyle: {
                normal: {
                    color: 'rgb(255, 70, 131)'
                }
            },
            label:{
            	normal:{
            		show:true,
            	}
            },
            areaStyle: {
                normal: {
                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                        offset: 0,
                        color: 'rgb(255, 158, 68)'
                    }, {
                        offset: 1,
                        color: 'rgb(255, 70, 131)'
                    }])
                }
            }
		}]	
};

var dailyLoginNumChart = echarts.init($("#dailyLoginNumBar")[0]);

/**
 * 累计访问人数
 */
var totalLoginNumOption = {
		title:{
			text:"累计访问人数",
			x:"center"
		},
	    tooltip: {
	        trigger: 'axis',
	        position: function (pt) {
	            return [pt[0], '10%'];
	        }
	    },
	    toolbox: {
	        feature: {
	            dataZoom: {
	                yAxisIndex: 'none'
	            },
	            restore: {},
	            saveAsImage: {}
	        }
	    },
	    grid:{
	    	left:80,
	    	right:80,
	    	bottom:80
	    },
		xAxis:{
            boundaryGap : false,
            axisLine: {onZero: false},
			data:[]
		},
	    yAxis: {
	        type: 'value',
	        boundaryGap: [0, '100%']
	    },
	    dataZoom: [{
	        type: 'inside',
	        start: 70,
	        end: 100
	    }, {
	        start: 70,
	        end: 100,
	    }],
		series:[{	
			data:[],
			name:"用户数",
			type:"line",
			smooth:true,
			itemStyle: {
                normal: {
                    color: 'rgb(255, 70, 131)'
                }
            },
            label:{
            	normal:{
            		show:true,
            	}
            },
            areaStyle: {
                normal: {
                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                        offset: 0,
                        color: 'rgb(255, 158, 68)'
                    }, {
                        offset: 1,
                        color: 'rgb(255, 70, 131)'
                    }])
                }
            }
		}]	
};
var totalLoginNumChart = echarts.init($("#totalLoginNumBar")[0]);

/**
 * 页面会话连接数
 */
var sessionNumOption = {
		title:{
			text:"页面会话连接数",
			x:"center"
		},
	    tooltip: {
	        trigger: 'axis',
	        position: function (pt) {
	            return [pt[0], '10%'];
	        }
	    },
	    toolbox: {
	        feature: {
	            dataZoom: {
	                yAxisIndex: 'none'
	            },
	            restore: {},
	            saveAsImage: {}
	        }
	    },
	    grid:{
	    	left:80,
	    	right:80,
	    	bottom:80
	    },
		xAxis:{
            boundaryGap : false,
            axisLine: {onZero: false},
			data:[]
		},
	    yAxis: {
	        type: 'value',
	        boundaryGap: [0, '100%']
	    },
	    dataZoom: [{
	        type: 'inside',
	        start: 70,
	        end: 100
	    }, {
	        start: 70,
	        end: 100,
	    }],
		series:[{	
			data:[],
			name:"会话连接数",
			type:"line",
			smooth:true,
			itemStyle: {
                normal: {
                    color: 'rgb(255, 70, 131)'
                }
            },
            label:{
            	normal:{
            		show:true,
            	}
            },
            areaStyle: {
                normal: {
                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                        offset: 0,
                        color: 'rgb(255, 158, 68)'
                    }, {
                        offset: 1,
                        color: 'rgb(255, 70, 131)'
                    }])
                }
            }
		}]	
};
var sessionNumChart = echarts.init($("#sessionNumBar")[0]);

/**
 * 系统服务响应时长
 */
var serverResTimeOption = {
		title:{
			text:"系统服务响应时长",
			x:"center"
		},
	    tooltip: {
	        trigger: 'axis',
	        position: function (pt) {
	            return [pt[0], '10%'];
	        }
	    },
	    toolbox: {
	        feature: {
	            dataZoom: {
	                yAxisIndex: 'none'
	            },
	            restore: {},
	            saveAsImage: {}
	        }
	    },
	    grid:{
	    	left:80,
	    	right:80,
	    	bottom:80
	    },
		xAxis:{
            boundaryGap : false,
            axisLine: {onZero: false},
			data:[]
		},
	    yAxis: {
	        type: 'value',
	        boundaryGap: [0, '100%']
	    },
	    dataZoom: [{
	        type: 'inside',
	        start: 70,
	        end: 100
	    }, {
	        start: 70,
	        end: 100,
	    }],
		series:[{	
			data:[],
			name:"响应时长",
			type:"line",
			smooth:true,
			itemStyle: {
                normal: {
                    color: 'rgb(255, 70, 131)'
                }
            },
            label:{
            	normal:{
            		show:true,
            	}
            },
            areaStyle: {
                normal: {
                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                        offset: 0,
                        color: 'rgb(255, 158, 68)'
                    }, {
                        offset: 1,
                        color: 'rgb(255, 70, 131)'
                    }])
                }
            }
		}]	
};
var serverResTimeChart = echarts.init($("#serverResTimeBar")[0]);

/**
 * 系统健康运行时长
 */
var runningTimeOption = {
		title:{
			text:"系统健康运行时长",
				x:"center"
		},
	    tooltip: {
	        trigger: 'axis',
	        position: function (pt) {
	            return [pt[0], '10%'];
	        }
	    },
	    toolbox: {
	        feature: {
	            dataZoom: {
	                yAxisIndex: 'none'
	            },
	            restore: {},
	            saveAsImage: {}
	        }
	    },
	    grid:{
	    	left:80,
	    	right:80,
	    	bottom:80
	    },
		xAxis:{
            boundaryGap : false,
            axisLine: {onZero: false},
			data:[]
		},
	    yAxis: {
	        type: 'value',
	        boundaryGap: [0, '100%']
	    },
	    dataZoom: [{
	        type: 'inside',
	        start: 70,
	        end: 100
	    }, {
	        start: 70,
	        end: 100,
	    }],
		series:[{	
			data:[],
			name:"响应时长",
			type:"line",
			smooth:true,
			itemStyle: {
                normal: {
                    color: 'rgb(255, 70, 131)'
                }
            },
            label:{
            	normal:{
            		show:true,
            	}
            },
            areaStyle: {
                normal: {
                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                        offset: 0,
                        color: 'rgb(255, 158, 68)'
                    }, {
                        offset: 1,
                        color: 'rgb(255, 70, 131)'
                    }])
                }
            }
		}]	
};

var runningTimeChart = echarts.init($("#runningTimeBar")[0]);

/**
 * 业务应用系统占用表空间大小
 */
var tableSpaceSizeOption = {
		title:{
			text:"业务应用系统占用表空间大小",
			x:"center"
		},
	    tooltip: {
	        trigger: 'axis',
	        position: function (pt) {
	            return [pt[0], '10%'];
	        }
	    },
	    toolbox: {
	        feature: {
	            dataZoom: {
	                yAxisIndex: 'none'
	            },
	            restore: {},
	            saveAsImage: {}
	        }
	    },
	    grid:{
	    	left:80,
	    	right:80,
	    	bottom:80
	    },
		xAxis:{
            boundaryGap : false,
            axisLine: {onZero: false},
			data:[]
		},
	    yAxis: {
	        type: 'value',
	        boundaryGap: [0, '100%']
	    },
	    dataZoom: [{
	        type: 'inside',
	        start: 70,
	        end: 100
	    }, {
	        start: 70,
	        end: 100,
	    }],
		series:[{	
			data:[],
			name:"占用表空间大小",
			type:"line",
			smooth:true,
			itemStyle: {
                normal: {
                    color: 'rgb(255, 70, 131)'
                }
            },
            label:{
            	normal:{
            		show:true,
            	}
            },
            areaStyle: {
                normal: {
                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                        offset: 0,
                        color: 'rgb(255, 158, 68)'
                    }, {
                        offset: 1,
                        color: 'rgb(255, 70, 131)'
                    }])
                }
            }
		}]	
};

var tableSpaceSizeChart = echarts.init($("#tableSpaceSizeBar")[0]);

/**
 * 数据库平均响应时长
 */
var dbResTimeOption = {
		title:{
			text:"数据库平均响应时长",
			x:"center"
		},
	    tooltip: {
	        trigger: 'axis',
	        position: function (pt) {
	            return [pt[0], '10%'];
	        }
	    },
	    toolbox: {
	        feature: {
	            dataZoom: {
	                yAxisIndex: 'none'
	            },
	            restore: {},
	            saveAsImage: {}
	        }
	    },
	    grid:{
	    	left:80,
	    	right:80,
	    	bottom:80
	    },
		xAxis:{
            boundaryGap : false,
            axisLine: {onZero: false},
			data:[]
		},
	    yAxis: {
	        type: 'value',
	        boundaryGap: [0, '100%']
	    },
	    dataZoom: [{
	        type: 'inside',
	        start: 70,
	        end: 100
	    }, {
	        start: 70,
	        end: 100,
	    }],
		series:[{	
			data:[],
			name:"响应时长",
			type:"line",
			smooth:true,
			itemStyle: {
                normal: {
                    color: 'rgb(255, 70, 131)'
                }
            },
            label:{
            	normal:{
            		show:true,
            	}
            },
            areaStyle: {
                normal: {
                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                        offset: 0,
                        color: 'rgb(255, 158, 68)'
                    }, {
                        offset: 1,
                        color: 'rgb(255, 70, 131)'
                    }])
                }
            }
		}]	
};
var dbResTimeChart = echarts.init($("#dbResTimeBar")[0]);
