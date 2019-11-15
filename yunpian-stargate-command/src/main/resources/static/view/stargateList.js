const stargateList = function () {
  return Vue.extend({
    template: ''
        + '<template>'
        + '<div>'
        + '  <el-collapse v-model="collapseIndex">'
        + '  <el-collapse-item title="操作面板" name="1">'
        + '  <div style="margin-top: 20px">'
        + '    <el-form :inline="true" :model="filterForm" class="demo-form-inline">'
        + '      <el-form-item label="应用名">'
        + '        <el-select v-model="filterForm.appName" multiple clearable filterable placeholder="应用名称">'
        + '          <el-option'
        + '            v-for="item in appNames"'
        + '            :key="item"'
        + '            :label="item"'
        + '            :value="item">'
        + '          </el-option>'
        + '        </el-select>'
        + '      </el-form-item>'
        + '      <br />'
        + '      <el-form-item label="Group">'
        + '        <el-select v-model="filterForm.group" multiple clearable filterable Group="应用名称">'
        + '          <el-option'
        + '            v-for="item in groups"'
        + '            :key="item"'
        + '            :label="item"'
        + '            :value="item">'
        + '          </el-option>'
        + '        </el-select>'
        + '      </el-form-item>'
        + '      <br />'
        + '      <el-form-item label="Topic">'
        + '        <el-select v-model="filterForm.topic" multiple clearable filterable Group="Topic">'
        + '          <el-option'
        + '            v-for="item in topics"'
        + '            :key="item"'
        + '            :label="item"'
        + '            :value="item">'
        + '          </el-option>'
        + '        </el-select>'
        + '      </el-form-item>'
        + '      <br />'
        + '      <el-form-item label="类型">'
        + '        <el-select v-model="filterForm.type" placeholder="生产者/消费者">'
        + '          <el-option label="全  部" value=""></el-option>'
        + '          <el-option label="消费者" value="0"></el-option>'
        + '          <el-option label="生产者" value="1"></el-option>'
        + '        </el-select>'
        + '      </el-form-item>'
        + '      <br />'
        + '      <el-form-item>'
        + '        <el-button type="primary" @click="onSubmit">查询</el-button>'
        + '      </el-form-item>'
        + '      <el-form-item>'
        + '        <el-button type="warning" @click="onRefresh">刷新客户端</el-button>'
        + '      </el-form-item>'
        + '    </el-form>'
        + '  </div>'
        + '  <div style="margin-bottom: 20px">'
        + '    <el-button type="primary" @click="handleResumeList">全部恢复</el-button>'
        + '    <el-button type="danger" @click="handleSuspendList">全部暂停</el-button>'
        + '    <el-button type="primary" @click="handleStartList">全部启动</el-button>'
        + '    <el-button type="danger" @click="handleStopList">全部停止</el-button>'
        + '  </div>'
        + '  </el-collapse-item>'
        + '  </el-collapse>'
        + '  <el-table'
        + '    :data="tableData"'
        + '    border'
        + '    style="width: 100%">'
        + '    <el-table-column'
        + '      width="180"'
        + '      prop="id"'
        + '      label="客户端ID">'
        + '    </el-table-column>'
        + '    <el-table-column'
        + '      width="380"'
        + '      label="客户端信息">'
        + '      <template slot-scope="scope">'
        + '        <p>应用组名称: <span>{{ scope.row.appName }}</span></p>'
        + '        <p>客户端ID: <span>{{ scope.row.idCard }}</span></p>'
        + '        <p>客户端IP: '
        + '          <span>{{ scope.row.clientIP }}</span>'
        + '        </p>'
        + '        <p>客户端类型:'
        + '          <span v-if="scope.row.type==0" >消费者</span>'
        + '          <span v-if="scope.row.type==1" >生产者</span>'
        + '        </p>'
        + '        <p>instanceName: '
        + '          <span>{{ scope.row.instanceName }}</span>'
        + '        </p>'
        + '      </template>'
        + '    </el-table-column>'
        + '    <el-table-column'
        + '      width="370"'
        + '      label="生产消费信息">'
        + '      <template slot-scope="scope">'
        + '        <p>group: <span>{{ scope.row.group }}</span></p>'
        + '        <p>topic: <span>{{ scope.row.topic }}</span></p>'
        + '        <p>tag: <span>{{ scope.row.tag }}</span></p>'
        + '      </template>'
        + '    </el-table-column>'
        + '    <el-table-column'
        + '      width="170"'
        + '      prop="refreshTime"'
        + '      label="数据刷新时间">'
        + '    </el-table-column>'
        + '    <el-table-column'
        + '      width="200"'
        + '      label="客户端状态">'
        + '      <template slot-scope="scope">'
        + '        <p>运行状态: <span>{{ scope.row.serviceState }}</span></p>'
        + '        <p v-if="scope.row.type==0">消费暂停: <span>{{ scope.row.pause }}</span></p>'
        + '        <p v-if="scope.row.type==0">最小线程池: <span>{{ scope.row.consumeThreadMin }}</span></p>'
        + '        <p v-if="scope.row.type==0">最大线程池: <span>{{ scope.row.consumeThreadMax }}</span></p>'
        + '      </template>'
        + '    </el-table-column>'
        + '    <el-table-column'
        + '      fixed="right"'
        + '      width="220"'
        + '      label="操作">'
        + '      <template slot-scope="scope">'
        + '        <el-button'
        + '          v-if="scope.row.type==0"'
        + '          size="mini"'
        + '          type="primary"'
        + '          @click="handleResume(scope.$index, scope.row)">恢复</el-button>'
        + '        <el-button'
        + '          v-if="scope.row.type==0"'
        + '          size="mini"'
        + '          type="danger"'
        + '          @click="handleSuspend(scope.$index, scope.row)">暂停</el-button>'
        // + '        <el-button'
        // + '          size="mini"'
        // + '          type="info"'
        // + '          @click="showDetail(scope.$index, scope.row)">详情</el-button>'
        + '        <br />'
        + '        <br />'
        + '        <el-button'
        + '          v-if="scope.row.type==0"'
        + '          size="mini"'
        + '          type="primary"'
        + '          @click="handleStart(scope.$index, scope.row)">启动</el-button>'
        + '        <el-button'
        + '          v-if="scope.row.type==0"'
        + '          size="mini"'
        + '          type="danger"'
        + '          @click="handleStop(scope.$index, scope.row)">停止</el-button>'
        + '        <br />'
        + '        <br />'
        + '        <el-button'
        + '          v-if="scope.row.type==0"'
        + '          size="mini"'
        + '          type="warning"'
        + '          @click="setThreadSize(scope.$index, scope.row)">设置线程池</el-button>'
        + '        '
        + '      </template>'
        + '    </el-table-column>'
        + '  </el-table>'
        + '</div>'
        + '</template>',
    data: function () {
      return {
        tableData: [],
        groups: [],
        topics: [],
        appNames: [],
        collapseIndex: [],
        filter: {
          appName: '',
          topic: '',
          type: '',
          group: '',
        },
        filterForm: {
          appName: '',
          topic: '',
          type: '',
          group: '',
        },
      }
    },
    methods: {
      setThreadSize(index, row) {
        let id = row.id;
        this.$prompt('请输入线程池大小', '设置线程池', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          inputPattern: /^\+?[1-9]\d{0,2}$/,
          inputErrorMessage: '请输入数字1到999'
        }).then(({value}) => {
          this.$http.post("/consumeManage/setThreadSize/" + id, value).then(
              function (res) {
                let parse = res.body;
                if (parse.code != 0) {
                  this.$notify({
                    title: '请求失败处理',
                    message: parse.msg,
                  });
                  return;
                }
                this.$notify({
                  title: '命令下发成功',
                  message: '修改线程池大小',
                });
              }, function () {
                this.$notify({
                  title: '请求失败处理',
                  message: "请求失败处理",
                });
              });
        });
      },
      onRefresh: function () {
        this.$http.post("/consumeManage/refresh").then(
            function (res) {
              let parse = res.body;
              if (parse.code != 0) {
                this.$notify({
                  title: '请求失败处理',
                  message: parse.msg,
                });
                return;
              }
              this.$notify({
                title: '命令下发成功',
                message: '刷新客户端数据',
              });
            }, function () {
              this.$notify({
                title: '请求失败处理',
                message: "请求失败处理",
              });
            });
      },
      handleResumeList: function () {
        let ids = [];
        for (let i = 0; i < this.tableData.length; ++i) {
          ids[i] = this.tableData[i].id;
        }
        this.handleResumePost(ids);
      },
      handleSuspendList: function () {
        let ids = [];
        for (let i = 0; i < this.tableData.length; ++i) {
          ids[i] = this.tableData[i].id;
        }
        this.handleSuspendPost(ids);
      },
      handleStartList: function () {
        let ids = [];
        for (let i = 0; i < this.tableData.length; ++i) {
          ids[i] = this.tableData[i].id;
        }
        this.handleStartPost(ids);
      },
      handleStopList: function () {
        let ids = [];
        for (let i = 0; i < this.tableData.length; ++i) {
          ids[i] = this.tableData[i].id;
        }
        this.handleStopPost(ids);
      },
      handleResume: function (index, row) {
        this.handleResumePost([row.id]);
      },
      handleSuspend: function (index, row) {
        this.handleSuspendPost([row.id]);
      },
      handleStart: function (index, row) {
        this.handleStartPost([row.id]);
      },
      handleStop: function (index, row) {
        this.handleStopPost([row.id]);
      },
      handleResumePost: function (idList) {
        this.$http.post("/consumeManage/resume/list", idList).then(
            function (res) {

              let parse = res.body;
              if (parse.code != 0) {
                this.$notify({
                  title: '请求失败处理',
                  message: parse.msg,
                });
                return;
              }
              this.$notify({
                title: '命令下发成功',
                message: '恢复客户端消费',
              });
            }, function () {
              this.$notify({
                title: '请求失败处理',
                message: "请求失败处理",
              });
            });
      },
      handleSuspendPost: function (idList) {
        this.$http.post("/consumeManage/suspend/list", idList).then(
            function (res) {

              let parse = res.body;
              if (parse.code != 0) {
                this.$notify({
                  title: '请求失败处理',
                  message: parse.msg,
                });
                return;
              }
              this.$notify({
                title: '命令下发成功',
                message: '暂停客户端消费',
              });
            }, function () {
              this.$notify({
                title: '请求失败处理',
                message: "请求失败处理",
              });
            });
      },
      handleStartPost: function (idList) {
        this.$http.post("/consumeManage/start/list", idList).then(
            function (res) {

              let parse = res.body;
              if (parse.code != 0) {
                this.$notify({
                  title: '请求失败处理',
                  message: parse.msg,
                });
                return;
              }
              this.$notify({
                title: '命令下发成功',
                message: '启动客户端',
              });
            }, function () {
              this.$notify({
                title: '请求失败处理',
                message: "请求失败处理",
              });
            });
      },
      handleStopPost: function (idList) {
        this.$http.post("/consumeManage/stop/list", idList).then(
            function (res) {

              let parse = res.body;
              if (parse.code != 0) {
                this.$notify({
                  title: '请求失败处理',
                  message: parse.msg,
                });
                return;
              }
              this.$notify({
                title: '命令下发成功',
                message: '停止客户端',
              });
            }, function () {
              this.$notify({
                title: '请求失败处理',
                message: "请求失败处理",
              });
            });
      },
      showDetail: function (index, row) {
        this.$alert('功能开发中', '系统提示', {
          confirmButtonText: '确定',
          callback: action => {
            this.$message({
              type: 'info',
              message: `action: ${ action }`
            });
          }
        });
      },
      setAlerts: function (index, row) {
        this.$alert('功能开发中', '系统提示', {
          confirmButtonText: '确定',
          callback: action => {
            this.$message({
              type: 'info',
              message: `action: ${ action }`
            });
          }
        });
      },
      timer() {
        return setTimeout(() => {
          this.getData(this.filter);
        }, 5000)
      },
      onSubmit() {
        this.filter = {
          appName: this.filterForm.appName,
          topic: this.filterForm.topic,
          type: this.filterForm.type,
          group: this.filterForm.group,
        };
        this.getData(this.filter);
      },
      getData(filter) {
        this.$http.get(
            '/mqClientData/list?topic=' + filter.topic + '&appName='
            + filter.appName + '&type=' + filter.type + '&group='
            + filter.group).then(function (res) {

          let parse = res.body;
          if (parse.code != 0) {
            this.$notify({
              title: '请求失败处理',
              message: parse.msg,
            });
            return;
          }
          this.tableData = parse.data;
        }, function () {
          this.$notify({
            title: '请求失败处理',
            message: "请求失败处理",
          });
        });
        this.$http.get(
            '/mqClientData/appNames').then(function (res) {
          let parse = res.body;
          if (parse.code != 0) {
            this.$notify({
              title: '请求失败处理',
              message: parse.msg,
            });
            return;
          }
          this.appNames = parse.data;
        }, function () {
          this.$notify({
            title: '请求失败处理',
            message: "请求失败处理",
          });
        });
        this.$http.get(
            '/mqClientData/groups').then(function (res) {
          let parse = res.body;
          if (parse.code != 0) {
            this.$notify({
              title: '请求失败处理',
              message: parse.msg,
            });
            return;
          }
          this.groups = parse.data;
        }, function () {
          this.$notify({
            title: '请求失败处理',
            message: "请求失败处理",
          });
        });
        this.$http.get(
            '/mqClientData/topics').then(function (res) {
          let parse = res.body;
          if (parse.code != 0) {
            this.$notify({
              title: '请求失败处理',
              message: parse.msg,
            });
            return;
          }
          this.topics = parse.data;
        }, function () {
          this.$notify({
            title: '请求失败处理',
            message: "请求失败处理",
          });
        });
      },
    },
    created: function () {
      this.getData(this.filter);
    },
    watch: {
      tableData() {
        this.timer()
      }
    },
    destroyed() {
      clearTimeout(this.timer)
    }
  })
};
