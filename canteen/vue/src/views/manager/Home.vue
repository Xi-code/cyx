<template>
  <div>

    <div class="card" style="line-height: 30px;margin-bottom: 10px">
      <div>欢迎您，<span style="color: #1450aa">{{ user.name }}</span> 祝您今天过得开心！</div>
    </div>

    <div class="card">
      <div style="display: flex;flex-wrap: wrap">
        <div v-for="item in data.tables" :key="item.id" style="text-align: center;margin-right: 20px;margin-bottom: 10px">
          <div><img src="@/assets/imgs/餐饮.png" alt="" style="width: 100px"></div>
          <div>{{item.no}}</div>
          <div style="font-size:14px">{{item.unit}}可用餐</div>
          <div style="margin: 10px 0">
            <span style="color: #3BB201" v-if="item.free === '是'">空闲</span>
            <span style="color: #b20130" v-else>占用</span>
          </div>
          <div v-if="item.free === '是'">
            <el-button type="primary" @click="addOrder(item)">开始点餐</el-button>
          </div>
        </div>

      </div>

    </div>

  </div>
</template>

<script setup>
import {reactive} from "vue";
  import request from "@/utils/request";
import router from "@/router";
const user = JSON.parse(localStorage.getItem('canteen-user') || '{}')

const data = reactive({
  tables: []
})

const loadTables = () =>{
  request.get('/tables/selectAll').then(res => {
    data.tables = res.data
  })
}

//订餐 修改餐桌状态
const addOrder = (item) =>{
  item.free = '否'
  item.userId = user.id
  request.put('/tables/addOrder',item).then(res =>{
    if(res.code === '200'){
      router.push('/order')
    }else{
      ElMessage.error(res.msg)
    }
  })
}

loadTables()

</script>