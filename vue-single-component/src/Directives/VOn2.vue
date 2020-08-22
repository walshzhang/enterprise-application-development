<template>
  <!-- greet 是在下面定义的方法名 -->
  <button v-on:click="greet">Greet</button>
</template>
<script>
export default {
  data: function () {
    return { name: 'Alice'};
  },
  methods: {
    greet: function(event) {
      alert(`Hello ${this.name}`);
      // event 是原生 DOM 事件
      if (event) {
        // 显示事件关联的 DOM 元素的标签名
        alert(event.target.tagName);
      }
    }
  }
}
</script>
