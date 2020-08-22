<template>
  <button v-on:click="warn('不能提交表单', $event)">
    提交
  </button>
</template>
<script>
export default {
  methods: {
    warn(message, event) {
      if (event) {
        event.preventDefault();
      }
      alert(message);
    }
  }
}
</script>
