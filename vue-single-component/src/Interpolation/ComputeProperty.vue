<template>
  <div>
    <p>原字符串："{{ message }}"</p>
    <p>翻转后字符串："{{ reversedMessage }}"</p>
  </div>
</template>
<script>
export default {
  data() {
    return {message: 'Hello'}
  },
  computed: {
    reversedMessage() {
      // 注意 message 前面要加 this
      // 只有在 this.message 发生变化时才会重新计算
      return this.message.split('').reverse().join('');
    }
  }
}
</script>
