var vm = new Vue({
    el: '#memberAnalysisDiv',
    data: {
        rankAndCounts: []
    },
    methods: {
        loadMemberRankAndCounts: function() {
            var _self = this;
            $.ajax({
                type: 'GET',
                url: basePath + "/admin/member/getRankAndCounts",
                success: function(result) {
                    if (result.code == "00") {
                        _self.rankAndCounts = result.rankAndCounts;
                        loadEchart(_self.rankAndCounts);
                    } else {
                        layer.alert('加载会员等级与数量失败: ' + result.msg);
                    }
                }
            });
        }
    },
    mounted: function() {
        this.loadMemberRankAndCounts();
    }
});

function loadEchart(rankAndCounts) {
    var _data = [];
    var _legend_data = [];
    var _data_item = { name: '', value: 0 };
    for (var i = 0; i < rankAndCounts.length; i++) {
        _data_item.name = rankAndCounts[i].memberRank;
        _data_item.value = rankAndCounts[i].counts;
        _legend_data.push(rankAndCounts[i].memberRank);
        _data.push(cloneJsonObj(_data_item));
    }
    var myChart = echarts.init(document.getElementById('chart'), 'infographic');
    myChart.setOption({
        title: {
            text: '会员分析（等级-会员数）',
            left: 'center',
            top: 20,
            textStyle: {
                color: 'black'
            }
        },
        color: ['#FF9966', '#FFCCCC', '#FF6666', '#666666', '#FFFF66', '#003366'],
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            x: 'left',
            data: _legend_data
        },
        toolbox: {
            show: true,
            feature: {
                mark: { show: true },
                dataView: { show: true, readOnly: false },
                magicType: {
                    show: true,
                    type: ['pie', 'funnel'],
                    option: {
                        funnel: {
                            x: '25%',
                            width: '50%',
                            funnelAlign: 'left',
                            max: 1548
                        }
                    }
                },
                restore: { show: true },
                saveAsImage: { show: true }
            }
        },
        series: [{
            name: '等级-会员数',
            type: 'pie',
            radius: '60%',
            center: ['50%', '50%'],
            data: _data.sort(function(a, b) { return a.value - b.value; }),
            roseType: 'radius',
            label: {
                normal: {
                    textStyle: {
                        color: '#333333'
                    }
                },
            },
            labelLine: {
                normal: {
                    lineStyle: {
                        color: '#333333'
                    },
                    smooth: 0.2,
                    length: 10,
                    length2: 20
                }
            },
            animationType: 'scale',
            animationEasing: 'elasticOut',
            animationDelay: function(idx) {
                return Math.random() * 200;
            }
        }]
    });
}