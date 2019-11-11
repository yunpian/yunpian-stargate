const topological = function () {
  return Vue.extend({
    template: ''
        + '<template>'
        + '<div>'
        + '  <div style="margin-top: 20px">'
        + '    <el-form :inline="true" :model="filterForm" class="demo-form-inline">'
        + '      <el-form-item label="应用名称">'
        + '        <el-input v-model="filterForm.appName" placeholder="应用名称"></el-input>'
        + '      </el-form-item>'
        + '      <el-form-item label="Group">'
        + '        <el-input v-model="filterForm.group" placeholder="Group"></el-input>'
        + '      </el-form-item>'
        + '      <el-form-item label="Topic">'
        + '        <el-input v-model="filterForm.topic" placeholder="Topic"></el-input>'
        + '      </el-form-item>'
        + '      <el-form-item label="模糊匹配" prop="delivery">'
        + '        <el-switch v-model="filterForm.fuzzy"></el-switch>'
        + '      </el-form-item>'
        // + '      <el-form-item label="显示Topic" prop="delivery">'
        // + '        <el-switch v-model="filterForm.showTopic"></el-switch>'
        // + '      </el-form-item>'
        + '      <el-form-item>'
        + '        <el-button type="primary" @click="onSubmit">查询</el-button>'
        + '      </el-form-item>'
        + '    </el-form>'
        + '  </div>'
        + '  <div id="chart" style="width:100%; height:700px;margin-top: 0px"></div>'
        + '</div>'
        + '</template>',
    data: function () {
      return {
        filter: {
          appName: '',
          topic: '',
          group: '',
          showTopic: true,
          fuzzy: false
        },
        filterForm: {
          appName: '',
          topic: '',
          group: '',
          showTopic: true,
          fuzzy: false
        },
      };
    },
    methods: {
      onSubmit() {
        this.filter = {
          appName: this.filterForm.appName,
          topic: this.filterForm.topic,
          fuzzy: this.filterForm.fuzzy,
          showTopic: this.filterForm.showTopic,
          group: this.filterForm.group,
        };
        this.drawLine(this.filter);
      },
      drawLine(filter) {
        this.$http.get(
            '/topological/list?topic=' + filter.topic + '&appName='
            + filter.appName + '&fuzzy=' + filter.fuzzy + '&group='
            + filter.group + '&showTopic=' + filter.showTopic).then(
            function (res) {

              let parse = res.body;
              if (parse.code != 0) {
                this.$notify({
                  title: '请求失败处理',
                  message: parse.msg,
                });
                return;
              }
              let nodes = parse.data.nodes;
              let links = parse.data.links;
              let myChart = echarts.init(document.getElementById('chart'))
              let categories = [{name: "应用"}, {name: "Topic"}];
              let option = {
                title: {
                  text: '',
                  subtext: '',
                  top: 'top',
                  left: 'left'
                },
                tooltip: {},
                legend: [{
                  data: categories
                }],
                animationDuration: 1500,
                animationEasingUpdate: 'quinticInOut',
                series: [
                  {
                    name: 'Les Miserables',
                    type: 'graph',
                    layout: 'force',
                    force: {
                      repulsion: 100,
                      gravity: 0.1,
                      edgeLength: [50, 300],
                    },
                    data: nodes,
                    links: links,
                    categories: categories,
                    roam: true,
                    focusNodeAdjacency: true,
                    itemStyle: {
                      normal: {
                        borderColor: '#fff',
                        borderWidth: 1,
                        shadowBlur: 10,
                        shadowColor: 'rgba(0, 0, 0, 0.3)'
                      }
                    },
                    label: {
                      position: 'right',
                      formatter: '{b}'
                    },
                    lineStyle: {
                      color: 'source',
                      curveness: 0.3
                    },
                    emphasis: {
                      lineStyle: {
                        width: 10,
                      }
                    }
                  }
                ]
              };

              myChart.setOption(option);
            }, function () {
              this.$notify({
                title: '请求失败处理',
                message: "请求失败处理",
              });
            });
      },
    },
    mounted: function () {
      this.drawLine(this.filter);
    }
  })
};
