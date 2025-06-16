<template>
  <div class="card">
    <div ref="chartRef" style="width: 100%; height: 400px;"></div>
  </div>
</template>

<script setup>
import * as echarts from 'echarts'
import { onMounted, ref } from 'vue'
import request from "@/utils/request";

const chartRef = ref(null)
let chartInstance

const initChart = (chartData) => {
  chartInstance = echarts.init(chartRef.value)
  const option = {
    title: {
      text: '最近7天订单统计',
      left: 'center'
    },
    tooltip: {
      trigger: 'axis'
    },
    xAxis: {
      type: 'category',
      data: chartData.map(item => item.date)
    },
    yAxis: {
      type: 'value'
    },
    series: [
      {
        name: '订单数',
        type: 'line',
        data: chartData.map(item => item.count)
      }
    ]
  }
  chartInstance.setOption(option)
}

onMounted(() => {
  request.get('/orders/statistics').then(res => {
    if (res.code === '200') {
      initChart(res.data)
    }
  })
})
</script>
