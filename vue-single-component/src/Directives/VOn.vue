<template>
  <div>
    <button v-on:click="counter += 1"> 加1</button>
    <p>按钮被点击了 {{ counter }} 次</p>
  </div>
</template>
<script>
export default {
  data() {
    return {counter: 0};
  }
}
</script>
