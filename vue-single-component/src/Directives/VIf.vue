<template>
  <div>
    <h1 v-if="false">永远不会被显示</h1>
    <h1 v-if="open">门已打开</h1>
    <h1 v-if="!open">门已关闭</h1>
    <button v-on:click="toggle">开关</button>
  </div>
</template>
<script>
export default {
  data() {
    return {open: true}
  },
  methods: {
    toggle() {
      this.open = !this.open;
    }
  }
}
</script>
