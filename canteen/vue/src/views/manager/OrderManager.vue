<template>
  <div>

    <div class="card" style="margin-bottom: 10px;">
      <el-input prefix-icon="Search" style="width: 300px; margin-right: 10px" placeholder="请输入用户名称" v-model="data.userName"></el-input>
      <el-button type="primary" @click="load">查询</el-button>
      <el-button type="info" style="margin: 0 10px" @click="reset">重置</el-button>
    </div>

    <div class="card" style="margin-bottom: 10px">
      <el-table :data="data.tableData">
        <el-table-column prop="id" label="序号" width="70"/>
        <el-table-column prop="orderNo" label="订单编号"/>
        <el-table-column prop="content" label="菜单内容"/>
        <el-table-column prop="total" label="订单总价">
          <template #default="scope">
            <strong style="color: red">￥{{ scope.row.total }}</strong>
          </template>
        </el-table-column>
        <el-table-column prop="userName" label="用户名称"/>
        <el-table-column prop="status" label="订单状态">
          <template #default="scope">
            <el-tag type="primary" v-if="scope.row.status === '待出餐'">{{ scope.row.status }}</el-tag>
            <el-tag type="warning" v-if="scope.row.status === '待结算'">{{ scope.row.status }}</el-tag>
            <el-tag type="success" v-if="scope.row.status === '已完成'">{{ scope.row.status }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="time" label="下单时间"/>
        <el-table-column label="操作" width="180">
          <template #default="scope">
            <el-button v-if="data.user.role === 'ADMIN'&& scope.row.status==='待出餐'" type="primary" @click="handleEdit(scope.row)">编辑</el-button>
            <el-button v-if="data.user.role === 'ADMIN'" type="danger" @click="del(scope.row.id)">删除</el-button>
            <el-button v-if="data.user.role === 'USER' && scope.row.status ==='待结算'" type="danger" @click="done(scope.row)">结算</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <div class="card" v-if="data.total">
      <div style="margin-bottom: 10px; color: #666; font-size: 14px">
        当前筛选条件：
        <template v-if="data.userName">名称包含 “{{ data.userName }}”</template>
        <template v-if="!data.userName">无</template>
      </div>

      <el-pagination
          background
          layout="prev, pager, next"
          @current-change="load"
          :page-size="data.pageSize"
          v-model:current-page="data.pageNum"
          :total="data.total"
      />
    </div>


    <el-dialog v-model="data.formVisible" title="信息" width="40%" destory-on-close>
      <el-form :model="data.form" label-width="100px" style="padding-right: 50px">
        <el-form-item label="订单状态">
          <el-select style="width: 100%" v-model="data.form.status">
            <el-option value="待出餐"></el-option>
            <el-option value="待结算"></el-option>
          </el-select>
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
  userName: '',
  user: JSON.parse(localStorage.getItem('canteen-user') || '{}')
})

const load = () => {
  request.get('/orders/selectAll', {
    params: {
      pageNum: data.pageNum,
      pageSize: data.pageSize,
      userName: data.userName,
      userId: data.user.role === 'USER' ? data.user.id : null
    }
  }).then(res => {
    data.tableData = res.data?.list || []
    data.total = res.data.total
  })
}

load()
//重置
const reset = () => {
  data.userName = ''
  load()
}


//保存数据
const save = () => {
  request.request({
    method: data.form.id ? 'PUT' : 'POST',
    url:    data.form.id ? '/orders/update' : '/orders/add',
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

const done = (row) =>{
  let form = JSON.parse(JSON.stringify(row)) //深拷贝
  form.status = '已完成'
  request.put('/orders/update',form).then(res => {
    if(res.code === '200'){//成功
      ElMessage.success('操作成功')
      load() //刷新表格数据
    }else{
      ElMessage.error(res.msg)
    }
  })

}
//删除
const del = (id) => {
  ElMessageBox.confirm('删除后数据无法恢复 您确定删除吗？', '确认删除',{type:'warning'}).then(res => {
    request.delete('/orders/delete/'+id).then(res => {
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