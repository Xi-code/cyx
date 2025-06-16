<template>
  <div>
    <button @click="btn">点击按钮 发送请求</button>
  </div>
  {{ msg }}
</template>

<script setup lang="ts">
import axios from 'axios';
import {ref} from 'vue';

let msg = ref("")

    function btn() {
      axios.get("http://localhost:8080/getmsg")
        .then(res => {
          Console.log(res.data);
          msg.value = res.data;
        })
        .catch(error => {
          console.error("Error fetching data:", error);
        });
    }

    let obj = {
    }
</script>