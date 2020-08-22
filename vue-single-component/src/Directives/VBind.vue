<template>
  <h2 id="message" v-bind:name="name">
    {{ message }}
  </h2>
</template>
<script>
export default {
  data() {
    return ({
      name: 'greeting',
      message: '你好！'
    });
  }
}
</script>
