<template>
  <div>

    <div class="card" style="margin-bottom: 10px;">
      <el-input prefix-icon="Search" style="width: 300px; margin-right: 10px" placeholder="请输入餐桌号查询" v-model="data.no"></el-input>
      <el-button type="primary" @click="load">查询</el-button>
      <el-button type="info" style="margin: 0 10px" @click="reset">重置</el-button>
    </div>

    <div class="card" style="margin-bottom: 10px">
      <div style="margin-bottom: 10px">
        <el-button type="primary" @click="handleAdd">新增</el-button>
      </div>
      <el-table :data="data.tableData" >
        <el-table-column prop="id" label="序号" width="70"/>
        <el-table-column prop="no" label="餐桌号" width="70"/>
        <el-table-column prop="unit" label="规格"/>
        <el-table-column prop="free" label="是否空闲"/>
        <el-table-column prop="userName" label="占用顾客"/>
        <el-table-column label="操作" width="180">
          <template #default="scope">
            <el-button type="primary" @click="handleEdit(scope.row)">编辑</el-button>
            <el-button type="danger" @click="del(scope.row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <div class="card" v-if="data.total">
      <el-pagination background layout="prev, pager, next" @current-change="load" :page-size="data.pageSize" v-model:current-page="data.pageNum" :total="data.total"/>
    </div>

    <el-dialog v-model="data.formVisible" title="信息" width="40%" destory-on-close>
      <el-form :model="data.form" label-width="100px" style="padding-right:50px">
        
        <el-form-item label="餐桌号">
          <el-input v-model="data.form.no" autocomplete="off"  />
        </el-form-item>
        <el-form-item label="规格">
          <el-input v-model="data.form.unit" autocomplete="off" />
        </el-form-item>
        <el-form-item label="是否空闲">
          <el-radio-group v-model="data.form.free">
            <el-radio label="是"></el-radio>
            <el-radio label="否"></el-radio>
          </el-radio-group>
        </el-form-item>
        
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="data.formVisible = false">取消</el-button>
          <el-button type="primary" @click="save">保存</el-button>
        </div>
      </template>
    </el-dialog>

  </div>
</template>

<script setup>
import {reactive} from "vue"
import request from "@/utils/request";
import {ElMessage, ElMessageBox} from "element-plus";

const data = reactive({
  tableData: [],
  total: 0,
  pageNum: 1,  // 当前的页码
  pageSize: 5,  // 每页的个数
  formVisible: false,
  form: {},
  no: ''
})

const load = () => {
  request.get('/tables/selectPage',{
    params: {
      pageNum: data.pageNum,
      pageSize: data.pageSize,
      no:  data.no
    }
  }).then(res => {
    data.tableData = res.data?.list || []
    data.total = res.data.total
  })
}

load()
//重置
const reset = () => {
  data.no = ''
  load()
}
const handleAdd = () => {
  data.form = {} //初始化表单
  data.formVisible = true //打开弹窗
}

//保存数据
const save = () => {
  request.request({
    method: data.form.id ? 'PUT' : 'POST',
    url:    data.form.id ? '/tables/update' : '/tables/add',
    data:   data.form
  }).then(res => {
    if(res.code === '200'){//成功
      ElMessage.success('保存成功')
      data.formVisible = false //关闭弹窗
      load() //刷新表格数据
    }else{
      ElMessage.error(res.msg)
    }
  })
}

//编辑
const handleEdit = (row) => {
  data.form
  =JSON.parse(JSON.stringify(row)) //深拷贝

  data.formVisible = true
}
//删除
const del = (id) => {
  ElMessageBox.confirm('删除后数据无法恢复 您确定删除吗？', '确认删除',{type:'warning'}).then(res => {
    request.delete('/tables/delete/'+id).then(res => {
      if(res.code === '200'){//成功
        ElMessage.success('删除成功')
        data.formVisible = false //关闭弹窗
        load() //刷新表格数据
      }else{
        ElMessage.error(res.msg)
      }
    })
  }).catch(err =>{
    console.log(err)
  })
}


</script>