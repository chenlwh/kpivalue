/**
 * app.title = '堆叠柱状图';
 */



var monthlyServerNumOption = {
	title: {
		text:"Portal服务日均分析" ,
		x:"center"
	},
    tooltip: {},
    toolbox: {
        feature: {
            magicType: {
                type: ['stack', 'tiled']
            },
        }
    },
    legend: {
    	right:"80",
    	top:"5",
        data:['在线','断开']
    },
    grid: {
        left: '100',
        right: '100',
    },
    xAxis: [{
    		type: 'category',
            data: []
    	}
    ],
    yAxis: {
        type: 'value',
        boundaryGap: [0, '100%']
    },
    dataZoom: [{
        type: 'inside',
        start: 60,
        end: 100
    }, {
        start: 60,
        end: 100,
    }],
    series: [{
            name:'断开',
            type:'bar',
            stack: 'status',
            data:[],
            barMaxWidth:60,
            itemStyle: {
            	normal: {
            		color: "rgba(237,31,18,0.84)"
            	}
            }
        },
        {
            name:'在线',
            type:'bar',
            stack: 'status',
            data:[],
            barMaxWidth:60,
            itemStyle: {
            	normal: {
            		color: "rgba(48,122,36,0.86)"
            	}
            }
            
        }
    ]
};

var monthlyServerChart = echarts.init($("#monthlyServerChart")[0]);


