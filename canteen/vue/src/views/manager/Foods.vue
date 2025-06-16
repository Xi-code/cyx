<template>
  <div>

    <div class="card" style="margin-bottom: 10px;">
      <el-input prefix-icon="Search" style="width: 300px; margin-right: 10px" placeholder="请输入餐品名称" v-model="data.name"></el-input>
      <el-input style="width: 120px; margin-right: 10px" placeholder="最低价格" v-model="data.minPrice"></el-input>
      <el-input style="width: 120px; margin-right: 10px" placeholder="最高价格" v-model="data.maxPrice"></el-input>
      <el-button type="primary" @click="load">查询</el-button>
      <el-button type="info" style="margin: 0 10px" @click="reset">重置</el-button>
    </div>

    <div class="card" style="margin-bottom: 10px">
      <div style="margin-bottom: 10px">
        <el-button type="primary" @click="handleAdd">新增</el-button>
      </div>
      <el-table :data="data.tableData">
        <el-table-column prop="id" label="序号" width="70"/>
        <el-table-column prop="name" label="名称"/>
        <el-table-column prop="descr" label="简介"/>
        <el-table-column prop="price" label="价格"/>
        <el-table-column label="图片">
          <template v-slot="scope">
            <el-image style="width: 100px; height: 100px; display: block" v-if="scope.row.img" :src="scope.row.img" :preview-src-list="[scope.row.img]" preview-teleported></el-image>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="180">
          <template #default="scope">
            <el-button type="primary" @click="handleEdit(scope.row)">编辑</el-button>
            <el-button type="danger" @click="del(scope.row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <div class="card" v-if="data.total">
      <div style="margin-bottom: 10px; color: #666; font-size: 14px">
        当前筛选条件：
        <template v-if="data.name">名称包含 “{{ data.name }}”</template>
        <template v-if="data.minPrice || data.maxPrice">
          <template v-if="data.name">，</template>
          <template v-if="data.minPrice && data.maxPrice">价格在 {{ data.minPrice }} ~ {{ data.maxPrice }} 元之间</template>
          <template v-else-if="data.minPrice">价格 ≥ {{ data.minPrice }} 元</template>
          <template v-else-if="data.maxPrice">价格 ≤ {{ data.maxPrice }} 元</template>
        </template>
        <template v-if="!data.name && !data.minPrice && !data.maxPrice">无</template>
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
        <el-form-item label="名称">
          <el-input v-model="data.form.name" autocomplete="off" />
        </el-form-item>
        <el-form-item label="简介">
          <el-input type="textarea" v-model="data.form.descr" autocomplete="off" />
        </el-form-item>
        <el-form-item label="价格">
          <el-input v-model="data.form.price" autocomplete="off" />
        </el-form-item>
        <el-form-item label="图片">
          <el-upload action="http://localhost:9090/files/upload" :on-success="handleFileUpload">
            <el-button type="primary">点击上传</el-button>
          </el-upload>
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
  name: '',
  minPrice: '',
  maxPrice: ''
})

const load = () => {
  request.get('/foods/selectPage',{
    params: {
      pageNum: data.pageNum,
      pageSize: data.pageSize,
      name:  data.name,
      minPrice: data.minPrice,
      maxPrice: data.maxPrice
    }
  }).then(res => {
    data.tableData = res.data?.list || []
    data.total = res.data.total
  })
}

load()
//重置
const reset = () => {
  data.name = ''
  data.minPrice = ''
  data.maxPrice = ''
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
    url:    data.form.id ? '/foods/update' : '/foods/add',
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
    request.delete('/foods/delete/'+id).then(res => {
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

const handleFileUpload = (file) => {
  data.form.img = file.data
}

</script>